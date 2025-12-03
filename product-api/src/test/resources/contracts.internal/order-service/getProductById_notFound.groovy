package contracts.internal.orderservice

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    name "order_service_get_product_by_id_not_found"
    description """
        Consumer: order-service
        Purpose: 존재하지 않는 상품 조회 시 404 응답
    """

    request {
        method GET()
        urlPath("/internal/v1/products/99999999-9999-9999-9999-999999999999")
        headers {
            contentType(applicationJson())
        }
    }

    response {
        status NOT_FOUND()
        headers {
            contentType(applicationJson())
        }
        body([
            error  : "PRODUCT_NOT_FOUND",
            message: $(consumer("Product not found with id: 99999999-9999-9999-9999-999999999999"), producer(regex("Product not found.*")))
        ])
    }
}
