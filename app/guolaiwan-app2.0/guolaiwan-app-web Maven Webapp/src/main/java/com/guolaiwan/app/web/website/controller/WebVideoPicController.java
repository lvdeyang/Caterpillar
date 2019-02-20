package com.guolaiwan.app.web.website.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.admin.vo.VPCommentVO;
import com.guolaiwan.app.web.admin.vo.VideoPicVO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.dao.VPCommentDAO;
import com.guolaiwan.bussiness.admin.dao.VPRelDAO;
import com.guolaiwan.bussiness.admin.dao.VideoPicDAO;
import com.guolaiwan.bussiness.admin.enumeration.VideoPicType;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.admin.po.VPCommentPO;
import com.guolaiwan.bussiness.admin.po.VPRelPO;
import com.guolaiwan.bussiness.admin.po.VideoPicPO;

@Controller
@RequestMapping("/web/videoPic")
public class WebVideoPicController extends WebBaseControll{
	@Autowired
	private VideoPicDAO conn_videoPic;

	@Autowired
	private VPRelDAO conn_vPRel;

	@Autowired
	private VPCommentDAO conn_vPComment;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private UserInfoDAO conn_user;



	//首页展示
	@ResponseBody
	@RequestMapping(value = "/list", method= RequestMethod.GET)
	public ModelAndView list(
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,Object> strMap =new HashMap<String, Object>();
		int counts = conn_videoPic.countAll();
		int pages = counts%10==0?counts/10:counts/10+1;
		strMap.put("pages", pages);
		ModelAndView mv = new ModelAndView("web/videoPic/list",strMap);
		return mv;		
	}

	//图文小视频分页
	@ResponseBody
	@RequestMapping(value = "/listPage", method= RequestMethod.GET)
	public Map<String, Object> listPage(
			HttpServletRequest request,
			HttpServletResponse response,
			Integer page,
			Integer pageSize,
			String vType,
			String sName,
			String userId) throws Exception {
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		//登录用户
		HttpSession session = request.getSession();
		UserInfoPO seuser = (UserInfoPO)session.getAttribute("userInfo");
		Long userIds=null;
		if(userId!=null&&userId.length()>0){
			userIds=Long.parseLong(userId);
		}
		//判断类型
		VideoPicType t=null;
		if(vType!=null && vType.length()>0){
			t=VideoPicType.fromString(vType);
		}
		//小视频图文
		Map<String,Object> strMap =new HashMap<String, Object>();
		List<VideoPicPO> videoPics = conn_videoPic.findAllDesc(page, pageSize,t,sName,userIds);

		List<VideoPicVO> _videoPics = new ArrayList<VideoPicVO>();
		for (VideoPicPO videoPic : videoPics) {
			VideoPicVO _videoPic = new VideoPicVO().set(videoPic);
			//路径
			_videoPic.setfUrl(split(_videoPic.getfUrl(), sysConfig.getWebUrl()));

			//点赞数
			int countPraise = conn_vPRel.countPraise(videoPic);
			_videoPic.setPraiseCount(countPraise);

			//评论数
			int countComment=0;
			List<VPCommentPO> vpComments = videoPic.getVPComments();
			if(vpComments!=null){
				countComment=vpComments.size();
			}
			_videoPic.setCommentCount(countComment);

			//用户
			UserInfoPO user = conn_user.get(videoPic.getUserId());
			UserInfoVO _user = new UserInfoVO().set(user);
			String userHeadimg = _user.getUserHeadimg();
			_user.setUserHeadimg(splitSimple(userHeadimg, sysConfig.getWebUrl()));
			_videoPic.setUser(_user);

			//是否可以删除
			if(seuser!=null&&seuser.getId()==user.getId()){
				_videoPic.setIsDelete(1);
			}

			//是否已经点赞
			if(seuser!=null){
				VPRelPO praise = conn_vPRel.getPraiseByVU(videoPic,seuser.getId());
				if(praise!=null){
					_videoPic.setIsPraise(1);
				}
			}
			_videoPics.add(_videoPic);
		}
		strMap.put("videoPics", _videoPics);

		return strMap;		
	}


	//上传
	@ResponseBody
	@RequestMapping(value = "/upload.do", method= RequestMethod.POST)
	public Map<String, Object> upload(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		String folderUrl = conn_sysConfig.getSysConfig().getFolderUrl();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date d = new Date();
		String dateStr = df.format(d);
		//保存路径
		String path = "cache"+File.separator+"videoPic"+File.separator+dateStr+File.separator;

		String path1 =folderUrl+path;//文件路径
		//文件名
		String fileName = file.getOriginalFilename();
		String newName = d.getTime()+fileName.substring(fileName.lastIndexOf("."));

		//文件
		File folder = new File(path1);
		if(folder.exists()==false){
			if(folder.getParentFile().exists()==false){
				System.out.println("系统找不到路径!"+folder.getParentFile());
				strMap.put("msg","error");
				return strMap;
			}
			folder.mkdir();//没有就新建
		}
		//上传文件
		File newFile = new File(path1+newName);
		file.transferTo(newFile);

		strMap.put("msg",path+newName);
		strMap.put("code","1");
		return strMap;
	}

	//移动文件
	@ResponseBody
	@RequestMapping(value = "/videoPic.do", method= RequestMethod.POST)
	public Map<String, Object> videoPic(
			HttpServletRequest request,
			HttpServletResponse response,
			String fUrl,
			String type,
			String context) throws Exception {
		String folderUrl = conn_sysConfig.getSysConfig().getFolderUrl();
		HttpSession session = request.getSession();
		UserInfoPO user = (UserInfoPO)session.getAttribute("userInfo");
		if(user==null){
			return FORBIDDEN("未获取到用户！");
		}
		if(fUrl!=null&&fUrl.length()>0){
			String[] fUrlsz = fUrl.split(",");
			for (int i = 0; i < fUrlsz.length; i++) {
				if(fUrlsz[i].indexOf("http://")!=-1){
				}else{
					String folder = folderUrl+fUrlsz[i].substring(0, fUrlsz[i].lastIndexOf(File.separator)).replace("cache"+File.separator, "");
					RemoveFile(folderUrl+fUrlsz[i],folder);
					fUrlsz[i]= "/"+fUrlsz[i].replace("cache"+File.separator, "").replace("\\", "/");
				}
			} 
			fUrl = StringUtils.join(fUrlsz, ",");
		}
		VideoPicPO videoPic = new VideoPicPO(); 
		videoPic.setUserId(user.getId());
		videoPic.setUpdateTime(new Date());
		videoPic.setContent(context);
		videoPic.setType(VideoPicType.fromString(type));
		videoPic.setfUrl(fUrl);
		conn_videoPic.save(videoPic);
		return success();
	}


	//查看全部
	@ResponseBody
	@RequestMapping(value = "/vpInfo/{vpId}", method= RequestMethod.GET)
	public ModelAndView vpInfo(
			HttpServletRequest request,
			HttpServletResponse response,
			@PathVariable(value="vpId") Long vpId) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		SysConfigPO sysConfig = conn_sysConfig.getSysConfig();

		HttpSession session = request.getSession();
		UserInfoPO seuser = (UserInfoPO)session.getAttribute("userInfo");
		
		VideoPicPO videoPic = conn_videoPic.get(vpId);
		VideoPicVO _videoPic = new VideoPicVO().set(videoPic);

		UserInfoPO vpuser = conn_user.get(videoPic.getUserId());
		UserInfoVO _vpuser = new UserInfoVO().set(vpuser);

		String userHeadimg = _vpuser.getUserHeadimg();
		_vpuser.setUserHeadimg(splitSimple(userHeadimg, sysConfig.getWebUrl()));

		_videoPic.setfUrl(split(_videoPic.getfUrl(), sysConfig.getWebUrl()));//路径
		_videoPic.setUser(_vpuser);   //用户
		
		//是否点赞
		if(seuser!=null){
			VPRelPO praise = conn_vPRel.getPraiseByVU(videoPic,seuser.getId());
			if(praise!=null){
				_videoPic.setIsPraise(1);
			}
		}
		
		//点赞数
		int countPraise = conn_vPRel.countPraise(videoPic);
		_videoPic.setPraiseCount(countPraise);

		//获取评论
		List<VPCommentPO> vpComments = videoPic.getVPComments();


		//评论数
		int countComment=0;
		int pages=0;
		if(vpComments!=null){
			countComment=vpComments.size();
			pages=countComment%10==0?countComment/10:countComment/10+1;
		}
		_videoPic.setCommentCount(countComment);


		//图文小视频,评论
		strMap.put("videoPic",_videoPic);
		strMap.put("pages",pages);
		ModelAndView mv = new ModelAndView("web/videoPic/vpInfo",strMap);
		return mv;
	}




	//评论分页
	@ResponseBody
	@RequestMapping(value = "/pageCommmets", method= RequestMethod.GET)
	public Map<String, Object> pageCommmets(Long vpId,Integer page,Integer pageSize) throws Exception {
		VideoPicPO videoPicPO = conn_videoPic.get(vpId);
		if(videoPicPO==null) return FORBIDDEN("没获取到图文小视频");
		String webUrl = conn_sysConfig.getSysConfig().getWebUrl();
		List<VPCommentPO> comments = conn_videoPic.findCommentsPage(videoPicPO, page, pageSize);
		Map<String,Object> strMap =new HashMap<String, Object>();

		List<VPCommentVO> _vpComments = new ArrayList<VPCommentVO>();
		for (VPCommentPO vpCommentPO : comments) {
			UserInfoPO user = conn_user.get(vpCommentPO.getUserId());
			UserInfoVO _user = new UserInfoVO().set(user);
			String userHeadimg = _user.getUserHeadimg();
			_user.setUserHeadimg(splitSimple(userHeadimg, webUrl));

			VPCommentVO _vpComment = new VPCommentVO().set(vpCommentPO);
			_vpComment.setUser(_user);//评论人;

			_vpComments.add(_vpComment);
		}
		strMap.put("vpComments", _vpComments);
		return strMap;
	}


	//评论
	@ResponseBody
	@RequestMapping(value = "/comment.do", method= RequestMethod.POST)
	public Map<String, Object> VpComment(
			Long vpId,
			String commentText,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		UserInfoPO user = (UserInfoPO)session.getAttribute("userInfo");
		if(user==null){
			return FORBIDDEN("未获取到用户！");
		}
		if(commentText==null||commentText=="") return ERROR("不能发送空评论");
		VideoPicPO videoPic = conn_videoPic.get(vpId);

		//评论
		VPCommentPO vpCommentPO = new VPCommentPO();
		vpCommentPO.setUpdateTime(new Date());
		vpCommentPO.setUserId(user.getId());
		vpCommentPO.setCommentText(commentText);

		//关联
		videoPic.getVPComments().add(vpCommentPO);
		vpCommentPO.setVideoPic(videoPic);

		conn_videoPic.save(videoPic);
		conn_vPComment.save(vpCommentPO);
		return success();
	}



	//删除
	@ResponseBody
	@RequestMapping(value = "/delComment.do", method= RequestMethod.POST)
	public Map<String, Object> delComment(
			HttpServletRequest request,
			Long vpId) throws Exception {
		HttpSession session = request.getSession();
		UserInfoPO user = (UserInfoPO)session.getAttribute("userInfo");
		if(user==null){
			return FORBIDDEN("未获取到用户！");
		}

		VideoPicPO videoPic = conn_videoPic.get(vpId);
		if(videoPic==null){
			return FORBIDDEN("未获取到图文,小视频！");
		}
		conn_videoPic.delete(videoPic);
		return success();
	}

	//点赞,取消点赞
	@ResponseBody
	@RequestMapping(value = "/praise.do", method= RequestMethod.POST)
	public Map<String, Object> praise(
			HttpServletRequest request,
			Long vpId) throws Exception {
		Map<String, Object> strMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		Date date = new Date();
		UserInfoPO user = (UserInfoPO)session.getAttribute("userInfo");
		if(user==null){
			return FORBIDDEN("未获取到用户！");
		}
		VideoPicPO videoPic = conn_videoPic.get(vpId);
		VPRelPO praise = conn_vPRel.getPraiseByVpU(videoPic, user.getId());
		if(praise==null){
			VPRelPO vpRel = new VPRelPO();
			vpRel.setUpdateTime(date);
			vpRel.setPraiseTime(date);
			vpRel.setPraise(1);
			vpRel.setUserId(user.getId());
			vpRel.setVideoPic(videoPic);

			videoPic.getVPRels().add(vpRel);
			conn_videoPic.save(videoPic);
			conn_vPRel.save(vpRel);
			strMap.put("msg", "success");
			strMap.put("code", "1");
			return strMap;
		}else if(praise.getPraise()==0){
			praise.setPraiseTime(date);
			praise.setPraise(1);
			strMap.put("msg", "success");
			strMap.put("code", "1");
			return strMap;
		}else if(praise.getPraise()==1){
			praise.setPraiseTime(date);
			praise.setPraise(0);
			strMap.put("msg", "success");
			strMap.put("code", "0");
			return strMap;
		}else{
			strMap.put("msg", "error");
			return strMap;
		}
	}
}
