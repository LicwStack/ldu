package com.example.ldu.view;

import com.example.ldu.R;
import com.example.ldu.model.User;
import com.example.util.Util;

import cn.bmob.push.BmobPush;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;
import cn.bmob.v3.BmobPushManager;
import cn.bmob.v3.listener.SaveListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity implements OnClickListener {
	
	private Button btnLogin;
	private TextView tvReg;
	private EditText etUsername;
	private EditText etPassword;

	private String username;
	private String password;
	
	BmobPushManager bmobPushManager;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// 初始化 Bmob SDK		
		Bmob.initialize(this, "8e5f64d658737ea7ec75af59950e0b88");
		// 使用推送服务时的初始化操作
		BmobInstallation.getCurrentInstallation(this).save();		    
		// 启动推送服务
		BmobPush.startWork(this, "8e5f64d658737ea7ec75af59950e0b88");	    
		// 创建推送消息的对象
		bmobPushManager = new BmobPushManager(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		btnLogin = (Button) findViewById(R.id.btn_login);
		tvReg = (TextView) findViewById(R.id.tv_register);
		
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
		
		btnLogin.setOnClickListener(this);
		tvReg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
            username = etUsername.getText().toString();
            password = etPassword.getText().toString();
            
            if( !Util.isNetworkConnected(this) ){
                toast("亲, 没有网络 ( ⊙ o ⊙ ) ");
            } 
            else if (username.equals("") || password.equals("")) {
                toast("亲, 请输入学号号和密码");
                break;
            } 
            else {
                User bu2 = new User();
                bu2.setUsername(username);
                bu2.setPassword(password);
                bu2.login(this, new SaveListener() {

					@Override
					public void onFailure(int arg0, String arg1) {
						// TODO Auto-generated method stub
						toast("账户或密码错误, 无法登录~");
					}

					@Override
					public void onSuccess() {
						// TODO Auto-generated method stub
						toast("欢迎登录~");
						Intent toHome = new Intent(LoginActivity.this,
								ZhuActivity.class);
						startActivity(toHome);
						finish();
						
					}

                });
            }
            break;
            
		case R.id.tv_register:
			Intent toReg = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(toReg);
			break;
		default:
			break;
		}
	}
	
	public void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
}
