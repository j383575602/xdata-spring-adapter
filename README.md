
# 1 usage:
   XData and all other artifacts are release to maven central repository, you can use like:

## 1.1 dependency declaration:

        implementation("top.xcore:xdata-core:1.0.1)
        implementation("top.xcore:springboot.adapter:0.0.1)

## 1.2 you should add Configuration for SpringBoot

    @Configuration
    class XDataConfiguration : WebMvcConfigurer {
        override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        super.extendMessageConverters(converters)
        println("========extendMessageConverters is ccreateed");
        converters.add(XDataConverter());
        }
    }

## 1.3  declare a controller like:


    @PostMapping("/xservice", produces = ["application/xdata"])
    fun api(@RequestBody request: TTUserListRequestWrapper): TTUserListResponseWrapper {
        return handleRequest(request);
    }

    private fun handleRequest(request:TTUserListRequestWrapper):TTUserListResponseWrapper {
        // handle the business and return an object of TTUserListResponseWrapper
    }

# 2  Run Test and See Performance Compared with fastjson

## 2.1 Run DemoController
    a web service will run at http://localhost:8080/xservice using xdata 
    a web service will run at http://localhost:8080/json using fastjson

## 2.2 Run Test
DemoApplicationTests.test3 is a test that will call the two services above , in this test, we use xdata and fastjson to send same business data, to compare the traffic data and time consumption

##2.3 Performance Compare

|method|traffic data(byte) |  time(ms) |
  |-----|-----|---------| 
|xdata|33074|36|
|fastjson|39863|147|

# 3 Contract

The xdata-contract of this demo is generated from https://www.xcore.top. You can edit your contract in this platform as well. which will help you generate and share the same contracts within your teams. 
including:
 - Backend(Java)
 - Frontend(Javascript)
 - iOS(Objective-C)
 - Android(Java)
 - Flutter(Dart)
 - React-Native(Javascript)

