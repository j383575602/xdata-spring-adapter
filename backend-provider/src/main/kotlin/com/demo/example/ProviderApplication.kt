package com.demo.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.ComponentScan


@EnableEurekaClient
@SpringBootApplication
@ComponentScan(value=["com.demo.example","top.xcore.springboot.adapter"])
class ProviderApplication

fun main(args: Array<String>) {
	runApplication<ProviderApplication>(*args)
}
