package com.my.taskmanage.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.my.taskmanage.R;
import com.my.taskmanage.adapter.TaskAdapter;
import com.my.taskmanage.db.DbHelper;
import com.my.taskmanage.entity.TaskEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private RecyclerView rv;
    private Button tvAdd;
    private List<TaskEntity> mList;
    private TaskAdapter mAdapter;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
        initEvent();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        tv1.setBackgroundResource(R.color.colorPrimary);
        tv1.setTextColor(getResources().getColor(R.color.white));
        tv2.setBackgroundResource(R.color.white);
        tv2.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv3.setBackgroundResource(R.color.white);
        tv3.setTextColor(getResources().getColor(R.color.colorPrimary));
        tv4.setBackgroundResource(R.color.white);
        tv4.setTextColor(getResources().getColor(R.color.colorPrimary));
        initData("P1");
    }
    
    private void initData(String type) {
        if (mList!=null){
            mList.clear();
        }
        DbManager dbManager= DbHelper.getInstance().getDbManager();
    
        switch (type){
            case "P1":
                try {
                    mList=dbManager.selector(TaskEntity.class).where("isUrgent","==",true).and("isImportant","==",true).findAll();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case "P2":
                try {
                    mList=dbManager.selector(TaskEntity.class).where("isUrgent","==",false).and("isImportant","==",true).findAll();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case "P3":
                try {
                    mList=dbManager.selector(TaskEntity.class).where("isUrgent","==",true).and("isImportant","==",false).findAll();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
            case "P4":
                try {
                    mList=dbManager.selector(TaskEntity.class).where("isUrgent","==",false).and("isImportant","==",false).findAll();
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
        }
        mAdapter.setData(mList);
        mAdapter.notifyDataSetChanged();
        rv.setAdapter(mAdapter);
    }
    
    private void initView() {
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        rv = (RecyclerView) findViewById(R.id.rv);
        tvAdd = (Button) findViewById(R.id.tv_add);
        mList=new ArrayList<>();
        mAdapter=new TaskAdapter(this,mList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(mAdapter);
    }
    
    private void initEvent() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent=new Intent(MainActivity.this,TaskDetailsActivity.class);
                intent.putExtra("id",mList.get(position).getId());
                startActivity(intent);
            }
        });
    }
    
   
    
    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv1:
                initData("P1");
                tv1.setBackgroundResource(R.color.colorPrimary);
                tv1.setTextColor(getResources().getColor(R.color.white));
                tv2.setBackgroundResource(R.color.white);
                tv2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv3.setBackgroundResource(R.color.white);
                tv3.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv4.setBackgroundResource(R.color.white);
                tv4.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.tv2:
                initData("P2");
                tv1.setBackgroundResource(R.color.white);
                tv1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv2.setBackgroundResource(R.color.colorPrimary);
                tv2.setTextColor(getResources().getColor(R.color.white));
                tv3.setBackgroundResource(R.color.white);
                tv3.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv4.setBackgroundResource(R.color.white);
                tv4.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.tv3:
                initData("P3");
                tv1.setBackgroundResource(R.color.white);
                tv1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv2.setBackgroundResource(R.color.white);
                tv2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv3.setBackgroundResource(R.color.colorPrimary);
                tv3.setTextColor(getResources().getColor(R.color.white));
                tv4.setBackgroundResource(R.color.white);
                tv4.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case R.id.tv4:
                initData("P4");
                tv1.setBackgroundResource(R.color.white);
                tv1.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv2.setBackgroundResource(R.color.white);
                tv2.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv3.setBackgroundResource(R.color.white);
                tv3.setTextColor(getResources().getColor(R.color.colorPrimary));
                tv4.setBackgroundResource(R.color.colorPrimary);
                tv4.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_add:
                Intent intent=new Intent(MainActivity.this,AddTaskActivity.class);
                startActivity(intent);
                break;
        }
    }
}
