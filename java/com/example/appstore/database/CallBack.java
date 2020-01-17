package com.example.appstore.database;


/**
 * 回调函数接口
 */
public interface CallBack {

    void onDataSuccessfully(Object object);//获取数据成功返回数据object
    void onDataFailed();//失败处理

}
