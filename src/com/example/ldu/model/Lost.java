package com.example.ldu.model;

import cn.bmob.v3.BmobObject;

public class Lost extends BmobObject {

	private String type; // 新闻类型
	private String title; // 新闻标题
	private String contacts; // 新闻作者
	private String content; // 新闻内容
	private String tel; // 新闻作者
	
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
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
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
