package com.example.administrator.pinedheardlistviewdemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Random;

/**
 * Created by zyr
 * DATE: 16-4-12
 * Time: 下午9:05
 * Email: yanru.zhang@renren-inc.com
 */
public class CustomPinnedHeaderListViewAdapter extends CustomPinnedHeaderListViewBaseAdapter {

    private final static String TAG = "CusPHeaderLvAdapter";
    private Context mContext;
    private List<Section> sections = new ArrayList<>();
    private int counts;
    private int sectionCounts;
    private Random random;
    public final static int TYPE_SECTION_HEADER = 1;
    public final static int TYPE_SECTION_ITEM = 2;
    public final static int TYPE_SECTION_ITEM_COUNT = 3;//getViewTypeCount() > getItemViewType返回的值


    public CustomPinnedHeaderListViewAdapter(Context context){
        this.mContext = context;
        this.random = new Random();
        initData();
    }

    @Override
    public int getCount() {
        return counts;
    }

    @Override
    public Object getItem(int position) {
        for(int i=0;i<sections.size();i++){
            Section section = sections.get(i);
            for(int j=0;j<section.sectionItemCounts;j++){
                Item item = section.items.get(j);
                if(item.position == position){
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_SECTION_ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        for(int i=0;i<sections.size();i++){
            Section section = sections.get(i);
            for(int j=0;j<section.sectionItemCounts;j++){
                Item item = section.items.get(j);
                if(item.position == position){
                    if(item.isSectionHeader){
                        return TYPE_SECTION_HEADER;
                    }else{
                        return TYPE_SECTION_ITEM;
                    }
                }
            }
        }
        Log.e(TAG, "!!!!!!!!!!!!!!!" + "no find item when getItemViewType");
        return TYPE_SECTION_ITEM;
    }

    @Override
    public boolean isSectionHeader(int position) {
        for(int i=0;i<sections.size();i++){
            Section section = sections.get(i);
            for(int j=0;j<section.sectionItemCounts;j++){
                Item item = section.items.get(j);
                if(item.position == position){
                    if(item.isSectionHeader){
                        return true;
                    }else{
                        return false;
                    }
                }
            }
        }
        Log.e(TAG, "!!!!!!!!!!!!!!!" + "no find item when isSectionHeader");
        return false;
    }

    @Override
    public int getSectionId(int position) {
        for(int i=0;i<sections.size();i++){
            Section section = sections.get(i);
            for(int j=0;j<section.sectionItemCounts;j++){
                Item item = section.items.get(j);
                if(item.position == position){
                    return item.sectionId;
                }
            }
        }
        return -1;
    }

    @Override
    public int getSectionPosition(int sectionId) {
        for(int i=0;i<sections.size();i++){
            Section section = sections.get(sectionId);
            for(int j=0;j<section.sectionItemCounts;j++){
                if(section.items.get(j).isSectionHeader){
                    return section.items.get(j).position;
                }
            }
        }
        return -1;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        Log.d(TAG,"getSectionHeaderView section:" + section);
        SectionHeaderViewHolder sectionHeaderViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_pinned_header_lv_section_header_layout,null);
            sectionHeaderViewHolder = new SectionHeaderViewHolder(convertView);
            convertView.setTag(sectionHeaderViewHolder);
        }else{
            sectionHeaderViewHolder = (SectionHeaderViewHolder)convertView.getTag();
        }
        sectionHeaderViewHolder.sectionHeaderName.setText(sections.get(section).sectionName+"");
        return convertView;
    }

    @Override
    public int getSectionHeaderViewType(int section) {
        return 0;
    }

    @Override
    public int getPositionIdInSection(int position) {
        for(int i=0;i<sections.size();i++){
            Section section = sections.get(i);
            for(int j=0;j<section.sectionItemCounts;j++){
                Item item = section.items.get(j);
                if(item.position == position){
                    return item.positionInSection;
                }
            }
        }
        Log.e(TAG, "!!!!!!!!!!!!!!!" + "no find item when getPositionIdInSection");
        return -1;
    }

    @Override
    public Object getItem(int section, int positionInSection) {
        return sections.get(section).items.get(positionInSection);
    }

    @Override
    public long getItemId(int section, int positionInSection) {
        return sections.get(section).items.get(positionInSection).position;
    }

    @Override
    public int getSectionCount() {
        return sectionCounts;
    }

    @Override
    public int getCountInSection(int section) {
        return sections.get(section).sectionItemCounts;
    }

    @Override
    public View getItemView(int section, int positionInSection, View convertView, ViewGroup parent) {
        ItemViewHolder itemViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_pinned_header_lv_section_item_layout,null);
            itemViewHolder = new ItemViewHolder(convertView);
            convertView.setTag(itemViewHolder);
        }else{
            itemViewHolder = (ItemViewHolder)convertView.getTag();
        }
        itemViewHolder.itemName.setText(getTelRandom());
        return convertView;
    }


    //联系人头部类，和联系人列表类
    class Item{
        public boolean isSectionHeader;
        public int position;
        public int sectionId;
        public int positionInSection;
        public String sectionName;
        public String itemName;

        public Item(boolean isSectionHeader,int position,int sectionId,int positionInSection,String sectionName,String itemName){
            this.isSectionHeader = isSectionHeader;
            this.position = position;
            this.sectionId = sectionId;
            this.positionInSection = positionInSection;
            this.sectionName = sectionName;
            this.itemName = itemName;
        }
    }

    class Section{
        public List<Item> items = new ArrayList<>();
        public int sectionId ;
        public char sectionName ;
        public int sectionItemCounts ;
    }

    //ViewHolder
    class ItemViewHolder{
        public TextView itemName;
        public ItemViewHolder(View view){
            itemName = (TextView) view.findViewById(R.id.tv);
        }
    }

    class SectionHeaderViewHolder{
        public TextView sectionHeaderName;
        public SectionHeaderViewHolder(View view){
            sectionHeaderName = (TextView) view.findViewById(R.id.tv);
        }
    }

    private void initData(){
        int position = 0;
        int temp = 0;
        for(int i='A';i<='Z';i++){
            Section section = new Section();
            int sectionItemCounts = (random.nextInt(10) + 1);
            section.sectionItemCounts = sectionItemCounts;
            if (sectionItemCounts==1){
                continue;
            }
            for(int j=0;j < sectionItemCounts;j++){
                Item item;
                if(j==0){
                    item = new Item(true,position,temp,j,(char)(i)+"","");
                }else {
                    item = new Item(false,position,temp,j,"","联系人:" + j);
                }
                section.items.add(item);
                position++;
            }
            section.sectionId = temp;
            ++temp;
            section.sectionName = (char)(i);
            counts = counts + section.sectionItemCounts;
            sections.add(section);
        }
        this.sectionCounts = temp;
    }

    private String getTelRandom(){
        StringBuilder builder = new StringBuilder();
        builder.append("1");
        for(int i=1;i<11;i++){
            int i1 = random.nextInt(10);
            builder.append(i1);
        }
        return builder.toString();
    }

}