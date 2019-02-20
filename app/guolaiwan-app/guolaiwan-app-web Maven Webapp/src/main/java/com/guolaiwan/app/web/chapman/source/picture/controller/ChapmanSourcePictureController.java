package com.guolaiwan.app.web.chapman.source.picture.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guolaiwan.app.web.chapman.source.picture.vo.PictureFileVO;
import com.guolaiwan.app.web.chapman.source.picture.vo.PictureVO;
import com.guolaiwan.app.web.chapman.source.tree.handler.impl.ChapmanFoldersAndPicturesQueryHandler;
import com.guolaiwan.app.web.chapman.source.tree.handler.impl.ChapmanRootFolderQueryHandler;
import com.guolaiwan.app.web.chapman.source.tree.util.SourceFolderTreeUtil;
import com.guolaiwan.app.web.chapman.source.vo.SourceFolderVO;
import com.guolaiwan.app.web.commons.tree.vo.UINodeVO;
import com.guolaiwan.bussiness.chapman.check.ChapmanDBCheck;
import com.guolaiwan.bussiness.chapman.dao.ChapmanDAO;
import com.guolaiwan.bussiness.chapman.po.ChapmanPO;
import com.guolaiwan.bussiness.chapman.source.dao.FileDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceDAO;
import com.guolaiwan.bussiness.chapman.source.dao.SourceFolderDAO;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceFolderType;
import com.guolaiwan.bussiness.chapman.source.enumeration.SourceType;
import com.guolaiwan.bussiness.chapman.source.po.FilePO;
import com.guolaiwan.bussiness.chapman.source.po.SourceFolderPO;
import com.guolaiwan.bussiness.chapman.source.po.SourcePO;
import pub.caterpillar.commons.util.binary.ByteUtil;
import pub.caterpillar.mvc.controller.BaseController;
import pub.caterpillar.mvc.util.HttpServletRequestParser;

@ControllerAdvice
@RequestMapping("/chapman/source/picture")
public class ChapmanSourcePictureController extends BaseController{

	@Autowired
	private SourceFolderDAO conn_source_folder;
	
	@Autowired
	private SourceDAO conn_source;
	
	@Autowired
	private ChapmanDAO conn_chapman;
	
	@Autowired
	private FileDAO conn_file;
	
	@Autowired
	private ChapmanDBCheck dbcheck_chapman;
	
	//展示一个商户某个目录下的全部图片以及目录
	@ResponseBody
	@RequestMapping(value = "/show/folder/{chapmanId}/{folderId}", method = RequestMethod.GET)
 	public Map<String, Object> showFolder(
			@PathVariable Long chapmanId,
			@PathVariable String folderId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//校验商户是否存在
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		Long targetFolderId = null;
		
		if("root".equals(folderId)){
			//获取商户默认目录
			SourceFolderPO rootFolder = dbcheck_chapman.chapmanRootFolderExist(chapman);
			targetFolderId = rootFolder.getId();
		}else{
			targetFolderId = Long.valueOf(folderId);
		}
		
		List<SourceFolderPO> folders = conn_source_folder.getSubFolders(chapmanId, targetFolderId);
		List<SourceFolderVO> _folders = SourceFolderVO.getConverter(SourceFolderVO.class).convert(folders, SourceFolderVO.class);
		
		List<SourcePO> pictures = conn_source.getFolderPictures(targetFolderId);
		List<PictureVO> _pictures = PictureVO.getConverter(PictureVO.class).convert(pictures, PictureVO.class);
		
		result.put("folders", _folders);
		result.put("pictures", _pictures);
		
		return success(result);
	}
	
	//向商户文件夹下添加子文件夹
	@ResponseBody
	@RequestMapping(value = "/add/folder/{chapmanId}/{folderId}", method = RequestMethod.POST)
	public Map<String, Object> addFolder(
			@PathVariable Long chapmanId,
			@PathVariable String folderId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//查找商户是否合法
		ChapmanPO reqChapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		SourceFolderPO parentFolder = null;
		
		if("root".equals(folderId)){
			//获取商户默认目录
			parentFolder = dbcheck_chapman.chapmanRootFolderExist(reqChapman);
		}else{
			parentFolder = dbcheck_chapman.folderExist(folderId);
		}
		
		//校验商户与目录是否匹配
		dbcheck_chapman.chapmanFolderMatching(reqChapman, parentFolder);
		
		String foldername = request.getParameter("foldername");
		//校验文件夹名称是否重复
		dbcheck_chapman.foldernameConflict(reqChapman, parentFolder, foldername);
		
		SourceFolderPO folder = new SourceFolderPO();
		folder.setChapman(reqChapman);
		reqChapman.getSourceFolders().add(folder);
		folder.setParent(parentFolder);
		parentFolder.getChildren().add(folder);
		folder.setFoldername(foldername);
		folder.setType(SourceFolderType.DYNAMIC);
		folder.setUpdateTime(new Date());
		
		conn_chapman.saveOrUpdate(reqChapman);
		
		//没有id再查询一下
		folder = conn_source_folder.getFolderByName(reqChapman.getId(), parentFolder.getId(), folder.getFoldername());
		
		SourceFolderVO _folder = new SourceFolderVO().set(folder);
		
		result.put("folder", _folder);
		
		return success(result);
	}
	
	//删除图片和目录
	@ResponseBody
	@RequestMapping(value = "/remove/{chapmanId}/{folderId}", method = RequestMethod.DELETE)
	public Map<String, Object> remove(
			@PathVariable Long chapmanId,
			@PathVariable Long folderId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		SourceFolderPO parentFolder = dbcheck_chapman.folderExist(folderId);
		
		dbcheck_chapman.chapmanFolderMatching(chapman, parentFolder);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		
		JSONObject params = parser.parseJSON();
		
		List<Long> pictureIds = JSON.parseArray(params.getString("pictures"), Long.class);
		
		List<SourcePO> pictures = conn_source.getPictures(folderId, pictureIds);
		
		if(pictures!=null && pictures.size()>0){
			for(SourcePO picture:pictures){
				parentFolder.getSources().remove(picture);
				picture.setSourceFolder(null);
				
				FilePO file = conn_file.get(picture.getFile().getId());
				file.setSource(null);
				picture.setFile(null);
				
				conn_file.delete(file);
			}
		}
		
		List<Long> folderIds = JSON.parseArray(params.getString("folders"), Long.class);
		
		List<SourceFolderPO> folders = conn_source_folder.getFolders(chapmanId, folderIds);
		
		if(folders!=null && folders.size()>0){
			for(SourceFolderPO folder:folders){
				chapman.getSourceFolders().remove(folder);
				folder.setChapman(null);
				
				parentFolder.getSources().remove(folder);
				folder.setParent(null);
			}
		}
		
		conn_source_folder.saveOrUpdate(parentFolder);
		
		return success(result);
	}
	
	//上传图片
	@ResponseBody
	@RequestMapping(value = "/upload/{chapmanId}/{folderId}", method = RequestMethod.POST)
	public Map<String, Object> upload(
			@PathVariable Long chapmanId,
			@PathVariable Long folderId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		SourceFolderPO folder = dbcheck_chapman.folderExist(folderId);
		
		dbcheck_chapman.chapmanFolderMatching(chapman, folder);
		
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		
		List<Object> parseResult = parser.parseRequestAndSaveFile(false);
		
		//Map<String, String> formMap = (Map<String, String>)parseResult.get(0);
		Map<String, Object> fileMap = (Map<String, Object>)parseResult.get(1);
		
		List<SourcePO> uploadPicture = new ArrayList<SourcePO>();
		
		Set<String> fileNoList = fileMap.keySet();
		for(String fileNo:fileNoList){
			FileItem fi = (FileItem)fileMap.get(fileNo);
			
			
			FilePO file = new FilePO();
			file.setContent(ByteUtil.inputStreamToBytes(fi.getInputStream()));
			conn_file.save(file);
			
			SourcePO source = new SourcePO();
			source.setType(SourceType.PICTURE);
			source.setSize(ByteUtil.getSize(file.getContent()));
			source.setIntroduction(dbcheck_chapman.getUnConflictName(folder, fi.getName()));
			source.setUpdateTime(new Date());
			conn_source.save(source);
			
			source.setFile(file);
			file.setSource(source);
			
			source.setSourceFolder(folder);
			folder.getSources().add(source);
			
			uploadPicture.add(source);
			
		}
		
		conn_source_folder.saveOrUpdate(folder);
		
		List<PictureVO> _pictures = PictureVO.getConverter(PictureVO.class).convert(uploadPicture, PictureVO.class);
		
		result.put("pictures", _pictures);
		
		return success(result);
	}
	
	//展示图片
	@ResponseBody
	@RequestMapping(value = "/show/picture/{chapmanId}/{sourceId}", method = RequestMethod.GET)
	public Map<String, Object> showPicture(
			@PathVariable Long chapmanId,
			@PathVariable Long sourceId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		SourcePO source = dbcheck_chapman.pictureExist(sourceId);
		
		dbcheck_chapman.chapmanFolderMatching(chapman, source.getSourceFolder());
		
		FilePO pictureFile = source.getFile();
		
		PictureFileVO _pictureFile = new PictureFileVO().set(pictureFile);
		
		result.put("file", _pictureFile);
		
		return success(result);
	}
	
	
	//获取图片资源树
	@ResponseBody
	@RequestMapping(value = "/show/picture/tree/{chapmanId}/{folderId}", method = RequestMethod.GET)
	public Map<String, Object> showPictureTree(
			@PathVariable Long chapmanId,
			@PathVariable String folderId,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<UINodeVO> nodes = null;
		
		ChapmanPO chapman = dbcheck_chapman.chapmanExist(chapmanId);
		
		if("root".equals(folderId)){
			SourceFolderPO rootFolder = dbcheck_chapman.chapmanRootFolderExist(chapman);
			JSONObject json = new JSONObject();
			json.put("chapman", chapman);
			json.put("folder", rootFolder);
			UINodeVO node = SourceFolderTreeUtil.getTree(new ChapmanRootFolderQueryHandler(chapman), new ChapmanFoldersAndPicturesQueryHandler(json), false, false);
			nodes = new ArrayList<UINodeVO>();
			nodes.add(node);
		}else{
			SourceFolderPO folder = dbcheck_chapman.folderExist(folderId);
			JSONObject json = new JSONObject();
			json.put("chapman", chapman);
			json.put("folder", folder);
			nodes = SourceFolderTreeUtil.getNodeList(new ChapmanFoldersAndPicturesQueryHandler(json), false, false, false);
		}
		
		result.put("nodes", nodes);
		
		return success(result);
	}
	
}
