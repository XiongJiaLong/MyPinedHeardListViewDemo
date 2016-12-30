package com.example.administrator.pinedheardlistviewdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CustomPinnedHeaderListView listViewMain;
    CustomPinnedHeaderListView listView;
    CustomPinnedHeaderListViewAdapter adapter;
    FloatingActionButton actionButton;
    View view;
    AlertDialog dialog;
    private View headerView;
    private View footerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionButton = (FloatingActionButton) findViewById(R.id.mFloatButton);
        listViewMain = (CustomPinnedHeaderListView) findViewById(R.id.mPinnedListView);
//        initListView(listViewMain);
        view = LayoutInflater.from(this).inflate(R.layout.my_dialog_item,null);
        listView = (CustomPinnedHeaderListView) view.findViewById(R.id.mCustomDialogView);
        initListView(listView);
        initAlertDialog();
        actionButton.setOnClickListener(this);
    }

    private void initListView(CustomPinnedHeaderListView pinnedHeaderListViewlistView){
        headerView = LayoutInflater.from(this).inflate(R.layout.cus_pinned_header_lv_header,null);
        footerView = LayoutInflater.from(this).inflate(R.layout.cus_pinned_header_lv_footer,null);
        pinnedHeaderListViewlistView.addHeaderView(headerView);
        pinnedHeaderListViewlistView.addFooterView(footerView);
        adapter = new CustomPinnedHeaderListViewAdapter(this);
        pinnedHeaderListViewlistView.setAdapter(adapter);
        pinnedHeaderListViewlistView.setOnMyItemClickListener(new CustomPinnedHeaderListView.MyOnItemClickListener() {
            @Override
            public void onSectionItemClick(AdapterView<?> adapterView, View view, int section, int position, long id) {
//                Toast.makeText(MainActivity.this,"s:" + section + "    item:" + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSectionClick(AdapterView<?> adapterView, View view, int section, long id) {
//                Toast.makeText(MainActivity.this,"s:" + section+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onHeaderClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,"header:" + position,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFooterClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(MainActivity.this,"footer:" + position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Log.e("===========","========="+"点击了floatingButton");
        if (dialog.isShowing()){
            dialog.dismiss();
        }else {
            dialog.show();
        }
    }

    private void initAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this,R.style.mDialog);
        builder.setView(view);
//        builder.setMessage("This is the message!");
//        builder.setTitle("This is the title!");
//        builder.setIcon(R.mipmap.ic_launcher);
//        builder.setNegativeButton("Nega",null);
//        builder.setPositiveButton("Posi",null);
          dialog = builder.create();
//        WindowManager manager = getWindowManager();
//        DisplayMetrics metrics = new DisplayMetrics();
//        manager.getDefaultDisplay().getMetrics(metrics);
//        Window window = dialog.getWindow();
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.height = metrics.heightPixels;
//        lp.width = metrics.widthPixels;
//        window.setAttributes(lp);
//        Log.e("=======","======"+metrics.heightPixels+"=="+metrics.widthPixels);
    }
}
