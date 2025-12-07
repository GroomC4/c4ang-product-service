package com.groom.product.common.config

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextClosedEvent
import org.springframework.test.context.support.TestPropertySourceUtils

/**
 * WireMock 서버 초기화 클래스
 *
 * Spring Context 초기화 시점에 WireMock 서버를 시작하고,
 * feign.clients.store-service.url 프로퍼티를 동적으로 설정합니다.
 */
class WireMockInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    companion object {
        private var wireMockServer: WireMockServer? = null

        fun getWireMockServer(): WireMockServer? = wireMockServer
    }

    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        wireMockServer = WireMockServer(wireMockConfig().dynamicPort())
        wireMockServer!!.start()

        // WireMock 서버 URL을 Spring Property로 설정
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
            applicationContext,
            "feign.clients.store-service.url=http://localhost:${wireMockServer!!.port()}"
        )

        // WireMockServer를 Bean으로 등록
        val beanFactory: ConfigurableListableBeanFactory = applicationContext.beanFactory
        (beanFactory as DefaultSingletonBeanRegistry).registerSingleton(
            "wireMockServer",
            wireMockServer!!
        )

        // Context 종료 시 WireMock 서버 정지
        applicationContext.addApplicationListener { event ->
            if (event is ContextClosedEvent) {
                wireMockServer?.stop()
            }
        }
    }
}
