package com.huayou.acc.acc.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.huayou.acc.acc.R;

/**
 * Created by alu on 2017/5/10.
 */

public class TestServiceActivity   extends BaseActivity {

    private Button startButton, bindButton;
    private Button stopButton, unbindButton;
    private ServiceConnection sc;
    private MyService myService;

    @Override
    public void addLayout() {
        setContentView(R.layout.activity_testservice);
    }


    @Override
    public void initView() {
        startButton = (Button) findViewById(R.id.startbutton_id);
        stopButton = (Button) findViewById(R.id.stopbutton_id);
        bindButton = (Button) findViewById(R.id.bindbutton_id);
        unbindButton = (Button) findViewById(R.id.unbindbutton_id);
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        sc = new ServiceConnection() {
            /*
             * 只有在MyService中的onBind方法中返回一个IBinder实例才会在Bind的时候
             * 调用onServiceConnection回调方法
             * 第二个参数service就是MyService中onBind方法return的那个IBinder实例，可以利用这个来传递数据
             */
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                myService = ((MyService.LocalBinder) service).getService();
                String recStr = ((MyService.LocalBinder) service).stringToSend;
//                if(recStr!=null){
//                   startAlarm(TestServiceActivity.this);
//                    Toast.makeText(TestServiceActivity.this, recStr, Toast.LENGTH_SHORT).show();
//                }
//                Toast.makeText(TestServiceActivity.this,"获取到值",Toast.LENGTH_SHORT).show();

                //利用IBinder对象传递过来的字符串数据（其他数据也可以啦，哪怕是一个对象也OK~~）
                Log.i("传过来的值","This String is :" + recStr);
                Log.i("TAG","onServiceConnected : MyService ---->" + myService);

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                /* SDK上是这么说的：
                 * This is called when the connection with the service has been unexpectedly disconnected
                 * that is, its process crashed. Because it is running in our same process, we should never see this happen.
                 * 所以说，只有在service因异常而断开连接的时候，这个方法才会用到*/
                // TODO Auto-generated method stub
                sc = null;
                Log.i("TAG", "onServiceDisconnected : ServiceConnection --->"
                        + sc);
            }


        };
        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(TestServiceActivity.this,
                        MyService.class);
                startService(intent);
                Log.i("TAG", "Start button clicked");
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent intent = new
                 * Intent(LocalServiceTestActivity.this,MyService.class);
                 * stopService(intent); 这种方法也是可以的哈~
                 */

                Intent intent = new Intent(TestServiceActivity.this,MyService.class);
                stopService(intent);
                Log.i("TAG", "Stop Button clicked");
            }
        });

        bindButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              Intent intent = new Intent(TestServiceActivity.this,
                      MyService.class);//这样也可以的
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.SERVICE_TEST");
                bindService(intent, sc, Context.BIND_AUTO_CREATE);//bind多次也只会调用一次onBind方法
                Log.i("TAG", "Bind button clicked");
            }
        });

        unbindButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                unbindService(sc);
                // 这边如果重复unBind会报错，提示该服务没有注册的错误——IllegalArgumentException:
                // Service not registered: null
                // 所以一般会设置一个flag去看这个service
                // bind后有没有被unBind过，没有unBind过才能调用unBind方法(这边我就不设置了哈~\(≧▽≦)/~啦啦啦)
                Log.i("TAG", "Unbind Button clicked");
            }
        });
    }

//    //提示音
//    private static void startAlarm(Context context) {
//        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        if (notification == null) return;
//        Ringtone r = RingtoneManager.getRingtone(context, notification);
//        r.play();
//    }



}
