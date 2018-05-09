package com.huayou.acc.acc.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.huayou.acc.acc.R;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by alu on 2017/5/10.
 */

public class MyService extends Service {

//    private TextView resultTv;
    private String host = "tcp://118.190.42.67:1883";
//    private String userName = "admin";
//    private String passWord = "password";
    private Handler handler;
    private MqttClient client;
    private String myTopic = "bridged/k";
    private MqttConnectOptions options;
    private ScheduledExecutorService scheduler;
//    private String message;
    private int REQUEST_CODE=0;
    private int NOTIFICATION_ID=1;


    public class LocalBinder extends Binder {
        String stringToSend = "I'm the test String";
//       String stringToSend=null;

        MyService getService() {
//            stringToSend=message;
            Log.i("TAG", "getService ---> " + MyService.this);
            return MyService.this;
        }
    }

//    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        Log.i("TAG", "onBind~~~~~~~~~~~~");
		IBinder mIBinder = null;
		if ( mIBinder == null )
			mIBinder = new LocalBinder() ;
		return mIBinder;
//        return mBinder;		//也可以像上面几个语句那样重新new一个IBinder
        //如果这边不返回一个IBinder的接口实例，那么ServiceConnection中的onServiceConnected就不会被调用
        //那么bind所具有的传递数据的功能也就体现不出来~\(≧▽≦)/~啦啦啦（这个返回值是被作为onServiceConnected中的第二个参数的）
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        init();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == 1) {
                    sendNotification(getApplicationContext(),(String) msg.obj);
                    Log.i("TAG",(String) msg.obj);
                    System.out.println("-----------------------------");
                } else if(msg.what == 2) {
                    Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_SHORT).show();
                    try {
                        client.subscribe(myTopic, 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if(msg.what == 3) {
                    Toast.makeText(getApplicationContext(), "连接失败，系统正在重连", Toast.LENGTH_SHORT).show();
                }
            }
        };

        startReconnect();
        Log.i("TAG", "onCreate~~~~~~~~~~");
    }
    //提示音
//    private static void startAlarm(Context context) {
//        Uri notification1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        if (notification1 == null) return;
//        Ringtone r = RingtoneManager.getRingtone(context, notification1);
//        r.play();
//    }



    private void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(!client.isConnected()) {
                    connect();
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }

    private void init() {
        try {
            //host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            client = new MqttClient(host, "test",
                    new MemoryPersistence());
            //MQTT的连接设置
            options = new MqttConnectOptions();
            //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            //设置连接的用户名
//            options.setUserName(userName);
//            //设置连接的密码
//            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //设置回调
            client.setCallback(new MqttCallback() {

                @Override
                public void connectionLost(Throwable cause) {
                    //连接丢失后，一般在这里面进行重连
                    System.out.println("connectionLost----------");
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    //publish后会执行到这里
                    System.out.println("deliveryComplete---------"
                            + token.isComplete());
                }

                @Override
                public void messageArrived(String topicName, MqttMessage message)
                        throws Exception {
                    //subscribe后得到的消息会执行到这里面
                    System.out.println("messageArrived----------");
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = topicName+"---"+message.toString();
                    handler.sendMessage(msg);
                    
                }
            });
//			connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void connect() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    client.connect(options);
                    Message msg = new Message();
                    msg.what = 2;
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    Message msg = new Message();
                    msg.what = 3;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }


    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            scheduler.shutdown();
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        Log.i("TAG", "onDestroy~~~~~~~~~~~");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        super.onStart(intent, startId);
        Log.i("TAG", "onStart~~~~~~");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.i("TAG", "onStartCommand~~~~~~~~~~~~");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        Log.i("TAG", "onUnbind~~~~~~~~~~~~~~~~");
        return super.onUnbind(intent);
    }
    private void sendNotification(Context context, String bundleStr) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        // 此处设置的图标仅用于显示新提醒时候出现在设备的通知栏
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);

        Notification notification = mBuilder.build();

        // 当用户下来通知栏时候看到的就是RemoteViews中自定义的Notification布局
        RemoteViews contentView = new RemoteViews(context.getPackageName(),
                R.layout.notification_layout);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher_round);
        contentView.setTextViewText(R.id.title, "您有新的派单");
        contentView.setTextViewText(R.id.text, bundleStr);
        notification.contentView = contentView;

        // 发送通知到通知栏时：提示声音 + 手机震动 + 点亮Android手机呼吸灯。
        // 注意！！（提示声音 + 手机震动）这两项基本上Android手机均支持。
        // 但Android呼吸灯能否点亮则取决于各个手机硬件制造商自家的设置。
        notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;
//        Uri notification1 = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        if (notification1 == null) return;
//        Ringtone r = RingtoneManager.getRingtone(context, notification1);
//        r.play();
        // 点击notification自动消失
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        // 通知的时间
        notification.when = System.currentTimeMillis();

        // 缺省设置为当发送通知到通知栏时候：提示声音 + 手机震动
        notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;

        // 需要注意的是，作为选项，此处可以设置MainActivity的启动模式为singleTop，避免重复新建onCreate()。
        Intent intent = new Intent(context, TestServiceActivity.class);
        //传递String
        intent.putExtra("msg",bundleStr);

        // 当用户点击通知栏的Notification时候，切换回MainActivity。
        PendingIntent pi = PendingIntent.getActivity(context, REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.contentIntent = pi;

        // 发送到手机的通知栏
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

}
