package com.guolaiwan.bussiness.chapman.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guolaiwan.bussiness.chapman.dao.ChapmanDAO;
import com.guolaiwan.bussiness.chapman.exception.ChapmanNoPermissionForProductException;
import com.guolaiwan.bussiness.chapman.exception.ChapmanNoPermissionForSourceFolderException;
import com.guolaiwan.bussiness.chapman.exception.ChapmanNotFoundException;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDAO;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDetailDAO;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductType;
import com.guolaiwan.bussiness.chapman.product.exception.ProductDetaiNotFoundException;
import com.guolaiwan.bussiness.chapman.product.exception.ProductNoPermissionForProductDetailException;
import com.guolaiwan.bussiness.chapman.product.exception.ProductNotFoundException;
import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceFolderDAO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;
import com.guolaiwan.bussiness.chapman.source.exception.RootFolderNotFondException;
import com.guolaiwan.bussiness.chapman.source.exception.SourceFolderNotFoundException;
import com.guolaiwan.bussiness.chapman.source.exception.SourceFoldernamConflictException;
import com.guolaiwan.bussiness.chapman.source.exception.SourceNotFoundException;
import com.guolaiwan.bussiness.chapman.source.exception.SourcenameConfilictException;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;

/**
 * 数据库校验
 * 商户校验
 * lvdeyang 2017年6月26日
 */
@Service("com.guolaiwan.bussiness.chapman.check.ChapmanCheck")
public class ChapmanDBCheck {

	@Autowired
	private SourceFolderDAO conn_source_folder;
	
	@Autowired
	private SourceDAO conn_source;
	
	@Autowired
	private ChapmanDAO conn_chapman;
	
	@Autowired
	private ProductDAO conn_product;
	
	@Autowired
	private ProductDetailDAO conn_product_detail;
	
	//商户是否存在
	public ChapmanPO chapmanExist(Long chapmanId) throws Exception{
		ChapmanPO chapman = conn_chapman.get(chapmanId);
		if(chapman == null){
			throw new ChapmanNotFoundException(chapmanId);
		}
		return chapman;
	}
	
	//商户是否存在--重载
	public ChapmanPO chapmanExist(String chapmanId) throws Exception{
		return chapmanExist(Long.valueOf(chapmanId));
	}
	
	//商户默认目录是否存在
	public SourceFolderPO chapmanRootFolderExist(ChapmanPO chapman) throws RootFolderNotFondException{
		SourceFolderPO rootFolder = conn_source_folder.getRootFolder(chapman.getId());
		if(rootFolder == null){
			throw new RootFolderNotFondException(chapman.getChapmanname());
		}
		return rootFolder;
	}
	
	//目录是否存在
	public SourceFolderPO folderExist(Long folderId) throws Exception{
		SourceFolderPO folder = conn_source_folder.get(folderId);
		if(folder == null){
			throw new SourceFolderNotFoundException(folderId);
		}
		return folder;
	}
	
	//目录是否存在--重载
	public SourceFolderPO folderExist(String folderId) throws Exception{
		return folderExist(Long.valueOf(folderId));
	}
	
	//商户目录权限匹配--重载
	public void chapmanFolderMatching(Long chapmanId, Long folderId) throws Exception{
		ChapmanPO chapman = chapmanExist(chapmanId);
		SourceFolderPO folder = folderExist(folderId);
		chapmanFolderMatching(chapman, folder);
	}
	
	//商户目录权限匹配
	public void chapmanFolderMatching(ChapmanPO chapman, SourceFolderPO folder) throws Exception{
		ChapmanPO fchapman = folder.getChapman();
		if(fchapman==null || !fchapman.getId().equals(chapman.getId())){
			throw new ChapmanNoPermissionForSourceFolderException(chapman.getChapmanname(), folder.getFoldername());
		}
	}
	
	//目录名冲突
	public void foldernameConflict(ChapmanPO chapman, SourceFolderPO parentFolder, String foldername) throws Exception{
		SourceFolderPO folder = conn_source_folder.getFolderByName(chapman.getId(), parentFolder.getId(), foldername);
		if(folder != null){
			throw new SourceFoldernamConflictException(parentFolder.getFoldername(), foldername);
		}
	}
	
	//资源名称冲突
	public void sourcenameConflict(SourceFolderPO parentFolder, String sourcename) throws Exception{
		SourcePO source = conn_source.getFolderPictureByName(parentFolder.getId(), sourcename);
		if(source != null){
			throw new SourcenameConfilictException(parentFolder.getFoldername(), sourcename);
		}
	}
	
	//获取一个不冲突的名字
	public String getUnConflictName(SourceFolderPO parentFolder, String foldername){
		String name = foldername;
		int count = 1;
		while (true) {
			try {
				sourcenameConflict(parentFolder, name);
				break;
			} catch (Exception e) {
				name = new StringBufferWrapper().append(foldername).append("(").append(count).append(")").toString();
				count++;
			}
		}
		return name;
	}
	
	//图片是否存在--重载
	public SourcePO pictureExist(String sourceId) throws Exception{
		return pictureExist(Long.valueOf(sourceId));
	}
	
	//图片是否存在
	public SourcePO pictureExist(Long sourceId) throws Exception{
		SourcePO source = conn_source.getPicture(sourceId);
		if(source == null){
			throw new SourceNotFoundException(sourceId, SourceType.PICTURE);
		}
		return source;
	}
	
	//商品是否存在--重载
	public ProductPO productExist(String productId) throws Exception{
		return productExist(Long.valueOf(productId));
	}
	
	//商品是否存在
	public ProductPO productExist(Long productId) throws Exception{
		ProductPO product = conn_product.get(productId);
		if(product == null){
			throw new ProductNotFoundException(productId, null);
		}
		return product;
	}
	
	//景点是否存在
	public ProductPO sceneryExist(Long sceneryId) throws Exception{
		ProductPO scenery = conn_product.getScenery(sceneryId);
		if(scenery == null){
			throw new ProductNotFoundException(sceneryId, ProductType.SCENERY);
		}
		return scenery;
	}
	
	//景点是否存在--重载
	public ProductPO sceneryExist(String sceneryId) throws Exception{
		return sceneryExist(Long.valueOf(sceneryId));
	}
	
	//商户景点权限匹配
	public void chapmanProductMatching(ChapmanPO chapman, ProductPO product) throws Exception{
		ChapmanPO schapman = product.getChapman();
		if(schapman==null || !schapman.getId().equals(chapman.getId())){
			throw new ChapmanNoPermissionForProductException(chapman.getChapmanname(), product.getIntroduction());
		}
	}
	
	//明细是否存在--重载
	public ProductDetailPO productDetailExist(String productDetailId) throws Exception{
		return productDetailExist(Long.valueOf(productDetailId));
	}
	
	//明细是否存在
	public ProductDetailPO productDetailExist(Long productDetailId) throws Exception{
		ProductDetailPO detail = conn_product_detail.get(productDetailId);
		if(detail == null){
			throw new ProductDetaiNotFoundException(productDetailId);
		}
		return detail;
	}
	
	//产品明细和产品是否匹配
	public void productDetailMatching(ProductPO product, ProductDetailPO detail) throws Exception{
		ProductPO dProduct = detail.getProduct();
		if(dProduct==null || !dProduct.getId().equals(product.getId())){
			throw new ProductNoPermissionForProductDetailException(product.getId(), detail.getId());
		}
	}
}
