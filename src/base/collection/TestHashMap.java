package base.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestHashMap {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put(null, null);
        Map<String, String> map1 = new ConcurrentHashMap<>();
        map1.put("not null", "not null");
        Map<String, String> map2= new Hashtable<>();
        map2.put("not null", "not null");

    }
}
