package contracts.internal.orderservice

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "order_service_get_products_by_ids_empty"
    description """
        Consumer: order-service
        Purpose: 존재하지 않는 상품 ID 목록 조회 시 빈 배열 반환
    """

    request {
        method GET()
        urlPath("/internal/v1/products") {
            queryParameters {
                parameter 'ids': "99999999-9999-9999-9999-999999999999"
            }
        }
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body([])
    }
}
