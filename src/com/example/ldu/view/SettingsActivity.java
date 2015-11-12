package com.example.ldu.view;

import com.example.ldu.R;



import android.app.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;



public class SettingsActivity extends Activity {
    /** Called when the activity is first created. */	
    Button button1=null;
    Button button2=null;
    Button button3=null;
    Button button4=null;
    Button button5=null;
    Button button6=null;
    Button button7=null;
    Button button8=null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); 
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button5=(Button)findViewById(R.id.button5);
        button6=(Button)findViewById(R.id.button6);
        
        
        
        button1.setOnClickListener(new OnClickListener(){
        	@Override
            public void onClick(View v){
        		Intent intent=new Intent();
        		intent.setClass(SettingsActivity.this,FeedbackActivity.class);
        	    startActivity(intent);    
        	}
        });
        button2.setOnClickListener(new OnClickListener(){
        	@Override
            public void onClick(View v){
        		Intent intent=new Intent();
        		intent.setClass(SettingsActivity.this,HelpActivity.class);
        	    startActivity(intent);    
        	}
        });
        button3.setOnClickListener(new OnClickListener(){
        	@Override
            public void onClick(View v){
        		Intent intent=new Intent();
        		intent.setClass(SettingsActivity.this,MyGuideViewActivity.class);
        	    startActivity(intent);    
        	}
        });

        button5.setOnClickListener(new OnClickListener(){
        	@Override
            public void onClick(View v){
        		Intent intent=new Intent();
        		intent.setClass(SettingsActivity.this,AboutActivity.class);
        	    startActivity(intent);    
        	}
        });
        button6.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//如果只是调用以下其中的一个方法，并不会完全退出应用
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(0);
				
				
				
			}
		});
    }
    
   
}
