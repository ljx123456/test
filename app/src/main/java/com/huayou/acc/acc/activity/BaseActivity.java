package com.huayou.acc.acc.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.huayou.acc.acc.R;

/**
 * Created by alu on 2017/5/8.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addLayout();
        initView();
    }
    //findViewById初始化控件的方法
    public abstract void initView();
    //加载布局的方法
    public abstract void addLayout();
    //吐司方法
    public void showToast(String str){
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    //重写Toast方法1
    public void showToast(int num){
        Toast.makeText(this,num+"",Toast.LENGTH_SHORT).show();
    }
    //重写Toast方法2
    public void showToast(double num){
        Toast.makeText(this,num+"",Toast.LENGTH_SHORT).show();
    }
    //跳转方法
    public void gotoActivity(Class<?> activity,int num) {

        if(num>0){//进入子菜单
            Intent intent = new Intent(this,activity);
            startActivity(intent);
            overridePendingTransition(R.anim.leftin, R.anim.rightout);
        }else{//退出子页面
            finish();
            overridePendingTransition(R.anim.lefthuanyuan,R.anim.lefthuan);
        }
    }
    //重写跳转方法1
    public void gotoActivity(Class<?> activity,String str1,String str2) {
        Intent intent = new Intent(this,activity);
        intent.putExtra(str1,str2);
        startActivity(intent);
        overridePendingTransition(R.anim.leftin,R.anim.rightout);
    }

}
