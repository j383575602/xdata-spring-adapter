package com.demo


import com.alibaba.fastjson.JSON
import com.demo.example.DemoApplication
import com.test.api.TTUserListRequest
import com.test.api.TTUserListRequestWrapper
import com.test.api.TTUserListResponse
import com.test.api.TTUserListResponseWrapper
import com.test.model.TTRequestHeader
import com.test.model.TTRequestHeaderWrapper

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpMethod
import top.xcore.xdata.XDataParser
import top.xcore.xdata.XDataWriter
import java.net.HttpURLConnection
import java.net.URL

@SpringBootTest(classes =[DemoApplication::class])
class DemoApplicationTests {

	@Test
	fun contextLoads() {

	}

	@Test
	fun test1() {
		doWithXData()
	}

	@Test
	fun test2() {
		doWithFastJson();
	}

	@Test
	fun test3() {
		doWithXData();
		doWithFastJson();
	}

	fun doWithXData() {
		var t0 = System.currentTimeMillis();
		var request = TTUserListRequestWrapper().apply {
			header = TTRequestHeaderWrapper().apply {
				requestId = "fdsafsdafdsfdsfiewrsv123334";
				sessionId = "09fdsafewjlkilfjdfajjsafdsf";
				token = "099ofdsafsdafdsafdsf";
			}
			startIndex = 101;
			count = 100;
		}

		var response = sendUserListRequestXData(request);
		var t1 = System.currentTimeMillis();
		println("xdata total Time:${t1-t0}")
		//printResponseWrapper(response);
	}


	fun doWithFastJson() {
		var t0 = System.currentTimeMillis();
		var request = TTUserListRequest().apply {
			header = TTRequestHeader().apply {
				requestId = "fdsafsdafdsfdsfiewrsv123334";
				sessionId = "09fdsafewjlkilfjdfajjsafdsf";
				token = "099ofdsafsdafdsafdsf";
			}
			startIndex = 101;
			count = 100;
		}

		var response = sendUserListRequestJSON(request);
		var t1 = System.currentTimeMillis();
		println("fastjson total Time:${t1-t0}")


		//printResponse(response);
	}

	fun printResponseWrapper(response: TTUserListResponseWrapper) {
		response.users.forEach {
			println("username:${it.name}} ====================")
			it.cars.forEachIndexed() { index, car ->
				println("    car $index = ${car.brand}")
			}
			println("    ====================")
			it.taggedCars.forEach { (key, car) ->
				println("    $key = ${car.brand}")
			}
		}
	}

	fun printResponse(response: TTUserListResponse) {
		println("code: ${response.header.code}");
		println("messge: ${response.header.message}");
		println("lastIndex: ${response.lastIndex}");
		response.users.forEach {
			println("username:${it.name}} ====================")
			it.cars.forEachIndexed() { index, car ->
				println("    car $index = ${car.brand}")
			}
			println("    ====================")
			it.taggedCars.forEach { (key, car) ->
				println("    $key = ${car.brand}")
			}
		}
	}


	fun sendUserListRequestXData(request:TTUserListRequestWrapper) : TTUserListResponseWrapper {
		var urlConnection = URL("http://localhost:8080/xservice").openConnection() as HttpURLConnection;
		urlConnection.doInput = true;
		urlConnection.doOutput = true;
		urlConnection.requestMethod = HttpMethod.POST.name;
		urlConnection.addRequestProperty("Content-Type","application/xdata")
		urlConnection.connect();
		var bytes = XDataWriter().writeData(request);
		urlConnection.outputStream.write(bytes)
		var byteout = urlConnection.inputStream.readBytes();
		println("bytesout.size:${byteout.size},${String(byteout)}")
		var outb = XDataParser().parse(byteout);
		var response = TTUserListResponseWrapper(outb);
		return response;
	}

	fun sendUserListRequestJSON(request:TTUserListRequest) : TTUserListResponse {
		var urlConnection = URL("http://localhost:8080/json").openConnection() as HttpURLConnection;
		urlConnection.doInput = true;
		urlConnection.doOutput = true;
		urlConnection.requestMethod = HttpMethod.POST.name;
		urlConnection.addRequestProperty("Content-Type","application/json")
		urlConnection.connect();
		var json = JSON.toJSONBytes(request);
		urlConnection.outputStream.write(json)
		var byteout = urlConnection.inputStream.readBytes();
		println("bytesout.size:${byteout.size},${String(byteout)}")
		var response =JSON.parseObject(String(byteout),TTUserListResponse::class.java)
		return response;
	}
}
