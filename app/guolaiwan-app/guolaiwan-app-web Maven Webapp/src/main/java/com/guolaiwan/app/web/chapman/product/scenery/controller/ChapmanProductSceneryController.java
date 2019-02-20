package com.guolaiwan.app.web.chapman.product.scenery.controller;

import java.util.Date;
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
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.chapman.product.scenery.vo.SceneryDetailVO;
import com.guolaiwan.app.web.chapman.product.scenery.vo.SceneryVO;
import com.guolaiwan.app.web.chapman.shelve.vo.ProductVO;
import com.guolaiwan.app.web.website.vo.PictureVO;
import com.guolaiwan.bussiness.chapman.check.ChapmanDBCheck;
import com.guolaiwan.bussiness.chapman.dao.ChapmanDAO;
import com.guolaiwan.bussiness.chapman.enumeration.TargetType;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDAO;
import com.guolaiwan.bussiness.chapman.product.dao.ProductDetailDAO;
import com.guolaiwan.bussiness.chapman.product.dto.ProductDTO;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductDetailType;
import com.guolaiwan.bussiness.chapman.product.enumeration.ProductType;
import com.guolaiwan.bussiness.chapman.product.po.ProductDetailPO;
import com.guolaiwan.bussiness.chapman.product.po.ProductPO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceFolderDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceMapDAO;
import com.guolaiwan.bussiness.chapman.source.dto.SourceMapDTO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceMapType;
import com.guolaiwan.bussiness.chapman.source.po.SourceMapPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@Controller
@RequestMapping("/chapman/product/scenery")
public class ChapmanProductSceneryController extends BaseController{

	@Autowired
	private ChapmanDAO conn_chapman;
	
	@Autowired
	private ProductDAO conn_product;
	
	@Autowired
	private ProductDetailDAO conn_product_detail;
	
	@Autowired
	private SourceFolderDAO conn_source_folder;
	
	@Autowired
	private SourceDAO conn_source;
	
	@Autowired
	private SourceMapDAO conn_source_map;
	
	@Autowired
	private ChapmanDBCheck dbcheck_chapman;
	
	//获取一个商户的所有景点
	@ResponseBody
	@RequestMapping(value = "/show/{chapmanId}", method = RequestMethod.GET)
	public Map<String, Object> show(
			@PathVariable Long chapmanId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		List<ProductDTO> scenerys = conn_product.getChapmanScenerys(chapman.getId());
		
		List<SceneryVO> _scenerys = ProductVO.getConverter(SceneryVO.class).convert(scenerys, SceneryVO.class);
		
		result.put("scenerys", _scenerys);
		
		return success(result);
	}
	
	//添加一个景区
	@ResponseBody
	@RequestMapping(value = "/add/{chapmanId}", method = RequestMethod.POST)
	public Map<String, Object> add(
			@PathVariable Long chapmanId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String name = request.getParameter("name");
		String introduction = request.getParameter("introduction");
		String position = request.getParameter("position");
		String price = request.getParameter("price");
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		ProductPO scenery = new ProductPO();
		scenery.setName(name);
		scenery.setIntroduction(introduction);
		scenery.setPosition(position);
		scenery.setPrice(price==null?null:Long.valueOf(price));
		scenery.setUpdateTime(new Date());
		scenery.setType(ProductType.SCENERY);
		conn_product.save(scenery);
		
		scenery.setChapman(chapman);
		chapman.getProducts().add(scenery);
		conn_chapman.saveOrUpdate(chapman);
		
		ProductDTO tScenery = new ProductDTO();
		tScenery.setId(scenery.getId())
				.setUuid(scenery.getUuid())
				.setUpdateTime(scenery.getUpdateTime())
				.setName(scenery.getName())
				.setIntroduction(scenery.getIntroduction())
				.setPosition(scenery.getPosition())
				.setPrice(scenery.getPrice());
		
		SceneryVO _scenery = new SceneryVO().set(tScenery);
		
		result.put("scenery", _scenery);
		
		return success(result);
	}
	
	//删除景区
	@ResponseBody
	@RequestMapping(value = "/remove/{chapmanId}", method = RequestMethod.DELETE)
	public Map<String, Object> remove(
			@PathVariable Long chapmanId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		
		JSONObject params = parser.parseJSON();
		
		List<Long> sceneryIds = JSON.parseArray(params.getString("sceneryIds"), Long.class);
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		List<ProductPO> scenerys = conn_product.getChapmanScenerys(chapman.getId(), sceneryIds);
		
		if(scenerys!=null && scenerys.size()>0){
			for(ProductPO scenery:scenerys){
				scenery.setChapman(null);
				chapman.getProducts().remove(scenery);
			}
			conn_chapman.saveOrUpdate(chapman);
		}
		
		return success(result);
	}
	
	//获取景区明细
	@ResponseBody
	@RequestMapping(value = "/show/details/{chapmanId}/{sceneryId}", method = RequestMethod.GET)
	public Map<String, Object> showDetails(
			@PathVariable Long chapmanId,
			@PathVariable Long sceneryId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO scenery = dbcheck_chapman.sceneryExist(sceneryId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, scenery);
		
		ProductDTO tScenery = new ProductDTO();
		tScenery.setId(scenery.getId())
				.setUuid(scenery.getUuid())
				.setUpdateTime(scenery.getUpdateTime())
				.setName(scenery.getName())
				.setIntroduction(scenery.getIntroduction())
				.setPosition(scenery.getPosition())
				.setPrice(scenery.getPrice());
		
		SceneryVO _scenery = new SceneryVO().set(tScenery);
		
		List<ProductDetailPO> sceneryDetails = conn_product_detail.getDetails(scenery.getId());
		
		List<SceneryDetailVO> _sceneryDetails = SceneryDetailVO.getConverter(SceneryDetailVO.class).convert(sceneryDetails, SceneryDetailVO.class);
		
		result.put("details", _sceneryDetails);
		
		result.put("scenery", _scenery);
		
		return success(result);
	}
	
	//添加景区明细
	@ResponseBody
	@RequestMapping(value = "/add/detail/{chapmanId}/{sceneryId}", method = RequestMethod.POST)
	public Map<String, Object> addDetail(
			@PathVariable Long chapmanId,
			@PathVariable Long sceneryId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String name = request.getParameter("name");
		String introduction = request.getParameter("introduction");
		String price = request.getParameter("price");
		String type = request.getParameter("type");

		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO scenery = dbcheck_chapman.sceneryExist(sceneryId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, scenery);
		
		ProductDetailPO detail = new ProductDetailPO();
		detail.setTitle(name);
		detail.setIntroduction(introduction);
		//detail.setPrice(Long.valueOf((price==null||"".equals(price))?"0":price));
		detail.setType(ProductDetailType.fromString(type));
		detail.setUpdateTime(new Date());
		conn_product_detail.save(detail);
		
		detail.setProduct(scenery);
		scenery.getProductDetails().add(detail);
		conn_product.saveOrUpdate(scenery);
		
		SceneryDetailVO _detail = new SceneryDetailVO().set(detail);
		
		result.put("detail", _detail);
		
		return success(result);
	}
	
	//删除景区明细
	@ResponseBody
	@RequestMapping(value = "/remove/details/{chapmanId}/{sceneryId}", method = RequestMethod.DELETE)
	public Map<String, Object> removeDetails(
			@PathVariable Long chapmanId,
			@PathVariable Long sceneryId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO scenery = dbcheck_chapman.sceneryExist(sceneryId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, scenery);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		
		JSONObject params = parser.parseJSON();
		
		List<Long> detailIds = JSON.parseArray(params.getString("detailIds"), Long.class);
		
		List<ProductDetailPO> sceneryDetails = conn_product_detail.getDetails(scenery.getId(), detailIds);
		
		if(sceneryDetails!=null && sceneryDetails.size()>0){
			for(ProductDetailPO sceneryDetail:sceneryDetails){
				scenery.getProductDetails().remove(sceneryDetail);
				sceneryDetail.setProduct(null);
			}
			conn_product.saveOrUpdate(scenery);
		}
		
		return success(result);
	}
	
	//获取景区明细资源列表
	@ResponseBody
	@RequestMapping(value = "/show/detail/sources/{chapmanId}/{sceneryId}/{detailId}", method = RequestMethod.GET)
	public Map<String, Object> showDetailSources(
			@PathVariable Long chapmanId,
			@PathVariable Long sceneryId,
			@PathVariable Long detailId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO scenery = dbcheck_chapman.sceneryExist(sceneryId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, scenery);
		
		ProductDetailPO sceneryDetail = dbcheck_chapman.productDetailExist(detailId);
		
		dbcheck_chapman.productDetailMatching(scenery, sceneryDetail);
		
		List<SourceMapDTO> pictures = conn_source_map.getPictures(sceneryDetail.getId());
		
		List<PictureVO> _pictures = PictureVO.getConverter(PictureVO.class).convert(pictures, PictureVO.class);
		
		result.put("sources", _pictures);
		
		return success(result);
	}
	
	//添加景区明细资源
	@ResponseBody
	@RequestMapping(value = "/add/detail/source/{chapmanId}/{sceneryId}/{detailId}", method = RequestMethod.POST)
	public Map<String, Object> addDetailSource(
			@PathVariable Long chapmanId,
			@PathVariable Long sceneryId,
			@PathVariable Long detailId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO scenery = dbcheck_chapman.sceneryExist(sceneryId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, scenery);
		
		ProductDetailPO sceneryDetail = dbcheck_chapman.productDetailExist(detailId);
		
		dbcheck_chapman.productDetailMatching(scenery, sceneryDetail);
		
		String sourceId = request.getParameter("sourceId");
		
		String mapType = request.getParameter("mapType");
		
		SourcePO source = dbcheck_chapman.pictureExist(sourceId);
		
		dbcheck_chapman.chapmanFolderMatching(chapman, source.getSourceFolder());
		
		SourceMapPO sourceMap = new SourceMapPO();
		sourceMap.setUpdateTime(new Date());
		sourceMap.setType(SourceMapType.fromString(mapType));
		sourceMap.setTargetType(TargetType.PRODUCTDETAIL);
		sourceMap.setTargetId(detailId);
		conn_source_map.save(sourceMap);
		
		sourceMap.setSource(source);
		source.getSourceMaps().add(sourceMap);
		conn_source.saveOrUpdate(source);
		
		SourceMapDTO picture = new SourceMapDTO().set(sourceMap);
		
		PictureVO _picture = new PictureVO().set(picture);
		
		result.put("source", _picture);
		
		return success(result);
	}
	
	//删除景区明细资源
	@ResponseBody
	@RequestMapping(value = "/remove/detail/source/{chapmanId}/{sceneryId}/{detailId}", method = RequestMethod.DELETE)
	public Map<String, Object> removeDetailSource(
			@PathVariable Long chapmanId,
			@PathVariable Long sceneryId,
			@PathVariable Long detailId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		ProductPO scenery = dbcheck_chapman.sceneryExist(sceneryId);
		
		dbcheck_chapman.chapmanProductMatching(chapman, scenery);
		
		ProductDetailPO sceneryDetail = dbcheck_chapman.productDetailExist(detailId);
		
		dbcheck_chapman.productDetailMatching(scenery, sceneryDetail);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		
		JSONObject params = parser.parseJSON();
		
		List<Long> sourceMapIds = JSON.parseArray(params.getString("sourceMapIds"), Long.class);
		
		List<SourceMapPO> sourceMaps = conn_source_map.getSourceMaps(detailId, sourceMapIds);
		
		if(sourceMaps!=null && sourceMaps.size()>0){
			for(SourceMapPO sourceMap:sourceMaps){
				sourceMap.getSource().getSourceMaps().remove(sourceMap);
				sourceMap.setSource(null);
			}
			conn_source_map.deleteAll(sourceMaps);
		}
		
		return success(result);
	}
	
}
