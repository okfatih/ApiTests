package test_data;

import java.util.HashMap;
import java.util.Map;

public class GoRestTestData {
    public Map<String,String>dataKeyMap(String name, String email, String gender, String status){

        Map<String, String> datakeyMap = new HashMap<>();
        datakeyMap.put("name",name);
        datakeyMap.put("email",email);
        datakeyMap.put("gender",gender);
        datakeyMap.put("status",status);


        return datakeyMap;
    }
    public Map<String, Object>expectedDataMethod(Object meta,Map<String,String>data){
        Map<String, Object>expectedData = new HashMap<>();
        expectedData.put("meta",meta);
        expectedData.put("data",data);
        return expectedData;
    }
}

/*
{
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Kanaka Jain",
        "email": "kanaka_jain@stark.net",
        "gender": "male",
        "status": "active"
    }
}
 */