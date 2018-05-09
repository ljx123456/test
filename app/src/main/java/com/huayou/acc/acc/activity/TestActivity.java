package com.huayou.acc.acc.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.huayou.acc.acc.R;
import com.huayou.acc.acc.bean.M;
import com.huayou.acc.acc.bean.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by alu on 2017/5/27.
 */

public class TestActivity extends BaseActivity {
    private Button btn;
    private PopupWindow mPopupWindow;
    private ImageView iv;
    private RelativeLayout layout;
    private String sid="833b";

    @Override
    public void addLayout() {
        setContentView(R.layout.activity_activity);
    }
    @Override
    public void initView() {
        btn= (Button) findViewById(R.id.tst);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHttp(sid);
            }
        });
    }
    public void show(View v){
        layout = (RelativeLayout) LayoutInflater.from(TestActivity.this).inflate(
                R.layout.popuwindow, null);
        iv= (ImageView) layout.findViewById(R.id.iv_cancel);
        mPopupWindow = new PopupWindow(layout, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(v,Gravity.CENTER,0,0);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x000000));
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
                mPopupWindow=null;
            }
        });
    }
    public void getHttp(String sid){
        //1.初始化Retrofit
        //网址、解析工具（gosn等）
        //http://192.168.3.46:8080/group/ticket/833b
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.3.51:8080/group/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.准备请求网络
        setWeatherService service=retrofit.create(setWeatherService.class);
        //4.通过回调获得结果
        Call<M> call=service.groupList(sid);
        //5.请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<M>() {
            @Override
            public void onResponse(Call<M> call, Response<M> response) {
                M.HeadBean headBean=response.body().getHead();
                Log.e("list2",headBean.toString());
                Toast.makeText(TestActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<M> call, Throwable t) {
                Log.e("aa","加载失败");
            }
        });
    }
    //定义接口
    public interface setWeatherService{
        @GET("ticket/{sid}")
        Call<M>  groupList(@Path("sid") String sid);
    }

//    public interface BlueService {
//        @GET
//        public Call<ResponseBody> profileData(@Url String url);
//    }
//    public void getData(){
//        Retrofit retrofit = Retrofit.Builder()
//                .baseUrl("https://your.api.url/").build();
//
//        BlueService service = retrofit.create(BlueService.class);
//        service.profilePicture("https://s3.amazon.com/profile-picture/path");
//    }
}
