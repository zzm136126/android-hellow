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
	
	private Button button1; // ������
	private Button button2;
	private Button button3;
	private Button button4; // set text
//	public static TextView mytime;	
	public static String telNu = null; // �����ŵ�����������
	public static String cName = null; // ����Ԥ���̳߳��в���
	// ��ʾ
	public static TextView cityName; // ��ʾ����
	public static TextView date; // ����
	public static TextView weather; // ����
	public static TextView wind; // ��
 		
	// ��ȡ�����߳�
	private thread_get_weather.get_weather_Task thread_get_weathre = new thread_get_weather.get_weather_Task();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // ȫ��Ļ����
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
//        	    WindowManager.LayoutParams.FLAG_FULLSCREEN);   
   
        button1 = (Button)findViewById(R.id.button1); // ͬ��������
        button2 = (Button)findViewById(R.id.button2); // ��ͼ
        
        button4 = (Button)findViewById(R.id.button4); // ����
              
        // ��ʼ����ʾ�����ؼ�
        cityName = (TextView)findViewById(R.id.cityname);
        date = (TextView)findViewById(R.id.date);
        weather = (TextView)findViewById(R.id.weather);
        wind = (TextView)findViewById(R.id.wind);
        // ��ʼ��������
        init_text();
//        // ���㲥�Ǳߴ��ͷ���������
//        send_phone();
        // ������ȡ�����߳�
        thread_get_weathre.execute(cName);
        
        // ���ͬ�������������ͻ�ȡ�������˵Ƶ�״̬�Ķ���
        button1.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, syncserver.class);
				
				Log.v("TAG", "go to sendmassage");
				Boolean value = sendsms.cn.sendsms.send("FF*11*keting*p10*EE");
				// �жϷ����Ƿ�ɹ�
				if (value == true)
				{
					Toast.makeText(getApplicationContext(), "�ѷ��Ͷ��ŵ��������ˣ����Եȡ�",
						     Toast.LENGTH_SHORT).show();
					//startActivity(intent);					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "���ŷ���ʧ�ܣ����Ժ����ԡ�",
						     Toast.LENGTH_SHORT).show();
				}
			}  	
        });
        //  �ȸ��ͼ
        button2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Log.v("TAG", "google map");
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, LoadMap.class);
				startActivity(intent);	
			}
		}); 
        // �����á������õ绰  ����Ԥ������ ��Ϣ
        button4.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, setText.class);
				startActivity(intent);
			}
		});       
    }       
    // ��ȡ�����á��������Ϣ����ʼ�� �绰 ����������
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