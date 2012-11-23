package activity.cn;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

// 保存服务器电话号码， 天气预报城市名称
public class setText extends Activity{ 
	private EditText text_phone = null;
	private EditText text_cityname = null;
	private Button button_yes = null;
	private Button button_no = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_text);
		
		text_phone = (EditText)findViewById(R.id.phone_ser);
		text_cityname = (EditText)findViewById(R.id.city_name);
		button_yes = (Button)findViewById(R.id.button_6);
		button_no = (Button)findViewById(R.id.button7);
		// 赋值
		init_text();
		button_yes.setOnClickListener(new OnClickListener() {
			// 点击 “确定”
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferences Info = getSharedPreferences("info", 0);
				Log.v("tag", text_phone.getText().toString());
				Log.v("tag", text_cityname.getText().toString());
				Info.edit().putString("phone", text_phone.getText().toString()).commit();
				Info.edit().putString("city", text_cityname.getText().toString()).commit();				
			}
		});
		button_no.setOnClickListener(new OnClickListener() {
			// “取消”
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent  = new Intent(setText.this, MainActivity.class);
				startActivity(intent);
			}
		});
		
	}
	// 初始化 电话 ，城市名字
    public void init_text()
    {
    	SharedPreferences Info = getSharedPreferences("info", 0);
    	String phone_info = Info.getString("phone", "");
    	String city_info = Info.getString("city", "");
    	Log.v("tag", phone_info);
    	Log.v("tag", city_info);
    	text_phone.setText(phone_info);
    	text_cityname.setText(city_info);
    }

}
