package com.guolaiwan.app.web.website.info.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guolaiwan.app.web.website.info.vo.ProductInfoPictureVO;
import com.guolaiwan.app.web.website.info.vo.ProductInfoVO;
import com.guolaiwan.app.web.website.info.vo.ProductVO;
import com.guolaiwan.bussiness.chapman.check.ChapmanDBCheck;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDetailDAO;
import com.guolaiwan.bussiness.chapman.product.dao.TagMapDAO;
import com.guolaiwan.bussiness.chapman.product.dto.TagMapDTO;
import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceMapDAO;
import com.guolaiwan.bussiness.chapman.source.dto.SourceMapDTO;

import pub.caterpillar.commons.util.wrapper.ArrayListWrapper;
import pub.caterpillar.commons.util.wrapper.StringBufferWrapper;
import pub.caterpillar.mvc.controller.BaseController;

@Controller
@RequestMapping("/website/info")
public class WebsiteInfoController extends BaseController {

	@Autowired
	private ProductDetailDAO conn_product_detail;
	
	@Autowired
	private SourceMapDAO conn_source_map;
	
	@Autowired
	private TagMapDAO conn_tag_map;
	
	@Autowired
	private ChapmanDBCheck dbcheck_chapman;
	
	@ResponseBody
	@RequestMapping(value = "/init/{productId}", method = RequestMethod.GET)
	public Map<String, Object> init(
			@PathVariable Long productId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//获取产品
		ProductPO product = dbcheck_chapman.productExist(productId);
		
		ProductVO _product = new ProductVO().set(product);
		
		//获取产品描述
		ProductDetailPO productIntroduction = conn_product_detail.getProductIntroduction(product.getId());
		
		ProductInfoVO attach_info_1 = new ProductInfoVO().setTitle("info_1").setContent(productIntroduction.getTitle());
		ProductInfoVO attach_info_2 = new ProductInfoVO().setTitle("info_2").setContent(productIntroduction.getIntroduction());
		_product.setInfos(new ArrayListWrapper<ProductInfoVO>().add(attach_info_1).add(attach_info_2).getList());
		
		//获取产品详情描述
		List<ProductDetailPO> productInfos = conn_product_detail.getProductInfo(product.getId());
		
		List<ProductInfoVO> _productInfos = new ArrayList<ProductInfoVO>();
		
		_productInfos.add(new ProductInfoVO().setTitle("价格").setContent(new StringBufferWrapper().append(productIntroduction.getPrice()).append("元").toString()));
		
		_productInfos.addAll(ProductInfoVO.getConverter(ProductInfoVO.class).convert(productInfos, ProductInfoVO.class));
		
		//获取产品详情图片
		List<SourceMapDTO> infoPictures = conn_source_map.getProductInfoPictures(productIntroduction.getId());
		
		List<ProductInfoPictureVO> _infoPictures = ProductInfoPictureVO.getConverter(ProductInfoPictureVO.class).convert(infoPictures, ProductInfoPictureVO.class);
		
		//获取产品标签
		List<TagMapDTO> tags = conn_tag_map.getTagByProduct(product.getId());
		
		List<String> _tags = new ArrayList<String>();
		if(tags!=null && tags.size()>0){
			for(TagMapDTO tag:tags){
				_tags.add(tag.getTagName());
			}
		}
		_product.setTags(_tags);
		
		result.put("product", _product);
		result.put("infos", _productInfos);
		result.put("pictures", _infoPictures);
		
		return success(result);
	}
	
}
