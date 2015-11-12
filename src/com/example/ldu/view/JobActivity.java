package com.example.ldu.view;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.ldu.R;
import com.example.ldu.adapter.ImagePagerAdapter;
import com.example.ldu.adapter.JobListAdapter;
import com.example.ldu.adapter.LostListAdapter;
import com.example.ldu.model.Job;
import com.example.ui.AutoScrollViewPager;
import com.example.ui.ListScrollView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("ShowToast")
public class JobActivity extends Activity implements OnClickListener,
OnItemClickListener {
	private Button btnjob;
	
    private ListScrollView listScrollView;

    // 图片轮播
    private FrameLayout flImageAds;
    private AutoScrollViewPager viewPager;
    private List<View> mImgViews;
    private ImageButton btnHideAds;
    private int[] mImgResId = { R.drawable.ic_banner1, R.drawable.ic_banner1,
            R.drawable.ic_banner1, R.drawable.ic_banner1 };

    // 校园新闻
    private ListView lvNewsList;
    private List<Job> newsList = new ArrayList<Job>();
    private JobListAdapter JobListAdapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job);
		
		 btnjob=(Button)findViewById(R.id.job_button);
		 btnjob.setOnClickListener(new OnClickListener(){
	        	@Override
	            public void onClick(View v){
	        		Intent intent=new Intent();
	        		intent.setClass(JobActivity.this,ParttimeActivity.class);
	        	    startActivity(intent);    
	        	}
	        });
		
        // 解决ScrollView和ListView之间的冲突
        listScrollView = (ListScrollView) findViewById(R.id.listScrollView);
        lvNewsList = (ListView) findViewById(R.id.lv_news);
        listScrollView.setListView(lvNewsList);
        
        flImageAds = (FrameLayout) findViewById(R.id.fl_image_ads);
        viewPager = (AutoScrollViewPager) findViewById(R.id.view_pager);
        btnHideAds = (ImageButton) findViewById(R.id.btn_hide_ads);
        
        mImgViews = new ArrayList<View>();
        for (int i = 0; i < mImgResId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(mImgResId[i]);
            imageView.setScaleType(ScaleType.CENTER_CROP);
            mImgViews.add(imageView);
        }
        
        btnHideAds.setOnClickListener(this);
        
        viewPager.setAdapter(new ImagePagerAdapter(this, mImgViews));
        viewPager.setInterval(3000); // 设置自动滚动的间隔时间，单位为毫秒
        viewPager.setDirection(AutoScrollViewPager.RIGHT); // 设置自动滚动的方向，默认向右
        viewPager.setCycle(true); // 是否自动循环轮播，默认为true
        viewPager.setScrollDurationFactor(3); // 设置ViewPager滑动动画间隔时间的倍率，达到减慢动画或改变动画速度的效果
        viewPager.setStopScrollWhenTouch(true); // 当手指碰到ViewPager时是否停止自动滚动，默认为true
        viewPager.setBorderAnimation(true); // 设置循环滚动时滑动到从边缘滚动到下一个是否需要动画，默认为true
        viewPager.setSlideBorderMode(AutoScrollViewPager.SLIDE_BORDER_MODE_NONE);// 滑动到第一个或最后一个Item的处理方式，支持没有任何操作、轮播以及传递到父View三种模式

        viewPager.startAutoScroll();

        // 新闻
        JobListAdapter = new com.example.ldu.adapter.JobListAdapter(this, newsList);
        lvNewsList.setAdapter(JobListAdapter);
        lvNewsList.setOnItemClickListener(this);

        getNewsData();
	}

    
    /**
     * 初始化新闻列表数据
     */
    public void getNewsData() {
        BmobQuery<Job> query = new BmobQuery<Job>();
        query.order("-updatedAt");
        query.findObjects(this, new FindListener<Job>() {

            @Override
            public void onSuccess(List<Job> object) {
                newsList = object;
                // 通知Adapter数据更新
                JobListAdapter.refresh((ArrayList<Job>) newsList);
                JobListAdapter.notifyDataSetChanged();
            }

			@Override
			public void onError(int arg0, String arg1) {
				// TODO Auto-generated method stub
				toast("获取数据失败了");
			}
        });
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // start auto scroll when onResume
        viewPager.startAutoScroll();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        Intent toNewsDetail = new Intent(JobActivity.this, NewsActivity.class);
        toNewsDetail.putExtra("NewsTitle", newsList.get(position).getTitle());
        toNewsDetail.putExtra("NewsAuthor", newsList.get(position).getTel());
        toNewsDetail.putExtra("NewsTime", newsList.get(position).getCreatedAt());
        toNewsDetail.putExtra("NewsContent", newsList.get(position).getContent());
        startActivity(toNewsDetail);
    }

	@Override
	public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_hide_ads:
            flImageAds.setVisibility(View.GONE);
            break;

        default:
            break;
        }	
	}
	
    public void toast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT);
    }

}
