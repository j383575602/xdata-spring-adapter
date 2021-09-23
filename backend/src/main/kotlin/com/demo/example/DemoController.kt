package com.demo.example


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
import org.springframework.web.bind.annotation.*
import java.util.ArrayList


fun <T> Any.addTo(list:ArrayList<T>) {
    list.add(this as T);
}

@RestController
class DemoController {
    @GetMapping("/api1")
    fun api1(@RequestParam name: String?): String? {
        return name;
    }

    @PostMapping("/xservice", produces = ["application/xdata"])
    fun api3(@RequestBody request: TTUserListRequestWrapper): TTUserListResponseWrapper {
        var logger = LoggerFactory.getLogger(DemoController::class.qualifiedName);
        logger.info("requestId:${request.header.requestId}")
        logger.info("requestId:${request.header.sessionId}")
        logger.info("requestId:${request.header.token}");

        logger.info("startIndex:${request.startIndex}");
        logger.info("count:${request.count}");
        return handleUserLisRequestXData(request);

    }

    @PostMapping("/json", produces = ["application/json"])
    fun api4(@RequestBody request: TTUserListRequest): TTUserListResponse {
        var logger = LoggerFactory.getLogger(DemoController::class.qualifiedName);
        logger.info("requestId:${request.header.requestId}")
        logger.info("requestId:${request.header.sessionId}")
        logger.info("requestId:${request.header.token}");

        logger.info("startIndex:${request.startIndex}");
        logger.info("count:${request.count}");
        return handleUserLisRequestJSON(request);

    }

    private fun handleUserLisRequestJSON(request: TTUserListRequest): TTUserListResponse {
       return TTUserListResponse().apply {
            header = TTResponseHeader().apply {
                code = 200;
                message = "success";
            }

            users = ArrayList<XUser>(request.count).apply {
                for (i in request.startIndex..request.startIndex+request.count) {
                    XUser().apply {
                        name = "User:$i";
                        cars = ArrayList<XCar>(10).apply {
                            for (j in 0..10) {
                                XCar().apply {
                                    brand = "Brand:$i";
                                }.addTo(this);
                            }
                        }
                        taggedCars = HashMap<String, XCar>().apply {
                            for (j in 0..3) {
                                put("key$j", XCar().apply { brand = "brand:$j" });
                            }
                        }
                    }.addTo(this);
                }
            }

            lastIndex = request.startIndex + users.size;
        }
    }

    private fun handleUserLisRequestXData(request: TTUserListRequestWrapper): TTUserListResponseWrapper {
        return TTUserListResponseWrapper().apply {
            header = TTResponseHeaderWrapper().apply {
                code = 200;
                message = "success";
            }

            users = ArrayList<XUserWrapper>(request.count).apply {
                for (i in request.startIndex..request.startIndex+request.count) {
                    XUserWrapper().apply {
                        name = "User:$i";
                        cars = ArrayList<XCarWrapper>(10).apply {
                            for (j in 0..10) {
                                XCarWrapper().apply {
                                    brand = "Brand:$i";
                                }.addTo(this);
                            }
                        }
                        taggedCars = HashMap<String, XCarWrapper>().apply {
                            for (j in 0..3) {
                                put("key$j", XCarWrapper().apply { brand = "brand:$j" });
                            }
                        }
                    }.addTo(this);
                }
            }

            lastIndex = request.startIndex + users.size;
        }
    }


}