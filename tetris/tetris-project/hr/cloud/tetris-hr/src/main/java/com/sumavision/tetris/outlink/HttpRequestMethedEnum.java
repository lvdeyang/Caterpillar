package com.sumavision.tetris.outlink;



import org.apache.http.client.methods.*;

/**
 * @Description
 * @Author:wangzhen
 * @Param:
 * @Date:Create in 11:482018/6/26 0026
 * @Modified By:
 */
public enum HttpRequestMethedEnum {
    // HttpGet请求
    HttpGet {
        @Override
        public HttpRequestBase createRequest(String url) { return new HttpGet(url); }
    },
    // HttpPost 请求
    HttpPost {
        @Override
        public HttpRequestBase createRequest(String url) { return new HttpPost(url); }
    },
    // HttpPut 请求
    HttpPut {
        @Override
        public HttpRequestBase createRequest(String url) { return new HttpPut(url); }
    },
    // HttpDelete 请求
    HttpDelete {
        @Override
        public HttpRequestBase createRequest(String url) { return new HttpDelete(url); }
    };
    public HttpRequestBase createRequest(String url) { return null; }
    }
