package com.example.ldu.view;


import com.example.ldu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.ldu.model.Lost;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;
public class MessageActivity extends Activity {
	private Button button0;
	private EditText etmessage1,etmessage2,etmessage3;
	private String message1,message2,message3 ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		etmessage1 = (EditText) findViewById(R.id.lost_title);
		etmessage2 = (EditText) findViewById(R.id.lost_tel);
		etmessage3 = (EditText) findViewById(R.id.lost_content);
		
		
		button0 = (Button) findViewById(R.id.btn_pubic);
		button0.setOnClickListener(new OnClickListener() {          
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.btn_pubic:
					message1 = etmessage1.getText().toString();
					message2 = etmessage2.getText().toString();
					message3 = etmessage3.getText().toString();
		            if (message1.equals("")||message2.equals("")||message3.equals("")) {
		                toast("发布内容不能为空");
		                
		                break;
		            } 
		            else {
		                Lost fe3 = new Lost();
		                fe3.setTitle(message1);
		                fe3.setTel(message2);
		                fe3.setContent(message3);
		                fe3.save(MessageActivity.this, new SaveListener() {
		                	
		                

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								toast("信息已经发布");
								Intent toHome = new Intent(MessageActivity.this,LostActivity.class);
								startActivity(toHome);
								finish();
								
							}

					
						
							@Override
							public void onFailure(int arg0, String arg1) {
								// TODO Auto-generated method stub
								
							}

		                });
		            }
		            break;
		            
			
				}
			}

			public void toast(String toast) {
				Toast.makeText(MessageActivity.this, toast, Toast.LENGTH_SHORT).show();
			}
		});
	}}
