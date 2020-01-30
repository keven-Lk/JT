package com.jt.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileVo {
	/**
	//文件上传后的VO对象
	 * error = 0
	 * url:图片的保存路径
	 * width:图片的宽度
	 * height:图片的高度
	 */
	private Integer error = 0;
	private String url;
	private Integer width;
	private Integer height;
	
	public FileVo() {
		super();
	}
	
	public FileVo(Integer error, String url, Integer width, Integer height) {
		super();
		this.error = error;
		this.url = url;
		this.width = width;
		this.height = height;
	}
	
}
