package com.sumavision.tetris.capacity.bo.task;

/**
 * 屏幕布局参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午4:48:04
 */
public class RectBO {

	/** 布局id */
	private String rect_id;
	
	/** 使用万分比[0,10000] */
	private Integer x;
	
	private Integer y;
	
	private Integer z_index;
	
	private Integer width;
	
	private Integer height;
	
	/** 当前使用的源,main/second,当main和second均不存在时，该值不生效 */
	private String use_source;
	
	private TaskSourceBO main;
	
	private TaskSourceBO second;

	public String getRect_id() {
		return rect_id;
	}

	public RectBO setRect_id(String rect_id) {
		this.rect_id = rect_id;
		return this;
	}

	public Integer getX() {
		return x;
	}

	public RectBO setX(Integer x) {
		this.x = x;
		return this;
	}

	public Integer getY() {
		return y;
	}

	public RectBO setY(Integer y) {
		this.y = y;
		return this;
	}

	public Integer getZ_index() {
		return z_index;
	}

	public RectBO setZ_index(Integer z_index) {
		this.z_index = z_index;
		return this;
	}

	public Integer getWidth() {
		return width;
	}

	public RectBO setWidth(Integer width) {
		this.width = width;
		return this;
	}

	public Integer getHeight() {
		return height;
	}

	public RectBO setHeight(Integer height) {
		this.height = height;
		return this;
	}

	public String getUse_source() {
		return use_source;
	}

	public RectBO setUse_source(String use_source) {
		this.use_source = use_source;
		return this;
	}

	public TaskSourceBO getMain() {
		return main;
	}

	public RectBO setMain(TaskSourceBO main) {
		this.main = main;
		return this;
	}

	public TaskSourceBO getSecond() {
		return second;
	}

	public RectBO setSecond(TaskSourceBO second) {
		this.second = second;
		return this;
	}
}
