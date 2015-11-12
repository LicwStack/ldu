package com.example.ldu.view;
import cn.bmob.v3.Bmob;
import com.example.ldu.R;
import android.app.Activity;
import android.os.Bundle;
import com.example.ldu.model.Feedback;
import com.example.ldu.model.User;
import com.example.util.Util;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.listener.SaveListener;
import android.os.Handler;
import android.os.Message;
public class FeedbackActivity extends Activity {
	private Button button0;
	private EditText etFeedback;
	private String feedback ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);

		etFeedback = (EditText) findViewById(R.id.et_feedback);

		button0 = (Button) findViewById(R.id.button0);
		button0.setOnClickListener(new OnClickListener() {          
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.button0:
					feedback = etFeedback.getText().toString();

		            
		            if (feedback.equals("")) {
		                toast("亲, 内容不能为空。");
		                
		                break;
		            } 
		            else {
		                Feedback fe3 = new Feedback();
		                fe3.setContent(feedback);
		                fe3.save(FeedbackActivity.this, new SaveListener() {
		                	
		                

							@Override
							public void onSuccess() {
								// TODO Auto-generated method stub
								toast("谢谢您的反馈(⊙o⊙)");
								Intent toHome = new Intent(FeedbackActivity.this,
										SettingsActivity.class);
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
				Toast.makeText(FeedbackActivity.this, toast, Toast.LENGTH_SHORT).show();
			}
		});
	}}