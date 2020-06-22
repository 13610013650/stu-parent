package com.test.stream.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestRefrence {


    public static void main(String[] args) throws ParseException {
//        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
//        PhantomReference<byte[]> phantomReference = new PhantomReference<byte[]>(new byte [1024*1024*10],referenceQueue);
//        WeakReference<byte[]> weakReference  = new WeakReference<>(new byte[1024*1024*10]);

//        List<String> list = null;
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

        Date date = null;

        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-11");
        System.out.println(date.before(date1));
    }
}
