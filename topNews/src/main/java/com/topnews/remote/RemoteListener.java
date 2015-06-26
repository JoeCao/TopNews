package com.topnews.remote;

import com.topnews.bean.NewsEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caozupeng on 15-6-25.
 */
public interface RemoteListener {
    void onSuccess(ArrayList<NewsEntity> items);
}
