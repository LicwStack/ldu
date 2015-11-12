package com.example.ldu.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.CountListener;

import com.example.data.MessageDef;
import com.example.ldu.R;
import com.example.ldu.adapter.MineListAdapter;
import com.example.ldu.model.Order;

/**
 * 个人中心主界面
 * @date  2014-4-24
 */
public class MineActivity extends Activity implements OnItemClickListener{
	
	@SuppressWarnings("unused")
	private static final String TAG = "MineActivity" ;
	
	private String[] userItemNames = {"stonekity"} ;
	private String[] userItemContents = {""} ;
	private String[] aboutItemNames = {"通知中心", "软件相关", "推荐给朋友", "退出账号"};
	private String[] aboutItemContents = {"", "", "", ""};
	private int[] userImgIds = {R.drawable.ic_menu_myplaces};
	private int[] aboutImgIds = {R.drawable.ic_menu_notifications, R.drawable.ic_menu_info_details, R.drawable.ic_menu_share, R.drawable.ic_star_yes};
	
	private ListView lvMineUser;
	private ListView lvMineAbout;
	private MineListAdapter userListAdapter;
	private MineListAdapter orderListAdapter;
	private MineListAdapter aboutListAdapter;
	
	private Handler mHandler = new Handler() {  
		  @Override  
		  public void handleMessage(Message msg) {  
		      switch (msg.what) {
				case MessageDef.MINE_FINISH_LOAD_DATA:
					//toast("Handler 收到数据加载完成的消息");
					orderListAdapter.notifyDataSetChanged();
					break;
				default:
					break;
				}
		  }  
	};  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mine);
		
		initData("已取餐");
		initData("未取餐");
		initView();
	}
	
	private void initView() {
		
		lvMineUser = (ListView) findViewById(R.id.lv_mine_user);
		lvMineAbout = (ListView) findViewById(R.id.lv_mine_about);	
		userListAdapter = new MineListAdapter(this, userItemNames, userItemContents, userImgIds);
		aboutListAdapter = new MineListAdapter(this, aboutItemNames, aboutItemContents, aboutImgIds);		
		lvMineUser.setAdapter(userListAdapter);
		lvMineAbout.setAdapter(aboutListAdapter);		
		lvMineUser.setOnItemClickListener(this);
		lvMineAbout.setOnItemClickListener(this);
		
	}
	
	//初始化列表菜单中数据
	public void initData(final String type) {
		//获取用户
		BmobUser user = BmobUser.getCurrentUser(this);
		userItemNames[0] = user.getUsername();

		BmobQuery<Order> query = new BmobQuery<Order>();
		query.order("-updatedAt");
		query.addWhereEqualTo("userName", user.getUsername());
		query.addWhereEqualTo("state", type);
		query.count(this, Order.class, new CountListener() {
			
			@Override
			public void onSuccess(int count) {
				Message msg = new Message();
				msg.what = MessageDef.MINE_FINISH_LOAD_DATA;
				mHandler.sendMessage(msg);
			}
			
			@Override
			public void onFailure(int arg0, String arg1) {
			//	toast("查询失败");
			}
		});
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		//个人资料
		if(parent.getId() == R.id.lv_mine_user) {
			switch (position) {
			case 0:		//资料卡
				Intent toMineInfo = new Intent(MineActivity.this, MineInfoActivity.class);
				startActivity(toMineInfo);
				break;

			default:
				break;
			}
		}
		
		
		//其他
		if(parent.getId() == R.id.lv_mine_about) {
			
			switch (position) {
			case 1: 	//软件相关
				Intent toMineSoft = new Intent(MineActivity.this, MineSoftActivity.class);
				startActivity(toMineSoft);
				break;
			case 2:		//推荐给朋友
				Intent toShare = new Intent(Intent.ACTION_SEND);
				toShare.setType("text/plain");
				toShare.putExtra(Intent.EXTRA_SUBJECT, "分享");
				toShare.putExtra(Intent.EXTRA_TEXT, "校园鲁大-HBUT版" +"\n" + "针对鲁大测试版上线了，赶紧下载体验吧"
						+ "http://luda.bmob.cn");
				startActivity(Intent.createChooser(toShare, "分享到"));
				break;
			case 3:		//退出当期账号
				BmobUser.logOut(this);
				Intent toLogin = new Intent(MineActivity.this, LoginActivity.class);
				startActivity(toLogin);
				finish();
				break;

			default:
				//toast("点击了通知区域");
				break;
			}
			
		}
		
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
	

}
