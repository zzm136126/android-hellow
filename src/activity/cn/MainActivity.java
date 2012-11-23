package activity.cn;

import java.util.Map;

import sendsms.cn.getsms;
import activity.cn.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	
	private Button button1; // 发短信
	private Button button2;
	private Button button3;
	private Button button4; // set text
//	public static TextView mytime;	
	public static String telNu = null; // 发短信到服务器参数
	public static String cName = null; // 天气预报线程城市参数
	// 显示
	public static TextView cityName; // 显示城市
	public static TextView date; // 日期
	public static TextView weather; // 天气
	public static TextView wind; // 风
 		
	// 获取天气线程
	private thread_get_weather.get_weather_Task thread_get_weathre = new thread_get_weather.get_weather_Task();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // 全屏幕代码
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
//        	    WindowManager.LayoutParams.FLAG_FULLSCREEN);   
   
        button1 = (Button)findViewById(R.id.button1); // 同步服务器
        button2 = (Button)findViewById(R.id.button2); // 地图
        
        button4 = (Button)findViewById(R.id.button4); // 设置
              
        // 初始化显示天气控件
        cityName = (TextView)findViewById(R.id.cityname);
        date = (TextView)findViewById(R.id.date);
        weather = (TextView)findViewById(R.id.weather);
        wind = (TextView)findViewById(R.id.wind);
        // 初始各个参数
        init_text();
//        // 给广播那边传送服务器号码
//        send_phone();
        // 启动获取天气线程
        thread_get_weathre.execute(cName);
        
        // 点击同步服务器：发送获取服务器端灯的状态的短信
        button1.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, syncserver.class);
				
				Log.v("TAG", "go to sendmassage");
				Boolean value = sendsms.cn.sendsms.send("FF*11*keting*p10*EE");
				// 判断发送是否成功
				if (value == true)
				{
					Toast.makeText(getApplicationContext(), "已发送短信到服务器端，请稍等。",
						     Toast.LENGTH_SHORT).show();
					//startActivity(intent);					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "短信发送失败，请稍后再试。",
						     Toast.LENGTH_SHORT).show();
				}
			}  	
        });
        //  谷歌地图
        button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.v("TAG", "google map");
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, LoadMap.class);
				startActivity(intent);	
			}
		}); 
        // “设置”：设置电话  天气预报城市 信息
        button4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, setText.class);
				startActivity(intent);
			}
		});       
    }       
    // 获取“设置”保存的信息，初始化 电话 ，城市名字
    public void init_text()
    {
    	SharedPreferences Info = getSharedPreferences("info", 0);
    	String phone_info = Info.getString("phone", "");
    	String city_info = Info.getString("city", "");
    	Log.v("tag", phone_info);
    	Log.v("tag", city_info);
    	this.telNu = phone_info;
    	this.cName = city_info;
    }

}