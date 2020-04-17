package com.sumavision.tetris.show;

import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.util.http.HttpUtil;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 JSONObject jsonParam=new JSONObject();
         jsonParam.put("liveId", 1);
	     jsonParam.put("url", "http://47.95.241.89:6690/123/stream.m3u8");
	     HttpUtil.httpPost("http://192.165.56.64:8080/guolaiwan-app-web/phoneApp/addrecord", jsonParam);
	}

}
