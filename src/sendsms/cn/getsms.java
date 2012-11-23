package sendsms.cn;

import activity.cn.MainActivity;
import activity.cn.R.string;
import activity.cn.syncserver;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class getsms extends BroadcastReceiver{
	private static final String mACTION = "android.provider.Telephony.SMS_RECEIVED";
	private static Boolean first = true;
	private String num;
	private String det;
	private String tim;
	private String phone_nu = activity.cn.MainActivity.telNu;
	
	//private databasehelper mydatabasehelper;
	@Override
	public void onReceive(Context context, Intent intent) {
		
		activity.cn.syncserver state_light = new activity.cn.syncserver();
//		String phone_number = intent.getStringExtra("telNu");
		Log.v("tag", phone_nu);
		// 接收短信
		if (intent.getAction().equals(mACTION)) { // 从广播判断
			StringBuilder sb = new StringBuilder();
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				Object[] myOBJpdus = (Object[]) bundle.get("pdus");
				SmsMessage[] messages = new SmsMessage[myOBJpdus.length];
				for (int i = 0; i < myOBJpdus.length; i++) {
					messages[i] = SmsMessage
							.createFromPdu((byte[]) myOBJpdus[i]);
				}
				for (SmsMessage currentMessage : messages) {
					// 判断是否服务器号码
					num = currentMessage.getDisplayOriginatingAddress();
					
					if (num.equals(phone_nu)) {
						//sb.append(currentMessage.getDisplayOriginatingAddress());
						sb.append(currentMessage.getDisplayMessageBody());	
						
						 
						String Stype = sb.toString();
						Log.v("TAG", "back is "+Stype);
						
						if (Stype.equals("FF*11*keting*0*EE")) {
							// "FF*11*keting*0*EE" 灯是灭的
							state_light.setstate_light("off");
							
						}
						// "0101"灯是亮的
						if (Stype.equals("FF*11*keting*1*EE")) {	
							state_light.setstate_light("on");
						}
						// 直接画Activity
						Intent i=new Intent(context,syncserver.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(i);
						
						
						// 如果是第一次则加载 Activity
						if (first != false ) {
							first = false;
//							Intent i=new Intent(context,syncserver.class);
//							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//							context.startActivity(i);
							Toast.makeText(context, "同步成功",
								     Toast.LENGTH_SHORT).show();
							
						}
						else {
							first = true;
							Toast.makeText(context, "服务器信息发生改变，重新同步成功",
								     Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		}
		
	}
	// 判断是否是第一次收到短信，如果是第一次则显示“同步成功”，否显示“...重新同步成功”
	void set_first(Boolean value)
	{
		getsms appState = new getsms();
		appState.first = value;
	
	}
}
