package com.demo


import com.demo.example.DemoApplication
import com.test.api.TTUserListRequestWrapper
import com.test.api.TTUserListResponseWrapper
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
	fun testApi1() {
		var urlConnection = URL("http://localhost:8080/api1").openConnection() as HttpURLConnection;
		urlConnection.doInput = true;
		urlConnection.doOutput = true;
		urlConnection.requestMethod = HttpMethod.GET.name;
		urlConnection.connect();
		var byteout = urlConnection.inputStream.readBytes();
		println("bytesout.size:${byteout.size},${String(byteout)}")
	}

	@Test
	fun testApi3() {
		var request = TTUserListRequestWrapper().apply {
			header = TTRequestHeaderWrapper().apply {
				requestId = "fdsafsdafdsfdsfiewrsv123334";
				sessionId = "09fdsafewjlkilfjdfajjsafdsf";
				token = "099ofdsafsdafdsafdsf";
			}
			startIndex = 101;
			count = 100;
		}

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

		println("code: ${response.header.code}");
		println("messge: ${response.header.message}");

		println("lastIndex: ${response.lastIndex}");

		response.users.forEach {
			println("username:${it.name}} ====================")
			it.cars.forEachIndexed() {index, car ->
				println("    car $index = ${car.brand}")
			}
			println("    ====================")
			it.taggedCars.forEach { (key, car) ->
				println("    $key = ${car.brand}")
			}
		}



	}

}
