package com.huayou.acc.acc.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huayou.acc.acc.R;
import com.huayou.acc.acc.bean.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alu on 2017/5/9.
 */

public class RegisterActivity extends BaseActivity {

    private Button btn_register;
    private TextView tv_phone;
    private TextView tv_password;
    private User user;

    @Override
    public void addLayout() {
        setContentView(R.layout.activity_register);
    }
    @Override
    public void initView() {
        btn_register= (Button) findViewById(R.id.btn_register);
        tv_phone= (TextView) findViewById(R.id.tv_phone);
        tv_password= (TextView) findViewById(R.id.tv_password);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHttp();
//                gotoActivity(LoginActivity.class,1);
            }
        });
    }

    public void getHttp(){
        //1.初始化Retrofit
        //网址、解析工具（gosn等）
        //http://192.168.3.9:8080/user/register/?login_type=0&phone=13688768576&password=123321&username&channel=1
        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://192.168.3.9:8080/user/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.准备请求网络
        setWeatherService service=retrofit.create(setWeatherService.class);
        //4.通过回调获得结果
        Call<User> call=service.getBean(0,"13665893371","123456","hah",1);
        //5.请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getInfo().equals("SUCCESS")) {
                    Log.e("aa", "加载成功" + response.body().getData().toString());
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(RegisterActivity.this, response.body().getInfo(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("aa","加载失败");
//                Toast.makeText(RegisterActivity.this,"加载失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //定义接口
    public interface setWeatherService{
        @GET("register/")
        Call<User> getBean(@Query("login_type") int type, @Query("phone") String phone,
                           @Query("password") String password, @Query("username") String name,@Query("channel") int channel);
    }
}
