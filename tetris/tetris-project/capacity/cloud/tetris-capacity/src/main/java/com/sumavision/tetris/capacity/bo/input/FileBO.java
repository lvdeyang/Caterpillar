package com.sumavision.tetris.capacity.bo.input;

/**
 * file参数<br/>
 * <b>作者:</b>wjw<br/>
 * <b>版本：</b>1.0<br/>
 * <b>日期：</b>2019年10月29日 下午3:24:59
 */
public class FileBO {

	private String url;
	
	private String file_start;
	
	private String file_end;

	public String getUrl() {
		return url;
	}

	public FileBO setUrl(String url) {
		this.url = url;
		return this;
	}

	public String getFile_start() {
		return file_start;
	}

	public FileBO setFile_start(String file_start) {
		this.file_start = file_start;
		return this;
	}

	public String getFile_end() {
		return file_end;
	}

	public FileBO setFile_end(String file_end) {
		this.file_end = file_end;
		return this;
	}
	
}
