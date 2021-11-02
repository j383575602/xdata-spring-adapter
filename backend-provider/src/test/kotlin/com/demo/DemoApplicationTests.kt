package com.demo


import com.alibaba.fastjson.JSON
import com.demo.example.ProviderApplication
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
import kotlin.math.abs
import kotlin.random.Random

@SpringBootTest(classes =[ProviderApplication::class])
class DemoApplicationTests {
	val benckmark = 2;
	val random = Random(System.currentTimeMillis());
	val seed = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYA1234567890";
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

	fun readomstring(len:Int):String {
		var sb = StringBuilder();
		for (i in 0..len) {
			var index = abs(random.nextInt()) % 62;
			var ch = seed[index];
			sb.append(ch)
		}
		return sb.toString();
	}

	fun doWithXData() {
		var t0 = System.currentTimeMillis();
		for(i in 0..benckmark) {
			var request = TTUserListRequestWrapper().apply {
				header = TTRequestHeaderWrapper().apply {
					requestId = readomstring(20);
					sessionId = readomstring(20);
					token = readomstring(10);
				}
				startIndex = 101;
				count = 10000;
			}

			var response = sendUserListRequestXData(request);
			//printResponseWrapper(response);
		}
		var t1 = System.currentTimeMillis();
		println("xdata total Time:${t1-t0}")
	}


	fun doWithFastJson() {
		var t0 = System.currentTimeMillis();
		for(i in 0..benckmark) {
			var request = TTUserListRequest().apply {
				header = TTRequestHeader().apply {
					requestId = readomstring(20);
					sessionId = readomstring(20);
					token = readomstring(10);
				}
				startIndex = 101;
				count = 10000;
			}

			var response = sendUserListRequestJSON(request);
			//printResponse(response);
		}
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
		printValue("xdata bytesout.size:${byteout.size}")
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
		printValue("json bytesout.size:${byteout.size}")
		var t1 = System.currentTimeMillis();
		var response =JSON.parseObject(String(byteout),TTUserListResponse::class.java)
		var t2 = System.currentTimeMillis();
		printValue("json read parsetime:${t2-t1}")
		return response;
	}

	fun logValue(msg:Any) {
		//println(msg);
	}
	fun printValue(msg:Any) {
		println(msg);
	}
}
