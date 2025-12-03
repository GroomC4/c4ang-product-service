package contracts.internal.orderservice

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "order_service_get_product_by_id_success"
    description """
        Consumer: order-service
        Purpose: 주문 시 상품 정보 조회 (id, storeId, name, storeName, price)
    """

    request {
        method GET()
        urlPath("/internal/v1/products/550e8400-e29b-41d4-a716-446655440000")
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status OK()
        headers {
            contentType(applicationJson())
        }
        body([
            id       : "550e8400-e29b-41d4-a716-446655440000",
            storeId  : "660e8400-e29b-41d4-a716-446655440001",
            name     : "아메리카노",
            storeName: "스타벅스 강남점",
            price    : 4500.00
        ])
        bodyMatchers {
            jsonPath('$.id', byRegex('[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}'))
            jsonPath('$.storeId', byRegex('[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}'))
            jsonPath('$.name', byType())
            jsonPath('$.storeName', byType())
            jsonPath('$.price', byRegex('[0-9]+\\.?[0-9]*'))
        }
    }
}
