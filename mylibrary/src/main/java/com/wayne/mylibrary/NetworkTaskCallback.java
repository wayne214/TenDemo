package com.wayne.mylibrary;

/**
 * Project:MyDemo
 * Author:wayne
 * Date:2016/5/27
 */
public interface NetworkTaskCallback {
    /**
     * 当newworkTask  执行完成，会回调这个接口方法
     * @param data
     */
    void onTaskFinished(byte[] data);
}
