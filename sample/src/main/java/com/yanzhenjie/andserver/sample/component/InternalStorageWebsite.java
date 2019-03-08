package com.yanzhenjie.andserver.sample.component;

import com.yanzhenjie.andserver.annotation.Website;
import com.yanzhenjie.andserver.framework.website.StorageWebsite;

/**
 * 加载sd的网页文件
 * 1,sd卡创建web目录
 * 2,web目录可以创建各种文件夹文件( 创建 pic/material.png )
 * 3,访问图片地址：http://192.168.1.109:8080/pic/material.png
 */
@Website
public class InternalStorageWebsite extends StorageWebsite {

    public InternalStorageWebsite() {
        super("/sdcard/web");
    }
}
