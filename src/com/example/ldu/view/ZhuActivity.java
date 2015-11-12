package com.example.ldu.view;

import com.example.ldu.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ZhuActivity extends Activity {
	//private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhu);
		findViewById(R.id.zhuYe).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(ZhuActivity.this,BaseActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}
