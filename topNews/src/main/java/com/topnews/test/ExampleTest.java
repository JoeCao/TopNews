package com.topnews.test;

import android.test.InstrumentationTestCase;

import com.topnews.bean.NewsEntity;
import com.topnews.remote.NewsDao;
import com.topnews.remote.RemoteListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by caozupeng on 15/7/8.
 */
public class ExampleTest extends InstrumentationTestCase{
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public void test() throws Exception {
        final CountDownLatch waitReturn = new CountDownLatch(1);
        final ArrayList<NewsEntity> ret = new ArrayList<NewsEntity>();
        NewsDao.getNewsList(1, sdf.parse("2015-07-05 09:14:00"), new RemoteListener() {
            @Override
            public void onSuccess(ArrayList<NewsEntity> items) {
                System.out.println("size = " + items.size());
                for (NewsEntity item : items) {
                    ret.add(item);
                }
                waitReturn.countDown();

            }
        });
        waitReturn.await();
        for (NewsEntity item : ret) {
            System.out.println(item.getCreateAt());
        }
        assertEquals(5, ret.size());
    }
}
