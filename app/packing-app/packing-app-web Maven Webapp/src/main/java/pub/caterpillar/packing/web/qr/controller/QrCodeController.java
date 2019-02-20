package pub.caterpillar.packing.web.qr.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pub.caterpillar.commons.img.QrCodeUtils;
import pub.caterpillar.commons.img.QrCodeUtils.Generator;
import pub.caterpillar.commons.img.QrCodeUtils.QrConfig;
import pub.caterpillar.commons.util.date.DateUtil;
import pub.caterpillar.commons.util.path.PathUtil;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.packing.business.qr.dao.QrCodeDAO;
import pub.caterpillar.packing.business.qr.dto.QrCodeDTO;
import pub.caterpillar.packing.business.qr.po.QrCodePO;
import pub.caterpillar.packing.business.user.dao.UserDAO;
import pub.caterpillar.packing.business.user.po.UserPO;
import pub.caterpillar.packing.web.admin.vo.AdminVO;
import pub.caterpillar.packing.web.qr.exception.QrCodeNotFountException;
import pub.caterpillar.packing.web.qr.vo.QrCodeVO;
import pub.caterpillar.packing.web.tocken.exception.TokenErrorException;
import pub.caterpillar.packing.web.tocken.exception.TokenTimeoutException;
import pub.caterpillar.packing.web.user.vo.UserVO;

@Controller
@RequestMapping(value = "/qr/code")
public class QrCodeController{

	//二维码存储项目路径
	private final String QRPATH = "images/qr/business";
	
	//目录分隔符
	private final String SEPERATOR = "/";
	
	//二维码生成后缀
	private final String QRSUFFIX = ".png";
	
	@Autowired
	private QrCodeDAO conn_qr_code;
	
	@Autowired
	private UserDAO conn_user;
	
	@RequestMapping(value = "/list")
	public ModelAndView List(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = null;
		HttpSession session = null;
		
		//检查登录
		session = request.getSession();
		AdminVO admin = (AdminVO)session.getAttribute("admin");
		
		mv = new ModelAndView("front/packing/qrcode/qrcode");
		
		//页面初始化
		List<QrCodeDTO> qrcode_entitys = conn_qr_code.queryByAdmin(admin.getId());
		
		List<QrCodeVO> qrcodes = QrCodeVO.getConverter(QrCodeVO.class).convert(qrcode_entitys, QrCodeVO.class);
		
		mv.addObject("qrcodes", qrcodes);
		
		return mv;
	}
	
	@RequestMapping(value = "/info/{id}")
	public ModelAndView Info(@PathVariable Long id) throws Exception{
		
		ModelAndView mv = null;
		
		//检查登录
		
		mv = new ModelAndView("front/packing/qrcode/qrcode-info");
		
		//获取二维码
		QrCodePO qrcodeEntity = conn_qr_code.get(id);
		if(qrcodeEntity == null){
			throw new QrCodeNotFountException(id);
		}
		QrCodeVO qrcode = new QrCodeVO().set(qrcodeEntity);
		
		//获取注册用户
		List<UserPO> user_entitys = conn_user.findByField("identification", qrcodeEntity.getIdentification());
		if(user_entitys==null || user_entitys.size()<=0){
			qrcode.setRegisted("未注册");
		}else{
			qrcode.setRegisted("已注册");
			
			UserPO user_entity = user_entitys.get(0);
			UserVO user = new UserVO().set(user_entity);
			mv.addObject("user", user);
		}
		
		mv.addObject("qrcode", qrcode);
		return mv;
	}
	
	@RequestMapping(value = "/generate/{width}/{height}")
	public ModelAndView generate(
			@PathVariable int width,
			@PathVariable int height,
			int num,
			String token,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		ModelAndView mv = null;
		HttpSession session = null;
		
		//检查登录
		session = request.getSession();
		AdminVO admin = (AdminVO)session.getAttribute("admin");
		
		//检验token
		if(token == null){
			throw new TokenErrorException("forward:/qr/code/list");
		}else{
			token = token.toLowerCase();
			Object verObj = session.getAttribute("verCode");
			if(verObj == null){
				throw new TokenTimeoutException("forward:/qr/code/list");
			}
			String verCode = verObj.toString();
			if(!verCode.equals(token)){
				throw new TokenErrorException("forward:/qr/code/list");
			}
			//验证码移除
			session.removeAttribute("verCode");
		}
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		//数量控制
		mv = new ModelAndView("forward:/qr/code/list"); 
		if(num<=0 || num>1000){
			mv.addObject("message", "二维码一次生成不要超过1000个！");
			return mv;
		}
		
		//webRoot路径
		String webRootPath = PathUtil.getWebappPath();
		String projectName = PathUtil.getProjectName();
		
		//以时间作为文件夹
		String folder = DateUtil.format(new Date(), DateUtil.currentDatePattern);
		
		String folderPath = new StringBufferWrapper().append(webRootPath)
													 .append(SEPERATOR)
													 .append(projectName)
													 .append(SEPERATOR)
													 .append(QRPATH)
													 .append(SEPERATOR)
													 .append(admin.getUsername())
													 .append(SEPERATOR)
													 .append(folder)
													 .toString();
		File folderFile = new File(folderPath);
		if(!folderFile.exists()){
			folderFile.mkdirs();
		}
		
		List<QrCodePO> qrcodes = new ArrayList<QrCodePO>();
		
		//获取二维码生成器
		Generator generator = QrCodeUtils.getGenerator(QrCodeUtils.Generator.SIMPLE);
		
		//生成二维码
		for(int i=1; i<=num; i++){
			String identification = UUID.randomUUID().toString();
			String fileName = new StringBufferWrapper().append(identification).append(QRSUFFIX).toString();
			String content = new StringBufferWrapper().append(basePath).append("base/route/").append(identification).toString();
			QrConfig config = new QrCodeUtils.QrConfig().setContent(content)
														.setFileName(fileName)
														.setFolder(folderPath)
														.setWidth(width)
														.setHeight(height);
			generator.generate(config);
			
			//保存数据库
			QrCodePO qrcode = new QrCodePO();
			qrcode.setIdentification(identification);
			//这个地方存相对basePath的路径
			qrcode.setPath(new StringBufferWrapper().append(QRPATH).append(SEPERATOR).append(admin.getUsername()).append(SEPERATOR).append(folder).append(SEPERATOR).append(fileName).toString());
			qrcode.setContent(content);
			qrcode.setAdminId(admin.getId());
			qrcodes.add(qrcode);
		}
		
		conn_qr_code.saveAll(qrcodes);
		
		mv.addObject("success", "操作成功！");
		return mv;
	}
	
}
