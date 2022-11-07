package test_data;

import java.util.HashMap;
import java.util.Map;

public class ReqresTestData {
    public Map<String,Object>dataKeyMapMethod(String name, String job){
        Map<String,Object>datakeyMap = new HashMap<>();
        if (name!=null){
            datakeyMap.put("name",name);
        }

        if (job!=null){
            datakeyMap.put("job",job);
        }

        return datakeyMap;
    }
    public String expectedDataInString(String name, String job){
        String expectedData = "{\n" +
                "                \"name\": \""+ name +"\",\n" +
                "                \"job\": \""+ job +"\"\n" +
                "                }";
        return expectedData;
    }
}
