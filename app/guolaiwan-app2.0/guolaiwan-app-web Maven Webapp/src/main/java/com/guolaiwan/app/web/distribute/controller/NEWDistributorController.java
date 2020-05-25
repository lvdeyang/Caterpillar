package com.guolaiwan.app.web.distribute.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.admin.vo.BalanceVO;
import com.guolaiwan.app.web.admin.vo.OrderInfoVO;
import com.guolaiwan.app.web.admin.vo.ProductVO;
import com.guolaiwan.app.web.admin.vo.UserInfoVO;
import com.guolaiwan.app.web.distribute.vo.DistributeOrderVo;
import com.guolaiwan.app.web.distribute.vo.DistributePolicyVo;
import com.guolaiwan.app.web.distribute.vo.DistributeProductVo;
import com.guolaiwan.app.web.weixin.WxConfig;
import com.guolaiwan.app.web.weixin.YuebaWxPayConstants;
import com.guolaiwan.app.web.weixin.YuebaWxUtil;
import com.guolaiwan.bussiness.Parking.po.OrderPO;
import com.guolaiwan.bussiness.Parking.po.VehiclePO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.MerchantDAO;
import com.guolaiwan.bussiness.admin.dao.OrderInfoDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.dao.UserInfoDAO;
import com.guolaiwan.bussiness.admin.enumeration.OrderSource;
import com.guolaiwan.bussiness.admin.enumeration.ShopAuditStateType;
import com.guolaiwan.bussiness.admin.po.CompanyPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.OrderInfoPO;
import com.guolaiwan.bussiness.admin.po.ProductPO;
import com.guolaiwan.bussiness.admin.po.SysConfigPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;
import com.guolaiwan.bussiness.distribute.classify.DistributorApplyStatus;
import com.guolaiwan.bussiness.distribute.classify.DistributorOrderStatus;
import com.guolaiwan.bussiness.distribute.classify.RecommendType;
import com.guolaiwan.bussiness.distribute.dao.DistributePolicyDao;
import com.guolaiwan.bussiness.distribute.dao.DistributeProductDao;
import com.guolaiwan.bussiness.distribute.dao.DistributorDao;
import com.guolaiwan.bussiness.distribute.dao.DistributorOrderDao;
import com.guolaiwan.bussiness.distribute.dao.OffOrderDao;
import com.guolaiwan.bussiness.distribute.dao.RegionDao;
import com.guolaiwan.bussiness.distribute.po.DistributePolicy;
import com.guolaiwan.bussiness.distribute.po.DistributeProduct;
import com.guolaiwan.bussiness.distribute.po.DistributorOrder;
import com.guolaiwan.bussiness.distribute.po.DistributorPo;

import com.guolaiwan.bussiness.distribute.po.OffOrder;
import com.guolaiwan.bussiness.distribute.po.RegionPo;
import com.guolaiwan.bussiness.website.dao.AddressDAO;
import com.sun.jna.platform.win32.Netapi32Util.UserInfo;

import pub.caterpillar.commons.exception.BaseException;
import pub.caterpillar.commons.exception.code.enumeration.StatusCode;
import pub.caterpillar.commons.file.oss.OSSUtils;
import pub.caterpillar.commons.util.binary.ByteUtil;
import pub.caterpillar.mvc.ext.response.json.aop.annotation.JsonBody;
import pub.caterpillar.mvc.util.HttpServletRequestParser;
import pub.caterpillar.weixin.constants.WXContants;


@Controller
@RequestMapping(value = "/distributor")
public class NEWDistributorController {

	@Autowired
	private DistributorDao conn_distributor;
	@Autowired
	private RegionDao conn_region;
	@RequestMapping(value = "/distribute/index/{region}")
	public ModelAndView distributeIndex(
			HttpServletRequest request,@PathVariable long region) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/distribute");
		HttpSession session=request.getSession();

		if(session.getAttribute("distributorId")==null){

			mv = new ModelAndView("redirect:/distributor/app/login/0");
			return mv;
		}else{
			DistributorPo distributorPo=conn_distributor.get(Long.parseLong(session.getAttribute("distributorId")+""));

			if(!distributorPo.getStatus().equals(DistributorApplyStatus.PASSED)){
				mv = new ModelAndView("redirect:/distributor/app/login/0");
				return mv;
			}
		}
		if(region==0l){
			mv.addObject("region",session.getAttribute("region"));
		}else{
			mv.addObject("region",region);
		}

		return mv;
	}
	@RequestMapping(value = "/apply/index")
	public ModelAndView applyIndex(
			HttpServletRequest request,Long disId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/apply-distributor");
		SysConfigPO sys=conn_sys.getSysConfig();
		if(disId!=0){
			DistributorPo distributorPo=conn_distributor.get(disId);
			mv.addObject("distributor",distributorPo);
			mv.addObject("region",0);
		}else{
			DistributorPo distributorPo=new DistributorPo();
			distributorPo.setId(0l);
			mv.addObject("distributor",distributorPo);
			mv.addObject("region",0);
		}
		mv.addObject("weburl",sys.getWebUrl());

		return mv;
	}

	@RequestMapping(value = "/delete")
	public ModelAndView delete(
			HttpServletRequest request,Long disId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/apply-distributor");

		conn_distributor.delete(disId);

		SysConfigPO sys=conn_sys.getSysConfig();
		DistributorPo distributorPo=new DistributorPo();
		distributorPo.setId(0l);
		HttpSession session = request.getSession();
		session.removeAttribute("distributorId");
		mv.addObject("distributor",distributorPo);
		mv.addObject("region",0);
		mv.addObject("city",0);
		mv.addObject("country",0);
		mv.addObject("weburl",sys.getWebUrl());

		return mv;
	}

    
	@Autowired
	CompanyDAO conn_com;
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/com/list", method = RequestMethod.GET)
	public Object getAllCom(HttpServletRequest request) throws Exception{

		return conn_com.findAll();
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/region/first", method = RequestMethod.GET)
	public Object getFirstRegion(HttpServletRequest request) throws Exception{

		return conn_region.queryAllFirstRegion();
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/region/parent/{parentId}", method = RequestMethod.GET)
	public Object getRegionByParent(HttpServletRequest request,@PathVariable long parentId) throws Exception{

		return conn_region.queryRegionByParent(parentId);
	}


	//,@RequestParam("license") CommonsMultipartFile license,@RequestParam("contract") CommonsMultipartFile contract
	@RequestMapping(value = "/apply")
	public ModelAndView apply(
			HttpServletRequest request,Long id) throws Exception{
		ModelAndView mv = null;
		String legalPerson=request.getParameter("legalPerson");
		String bankNo=request.getParameter("bankNo");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String licenseUrl=request.getParameter("licenseUrl");
		String contractUrl=request.getParameter("contractUrl");
		String contractPicUrl=request.getParameter("contractPicUrl");
		String region=request.getParameter("region");
		String city=request.getParameter("city");
		String contry=request.getParameter("contry");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		Long userId=Long.parseLong(session.getAttribute("userId").toString());
		DistributorPo distributorPo=new DistributorPo();
		if(id.longValue()==0){

		}else{
			distributorPo=conn_distributor.get(id);
		}
		distributorPo.setAddress(address);
		distributorPo.setPassword(password);
		distributorPo.setBankNo(bankNo);
		distributorPo.setContractUrl(contractUrl);
		distributorPo.setLegalPerson(legalPerson);
		distributorPo.setLicenseUrl(licenseUrl);
		distributorPo.setPhone(phone);
		distributorPo.setStatus(DistributorApplyStatus.CHECKED);
		distributorPo.setUserId(userId);
		distributorPo.setContractPicUrl(contractPicUrl);
		distributorPo.setRegion(Long.parseLong(region));
		conn_distributor.save(distributorPo);
		mv = new ModelAndView("redirect:/distributor/app/login/0");
		return mv;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public Object upload(HttpServletRequest request) throws Exception{

		String url="";

		if(!ServletFileUpload.isMultipartContent(request)){

		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置缓冲区大小，这里是4kb
		factory.setSizeThreshold(40960); 
		//设置缓冲区目录
		factory.setRepository(null);
		ServletFileUpload upload = new ServletFileUpload(factory);
		//设置最大文件尺寸，这里是80MB
		upload.setSizeMax(81943040); 
		//得到所有的文件
		List<FileItem> items = upload.parseRequest(request);
		Iterator<FileItem> i = items.iterator();
		File fileIt=null;
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			url=uploadFile(fi);
		}
		
		
		SysConfigPO sys=conn_sys.getSysConfig();
		String webPath=sys.getWebUrl()+"/"+url;
		JSONObject obj=new JSONObject();
		obj.put("url", url);
		obj.put("webPath", webPath);
		return obj;
	}


	private String uploadFile(FileItem file){
		
		
		
		
		SysConfigPO sys=conn_sys.getSysConfig();

		String url="licence"+File.separator+file.getName();
		File folder=new File(sys.getFolderUrl()+"licence");
		if(!folder.exists()){
			folder.mkdir();
		}
		try {
			//获取输出流
			OutputStream os=new FileOutputStream(sys.getFolderUrl()+url);
			//获取输入流 CommonsMultipartFile 中可以直接得到文件的流
			InputStream is=file.getInputStream();
			int temp;
			//一个一个字节的读取并写入
			while((temp=is.read())!=(-1))
			{
				os.write(temp);
			}
			os.flush();
			os.close();
			is.close();
			
			File newFile=new File(sys.getFolderUrl()+url);
			OSSUtils.createFolder("glw-old-file", "file/licence/");
			OSSUtils.uploadObjectOSS("file/licence/", file.getName(),newFile, new FileInputStream(newFile));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			return url;
		}

	}

	@RequestMapping(value = "/purchase/index")
	public ModelAndView purchaseIndex(
			HttpServletRequest request,long proRegion) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/purchase");
		mv.addObject("proRegion", proRegion);
		return mv;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/purchase/list", method = RequestMethod.GET)
	public Object puchaseIndex(HttpServletRequest request,long proRegion) throws Exception{



		
		List<DistributeProduct> products=conn_dispro.queryAllFirstRegionPro(proRegion);
		
		
		List<DistributeProductVo> vos=DistributeProductVo.getConverter(DistributeProductVo.class).convert(products, DistributeProductVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeProductVo distributeProductVo : vos) {
			distributeProductVo.setPic(sysConfigPO.getWebUrl()+distributeProductVo.getPic());
		}
		return vos;
	}

	@Autowired
	private MerchantDAO conn_merchant;

	@RequestMapping(value = "/product/index/{productId}")
	public ModelAndView productIndex(
			HttpServletRequest request,@PathVariable long productId) throws Exception{
		ModelAndView mv = null;
		HttpSession session=request.getSession();
		mv = new ModelAndView("mobile/guolaiwan/distribute-product");
		DistributeProductVo vo=new DistributeProductVo();
		DistributeProduct po=conn_dispro.get(productId);
		vo.set(po);
		MerchantPO merchantPO=conn_merchant.get(po.getDistributorId());
		vo.setTel(merchantPO.getShopTel());
		SysConfigPO sys=conn_sys.getSysConfig();
		vo.setPic(sys.getWebUrl()+vo.getPic());
		mv.addObject("product",vo);
		List<RegionPo> regionPos=conn_region.findAll();
		mv.addObject("regions",regionPos);

		//RegionPo regionPo=conn_region.get(Long.parseLong(session.getAttribute("region").toString()));
		CompanyPO regionPo=conn_com.get(Long.parseLong(session.getAttribute("region").toString()));
		mv.addObject("orderId","null");
		mv.addObject("myregion",regionPo);
		return mv;
	}
	@Autowired
	private MerchantDAO mer_chantd;
	@Autowired
	private DistributorOrderDao conn_order;
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/apply/product")
	public Object applyProduct(
			HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		DistributorOrder order=new DistributorOrder();
		order.setCount(params.getIntValue("count"));
		order.setProductId(params.getLongValue("productId"));
		order.setRegion(params.getLongValue("region"));
		order.setPrice(params.getDoubleValue("price"));
		order.setContractPicUrl(params.getString("contractPic"));
		order.setContractVideoUrl(params.getString("contractVideo"));
		DistributeProduct product=conn_dispro.get(params.getLongValue("productId"));
		order.setParentId(product.getDistributorId());
		HttpSession session=request.getSession();
		order.setDistributorId(Long.parseLong(session.getAttribute("distributorId").toString()));

		if (params.get("orderId") != null && params.get("orderId") != "null") {
			order = conn_order.get(params.getLong("orderId"));
		}

		order.setStatus(DistributorOrderStatus.CHECKED);
		order.setApplicationDate(new Date());

		conn_order.saveOrUpdate(order);
		return order.getId();
	}


	@RequestMapping(value = "/order/index")
	public ModelAndView orderIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/my-order");
		HttpSession session=request.getSession();
		mv.addObject("distributorId", session.getAttribute("distributorId"));
		return mv;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/order/list/{distributorId}")
	public Object orderList(
			HttpServletRequest request,@PathVariable long distributorId) throws Exception{
		List<DistributorOrder> orderPos=conn_order.findByDistributor(distributorId);
		List<DistributeOrderVo> vos=DistributeOrderVo.getConverter(DistributeOrderVo.class).convert(orderPos, DistributeOrderVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeOrderVo distributeOrderVo : vos) {
			DistributeProduct pro=conn_dispro.get(distributeOrderVo.getProductId());
			distributeOrderVo.setName(pro.getProduct().getProductName());
			distributeOrderVo.setPic(sysConfigPO.getWebUrl()+pro.getProduct().getProductShowPic());
		}
		return vos;
	}

	@RequestMapping(value = "/selladminorder/index")
	public ModelAndView selladminorder(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/sell-adminorder");
		HttpSession session=request.getSession();
		mv.addObject("distributorId", session.getAttribute("merchantId"));
		return mv;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/sellorder/list/{distributorId}")
	public Object sellorderList(
			HttpServletRequest request,@PathVariable long distributorId) throws Exception{
		List<DistributorOrder> orderPos=conn_order.findByParent(distributorId);
		List<DistributeOrderVo> vos=DistributeOrderVo.getConverter(DistributeOrderVo.class).convert(orderPos, DistributeOrderVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeOrderVo distributeOrderVo : vos) {
			DistributeProduct pro=conn_dispro.get(distributeOrderVo.getProductId());
			distributeOrderVo.setName(pro.getProduct().getProductName());
			distributeOrderVo.setPic(sysConfigPO.getWebUrl()+pro.getProduct().getProductShowPic());
		}
		return vos;
	}

	@RequestMapping(value = "/order/check/{orderId}")
	public ModelAndView orderCheck(
			HttpServletRequest request,@PathVariable long orderId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/check-order");
		DistributorOrder order=conn_order.get(orderId);
		DistributeOrderVo vo=new DistributeOrderVo();
		vo.set(order);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		vo.setContractPicUrl(sysConfigPO.getWebUrl()+vo.getContractPicUrl());
		vo.setContractVideoUrl(sysConfigPO.getWebUrl()+vo.getContractVideoUrl());

		DistributeProduct pro=conn_dispro.get(vo.getProductId());
		vo.setName(pro.getProduct().getProductName());
		RegionPo region=conn_region.get(vo.getRegionId());
		vo.setRegion(region.getName());
		mv.addObject("order",vo);
		//HttpSession session=request.getSession();
		//mv.addObject("distributorId", session.getAttribute("distributorId"));
		return mv;
	}

	@RequestMapping(value = "/order/checkadmin/{orderId}")
	public ModelAndView orderCheckAdmin(
			HttpServletRequest request,@PathVariable long orderId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/check-adminorder");
		DistributorOrder order=conn_order.get(orderId);
		DistributeOrderVo vo=new DistributeOrderVo();
		vo.set(order);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		vo.setContractPicUrl(sysConfigPO.getWebUrl()+vo.getContractPicUrl());
		vo.setContractVideoUrl(sysConfigPO.getWebUrl()+vo.getContractVideoUrl());

		DistributeProduct pro=conn_dispro.get(vo.getProductId());
		vo.setName(pro.getProduct().getProductName());
		RegionPo region=conn_region.get(vo.getRegionId());
		vo.setRegion(region.getName());
		mv.addObject("order",vo);
		//HttpSession session=request.getSession();
		//mv.addObject("distributorId", session.getAttribute("distributorId"));
		return mv;
	}


	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/order/cancel")
	public Object cancelOrder(
			HttpServletRequest request,long orderId) throws Exception{
		conn_order.delete(orderId);
		return "";
	}

	@RequestMapping(value = "/recheckproduct/index/{orderId}")
	public ModelAndView recheckproductIndex(
			HttpServletRequest request,@PathVariable long orderId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/distribute-product");
		DistributeProductVo vo=new DistributeProductVo();
		Long id = conn_order.get(orderId).getProductId();
		request.setAttribute("recheckproductId", id);
		DistributeProduct po=conn_dispro.get(id);
		vo.set(po);
		SysConfigPO sys=conn_sys.getSysConfig();
		vo.setPic(sys.getWebUrl()+vo.getPic());
		mv.addObject("product",vo);
		List<CompanyPO> regionPos=conn_com.findAll();
		mv.addObject("regions",regionPos);
		HttpSession session=request.getSession();
		CompanyPO regionPo=conn_com.get(Long.parseLong(session.getAttribute("region").toString()));
		mv.addObject("orderId",orderId);
		mv.addObject("myregion",regionPo);
		return mv;
	}

	@RequestMapping(value = "/deleteorder/index/{orderId}")
	public ModelAndView deleteOrderIndex(
			HttpServletRequest request,@PathVariable long orderId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/my-order");
		conn_order.delete(orderId);
		HttpSession session=request.getSession();
		mv.addObject("distributorId", session.getAttribute("distributorId"));
		return mv;
	}


	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/order/doCheck")
	public Object orderDocheck(
			HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		long orderId=params.getLongValue("id");
		String status=params.getString("status");
		String reason=params.getString("reason");
		DistributorOrder order=conn_order.get(orderId);
		order.setStatus(DistributorOrderStatus.valueOf(status));
		order.setRejectReason(reason);
		conn_order.save(order);
		return order;
	}




	@RequestMapping(value = "/order/info/{orderId}")
	public ModelAndView orderInfo(
			HttpServletRequest request,@PathVariable long orderId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/order-info");
		DistributorOrder order=conn_order.get(orderId);
		DistributeOrderVo vo=new DistributeOrderVo();
		vo.set(order);
		DistributeProduct pro=conn_dispro.get(vo.getProductId());
		vo.setName(pro.getProduct().getProductName());
		RegionPo region=conn_region.get(vo.getRegionId());
		vo.setRegion(region.getName());
		mv.addObject("order",vo);
		return mv;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/order/status", method = RequestMethod.GET)
	public Object orderStatus(
			HttpServletRequest request,Long orderId) throws Exception{
		DistributorOrder orderInfoPO=conn_order.get(orderId);
		return orderInfoPO.getStatus().toString();
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/policy/price/{id}/{count}")
	public Object productIndex(
			HttpServletRequest request,@PathVariable long id,@PathVariable int count) throws Exception{
		List<DistributePolicy> policys = conn_policy.queryByProduct(id);
		for (DistributePolicy distributePolicy : policys) {
			if(distributePolicy.getCount()==count){

				return distributePolicy.getPrice();
			}
		}
		return null;
	}


	@RequestMapping(value = "/pay/index/{orderId}")
	public ModelAndView payIndex(
			HttpServletRequest request,@PathVariable long orderId) throws Exception{
		ModelAndView mv = null;

		mv = new ModelAndView("mobile/guolaiwan/pay");
		DistributorOrder order=conn_order.get(orderId);
		DistributeOrderVo vo=new DistributeOrderVo();
		vo.set(order);
		DistributeProduct pro=conn_dispro.get(vo.getProductId());
		vo.setName(pro.getProduct().getProductName());
		RegionPo region=conn_region.get(vo.getRegionId());
		vo.setRegion(region.getName());
		mv.addObject("order",vo);
		return mv;

	}
	@RequestMapping(value = "/my/index")
	public ModelAndView myIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/my-distribute");
		HttpSession session=request.getSession();
		mv.addObject("distributorId", session.getAttribute("distributorId"));
		return mv;
	}
	

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/product/modify", method = RequestMethod.POST)
	public Object modProduct(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		DistributeProductVo vo= params.toJavaObject(params, DistributeProductVo.class);
		DistributeProduct distributeProduct =conn_dispro.get(params.getLong("id"));
		conn_policy.deleteByProduct(distributeProduct.getId());
		for (DistributePolicyVo policyVo : vo.getPolicys()) {
			DistributePolicy policy=new DistributePolicy();
			policy.setPrice(policyVo.getPrice());
			policy.setCount(policyVo.getCount());
			policy.setDistributeProduct(distributeProduct);
			distributeProduct.getDistributePolicies().add(policy);
		}
		conn_dispro.saveOrUpdate(distributeProduct);
		return null;
	}


	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/region", method = RequestMethod.GET)
	public Object queryAllFirstRegion(HttpServletRequest request) throws Exception{
		List<RegionPo> regions = conn_region.queryAllFirstRegion();
		return regions;
	}
	@Autowired 
	private DistributeProductDao conn_dispro;
	@Autowired
	private SysConfigDAO conn_sys;
	@Autowired
	private DistributePolicyDao conn_policy;

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/dispro/{distributorId}", method = RequestMethod.GET)
	public Object queryDistributeProduct(HttpServletRequest request,@PathVariable Long distributorId) throws Exception{

		List<DistributeProduct> products=conn_dispro.queryAllByDistributor(distributorId);
		List<DistributeProductVo> vos=DistributeProductVo.getConverter(DistributeProductVo.class).convert(products, DistributeProductVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeProductVo distributeProductVo : vos) {
			distributeProductVo.setPic(sysConfigPO.getWebUrl()+distributeProductVo.getPic());
		}
		return vos;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/onlinedispro/{distributorId}", method = RequestMethod.GET)
	public Object queryOnlineDistributeProduct(HttpServletRequest request,@PathVariable Long distributorId) throws Exception{

		List<DistributeProduct> products=conn_dispro.queryOnlineByDistributor(distributorId);
		List<DistributeProductVo> vos=DistributeProductVo.getConverter(DistributeProductVo.class).convert(products, DistributeProductVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeProductVo distributeProductVo : vos) {
			distributeProductVo.setPic(sysConfigPO.getWebUrl()+distributeProductVo.getPic());
		}
		return vos;
	}


	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/change/onlineStatus/{id}/{status}", method = RequestMethod.GET)
	public Object changeOnlineStatus(HttpServletRequest request,@PathVariable Long id,@PathVariable int status) throws Exception{
		DistributeProduct product=conn_dispro.get(id);
		product.setOnline(status);
		conn_dispro.save(product);
		return null;
	}


	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/recoments/{regionId}", method = RequestMethod.GET)
	public Object queryRecomments(HttpServletRequest request,@PathVariable long regionId) throws Exception{

		RegionPo regionPo=conn_region.get(regionId);
		List<DistributeProduct> products=new ArrayList<DistributeProduct>();
		products=conn_dispro.queryOnlineByCom(regionPo.getParentId(),5);
		List<DistributeProductVo> vos=DistributeProductVo.getConverter(DistributeProductVo.class).convert(products, DistributeProductVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeProductVo distributeProductVo : vos) {
			distributeProductVo.setPic(sysConfigPO.getWebUrl()+distributeProductVo.getPic());
		}
		return vos;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/secrecoments/{regionId}", method = RequestMethod.GET)
	public Object querySecondRecomments(HttpServletRequest request,@PathVariable long regionId) throws Exception{
		RegionPo regionPo=conn_region.get(regionId);
		List<DistributeProduct> products=conn_dispro.queryOnlineByCom(regionPo.getParentId(),7);
		List<DistributeProductVo> vos=DistributeProductVo.getConverter(DistributeProductVo.class).convert(products, DistributeProductVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeProductVo distributeProductVo : vos) {
			distributeProductVo.setPic(sysConfigPO.getWebUrl()+distributeProductVo.getPic());
		}
		return vos;
	}
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/query/thirdcoments/{regionId}", method = RequestMethod.GET)
	public Object queryThirdRecomments(HttpServletRequest request,@PathVariable long regionId) throws Exception{
		RegionPo regionPo=conn_region.get(regionId);
		List<DistributeProduct> products=conn_dispro.queryOnlineByCom(regionPo.getParentId(),50);
		List<DistributeProductVo> vos=DistributeProductVo.getConverter(DistributeProductVo.class).convert(products, DistributeProductVo.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (DistributeProductVo distributeProductVo : vos) {
			distributeProductVo.setPic(sysConfigPO.getWebUrl()+distributeProductVo.getPic());
		}
		return vos;
	}


	@RequestMapping(value = "/personal/index")
	public ModelAndView personalIndex(
			HttpServletRequest request) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/distribute-personal");
		return mv;
	}
	@Autowired
	private UserInfoDAO conn_user;
	@RequestMapping(value = "/app/login/{userId}")
	public ModelAndView appLogin(
			HttpServletRequest request,@PathVariable long userId) throws Exception{	
		ModelAndView mv = null;
		HttpSession session = request.getSession();
		mv = new ModelAndView("mobile/guolaiwan/distribute-personal");
		if(session.getAttribute("distributorId")==null){
			mv.addObject("distributorId", 0);
			mv.addObject("status","null");
		}else{
			String distributorIdstr=session.getAttribute("distributorId").toString();	
			DistributorPo distributorPo=conn_distributor.get(Long.parseLong(distributorIdstr));
			mv.addObject("distributorId", distributorPo.getId());
			mv.addObject("status", distributorPo.getStatus().getName());
			mv.addObject("reason", distributorPo.getCheckReason());
			mv.addObject("distributor", distributorPo);
			mv.addObject("region",conn_com.get(distributorPo.getRegion()).getComName());
			UserInfoPO user=conn_user.get(Long.parseLong(session.getAttribute("userId").toString()));
			mv.addObject("user",user);
		}
		return mv;
	}

	
	@RequestMapping(value = "/reallogin/index")
	public ModelAndView realLogin(
			HttpServletRequest request) throws Exception{	
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/login");
		return mv;
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/dologin/index", method = RequestMethod.POST)
	public Object dologin(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject pageObject = parser.parseJSON();
		String phone = pageObject.getString("phone");
		String password = pageObject.getString("password");
		DistributorPo distributorPo=conn_distributor.getbyphoneAndpassword(phone, password);
		if(distributorPo==null){
			throw new BaseException(StatusCode.FORBIDDEN, "用户名密码错误");
		}
		HttpSession session = request.getSession();
		session.setAttribute("distributorId", distributorPo.getId());
		session.setAttribute("region",distributorPo.getRegion());
		return "success";
	}
	
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/prev/pay/{id}")
	public Object prevPay(
			@PathVariable long id,
			String cip,HttpServletRequest request) throws Exception{

		DistributorOrder order=conn_order.get(id);

		String orderNo="distribute-"+id;
		Long userId=Long.parseLong(request.getSession().getAttribute("userId").toString());
		UserInfoPO user= conn_user.get(userId);
		Double amount=order.getPrice()*order.getCount();
		YuebaWxPayConstants.set("http://"+WXContants.Website+"/pubnum/wxreport/payreport", WxConfig.appId, WxConfig.appsrcret);
		//统一下单，返回xml，用return_code判断统一下单结果,获取prepay_id等预支付成功信息
		String prePayInfoXml = com.guolaiwan.app.web.weixin.YuebaWxUtil.unifiedOrder("WxPay", orderNo, 1, "192.165.56.64", user.getUserOpenID());
		//生成包含prepay_id的map，map传入前端
		java.util.Map<String, Object> map = YuebaWxUtil.getPayMap(prePayInfoXml);
		//将订单号放入map，用以支付后处理
		map.put("orderNo",orderNo);
		return map;
	}



	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getCommodity/{disId}", method = RequestMethod.GET)
	public Object SeleFinish( @PathVariable Integer disId,HttpServletRequest request) throws Exception {
		String fiel =  "distributionId";
		List<MerchantPO> productCoun = mer_chantd.findByField(fiel, disId);
		long merchantId = 0L;
		for (MerchantPO merchantPO : productCoun) {
			merchantId = merchantPO.getId();
		}
		String field =  "productMerchantID" ;
		Object value =  merchantId ;
		List<ProductPO> productCount = conn_product.findByField(field, value);
		List<ProductVO> vos = ProductVO.getConverter(ProductVO.class).convert(productCount, ProductVO.class); 
		return vos;
	}
	
	/**
	 * 修改库存
	 * @param disId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/amendInventory/{ProductId}/{input}/{judge}", method = RequestMethod.GET)
	public Object AmendInventory( @PathVariable Long ProductId,@PathVariable Double input,@PathVariable Long judge,HttpServletRequest request) throws Exception {
		DistributeProduct products  =	conn_dispro.getByField("id",ProductId);
		if (judge == 1 && judge !=null) {
			products.setRetailRepertory(input.intValue());
		}else if (judge != null) {
			products.setRetailPrice(input);
		}
		conn_dispro.save(products);
		return "";
	}
	
	/**
	 * 修改状态 上下架
	 * @param disId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	//TODO
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/amendState/{ProductId}/{judge}", method = RequestMethod.GET)
	public Object AmendState( @PathVariable Long ProductId,@PathVariable String judge,HttpServletRequest request) throws Exception {
		DistributeProduct products  =	conn_dispro.getByField("id",ProductId);
		if ("T" .equals(judge)  && judge !=null&&products != null) {
			products.setStore(1);
		}else if (judge != null&&products != null) {
			products.setStore(0);
		}
		conn_dispro.save(products);
		return "";
	}



	@Autowired
	private ProductDAO conn_product;
	@Autowired
	private OffOrderDao conn_offorder;
	@Autowired
	private OrderInfoDAO conn_onlineorder;

	@RequestMapping(value = "/onlineorder/index")
	public ModelAndView onlineindex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/onlineorder-list");
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/onlineorder/list/{distributorId}")
	public Object onlineorderList(
			HttpServletRequest request,@PathVariable long distributorId) throws Exception{
		List<OrderInfoPO> orderPos=conn_onlineorder.findByDistributorAndSource(distributorId,OrderSource.DISTRIBUTORONLINE);
		List<OrderInfoVO> vos=DistributeOrderVo.getConverter(OrderInfoVO.class).convert(orderPos, OrderInfoVO.class);
		SysConfigPO sysConfigPO=conn_sys.getSysConfig();
		for (OrderInfoVO orderInfoVO : vos) {
			orderInfoVO.setProductPic(sysConfigPO.getWebUrl()+orderInfoVO.getProductPic());
		}
		return vos;
	}
	
	@RequestMapping(value = "/online/orderinfo")
	public ModelAndView adminOrderInfo(HttpServletRequest request, long orderId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/onlineorderInfo");
		mv.addObject("orderId", orderId);
		return mv;
	}
	
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/getallOfforder", method = RequestMethod.GET)
	public Object getallOfforder(
			HttpServletRequest request,Long proId) throws Exception{
		return conn_offorder.findByProid(proId);
	}
	@RequestMapping(value = "/off/sell")
	public ModelAndView offSellIndex(
			HttpServletRequest request,long disId) throws Exception{
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/off-sell");
		mv.addObject("disId", disId);
		return mv;
	}

	@RequestMapping(value = "/offorder/index")
	public ModelAndView addressIndex(HttpServletRequest request) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/offorder");
		return mv;
	}
	@RequestMapping(value = "/offorder/edit")
	public ModelAndView addressAdd(HttpServletRequest request,long proId) throws Exception {
		ModelAndView mv = null;
		mv = new ModelAndView("mobile/guolaiwan/editOfforder");
		mv.addObject("proId", proId);
		return mv;
	}



	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/delOff", method = RequestMethod.GET)
	public Object delOff(
			HttpServletRequest request,Long offId) throws Exception{
		conn_offorder.delete(offId);
		return "";
	}

	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/offorder/add", method = RequestMethod.POST)
	public Object addOfforder(
			HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		OffOrder order=new OffOrder();
		order.setAddress(params.getString("address"));
		order.setPhone(params.getString("phone"));
		order.setUserName(params.getString("userName"));
		order.setProId(params.getLongValue("proId"));
		conn_offorder.save(order);
		return "";
	}
	
	
	
	
}
