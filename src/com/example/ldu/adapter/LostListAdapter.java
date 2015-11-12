package com.example.ldu.adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.ldu.R;
import com.example.ldu.model.Lost;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LostListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater = null;
    private List<Lost> mNewsList = null; 

    public LostListAdapter(Context context, List<Lost> newsList) {
        mContext = context;
        mNewsList = newsList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mNewsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mNewsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // 刷新列表中的数据
    public void refresh(ArrayList<Lost> list) {
        mNewsList = list;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsHolder newsHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.news_list_item, null);
            newsHolder = new NewsHolder();
            newsHolder.tvNewsType = (TextView) convertView
                    .findViewById(R.id.tv_news_type);
            newsHolder.tvNewsTitle = (TextView) convertView
                    .findViewById(R.id.tv_news_title);
            newsHolder.tvNewsDate = (TextView) convertView
                    .findViewById(R.id.tv_news_date);
            convertView.setTag(newsHolder);
        } else {
            newsHolder = (NewsHolder) convertView.getTag();
        }
        //拆分字符串，只取年月日
        String[] ss = new String[2];
        ss = mNewsList.get(position).getCreatedAt().split(" ");
        newsHolder.tvNewsType.setText(mNewsList.get(position).getType());   //新闻类型
        newsHolder.tvNewsTitle.setText(mNewsList.get(position).getTitle()); //新闻标题
        newsHolder.tvNewsDate.setText(ss[0]);   //新闻发布日期
        return convertView;
    }

}