package com.groom.product.application.service

import com.groom.product.application.dto.GenerateProductDescriptionCommand
import com.groom.product.application.dto.RegisterProductCommand
import com.groom.product.application.dto.RegisterProductResult
import com.groom.product.domain.event.DomainEventPublisher
import com.groom.product.common.exception.StoreException
import com.groom.product.domain.event.ProductRegisteredEvent
import com.groom.product.domain.model.Price
import com.groom.product.domain.model.ProductDescription
import com.groom.product.domain.model.ProductName
import com.groom.product.domain.model.StockQuantity
import com.groom.product.domain.model.ThumbnailUrl
import com.groom.product.domain.port.LoadStorePort
import com.groom.product.domain.port.SaveProductPort
import com.groom.product.domain.service.ProductFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

/**
 * 상품 등록 서비스.
 *
 * 판매자가 새로운 상품을 등록합니다.
 * - 스토어 존재 여부 확인
 * - AI 설명 생성 (useAiDescription = true인 경우)
 * - Product 엔티티 생성
 * - Product 이미지 등록
 * - ProductRegisteredEvent 발행 (감사 로깅)
 */
@Service
class RegisterProductService(
    private val saveProductPort: SaveProductPort,
    private val loadStorePort: LoadStorePort,
    private val productFactory: ProductFactory,
    private val domainEventPublisher: DomainEventPublisher,
    private val generateProductDescriptionService: GenerateProductDescriptionService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun register(
        requestUserId: UUID,
        command: RegisterProductCommand,
    ): RegisterProductResult {
        // 1. Value Objects 생성 및 유효성 검사
        val productName = ProductName.from(command.name)
        val price = Price.from(command.price)
        val stockQuantity = StockQuantity.from(command.stockQuantity)
        val thumbnailUrl = command.thumbnailUrl?.let { ThumbnailUrl.from(it) }
        val description = ProductDescription.from(command.description)

        // 2. 스토어 존재 여부 확인 및 storeName 조회 (Port-Adapter 패턴)
        val store =
            loadStorePort.loadByIdAndOwnerId(command.storeId, requestUserId)
                ?: throw StoreException.StoreRegistrationRequired(
                    userId = requestUserId,
                    storeId = command.storeId,
                )

        // 3. AI 설명 생성 (useAiDescription = true인 경우)
        val finalDescription =
            if (command.useAiDescription) {
                try {
                    val aiDescriptionCommand =
                        GenerateProductDescriptionCommand(
                            userId = requestUserId,
                            prompt = productName.value, // 상품 이름을 프롬프트로 사용
                        )
                    val aiDescription = generateProductDescriptionService.generate(aiDescriptionCommand)

                    // AI 응답이 있으면 새로운 ProductDescription 생성, 없으면 원래 description 사용
                    aiDescription?.let { ProductDescription.from(it) } ?: description.also {
                        logger.info("AI description generation returned null, using original description")
                    }
                } catch (ex: Exception) {
                    // AI 설명 생성 실패 시 원래 description 사용 (로그만 남김)
                    logger.warn("Failed to generate AI description, using original description: ${ex.message}")
                    description
                }
            } else {
                description
            }

        // 3. Product 엔티티 생성 (팩토리 활용)
        val images =
            command.images.map { imageDto ->
                ProductFactory.ImageData(
                    imageType = imageDto.imageType,
                    imageUrl = imageDto.imageUrl,
                    displayOrder = imageDto.displayOrder,
                )
            }

        val product =
            productFactory.createNewProduct(
                storeId = command.storeId,
                storeName = store.name, // 비정규화 필드 설정
                categoryId = command.categoryId,
                name = productName.value,
                price = price.value,
                stockQuantity = stockQuantity.value,
                thumbnailUrl = thumbnailUrl?.value,
                description = finalDescription.value, // AI 생성 또는 원래 description
                images = images,
            )

        // 4. Product 저장
        val savedProduct = saveProductPort.save(product)

        // 5. 도메인 이벤트 발행
        val event =
            ProductRegisteredEvent(
                productId = savedProduct.id,
                storeId = savedProduct.storeId,
                categoryId = savedProduct.categoryId,
                name = savedProduct.name,
                price = savedProduct.price,
                stockQuantity = savedProduct.stockQuantity,
                status = savedProduct.status,
                hiddenAt = savedProduct.hiddenAt,
            )
        domainEventPublisher.publish(event)

        return RegisterProductResult.from(savedProduct)
    }
}
