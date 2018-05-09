package com.huayou.acc.acc.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.huayou.acc.acc.adapter.ListAdapter;
import com.huayou.acc.acc.bean.Map.ListBean;
import com.huayou.acc.acc.bean.Map;
import com.huayou.acc.acc.R;
import com.huayou.acc.acc.util.SharedUtil;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private static final int ACCESS_COARSE_LOCATION_REQUESTCODE = 1;
    private MapView mMapView = null;
    private AMap aMap;
    private MyLocationStyle myLocationStyle;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private  LatLng latLng;
    private List<ListBean> list=new ArrayList<>();
    private ListView lv;
    private ListAdapter listAdapter;
    private static LinearLayout linearLayout;
    private static RelativeLayout relativeLayout;
    private TextView mtv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout= (LinearLayout) findViewById(R.id.linearLayout);
        relativeLayout= (RelativeLayout) findViewById(R.id.relativeLayout);
        mtv= (TextView) findViewById(R.id.mtv);
        initGPS();
        isConn(this);
        mtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                startActivity(intent);
            }
        });
//        获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示
        testCall();
        lv= (ListView) findViewById(R.id.lv);
        listAdapter=new ListAdapter(MainActivity.this);
        lv.setAdapter(listAdapter);
        getHttp();
//        Log.e("list",list.size()+"");
//        LatLng latLng = new LatLng(30.669983, 104.065692);
//        final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title("骡马市").snippet("DefaultMarker"));
//        markerOption = new MarkerOptions();
//        markerOption.position(latLng);
//        markerOption.title("西安市");
//        markerOption.snippet("上海：30.669983, 104.065692");
//        markerOption.draggable(true);
//        markerOption.setFlat(true);
//        markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.mpoint));//设置图标
//        Marker marker=aMap.addMarker(markerOption);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
    // 1. 检测权限是否授权成功
    private void testCall() {
        /** 1. 检测ContextCompat.checkSelfPermission(),返回值
         * PackageManager.PERMISSION_GRANTED 授权已成功
         * PackageManager.PERMISSION_DENIED 授权被拒绝
         */
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 清单里面授权被拒绝，向用户申请
            // 2. 申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},ACCESS_COARSE_LOCATION_REQUESTCODE);
        } else {
            //获取位置
            getLocation();
            Log.e("MainActivity", "1");
        }
    }
    public void getLocation(){
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
//        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
//        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.mpoint));
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }
//     3. 处理回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==ACCESS_COARSE_LOCATION_REQUESTCODE){
            // 判断权限是不是授权了
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                // 用户真正同意了授权
                getLocation();
                Log.e("MainActivity", "2");
            }else {
                // 用户给拒绝了
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
                // 能不能在用户彻底拒绝权限之后，可以友好的提示他一下
                // 4. 彻底拒绝的时候
                //获取相关权限，再次和用户进行交互，沟通，提示用户一旦拒绝之后就将无法实现
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_COARSE_LOCATION)){
                    showDialog();

                }
            }
        }
    }

    // 提示用户去设置
    public void showDialog(){
        new AlertDialog.Builder(this)
                .setMessage("权限被彻底拒绝，请到设置里面打开，才可使用此功能！")
                .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到本应用的设置页面，可以打开权限
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri data = Uri.fromParts("package",MainActivity.this.getPackageName(),null);
                        intent.setData(data);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("取消",null)
                .create()
                .show();
    }
    public void getHttp(){
        //1.初始化Retrofit
        //网址、解析工具（gosn等）
        //http://192.168.3.9:8080/group/?city_id=1&category_id=1
        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://192.168.3.9:8080/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.准备请求网络
        setWeatherService service=retrofit.create(setWeatherService.class);
        //4.通过回调获得结果
        Call<Map> call=service.getBean(1,1);
        //5.请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                Log.e("aa","加载成功"+response.body().getList().toString());
                list=response.body().getList();
                listAdapter.addList(list);
                Log.e("list2",list.size()+"");
                for(int i=0;i<list.size();i++){
                    latLng=new LatLng(list.get(i).getLat(),list.get(i).getLng()) ;
                    final Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(list.get(i).getTitle()).snippet(list.get(i).getAddress()));
                }
            }
            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                Log.e("aa","加载失败");
            }
        });
    }
    //定义接口
    public interface setWeatherService{
        @GET("group/")
        Call<Map> getBean(@Query("city_id") int  city_id, @Query("category_id") int category_id);
    }
    private void initGPS() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS模块是否开启，如果没有则开启
        if (!locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(MainActivity.this, "请打开GPS", Toast.LENGTH_SHORT).show();
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("请打开GPS连接");
            dialog.setMessage("为方便司机更容易接到您，请先打开GPS");
            dialog.setPositiveButton("设置", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // 转到手机设置界面，用户设置GPS
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    Toast.makeText(MainActivity.this, "打开后直接点击返回键即可，若不打开返回下次将再次出现", Toast.LENGTH_SHORT).show();
                    startActivityForResult(intent, 0); // 设置完成后返回到原来的界面
                }
            });
            dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                }
            });
            dialog.show();
        } else {
//            searchRouteResult(startPoint, endPoint);//路径规划
            // 弹出Toast
//          Toast.makeText(TrainDetailsActivity.this, "GPS is ready",Toast.LENGTH_LONG).show();
//          // 弹出对话框
//          new AlertDialog.Builder(this).setMessage("GPS is ready").setPositiveButton("OK", null).show();
        }
    }
    /**
     * 判断网络连接是否已开
     * true 已打开  false 未打开
     */
    public static boolean isConn(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
            searchNetwork(context);//弹出提示对话框
        }
        return false;
    }

    /**
     * 判断网络是否连接成功，连接成功不做任何操作
     * 未连接则弹出对话框提示用户设置网络连接
     */
    public static void searchNetwork(final Context context) {
        //提示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);

            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                linearLayout.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        }).show();
    }
}
