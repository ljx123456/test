package com.huayou.acc.acc.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.huayou.acc.acc.R;
import com.huayou.acc.acc.adapter.DataAdapter;
import com.huayou.acc.acc.bean.P;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by alu on 2017/6/13.
 */

public class Activity extends BaseActivity {
    private LRecyclerView list;
    private LRecyclerViewAdapter adapter;
    private DataAdapter mDataAdapter;

    @Override
    public void initView() {
        list= (LRecyclerView) findViewById(R.id.list);
        ArrayList<P> list=new ArrayList<>();
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));
        list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));list.add(new P("1",1));

        mDataAdapter=new DataAdapter(this);
        mDataAdapter.addList(list);
        adapter=new LRecyclerViewAdapter(mDataAdapter);


    }

    @Override
    public void addLayout() {
        setContentView(R.layout.test);
    }

//    private TextInputEditText edt;
//    private TextView tv_word;
//    private int cursor = 0;// 用来记录输入字符的时候光标的位置
//    private int before_length;// 用来标注输入某一内容之前的编辑框中的内容的长度
//
//    @Override
//    public void initView() {
//        edt= (TextInputEditText) findViewById(R.id.edt);
//        tv_word= (TextView) findViewById(R.id.word);
//        edt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                before_length = charSequence.length();
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                cursor = i;
//                if(edt.getText().toString().length()<=100){
//                    tv_word.setText(edt.getText().toString().length()+"/100");
//                }
////      Log.e("此时光标的位置为", cursor + "");
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // 这里可以知道你已经输入的字数，大家可以自己根据需求来自定义文本控件实时的显示已经输入的字符个数
//                Log.e("此时你已经输入了", "" + editable.length());
//
//                int after_length = editable.length();// 输入内容后编辑框所有内容的总长度
//                // 如果字符添加后超过了限制的长度，那么就移除后面添加的那一部分，这个很关键
//                if (after_length > 100) {
//
//                    // 比限制的最大数超出了多少字
//                    int d_value = after_length - 100;
//                    // 这时候从手机输入的字的个数
//                    int d_num = after_length - before_length;
//
//                    int st = cursor + (d_num - d_value);// 需要删除的超出部分的开始位置
//                    int en = cursor + d_num;// 需要删除的超出部分的末尾位置
//                    // 调用delete()方法将编辑框中超出部分的内容去掉
//                    Editable s_new = editable.delete(st, en);
//                    // 给编辑框重新设置文本
//                    edt.setText(s_new.toString());
//                    // 设置光标最后显示的位置为超出部分的开始位置，优化体验
//                    edt.setSelection(st);
//                    // 弹出信息提示已超出字数限制
//                    Toast.makeText(getApplicationContext(), "已超出最大字数限制", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void addLayout() {
//        setContentView(R.layout.layout);
//    }
//
//    private TextureMapView mMapView = null;
//    private AMap aMap;
//    private MyLocationStyle myLocationStyle;
//    private UiSettings mUiSettings;//定义一个UiSettings对象
//    private LatLng latLng;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.layout);
//        mMapView = (TextureMapView)findViewById(R.id.t_map);
//        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
//        mMapView.onCreate(savedInstanceState);
//        if (aMap == null) {
//            aMap = mMapView.getMap();
//        }else{
//            aMap.clear();
//            aMap = mMapView.getMap();
//        }
//        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
//        mUiSettings.setScrollGesturesEnabled(true);
//        mUiSettings.setZoomControlsEnabled(true);
//        mUiSettings.setCompassEnabled(true);
//        mUiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
//        getLocation();
//    }
//
//    public void getLocation() {
//        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
////        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
////        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.mpoint));
//        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
//        // 自定义精度范围的圆形边框颜色
//        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
//        // 自定义精度范围的圆形边框宽度
//        myLocationStyle.strokeWidth(0);
//        // 设置圆形的填充颜色
//        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
//        // 将自定义的 myLocationStyle 对象添加到地图上
//        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
//        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
//    }
//
//    /**
//     * 必须重写以下方法
//     */
//    @Override
//    public void onResume() {
//        super.onResume();
//        mMapView.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        mMapView.onPause();
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mMapView.onSaveInstanceState(outState);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mMapView.onDestroy();
//    }
}

