package com.example.ldu.model;

import cn.bmob.v3.BmobObject;

public class Job extends BmobObject {

	private String title; // 新闻标题
	private String tel; // 新闻作者
	private String content; // 新闻内容
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
}
