package com.example.ldu.model;

import cn.bmob.v3.BmobObject;

public class News extends BmobObject {

	private String type; // 新闻类型
	private String title; // 新闻标题
	private String author; // 新闻作者
	private String content; // 新闻内容

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
