package com.example.administrator.pinedheardlistviewdemo;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by zyr
 * DATE: 16-4-12
 * Time: 下午8:02
 * Email: yanru.zhang@renren-inc.com
 */
public abstract class CustomPinnedHeaderListViewBaseAdapter extends BaseAdapter{

    private final static String TAG = "CusPHLVBaseAdapter";

    @Override
    public abstract int getCount() ;

    @Override
    public abstract Object getItem(int position) ;

    @Override
    public abstract long getItemId(int position) ;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(isSectionHeader(position)){
            return getSectionHeaderView(getSectionId(position) , convertView ,parent);
        } else{
            return getItemView(getSectionId(position) , getPositionIdInSection(position) , convertView , parent);
        }
    }

    @Override
    public abstract int getViewTypeCount() ;

    @Override
    public abstract int getItemViewType(int position) ;

    public abstract boolean isSectionHeader(int position) ;
    public abstract int getSectionId(int position) ;
    public abstract int getSectionPosition(int sectionId);
    public abstract View getSectionHeaderView(int section, View convertView, ViewGroup parent);
    public abstract int getSectionHeaderViewType(int section) ;
    public abstract int getPositionIdInSection(int position) ;
    public abstract Object getItem(int section, int positionInSection);
    public abstract long getItemId(int section, int positionInSection);
    public abstract int getSectionCount();
    public abstract int getCountInSection(int section);
    public abstract View getItemView(int section, int positionInSection, View convertView, ViewGroup parent);

}