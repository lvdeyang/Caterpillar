package com.sumavision.tetris.capacity.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.capacity.management.authorization.AuthorizationBO;
import com.sumavision.tetris.capacity.management.authorization.AuthorizationInfoBO;
import com.sumavision.tetris.capacity.service.CapacityService;
import com.sumavision.tetris.commons.exception.BaseException;
import com.sumavision.tetris.commons.exception.code.StatusCode;

@Service
@Transactional(rollbackFor = Exception.class)
public class CapacityManagementService {
	
	@Autowired
	private CapacityManagementDAO capacityManagementDao;
	
	@Autowired
	private CapacityService capacityService;

	public CapacityManagementPO addCapacity(String ip, String type) throws Exception{
		
		CapacityManagementPO exsitCapacity = capacityManagementDao.findByIp(ip);
		if(exsitCapacity != null){
			throw new BaseException(StatusCode.FORBIDDEN, "ip为：" + ip + "的能力已存在！");
		}
		
		CapacityManagementPO capacity = new CapacityManagementPO();
		capacity.setIp(ip);
		capacity.setType(CapacityType.fromName(type));
		
		//查询授权信息并持久化,TODO:先做了封装和转码的audio,没做转码video
		JSONObject authorizationJsonObject = capacityService.getAuthorizationAddMsgId(ip, capacity.getPort());
		String authorizationInfo = JSON.toJSONString(authorizationJsonObject);
		
		AuthorizationInfoBO authorizationInfoBO = JSONObject.parseObject(authorizationInfo, AuthorizationInfoBO.class);
		
		//封装
		if(type.equals(CapacityType.ENCAPSULATE.getName())){
			
			
			
		//转码
		}else if(type.equals(CapacityType.TRANSCODE.getName())){
			
			
			
		}
		
		capacityManagementDao.save(capacity);
		
		return capacity;
		
	}
	
}
