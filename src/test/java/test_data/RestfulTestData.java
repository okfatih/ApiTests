package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {
    public Map<String, String> bookingDatesMethod(String checkin, String checkout) {

        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", checkin);
        bookingDatesMap.put("checkout", checkout);
        return bookingDatesMap;


    }

    public Map<String, Object> expectedDataMethod(String firstname, String lastname, Integer totalprice, Boolean depositpaid, Map<String, String> bookingdates) {

        Map<String, Object> expectedDataMap = new HashMap<>();
        if (firstname!=null){
            expectedDataMap.put("firstname", firstname);
        }
      if (lastname!=null){
          expectedDataMap.put("lastname", lastname);
      }
       if (totalprice!=null){
           expectedDataMap.put("totalprice", totalprice);
       }
        if (depositpaid!=null){
            expectedDataMap.put("depositpaid", depositpaid);
        }
        if (bookingdates!=null){
            expectedDataMap.put("bookingdates", bookingdates);
        }

        return expectedDataMap;
    }
}
