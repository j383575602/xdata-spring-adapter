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
		doWithFastJson();
		doWithXData();
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
		printResponseWrapper(response);
		var t1 = System.currentTimeMillis();
		println("xdata total Time:${t1-t0}")
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
		printResponse(response);
		var t1 = System.currentTimeMillis();
		println("fastjson total Time:${t1-t0}")


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

	fun printResponse(response: TTUserListResponse) {
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

	fun logValue(msg:Any) {
		//println(msg);
	}
}
