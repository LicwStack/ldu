package com.example.ldu.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.SaveListener;

public class Feedback extends BmobObject {

	private String content;// 意见反馈
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}