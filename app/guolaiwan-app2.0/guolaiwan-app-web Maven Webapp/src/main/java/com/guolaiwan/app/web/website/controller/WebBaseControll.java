package com.guolaiwan.app.web.website.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.zxing.WriterException;
import com.guolaiwan.bussiness.admin.dao.CityInfoDAO;
import com.guolaiwan.bussiness.admin.dao.CompanyDAO;
import com.guolaiwan.bussiness.admin.dao.ProductDAO;
import com.guolaiwan.bussiness.admin.dao.SysConfigDAO;
import com.guolaiwan.bussiness.admin.po.CityInfoPO;
import com.guolaiwan.bussiness.admin.po.DistributorProductPO;
import com.guolaiwan.bussiness.admin.po.MerModularPO;
import com.guolaiwan.bussiness.admin.po.MerchantPO;
import com.guolaiwan.bussiness.admin.po.UserInfoPO;

import pub.caterpillar.commons.qrcode.QRCodeGenerator;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.orm.po.AbstractBasePO;

public class WebBaseControll<T extends AbstractBasePO> extends BaseController{

	@Autowired  
	private  HttpServletRequest request;  

	@Autowired
	private CityInfoDAO conn_CityInfo;

	@Autowired
	private ProductDAO conn_product;

	@Autowired
	private SysConfigDAO conn_sysConfig;

	@Autowired
	private CompanyDAO conn_company;

	//获取城市code
	protected String getCityCodeByDomain(){

		String domains=request.getServerName();

		CityInfoPO cityinfopo = conn_CityInfo.getByField("cityDomain", domains);
		if(cityinfopo==null){
			//如果为null转到遵化
			return "0001";
		}
		return cityinfopo.getCityCode();
	}

	//将comId存到session中
	protected void setComIdSession(){

		String comCode =request.getParameter("com");
		long comId = 1l;
		if(comCode !=null&&comCode!=""){
			if(conn_company.getByCode(comCode)!=null){
				comId = conn_company.getByCode(comCode).getId();
			}
		}
		request.getSession().setAttribute("companyId", comId);
	}
	
	//从session中获取comId
	protected long getComIdSession(){

		if(request.getSession().getAttribute("companyId")==null){
			return 1l;
		}else{
			return (long)request.getSession().getAttribute("companyId");
		}
	}


	//分页
	protected String getPages(int pagecurr,int count,int pageSize,String url,String params){
		//总页数
		int pages = GetAllPages(count,pageSize);
		//分页样式
		StringBuilder sbPage=new StringBuilder();
		sbPage.append("<div class=\"fr pc-search-g\">");
		if(pagecurr==1)
			sbPage.append("<a class='fl pc-search-f' >上一页</a>");
		else
			sbPage.append("<a class='pc-search-u' href='"+url+"?pagecurr="+(pagecurr-1)+params+"' >上一页</a>");
		if(pages<10){
			for (int i = 1; i <= pages; i++){
				if(pagecurr==i)
					sbPage.append("<a href='#' class='current'>"+i+"</a>");
				else
					sbPage.append("<a href='"+url+"?pagecurr="+i+params+"' >"+i+"</a>");
			}
		}else{
			if(pagecurr>=1&&pagecurr<=4){
				if(pagecurr==4){
					sbPage.append("<a href='"+url+"?pagecurr="+1+params+"' >"+1+"</a>");
				}
			}else{
				sbPage.append("<a href='"+url+"?pagecurr="+1+params+"' >"+1+"</a>");
				sbPage.append("<span class='pc-search-di'>…</span>");
			}
			for (int i = pagecurr-2; i <= pagecurr+2; i++){
				if(i>=1&&i<=pages){	
					if(pagecurr==i)
						sbPage.append("<a href='#' class='current'>"+i+"</a>");
					else
						sbPage.append("<a href='"+url+"?pagecurr="+i+params+"' >"+i+"</a>");
				}
			}
			if(pagecurr>=pages-3&&pagecurr<=pages){
				if(pagecurr==pages-3){
					sbPage.append("<a href='"+url+"?pagecurr="+pages+params+"' >"+pages+"</a>");
				}
			}else{
				sbPage.append("<span class='pc-search-di'>…</span>");
				sbPage.append("<a href='"+url+"?pagecurr="+pages+params+"' >"+pages+"</a>");
			}
		}



		if(pagecurr!=pages)
			sbPage.append("<a class='pc-search-n' href='"+url+"?pagecurr="+(pagecurr+1)+params+"' >下一页</a>");
		else
			sbPage.append("<a class='fr pc-search-nf'>下一页</a>");

		sbPage.append("<span class='pc-search-y'>");
		sbPage.append("<em>  共"+pages+"页    到第</em>");
		sbPage.append("<input type='text' id='pagecurr'  class='pc-search-j' placeholder='"+pagecurr+"'>");
		sbPage.append("<em>页</em>");
		sbPage.append("<a href='javascript:searchPage()' class='confirm'>确定</a>");
		sbPage.append("</span>");
		sbPage.append("</div>");


		return sbPage.toString();
	}

	//用户信息
	protected UserInfoPO GetUserInfo()
	{
		Object o=request.getSession().getAttribute("userInfo");
		if(o!=null)
		{
			UserInfoPO uinfo=(UserInfoPO)o;
			return uinfo;
		}
		else{
			//重定向地址
			return null;
			
		}


	}

	//判断表单号是否有效  
	protected boolean isTokenValue(HttpServletRequest request){  
		String clientToken = request.getParameter("token");  
		if(clientToken==null){  
			return false;  
		}  
		String serverToken = (String) request.getSession().getAttribute("token");  
		if(serverToken==null){  
			return false;  
		}  

		if (!clientToken.equals(serverToken)){  
			return false;  
		}  

		return true;  
	} 

	protected String ydNoCode(String orderNO) throws WriterException, IOException{//
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		//内容
		String content = orderNO;
		//路径
		Date d = new Date();
		String datepath =sdf.format(d); 
		String fileName = orderNO+".png";
		String ydNO = File.separator+"orderNO"+File.separator+datepath+File.separator+fileName;
		String path = conn_sysConfig.getSysConfig().getFolderUrl()+ydNO;
		//生成二维码
		QRCodeGenerator.generate(path, content);
		return ydNO;
	}

	//将list转化成map
	public HashMap<Long, List<MerModularPO>> queryList(List<MerModularPO> list) {
		HashMap<Long, List<MerModularPO>> map = new HashMap<Long,List<MerModularPO>>();

		for (MerModularPO li : list) {
			Long parentId =  li.getParentId();
			if (map.containsKey(parentId)) {
				ArrayList<MerModularPO> templist = (ArrayList<MerModularPO>) map.get(parentId);
				templist.add(li);
			} else {
				ArrayList<MerModularPO> temlist = new ArrayList<MerModularPO>();
				temlist.add(li);
				map.put(parentId, temlist);
			}
		}
		return map;
	}


	public StringBuilder dgWString(StringBuilder sbHtml,Long id,HashMap<Long, List<MerModularPO>> merMap,String path,MerchantPO merchant) {
		List<MerModularPO> mMListc = merMap.get(id);
		if(mMListc==null||mMListc.size()<=0){
			return sbHtml;
		}else{
			for (MerModularPO merModularPO : mMListc) {
				sbHtml.append("<ul class='fenlei-wrap'>");
				String fields ="merMId";
				Object values =merModularPO.getId();
				sbHtml.append("<li><a href='"+path+"/web/merchant/productList?merchant="+merchant.getUuid()+"&merM="+merModularPO.getUuid()+"'><cite class='select"+merModularPO.getUuid()+" select'>"+merModularPO.getName()+"("+conn_product.countProduct(fields,values)+")</cite></a></li>");

				sbHtml = dgWString(sbHtml,merModularPO.getId(),merMap,path,merchant);
				sbHtml.append("</ul>");
			}
			return sbHtml;
		}
	}
	
	
	//多图装换
	public String split(String old,String add){
		if(old==null||old.length()==0){
			return "";
		}
		String[] oldArray   = old.split(",");
		for (int i = 0; i < oldArray.length; i++) {
			if(oldArray[i].indexOf("http://")==-1){//过滤http://
				oldArray[i] = add+oldArray[i] ;
			}
		}
		String newStr = String.join(",", oldArray);
		return newStr;
	}
	
	//单图装换
	public String splitSimple(String old,String add){
		if(old==null||old.length()==0){
			return "";
		}
		if(old.indexOf("http://")==-1){//过滤http://
			old = add+old ;
		}
		return old;
	}
	
	
	//list分页
	public   List<T> listPage(List<T> list, int page,int pageSize) {
		int totalCount = list.size();
		if(totalCount==0){
	    	return list;
	    }
	    int pages = totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;//页数
	    if(page==pages){
	    	list = list.subList((page-1)*pageSize,list.size());
	    }else if(page>pages){
	    	list = new ArrayList<T>();
	    }else{
	    	list = list.subList((page-1)*pageSize,page*pageSize);
	    }
	    return list;
	}
	
	public boolean RemoveFile(String fileName,String destinationFloderUrl)
	{
		System.out.println(fileName+"-"+destinationFloderUrl);
		File file = new File(fileName);
		File destFloder = new File(destinationFloderUrl);
		//检查目标路径是否合法
		if(destFloder.exists()==false){
			if(destFloder.getParentFile().exists()==false){
				System.out.println("要备份的文件路径不正确，移动失败！");
				return false;
			}
			destFloder.mkdir();//没有就新建
		}
		if(destFloder.isFile()){
			System.out.println("目标路径是个文件，请检查目标路径！");
			return false;
		}

		//检查源文件是否合法
		if(file.isFile() &&file.exists())
		{
			String destinationFile = destinationFloderUrl+"/"+file.getName();
			if(!file.renameTo(new File(destinationFile)))
			{
				System.out.println("移动文件失败！");
				return false;
			}
		}
		System.out.println("已成功移动文件"+file.getName()+"到"+destinationFloderUrl);
		return true;
	}
	
	public String getAllRequestJson(HttpServletRequest request) {
		try {
			BufferedReader br;
			br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			request.getInputStream().close();
			br.close();
			return sb.toString();
		} catch (IOException e) {
			return "";
		}
	}
	
}
