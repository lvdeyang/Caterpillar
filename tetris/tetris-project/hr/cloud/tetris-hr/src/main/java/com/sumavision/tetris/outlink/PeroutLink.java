package com.sumavision.tetris.outlink;

import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Component
public class PeroutLink {
	
	 /**
     * 删除员工权限
     * @return
     */
    public String delAuth(int deviceId,int workerID){
        JSONObject params=new JSONObject();
        params.put("deviceId",deviceId);
        params.put("workerId",workerID);
        String response= HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,myUrl.HOST+myUrl.DELAUTH,params.toJSONString(),null);
        return response;
    }
    /**
     * 批量删除员工权限
     * @return
     */
    /*public String delAllAuth(List<WorkerPo> authBeans){
        JSONArray params=new JSONArray();
        for (WorkerPo auth:authBeans){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("deviceId",auth.getDeviceId());
            jsonObject.put("workerId",auth.getWorkerId());
            params.add(jsonObject);
        }
        String response= HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,myUrl.HOST+myUrl.DELALLAUTH,params.toJSONString(),null);
        return response;
    }*/
    /**
     * 添加员工权限
     * @return
     */
    public String addAuth(int deviceId,int workerID){
        JSONObject params=new JSONObject();
        params.put("deviceId",deviceId);
        params.put("workerId",workerID);
        String response= HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,myUrl.HOST+myUrl.ADDAUTH,params.toJSONString(),null);
        return response;
    }

    /**
     * 批量添加员工权限
     * @return
     */
    /*public String addAllAuth(List<WorkerPo> authBeans){
        JSONArray params=new JSONArray();
        for(WorkerPo auth:authBeans){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("deviceId",auth.getDeviceId());
            jsonObject.put("workerId",auth.getWorkerId());
            params.add(jsonObject);
        }
        String response= HttpClientUtil.sendHttp(HttpRequestMethedEnum.HttpPost,myUrl.HOST+myUrl.ADDALLAUTH,params.toJSONString(),null);
        return response;
    }*/

}
