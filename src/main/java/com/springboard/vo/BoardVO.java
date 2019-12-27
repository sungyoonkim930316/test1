package com.springboard.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {

	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private Date updateDate;
	private boolean deleted;
	private int readCount;
	
}
