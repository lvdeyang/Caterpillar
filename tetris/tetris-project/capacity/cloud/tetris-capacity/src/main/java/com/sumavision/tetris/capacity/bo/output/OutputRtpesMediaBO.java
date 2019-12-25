package com.sumavision.tetris.capacity.bo.output;

public class OutputRtpesMediaBO {

	private String task_id;
	
	private String encode_id;
	
	private Integer payload_type;

	public String getTask_id() {
		return task_id;
	}

	public OutputRtpesMediaBO setTask_id(String task_id) {
		this.task_id = task_id;
		return this;
	}

	public String getEncode_id() {
		return encode_id;
	}

	public OutputRtpesMediaBO setEncode_id(String encode_id) {
		this.encode_id = encode_id;
		return this;
	}

	public Integer getPayload_type() {
		return payload_type;
	}

	public OutputRtpesMediaBO setPayload_type(Integer payload_type) {
		this.payload_type = payload_type;
		return this;
	}
	
}
