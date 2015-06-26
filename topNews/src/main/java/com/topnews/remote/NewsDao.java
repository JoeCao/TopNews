package com.topnews.remote;

import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.topnews.R;
import com.topnews.bean.NewsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caozupeng on 15-6-25.
 */
public class NewsDao {
    public static void getNewsList(final RemoteListener listener) {
        final ArrayList<NewsEntity> results = new ArrayList<NewsEntity>();
        AVQuery<AVObject> query = new AVQuery<AVObject>("News");
        query.setLimit(30);
        query.findInBackground(new FindCallback<AVObject>() {
                                   public void done(List<AVObject> avObjects, AVException e) {
                                       if (e == null) {
                                           Log.d("成功", "查询到" + avObjects.size() + " 条符合条件的数据");
                                           int i = 0;
                                           for (AVObject avObject : avObjects) {
                                               NewsEntity news = new NewsEntity();
                                               news.setId(i);
                                               news.setNewsId(avObject.getInt("newId"));
                                               news.setCollectStatus(avObject.getBoolean("collectStatus"));
                                               news.setCommentNum(i + 10);
                                               news.setInterestedStatus(true);
                                               news.setLikeStatus(avObject.getBoolean("interestedStatus"));
                                               news.setReadStatus(false);
                                               news.setNewsCategory("推荐");
                                               news.setNewsCategoryId(1);
                                               news.setSource_url(avObject.getString("source_url"));
                                               news.setTitle(avObject.getString("title"));
                                               List<String> url_list = new ArrayList<String>();
                                               news.setPicOne(avObject.getString("picOne"));
                                               url_list.add(avObject.getString("picOne"));
                                               news.setPicList(url_list);
                                               news.setIsLarge(avObject.getBoolean("isLarge"));
                                               news.setPublishTime(avObject.getLong("publishTime"));
                                               news.setSource(avObject.getString("source"));
                                               news.setSummary(avObject.getString("summary"));
                                               news.setMark(i);
                                               results.add(news);
                                               i++;
                                           }
                                           listener.onSuccess(results);
                                       } else {
                                           Log.d("失败", "查询错误: " + e.getMessage());
                                       }
                                   }

                               }

        );
    }

}
