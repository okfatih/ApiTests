package Utils;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class ObjeMapperUtils {

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }
    public static <T> T convertJsontoJava(String json, Class<T> cls){//generic method
        T javaResult = null;
        try {
            javaResult =   mapper.readValue(json,cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return javaResult;
    }

}
