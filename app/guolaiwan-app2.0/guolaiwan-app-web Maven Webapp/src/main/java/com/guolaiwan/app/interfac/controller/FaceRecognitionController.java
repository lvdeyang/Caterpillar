package com.guolaiwan.app.interfac.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.interfac.util.FaceUtil;
import com.guolaiwan.app.web.business.controller.PictureConvertor;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.MessageDAO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.MessagePO;

@Controller
@RequestMapping("/face")
public class FaceRecognitionController {

	@Autowired
	private MessageDAO messagedao;

	@Autowired
	private MerchantDAO conn_merchant;

	// 保存身份证信息
	@ResponseBody
	@RequestMapping(value = "/recognition ", method = RequestMethod.GET)
	public ModelAndView discern(String merchantid) {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/pubnum/FaceRecognition");
		mv.addObject("merchantid", merchantid);
		return mv;
	}

	// 人脸识别调用阿里接口对比
	// 放回值，1代表识别到，0没找到，2出现异常
	@ResponseBody
	@RequestMapping(value = "/facerecognition", method = RequestMethod.POST)
	public Map<String, String> facerecognition(String merchantid, String localData) {
		List<MessagePO> getmerchantid = messagedao.getmerchantid(merchantid);
		try {

			for (int i = 0; i < getmerchantid.size(); i++) {
				String sendPost = FaceUtil.sendPost(localData, PictureConvertor.getImg(getmerchantid.get(i).getFacePicWebUrl()));
				sendPost = "[" + sendPost + "]";
				List<Map> list = JSONObject.parseArray(sendPost, Map.class);
				String number = list.get(0).get("confidence").toString();
				String[] split = number.split("\\.");
				int a = Integer.parseInt(split[0]);
				if (a > 80) {
					MerchantPO merchantPO = conn_merchant.get(Long.parseLong(merchantid));
					HashMap<String, String> hashMap = new HashMap<String, String>();

					hashMap.put("id", getmerchantid.get(i).getId().toString());
					hashMap.put("msg", "1");
					hashMap.put("orderId", getmerchantid.get(i).getOderId());
					hashMap.put("merchantid", merchantid);
					hashMap.put("userid", merchantPO.getUser().getId() + "");
					return hashMap;
				}
			}
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("msg", "0");
			return hashMap;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			HashMap<String, String> hashMap = new HashMap<String, String>();
			hashMap.put("msg", "2");
			return hashMap;
		}
	}

}
