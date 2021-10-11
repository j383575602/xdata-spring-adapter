package com.demo.example


import com.demo.example.serivce.XDateService
import com.test.XCar
import com.test.XCarWrapper
import com.test.XUser
import com.test.XUserWrapper
import com.test.api.TTUserListRequest
import com.test.api.TTUserListRequestWrapper
import com.test.api.TTUserListResponse
import com.test.api.TTUserListResponseWrapper
import com.test.model.TTRequestHeaderWrapper
import com.test.model.TTResponseHeader
import com.test.model.TTResponseHeaderWrapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.ArrayList


fun <T> Any.addTo(list:ArrayList<T>) {
    list.add(this as T);
}

@RestController
class ConsumerController {

    @Autowired
    lateinit var service: XDateService

    @GetMapping("/test")
    fun api3(): String {
        val requst = createRequest();
        val response = service.sendXService(requst);
        printResponseWrapper(response)
        return "OK"
    }

    fun printResponseWrapper(response: TTUserListResponseWrapper) {
        logValue("code: ${response.header.code}");
        logValue("messge: ${response.header.message}");
        logValue("lastIndex: ${response.lastIndex}");
        response.users.forEach {
            logValue("username:${it.name}} ====================")
            it.cars.forEachIndexed() { index, car ->
                logValue("    car $index = ${car.brand}")
            }
            logValue("    ====================")
            it.taggedCars.forEach { (key, car) ->
                logValue("    $key = ${car.brand}")
            }
        }
    }


    fun createRequest():TTUserListRequestWrapper {
        var request = TTUserListRequestWrapper().apply {
            header = TTRequestHeaderWrapper().apply {
                requestId = "fdsafsdafdsfdsfiewrsv123334";
                sessionId = "09fdsafewjlkilfjdfajjsafdsf";
                token = "099ofdsafsdafdsafdsf";
            }
            startIndex = 101;
            count = 100;
        }
        return request
    }

    fun logValue(msg:Any) {
        println(msg);
    }



//    private fun handleUserLisRequestXData(request: TTUserListRequestWrapper): TTUserListResponseWrapper {
//        return TTUserListResponseWrapper().apply {
//            header = TTResponseHeaderWrapper().apply {
//                code = 200;
//                message = "success";
//            }
//
//            users = ArrayList<XUserWrapper>(request.count).apply {
//                for (i in request.startIndex..request.startIndex+request.count) {
//                    XUserWrapper().apply {
//                        name = "User:$i";
//                        cars = ArrayList<XCarWrapper>(10).apply {
//                            for (j in 0..10) {
//                                XCarWrapper().apply {
//                                    brand = "Brand:$i";
//                                }.addTo(this);
//                            }
//                        }
//                        taggedCars = HashMap<String, XCarWrapper>().apply {
//                            for (j in 0..3) {
//                                put("key$j", XCarWrapper().apply { brand = "brand:$j" });
//                            }
//                        }
//                    }.addTo(this);
//                }
//            }
//
//            lastIndex = request.startIndex + users.size;
//        }
//    }


}