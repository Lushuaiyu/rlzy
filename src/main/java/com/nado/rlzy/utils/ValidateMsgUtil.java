package com.nado.rlzy.utils;

import com.nado.rlzy.utils.telMessage.Title;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName
 * @Description TODO
 * @Author lushuaiyu
 * @Data 2019-09-17 11:06
 * @Version 1.0
 */
public class ValidateMsgUtil implements Runnable {
    public final static List<Title> list = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                List<Title> k = new ArrayList<Title>();
                for (Title t : list) {
                    int time = Integer.parseInt(t.getTime());
                    if (System.currentTimeMillis() - t.getSendTime() > time * 60 * 1000) {
                        k.add(t);
                    }
                }
                for (Title tit : k) {
                    list.remove(tit);
                }
            }
            try {
                Thread.sleep(1000 * 60 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Title getTitle(String phone, String type) {
        synchronized (list) {
            for (Title t : list) {
                if (t.getPhone().equals(phone) && t.getType().equals(type)) {
                    return t;
                }
            }
            return null;
        }
    }

    public static void addTitle(Title t) {
        synchronized (list) {
            Title t2 = null;
            for (Title title : list) {
                if (t.equals_2(title)) {
                    t2 = title;
                    break;
                }
            }
            if (t2 != null)
                list.remove(t2);
            list.add(t);
        }
    }
}
