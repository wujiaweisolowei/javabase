package base.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestArrayList {

    public static void main(String[] args) {
//        List<String> list=Collections.synchronizedList(new ArrayList<>());
//        List<String> voctor = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("aa");
    }
}
