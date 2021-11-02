import com.alibaba.fastjson.JSON;
import com.test.XCar;
import com.test.XCarWrapper;
import com.test.XUser;
import com.test.XUserWrapper;
import com.test.api.TTUserListResponse;
import com.test.api.TTUserListResponseWrapper;
import com.test.model.TTResponseHeader;
import com.test.model.TTResponseHeaderWrapper;
import top.xcore.xdata.XData;
import top.xcore.xdata.XDataParser;
import top.xcore.xdata.XDataWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class Performance {

    private static final int REPEATED = 5000000;
    private static final int SIZE = 50;
    public static void main(String[] args) {
        compareCreate();
        compareSerialize();
        compareDeserialize();
    }

    public static void compareCreate() {
        benchmark("create xdata",() -> createXDataResponse(SIZE),REPEATED);
        benchmark("create json",() -> createXDataResponse(SIZE),REPEATED);
    }

    public static void compareSerialize() {
        TTUserListResponseWrapper xdata = createXDataResponse(SIZE);
        TTUserListResponse json = createJsonResponse(SIZE);
        benchmark("serialize xdata",() -> serializeXDataResponse(xdata),REPEATED);
        benchmark("serialize json",() -> serializeJsonResponse(json),REPEATED);
    }

    public static void compareDeserialize() {
        TTUserListResponseWrapper xdata = createXDataResponse(SIZE);
        TTUserListResponse bean = createJsonResponse(SIZE);
        XDataWriter writer = new XDataWriter();
        writer.setDebug(false);
        byte[] bytes = writer.writeData(xdata);
        String json = JSON.toJSONString(bean,false);
        benchmark("deserialize xdata",() -> deSerializeXDataResponse(bytes),REPEATED);
        benchmark("deserialize json",() -> deSerializeJsonResponse(json),REPEATED);
    }


    public static void benchmark(String label ,Runnable runnable,int count) {
        long t0 = System.currentTimeMillis();
        for(int i=0;i<count;i++) {
            runnable.run();
        }
        long t1 = System.currentTimeMillis();
        System.out.println(label +":" +(t1-t0));

    }


    public static byte[] serializeXDataResponse(TTUserListResponseWrapper response) {
        XDataWriter writer = new XDataWriter();
        writer.setDebug(false);
        byte[] bytes = writer.writeData(response);
        return bytes;
    }

    public static String serializeJsonResponse(TTUserListResponse response) {
        String json = JSON.toJSONString(response,false);
        return json;
    }

    public static TTUserListResponseWrapper deSerializeXDataResponse(byte[] data) {
        XDataParser parser = new XDataParser();
        parser.setDebug(false);
        XData xdata = parser.parse(data);
        return new TTUserListResponseWrapper(xdata);
    }

    public static TTUserListResponse deSerializeJsonResponse(String json) {
        return JSON.parseObject(json,TTUserListResponse.class);
    }


    public static TTUserListResponseWrapper createXDataResponse(int count) {
        TTUserListResponseWrapper response = new  TTUserListResponseWrapper();
        TTResponseHeaderWrapper header = new TTResponseHeaderWrapper();
        header.setCode((short) 200);
        header.setMessage("success");
        response.setHeader(header);
        ArrayList<XUserWrapper> users = new ArrayList<>(count);

        for (int i=0; i < count;i++) {
            XUserWrapper user = new XUserWrapper();
            user.setName("User:" + i);
            ArrayList<XCarWrapper> cars = new ArrayList<>(10);
            HashMap<String, XCarWrapper> taggedCars = new HashMap<>(3);
            for (int j = 0; j < 10; j++) {
                XCarWrapper car = new XCarWrapper();
                car.setBrand("Brand:" + i);
                cars.add(car);
            }
            user.setCars(cars);
            for (int j = 0; j < 3; j++) {
                XCarWrapper car = new XCarWrapper();
                car.setBrand("brand:" + j);
                taggedCars.put("key:" + j, car);
            }
            user.setTaggedCars(taggedCars);
            users.add(user);
        }
        response.setUsers(users);
        return response;
    }

    public static TTUserListResponse createJsonResponse(int count) {
        TTUserListResponse response = new  TTUserListResponse();
        TTResponseHeader header = new TTResponseHeader();
        header.setCode((short) 200);
        header.setMessage("success");
        response.setHeader(header);
        ArrayList<XUser> users = new ArrayList<>(count);

        for (int i=0; i < count;i++) {
            XUser user = new XUser();
            user.setName("User:" + i);
            ArrayList<XCar> cars = new ArrayList<>(10);
            HashMap<String, XCar> taggedCars = new HashMap<>(3);
            for (int j = 0; j < 10; j++) {
                XCar car = new XCar();
                car.setBrand("Brand:" + i);
                cars.add(car);
            }
            user.setCars(cars);
            for (int j = 0; j < 3; j++) {
                XCar car = new XCar();
                car.setBrand("brand:" + j);
                taggedCars.put("key:" + j, car);
            }
            user.setTaggedCars(taggedCars);
            users.add(user);
        }
        response.setUsers(users);
        return response;
    }
}
