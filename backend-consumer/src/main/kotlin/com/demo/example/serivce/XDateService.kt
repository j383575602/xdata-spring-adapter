package com.demo.example.serivce

import com.test.api.TTUserListRequestWrapper
import com.test.api.TTUserListResponseWrapper
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import top.xcore.xdata.XData

@FeignClient(name = "producer")
interface XDateService {
    @RequestMapping(path = ["/xservice"],name = "POST",consumes = ["application/xdata"])
    fun sendXService(@RequestBody request: TTUserListRequestWrapper):TTUserListResponseWrapper
}