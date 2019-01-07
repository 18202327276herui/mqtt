package usung.com.mqttclient.utils;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import usung.com.mqttclient.R;
import usung.com.mqttclient.bean.db.Contact;


/**
 * Created by you on 2017/9/11.
 */

public class TestUtils {

    public static List<Contact> contactList(Context context) {
        List<Contact> contactList = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        String[] names = context.getResources().getStringArray(R.array.names);
        for (int i = 0; i < names.length; i++) {
            int urlIndex = random.nextInt(URLS.length);
            int url = URLS[urlIndex];
//            contactList.add(new Contact(names[i], url));
        }
        return contactList;
    }


    static int[] URLS = {R.mipmap.header0, R.mipmap.header1, R.mipmap.header2, R.mipmap.header3};

}
