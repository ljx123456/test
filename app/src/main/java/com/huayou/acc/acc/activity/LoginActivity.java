package com.huayou.acc.acc.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huayou.acc.acc.R;
import com.huayou.acc.acc.bean.User;
import com.huayou.acc.acc.util.SharedUtil;

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

public class LoginActivity extends BaseActivity {

    private TextView tv_register,tv_forget;
    private EditText edit_name,edit_password;
    private Button btn_login;

    @Override
    public void addLayout() {
        if(!SharedUtil.getTag(this)){//默认是false
            gotoActivity(LeadActivity.class,1);
            finish();
        }
        setContentView(R.layout.activity_login);

    }

    @Override
    public void initView() {
        tv_register= (TextView) findViewById(R.id.tv_register);
        tv_forget= (TextView) findViewById(R.id.tv_forget);
        edit_name= (EditText) findViewById(R.id.edit_name);
        edit_password= (EditText) findViewById(R.id.edit_password);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHttp();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoActivity(RegisterActivity.class,1);
            }
        });
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "暂未开发", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getHttp(){
        //1.初始化Retrofit
        //网址、解析工具（gosn等）
        //http://192.168.3.9:8080/user/login/?login_type=0&phone=13540356101&password=123321&username&channel=1
        final Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://192.168.3.9:8080/user/").
                addConverterFactory(GsonConverterFactory.create())
                .build();
        //3.准备请求网络
        setWeatherService service=retrofit.create(setWeatherService.class);
        //4.通过回调获得结果
        Call<User> call=service.getBean(0,"13665893371","123456",null,1);
        //5.请求加入调度，正式排队去网络获取数据
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getInfo().equals("SUCCESS")) {
                    Log.e("aa", "加载成功" + response.body().getData().toString());
//                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    gotoActivity(MainActivity.class, 1);
                }else{
                    Toast.makeText(LoginActivity.this, response.body().getInfo(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("aa","加载失败");

            }
        });
    }
    //定义接口
    public interface setWeatherService{
        @GET("login/")
        Call<User> getBean(@Query("login_type") int type, @Query("phone") String phone,
                           @Query("password") String password, @Query("username") String name, @Query("channel") int channel);
    }
}
