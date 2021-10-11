package top.xcore.springboot.adapter;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign adapter
 */

@Configuration
public class XDataFeignConfiguration  {
    /**
     *
     * @return  a decoder use xdata
     */
    @Bean
    public ResponseEntityDecoder feignDecoder() {
        XDataConverter converter =  new XDataConverter();
        ObjectFactory objectFactory = () ->  new HttpMessageConverters(converter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
    }

    /**
     *
     * @return an encoder use xdata
     */
    @Bean
    public SpringEncoder feignEncoder()  {
        XDataConverter converter = new  XDataConverter();
        ObjectFactory objectFactory = ()-> new HttpMessageConverters(converter);
        return new SpringEncoder(objectFactory);
    }
}