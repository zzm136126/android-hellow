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
		// ���ն���
		if (intent.getAction().equals(mACTION)) { // �ӹ㲥�ж�
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
					// �ж��Ƿ����������
					num = currentMessage.getDisplayOriginatingAddress();
					
					if (num.equals(phone_nu)) {
						//sb.append(currentMessage.getDisplayOriginatingAddress());
						sb.append(currentMessage.getDisplayMessageBody());	
						
						 
						String Stype = sb.toString();
						Log.v("TAG", "back is "+Stype);
						
						if (Stype.equals("FF*11*keting*0*EE")) {
							// "FF*11*keting*0*EE" �������
							state_light.setstate_light("off");
							
						}
						// "0101"��������
						if (Stype.equals("FF*11*keting*1*EE")) {	
							state_light.setstate_light("on");
						}
						// ֱ�ӻ�Activity
						Intent i=new Intent(context,syncserver.class);
						i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(i);
						
						
						// ����ǵ�һ������� Activity
						if (first != false ) {
							first = false;
//							Intent i=new Intent(context,syncserver.class);
//							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//							context.startActivity(i);
							Toast.makeText(context, "ͬ���ɹ�",
								     Toast.LENGTH_SHORT).show();
							
						}
						else {
							first = true;
							Toast.makeText(context, "��������Ϣ�����ı䣬����ͬ���ɹ�",
								     Toast.LENGTH_SHORT).show();
						}
					}
				}
			}
		}
		
	}
	// �ж��Ƿ��ǵ�һ���յ����ţ�����ǵ�һ������ʾ��ͬ���ɹ���������ʾ��...����ͬ���ɹ���
	void set_first(Boolean value)
	{
		getsms appState = new getsms();
		appState.first = value;
	
	}
}
