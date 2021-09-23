package top.xcore.springboot.adapter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import top.xcore.xdata.LinkedBuffer;
import top.xcore.xdata.XData;
import top.xcore.xdata.XDataParser;
import top.xcore.xdata.XDataWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * A converter use to convert xdata to wrapper
 */
public class XDataConverter extends AbstractHttpMessageConverter<XData> {
    /**
     * Constructor
     */
    public XDataConverter() {
        super(MediaType.valueOf("application/xdata"));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return XData.class.isAssignableFrom(clazz);
    }

    @Override
    protected XData readInternal(Class<? extends XData> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        XDataParser parser = new XDataParser();
        XData xdata =  parser.parse(inputMessage.getBody().readAllBytes());
        if (clazz == XData.class) {
            return xdata;
        }
        try {
            return clazz.getConstructor(XData.class).newInstance(xdata);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Ensure wrapper class " + clazz+ " have a constructor : " +clazz.getName()+"(XData data),or check whether it is a XData");
    }

    @Override
    protected void writeInternal(XData xData, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        XDataWriter writer = new XDataWriter();
        LinkedBuffer buffer = writer.writeDataToBuffer(xData);
        buffer.writeToStream(outputMessage.getBody());
    }
}
