package pub.caterpillar.packing.web.cloud.yunpian.token.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import pub.caterpillar.commons.img.VerifyCodeUtils;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.packing.web.cloud.yunpian.Yunpian;

@Controller
@RequestMapping(value = "/token")
public class TokenController{

	private static final Logger LOG = LoggerFactory.getLogger(TokenController.class);
	
	//验证码短信通知模板id
	private static final String SMS_TPL_ID = "2081140";
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/generate")
	public Object generate(
			String mobile,
			HttpServletRequest request){
		
		//手机号校验
		
		//推送短信
		YunpianClient client = Yunpian.getClient();
		
		//加一个client锁
		synchronized (client) {
			//生成验证码
			String verCode = VerifyCodeUtils.generateVerifyCode(4, VerifyCodeUtils.NUMBER_CODES);
			HttpSession session = request.getSession(true);
			
	        //删除以前的
	        session.removeAttribute("verCode");
	        session.setAttribute("verCode", verCode.toLowerCase()); 
			
			Map<String, String> param = client.newParam(5);
	        param.put(Yunpian.MOBILE, mobile);
	        param.put(Yunpian.TPL_ID, SMS_TPL_ID);
	        param.put(Yunpian.TPL_VALUE, new StringBufferWrapper().append("#code#=").append(verCode).toString());
	        Result<SmsSingleSend> r = client.sms().tpl_single_send(param);
	        LOG.info(r.toString());
		}
		
		return null;
	}
	
}
