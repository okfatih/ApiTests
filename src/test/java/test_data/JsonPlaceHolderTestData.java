package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> expectedDataMetjod(Integer userId, String title, Boolean completed){


        Map<String, Object>expectedDataMap = new HashMap<>();
        if (userId!=null){
            expectedDataMap.put("userId",userId);
        }
       if (title!=null){
           expectedDataMap.put("title",title);
       }
       if (completed!=null){
           expectedDataMap.put("completed", completed);
       }


        return expectedDataMap;
    }
    public String expectedDataInString(Integer userId, String title, Boolean completed){ //Dinamik expected data methodu Json datayı str
        String expectedData  = "{\n" +
                "                 \"userId\": "+userId+",\n" +
                "                 \"title\": \""+title+"\",\n" +
                "                 \"completed\": "+completed+"\n" +
                "               }";
        return expectedData;
    }

}
/*
{
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
               }
 */