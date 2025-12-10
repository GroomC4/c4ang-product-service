package com.groom.product.configuration.kafka

import io.confluent.kafka.serializers.KafkaAvroDeserializer
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.listener.DefaultErrorHandler
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer
import org.springframework.util.backoff.FixedBackOff

/**
 * Kafka Consumer 설정
 *
 * Product Service가 구독하는 이벤트:
 * - order.created (주문 생성 → 재고 예약)
 * - payment.completed (결제 완료 → 재고 확정)
 * - store.info.updated (스토어 정보 변경 → 상품 동기화)
 * - store.deleted (스토어 삭제 → 상품 비활성화)
 *
 * ErrorHandlingDeserializer를 사용하여 역직렬화 실패 시에도
 * 무한 재시도를 방지하고 정상 메시지 처리를 계속합니다.
 */
@EnableKafka
@Configuration
class KafkaConsumerConfig {
    @Value("\${kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Value("\${kafka.schema-registry.url}")
    private lateinit var schemaRegistryUrl: String

    @Bean
    fun consumerFactory(): ConsumerFactory<String, Any> {
        val configProps =
            mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers,
                ConsumerConfig.GROUP_ID_CONFIG to "product-service",
                // ErrorHandlingDeserializer로 래핑하여 역직렬화 에러 처리
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ErrorHandlingDeserializer::class.java,
                ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS to StringDeserializer::class.java,
                ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS to KafkaAvroDeserializer::class.java,
                "schema.registry.url" to schemaRegistryUrl,
                KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG to true,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest",
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to false,
            )
        return DefaultKafkaConsumerFactory(configProps)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = consumerFactory()
        factory.containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
        // 에러 핸들러: 3회 재시도 후 스킵 (1초 간격)
        factory.setCommonErrorHandler(
            DefaultErrorHandler(FixedBackOff(1000L, 3L))
        )
        return factory
    }
}
