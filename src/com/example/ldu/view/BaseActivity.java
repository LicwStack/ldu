package com.example.ldu.view;

import com.example.ldu.R;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class BaseActivity extends TabActivity {

	private TabHost tabHost;
	private LayoutInflater layoutInflater;

	String[] mTitle = new String[] { "校园资讯", "失物招领","兼职", "设置", "我" };
	int[] mIcon = new int[] { R.drawable.ic_shop, R.drawable.ic_sale,
			R.drawable.ic_car, R.drawable.ic_set, R.drawable.ic_mine };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		initTabView();
	}

	public View getTabItemView(int i) {
		// TODO Auto-generated method stub
		//View view = layoutInflater.inflate(R.layout.tab_widget_item, null);
		 View view = layoutInflater.inflate(R.layout.tab_widget_item, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mIcon[i]);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTitle[i]);
		return view;
	}

	public void initTabView() {

		tabHost = getTabHost();
		layoutInflater = LayoutInflater.from(this);
		TabHost.TabSpec spec;

		Intent intent1 = new Intent(this, HomeActivity.class);
		spec = tabHost.newTabSpec(mTitle[0]).setIndicator(getTabItemView(0)).setContent(intent1);
		tabHost.addTab(spec);
		
        Intent intent2 = new Intent(this, LostActivity.class);  
        spec = tabHost.newTabSpec(mTitle[1]).setIndicator( getTabItemView(1) ).setContent(intent2);  
        tabHost.addTab(spec); 
        
        Intent intent3 = new Intent(this, JobActivity.class);  
        spec = tabHost.newTabSpec(mTitle[2]).setIndicator( getTabItemView(2) ).setContent(intent3);  
        tabHost.addTab(spec);
 
        Intent intent4 = new Intent(this, SettingsActivity.class);  
        spec = tabHost.newTabSpec(mTitle[3]).setIndicator( getTabItemView(3) ).setContent(intent4);  
        tabHost.addTab(spec); 
        
        Intent intent5 = new Intent(this, MineActivity.class);  
        spec = tabHost.newTabSpec(mTitle[4]).setIndicator( getTabItemView(4) ).setContent(intent5);  
        tabHost.addTab(spec); 

		tabHost.setCurrentTab(0);
	}
	@Override
	public void onBackPressed() {
		Toast.makeText(this, "确定要退出?", Toast.LENGTH_LONG).show();
		//super.onBackPressed();
	}
}
