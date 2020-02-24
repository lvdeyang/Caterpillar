package com.sumavision.tetris.capacity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumavision.tetris.capacity.bo.request.DeleteInputsRequest;
import com.sumavision.tetris.capacity.bo.request.DeleteOutputsRequest;
import com.sumavision.tetris.capacity.bo.request.DeleteTasksRequest;
import com.sumavision.tetris.capacity.bo.request.IdRequest;
import com.sumavision.tetris.capacity.bo.response.AllResponse;
import com.sumavision.tetris.capacity.bo.response.CreateInputsResponse;
import com.sumavision.tetris.capacity.bo.response.CreateOutputsResponse;
import com.sumavision.tetris.capacity.bo.response.CreateTaskResponse;
import com.sumavision.tetris.capacity.bo.response.ResultResponse;
import com.sumavision.tetris.capacity.enumeration.InputResponseEnum;
import com.sumavision.tetris.capacity.enumeration.OutputResponseEnum;
import com.sumavision.tetris.capacity.enumeration.TaskResponseEnum;
import com.sumavision.tetris.capacity.exception.InputResponseErrorException;
import com.sumavision.tetris.capacity.exception.OutputResponseErrorException;
import com.sumavision.tetris.capacity.exception.TaskResponseErrorException;

/**
 * 能力返回处理--回滚<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年11月20日 上午9:41:26
 */
@Service
public class ResponseService {
	
	@Autowired
	private CapacityService capacityService;
	
	/**
	 * 创建所有返回<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月28日 下午3:37:23
	 * @param AllResponse allResponse
	 */
	public void allResponseProcess(AllResponse allResponse) throws Exception{
		
		List<ResultResponse> inputs = allResponse.getInput_array();
		List<ResultResponse> tasks = allResponse.getTask_array();
		List<ResultResponse> outputs = allResponse.getOutput_array();
		System.out.println(inputs);
		System.out.println(tasks);
		System.out.println(outputs);
		if(inputs != null && inputs.size() > 0){
			for(ResultResponse input_response: inputs){
				if(!input_response.getResult_code().equals(InputResponseEnum.SUCCESS.getCode())){
					throw new InputResponseErrorException(input_response.getResult_msg());
				}
			}
		}
		
		if(tasks != null && tasks.size() > 0){
			for(ResultResponse task_response: tasks){
				if(!task_response.getResult_code().equals(TaskResponseEnum.SUCCESS.getCode())){
					throw new TaskResponseErrorException(task_response.getResult_msg());
				}
			}
		}
		
		if(outputs != null && outputs.size() > 0){
			for(ResultResponse output_response: outputs){
				if(!output_response.getResult_code().equals(InputResponseEnum.SUCCESS.getCode())){
					throw new InputResponseErrorException(output_response.getResult_msg());
				}
			}
		}

	}

	/**
	 * 创建输入返回处理--全部成功则返回所有inputId，用于task和output,
	 *               有失败则删除成功的并且抛异常<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 上午9:55:22
	 * @param CreateInputsResponse inputResponse 创建输入返回
	 * @return List<String> 成功的inputIds
	 */
	public List<String> inputResponseProcess(CreateInputsResponse inputResponse) throws Exception{
		
		List<ResultResponse> input_response_array = inputResponse.getInput_array();
		
		//成功拿出id
		List<String> ids = new ArrayList<String>();
		//失败拿出response
		List<ResultResponse> responses = new ArrayList<ResultResponse>();
		for(ResultResponse input_response: input_response_array){
			if(input_response.getResult_code().equals(InputResponseEnum.SUCCESS.getCode())){
				ids.add(input_response.getId());
			}else{
				responses.add(input_response);
			}
		}
		
		if(responses.size() > 0){
			//删除成功的输入
			deleteInputs(ids);
			
			ids.removeAll(ids);
			
			//抛一个失败异常回去
			throw new InputResponseErrorException(InputResponseEnum.fromCode(responses.get(0).getResult_code()).getMessage());
		}
		
		return ids;
	}
	
	/**
	 * 创建任务返回处理--全部成功则返回所有taskId，用于output,
	 *               有失败则删除输入和成功的任务并且抛异常<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 下午1:25:28
	 * @param CreateTaskResponse taskResponse 创建任务返回
	 * @param List<String> inputIds 成功的inputIds
	 * @return List<String> 成功的taskIds
	 */
	public List<String> taskResponseProcess(CreateTaskResponse taskResponse, List<String> inputIds) throws Exception{
		
		List<ResultResponse> task_response_array = taskResponse.getTask_array();
		
		//成功拿出id
		List<String> ids = new ArrayList<String>();
		//失败拿出response
		List<ResultResponse> responses = new ArrayList<ResultResponse>();
		for(ResultResponse task_response: task_response_array){
			if(task_response.getResult_code().equals(TaskResponseEnum.SUCCESS.getCode())){
				ids.add(task_response.getId());
			}else{
				responses.add(task_response);
			}
		}
		
		if(responses.size() > 0){
			//删除任务
			deleteTasks(ids);
			//删除输入
			deleteInputs(inputIds);
			
			ids.removeAll(ids);
			inputIds.removeAll(inputIds);
			
			//抛异常
			throw new TaskResponseErrorException(responses.get(0).getResult_msg());
		}
		
		return ids;
	}
	
	/**
	 * 创建输出返回处理--有失败则删除输入和任务并且抛异常<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月26日 下午2:25:02
	 * @param outputsResponse
	 * @param taskIds
	 * @param inputIds
	 */
	public List<String> outputResponseProcess(CreateOutputsResponse outputsResponse, List<String> taskIds, List<String> inputIds) throws Exception{
		
		List<ResultResponse> output_response_array = outputsResponse.getOutput_array();
		
		//成功取出id
		List<String> ids = new ArrayList<String>();
		//失败拿出response
		List<ResultResponse> responses = new ArrayList<ResultResponse>();
		for(ResultResponse output_response: output_response_array){
			if(output_response.getResult_code().equals(OutputResponseEnum.SUCCESS.getCode())){
				ids.add(output_response.getId());
			}else{
				responses.add(output_response);
			}
		}
		
		if(responses.size() > 0){
			//删除输出
			if(ids != null && ids.size() > 0){
				deleteOutputs(ids);
				ids.removeAll(ids);
			}
			//删除任务
			if(taskIds != null && taskIds.size() > 0){
				deleteTasks(taskIds);
				taskIds.removeAll(taskIds);
			}
			//删除输入
			if(inputIds != null && inputIds.size() > 0){
				deleteInputs(inputIds);
				inputIds.removeAll(inputIds);
			}

			//抛异常
			throw new OutputResponseErrorException(OutputResponseEnum.fromCode(responses.get(0).getResult_code()).getMessage());
		}
		
		return ids;
		
	}
	
	/**
	 * 根据id删除input<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 上午10:02:44
	 * @param List<String> ids 需要删除的inputId
	 */
	public void deleteInputs(List<String> ids) throws Exception{
		
		if(ids != null && ids.size() > 0){
			DeleteInputsRequest delete = new DeleteInputsRequest().setInput_array(new ArrayList<IdRequest>());
			for(String id: ids){
				IdRequest request = new IdRequest().setId(id);
				delete.getInput_array().add(request);
			}
			
			capacityService.deleteInputsAddMsgId(delete);
		}

	}
	
	/**
	 * 根据id删除task<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 下午1:15:09
	 * @param List<String> ids 需要删除的taskIds
	 */
	public void deleteTasks(List<String> ids) throws Exception{
		
		if(ids != null && ids.size() > 0){
			DeleteTasksRequest delete = new DeleteTasksRequest().setTask_array(new ArrayList<IdRequest>());
			for(String id: ids){
				IdRequest request = new IdRequest().setId(id);
				delete.getTask_array().add(request);
			}
			
			capacityService.deleteTasksAddMsgId(delete);
		}

	}
	
	/**
	 * 根据id删除output<br/>
	 * <b>作者:</b>wjw<br/>
	 * <b>版本：</b>1.0<br/>
	 * <b>日期：</b>2019年11月20日 下午1:19:27
	 * @param List<String> ids 需要删除的outputIds
	 */
	public void deleteOutputs(List<String> ids) throws Exception{
		
		if(ids != null && ids.size() > 0){
			DeleteOutputsRequest delete = new DeleteOutputsRequest().setOutput_array(new ArrayList<IdRequest>());
			for(String id: ids){
				IdRequest request = new IdRequest().setId(id);
				delete.getOutput_array().add(request);
			}
			
			capacityService.deleteOutputsAddMsgId(delete);
		}

	}
	
}
