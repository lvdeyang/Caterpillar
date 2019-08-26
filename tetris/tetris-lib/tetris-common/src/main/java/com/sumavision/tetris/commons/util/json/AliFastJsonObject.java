package com.sumavision.tetris.commons.util.json;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sumavision.tetris.commons.constant.DataType;
import com.sumavision.tetris.commons.util.wrapper.ArrayListWrapper;
import com.sumavision.tetris.commons.util.wrapper.StringBufferWrapper;

@Component
public class AliFastJsonObject {

	/**
	 * 将json转换成map(变成key path)<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月26日 下午3:31:28
	 * @param JSONObject json
	 * @return Map<String, Object>
	 */
	public Map<String, Object> convertToHashMap(JSONObject json) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		if(json==null || json.size()<=0) return map;
		packMap(null, json, map);
		return map;
	}
	
	private void packMap(String parentKeyPath, JSONObject json, Map<String, Object> map) throws Exception{
		parentKeyPath = parentKeyPath==null?"":parentKeyPath;
		Set<String> jsonKeys = json.keySet();
		for(String key:jsonKeys){
			Object target = json.get(key);
			if(DataType.isBasicType(target) || DataType.isArray(target)){
				map.put(new StringBufferWrapper().append(parentKeyPath).append(key).toString(), target);
			}else{
				packMap(new StringBufferWrapper().append(parentKeyPath).append(key).append(".").toString(), json.getJSONObject(key), map);
			}
		}
	}
	
	/**
	 * 将map(变成key path)转换成json<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月26日 下午3:33:06
	 * @param Map<String, Object> map
	 * @return JSONObject
	 */
	public JSONObject convertFromHashMap(Map<String, Object> map) throws Exception{
		JSONObject json = new JSONObject();
		if(map==null || map.size()<=0) return json;
		Set<String> mapKeys = map.keySet();
		for(String key:mapKeys){
			String[] paths = key.split("\\.");
			JSONObject current = json;
			for(int i=0; i<paths.length; i++){
				String path = paths[i];
				if(i == paths.length-1){
					current.put(path, map.get(key));
				}else{
					JSONObject sub = current.getJSONObject(path);
					if(sub == null){
						current.put(path, new JSONObject());
						sub = current.getJSONObject(path);
					}
					current = sub;
				}
			}
		}
		return json;
	}
	
	/**
	 * 将json转换成xml<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月27日 下午1:52:18
	 * @param JSONObject json json格式
	 * @return String xml 格式
	 */
	public String convertToXml(JSONObject json) throws Exception{
		return null;
	}
	
	/**
	 * 将xml转换成json<br/>
	 * <b>作者:</b>lvdeyang<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年3月27日 下午2:55:53
	 * @param String xml xml格式
	 * @return JSONObject json格式
	 */
	public JSONObject convertFromXml(String xml) throws Exception{
		return null;
	}
	
	public static void main(String[] args) throws Exception{
		AliFastJsonObject converter = new AliFastJsonObject();
		JSONObject json = new JSONObject();
		json.put("a", new JSONObject());
		json.getJSONObject("a").put("a", "a.a");
		json.getJSONObject("a").put("b", "a.b");
		json.getJSONObject("a").put("c", new JSONObject());
		json.getJSONObject("a").getJSONObject("c").put("a", "a.c.a");
		json.getJSONObject("a").getJSONObject("c").put("b", "a.c.b");
		json.getJSONObject("a").getJSONObject("c").put("c", "a.c.c");
		json.getJSONObject("a").getJSONObject("c").put("d", new int[]{1, 2, 3});
		json.getJSONObject("a").put("d", new ArrayListWrapper<String>().add("1").add("2").add("3").getList());
		json.put("b", "b");
		json.put("c", "c");
		JSONArray dArray = new JSONArray();
		dArray.add(1);
		dArray.add(2);
		json.put("d", dArray);
		Map<String, Object> map = converter.convertToHashMap(json);
		Set<String> mapKeys = map.keySet();
		for(String key:mapKeys){
			System.out.println(new StringBufferWrapper().append(key).append("----").append(map.get(key)).toString());
		}
		json = converter.convertFromHashMap(map);
		System.out.println(json.toJSONString());
	}
	
}
