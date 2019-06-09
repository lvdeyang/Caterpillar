package com.guolaiwan.app.web.admin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.Utils.LetvDownUrl;

import pub.caterpillar.mvc.controller.BaseController;

//乐视云导出界面

@Controller
@RequestMapping("/admin/letv")
public class LetvPageDownController extends BaseController {

	/**
	 * 乐视云视频id 得出地址
	 * 
	 * @throws Exception
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/letv.do", method = RequestMethod.POST)
	public String getLetvId(String letvId) throws Exception {
		LetvDownUrl ldu = new LetvDownUrl();
		// 判断页面数据是否有值
		if (letvId.length() > 0 && letvId != null) {
			// 要验证的正则表达式
			String regEx = "^[0-9]{8}$";
			// 编译正则表达式
			Pattern pattern = Pattern.compile(regEx);
			// 进行正则校检
			if (pattern.matcher(letvId).matches()) {
				// 符合要求传参
				ldu.letvDownUrl(letvId);
				// 返回数据进行判断
				if (ldu.getLetvUrl().length() > 0 && ldu.getLetvUrl() != null) {
					String str = ldu.getLetvUrl();
					try {
						JSONObject jsonObject = JSONObject.parseObject(str);
						JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.getString("data"));
						String str2 = jsonObject2.getString("download_url");
						return str2;
					} catch (Exception e) {
						return "2";
					}
				} else {
					return "2";
				}
			} else {
				return "1";
			}
		} else {
			return "0";
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		ModelAndView mv = new ModelAndView("admin/letv/letv", strMap);
		return mv;
	}
}
