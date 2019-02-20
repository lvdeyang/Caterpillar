import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import ${voPath};
import ${daoPath};
import ${poPath};
import com.sumavision.tetris.commons.util.wrapper.HashMapWrapper;
import com.sumavision.tetris.mvc.ext.response.json.aop.annotation.JsonBody;
import com.sumavision.tetris.mvc.util.HttpServletRequestParser;


/**
 * @ClassName: 参数模板接口 
 * @author lvdeyang
 * @date 2018年7月25日 上午9:01:14 
 */
@Controller
@RequestMapping(value = "/${controllerPath}")
public class ${controllerClassName} {
	
	@Autowired
	private ${daoName} ${daoParaName};
	
	
	/**
	 * @Title: 分页查询 
	 * @param pageSize 每页数据量
	 * @param currentPage 当前页
	 * @return rows 数据行
	 * @return total 总数据量
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/load", method=RequestMethod.GET)
	public Object load(
			int pageSize,
			int currentPage,
			HttpServletRequest request) throws Exception{
		PageRequest page = new PageRequest(currentPage-1, pageSize);
		Page<${poName}> pagedPOs = ${daoParaName}.findDics(page);
		long total = pagedPOs.getTotalElements();
		List<${voName}> _vos = ${voName}.getConverter(${voName}.class).convert(pagedPOs.getContent(), ${voName}.class);
		JSONObject data = new JSONObject();
		data.put("rows", _vos);
		data.put("total", total);
		return data;
	}
	
	/**
	 * @Title: 新增数据 
	 * @param name
	 * @param request
	 * @throws Exception
	 * @return Object 参数数据
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/save")
	public Object save(
			//循环生成参数
			<#list paramList as params>
			   ${params},
			</#list>  
			HttpServletRequest request) throws Exception{
		
		${poName} po=new ${poName}();
		//循环生成set方法
		<#list setParamList as setParams>
		   ${setParams};
		</#list>
		${daoParaName}.save(po);
		
		${voName} _vo = new ${voName}().set(po);
		
		return _vo;
	}
	
	/**
	 * @Title: 修改数据 
	 * @param id
	 * @param request
	 * @throws Exception
	 * @return Object 参数数据 
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/update/{id}")
	public Object update(
			@PathVariable Long id,
			<#list paramList as params>
			   ${params},
			</#list>
			HttpServletRequest request) throws Exception{
		
		${poName} po = conn_dictionary.findOne(id);
		
		<#list setParamList as setParams>
		   ${setParams};
		</#list>
		
		${daoParaName}.save(po);
		
		${voName} _vo = new ${voName}().set(po);
		
		return _vo;
	}
	
	/**
	 * @Title: 根据id删除数据 
	 * @param id
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/remove/{id}")
	public Object remove(
			@PathVariable Long id,
			HttpServletRequest request) throws Exception{
		${daoParaName}.delete(id);
		return null;
	}
	
	/**
	 * @Title: 根据id批量删除 
	 * @param ids
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@JsonBody
	@RequestMapping(value = "/remove/all")
	public Object removeAll(HttpServletRequest request) throws Exception{
		HttpServletRequestParser parser = new HttpServletRequestParser(request);
		JSONObject params = parser.parseJSON();
		List<Long> ids = JSON.parseArray(params.getString("ids"), Long.class);
		
		List<${poName}> pos = ${daoParaName}.findAll(ids);
		${daoParaName}.deleteInBatch(pos);
		
		return null;
	}
	
}
