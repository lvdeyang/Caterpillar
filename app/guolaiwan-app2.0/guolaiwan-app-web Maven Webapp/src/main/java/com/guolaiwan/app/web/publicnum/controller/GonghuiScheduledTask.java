package com.guolaiwan.app.web.publicnum.controller;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlProcessor.ResolutionMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.gonghui.dao.VideoDao;
import com.guolaiwan.bussiness.gonghui.po.VideoPo;
import com.guolaiwan.bussiness.merchant.dao.CameraFaceDAO;
import com.guolaiwan.bussiness.merchant.dao.CarMessageDAO;
import com.guolaiwan.bussiness.merchant.po.CameraFacePO;
import com.guolaiwan.bussiness.merchant.po.CarMessagePO;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.commons.util.date.DateUtil;

@Component
@EnableScheduling
public class GonghuiScheduledTask {
	@Autowired
	SysConfigDAO conn_sys;
	@Autowired
	VideoDao conn_video;
	/**
	 * 定时迁移文件到oss
	 * @throws ParseException 
	 * @throws JSONException 
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void transVideos() throws JSONException, ParseException {
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		List<VideoPo> videoList=conn_video.findByField("tooss", 0);
		if(videoList!=null&&videoList.size()>0){
			VideoPo videoPo=videoList.get(0);
			synchronized(videoPo){
				try{
					File file=new File(sysConfigPO.getFolderUrl()+videoPo.getPlayUrl());
					File newImageFile=new File(sysConfigPO.getFolderUrl()+videoPo.getCoverUrl());
					OSSUtils.createFolder("glw-old-file", "file/gonghui/");
					OSSUtils.uploadObjectOSS("file/gonghui/", file.getName(), file, new FileInputStream(file));
					OSSUtils.uploadObjectOSS("file/gonghui/", newImageFile.getName(), newImageFile, new FileInputStream(newImageFile));
				    videoPo.setTooss(1);
				    conn_video.save(videoPo);
				    file.delete();
				    newImageFile.delete();
				}catch(Exception e){
					
				}
				
			}
		}
			
		
	}
	
}
