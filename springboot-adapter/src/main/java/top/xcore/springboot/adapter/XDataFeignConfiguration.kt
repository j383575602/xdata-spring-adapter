package top.xcore.springboot.adapter


import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class XDataFeignConfiguration  {
    @Bean
    fun feignDecoder():ResponseEntityDecoder {
        var converter = XDataConverter();
        var objectFactory = ObjectFactory<HttpMessageConverters> { HttpMessageConverters(converter) }
        return ResponseEntityDecoder(SpringDecoder(objectFactory))
    }

    @Bean
    fun feignEncoder() : SpringEncoder {
        var converter = XDataConverter();
        var objectFactory = ObjectFactory<HttpMessageConverters> { HttpMessageConverters(converter) }
        return SpringEncoder(objectFactory)
    }
}