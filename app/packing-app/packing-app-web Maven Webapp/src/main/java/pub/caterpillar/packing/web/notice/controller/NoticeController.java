package pub.caterpillar.packing.web.notice.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import com.yunpian.sdk.model.VoiceSend;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.packing.business.admin.dao.AdminDAO;
import pub.caterpillar.packing.business.admin.po.AdminPO;
import pub.caterpillar.packing.business.qr.dao.QrCodeDAO;
import pub.caterpillar.packing.business.qr.po.QrCodePO;
import pub.caterpillar.packing.business.user.dao.UserDAO;
import pub.caterpillar.packing.business.user.po.UserPO;
import pub.caterpillar.packing.web.cloud.yunpian.Yunpian;
import pub.caterpillar.packing.web.notice.exception.IdentificationUnregistedException;
import pub.caterpillar.packing.web.notice.exception.LicenseUnregistedException;
import pub.caterpillar.packing.web.packing.exception.EntranceInvalidException;
import pub.caterpillar.packing.web.packing.exception.IdentificationInvalidException;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController{
	private static final Logger LOG = LoggerFactory.getLogger(NoticeController.class);
	
	//短信通知模板id
	private static final String SMS_TPL_ID = "2081144";
	
	//语音通知模板id
	private static final String VOICE_TPL_ID = "2081152";
	
	@Autowired
	private QrCodeDAO conn_qr_code;
	
	@Autowired
	private UserDAO conn_user;
	
	@Autowired
	private AdminDAO conn_admin;
	
	@RequestMapping(value = "/index/{entrance}")
	public ModelAndView index(@PathVariable String entrance) throws Exception{
		
		ModelAndView mv = null;
		
		AdminPO admin = conn_admin.get(entrance);
		
		if(admin == null){
			throw new EntranceInvalidException(entrance);
		}
		
		mv = new ModelAndView("front/packing/notice/license_notice");
		
		mv.addObject("entrance", entrance);
		
		return mv;
	}
	
	//按照绑定id精准推送
	@RequestMapping(value = "/do/notice/{identification}")
	public ModelAndView doNotice(
			@PathVariable String identification,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		//检验token是否有效
		
		//2分钟内是否已经通知车主
		
		//检验绑定id是否有效
		List<QrCodePO> codeList = conn_qr_code.findByField("identification", identification);
		
		if(codeList==null || codeList.size()<=0){
			throw new IdentificationInvalidException(identification);
		}
		
		//检验绑定id是否注册
		List<UserPO> users = conn_user.findByField("identification", identification);
		
		if(users==null || users.size()<=0){
			throw new IdentificationUnregistedException(identification);
		}
		
		//通知车主
		UserPO user = users.get(0);
		
		callUser(user);
		
		mv = new ModelAndView("success/success");
		mv.addObject("message", "已经成功通知车主，2分钟内到位");
		
		return mv;
	}
	
	//按照车牌号码推送
	@RequestMapping(value = "/do/license/notice")
	public ModelAndView doLicenseNotice(
			String region,
			String license,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		
		//检验token是否有效
		
		//2分钟内是否已经通知车主
		
		//参数校验
		
		//忽略参数大小写
		if(region!=null && !"".equals(region)) region = region.toUpperCase();
		if(license!=null && !"".equals(license)) license = license.toUpperCase();
		
		//车牌号
		if(region!=null && !"".equals(region)){
			license = new StringBufferWrapper().append(region).append(license).toString();
		}
		
		//检验车牌号是否有效
		List<UserPO> users = conn_user.queryByLicense(license);
		
		if(users==null || users.size()<=0){
			throw new LicenseUnregistedException(license);
		}
		
		//通知车主
		UserPO user = users.get(0);
		
		callUser(user);
		
		mv = new ModelAndView("success/success");
		mv.addObject("message", "已经成功通知车主，2分钟内到位");
		
		return mv;
	}
	
	//通知车主
	private void callUser(UserPO user){
		
		//车牌号
		String license = new StringBufferWrapper().append(user.getRegion()).append(user.getLicense()).toString();
		
		YunpianClient client = Yunpian.getClient();
		synchronized(client){
			
			//短信通知
			Map<String, String> param = client.newParam(5);
	        param.put(Yunpian.MOBILE, user.getMobile());
	        param.put(Yunpian.TPL_ID, SMS_TPL_ID);
	        param.put(Yunpian.TPL_VALUE, new StringBufferWrapper().append("#license#=").append(license).toString());
	        Result<SmsSingleSend> r0 = client.sms().tpl_single_send(param);
	        LOG.info(r0.toString());
	        
	        //语音通知
	        param = client.newParam(4);
	        param.put(Yunpian.MOBILE, user.getMobile());
	        param.put(Yunpian.TPL_ID, VOICE_TPL_ID);
	        param.put(Yunpian.TPL_VALUE, new StringBufferWrapper().append("license=").append(license).toString());
	        Result<VoiceSend> r1 = client.voice().tpl_notify(param);
	        LOG.info(r1.toString());
		}
	}
}
