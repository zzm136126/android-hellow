package sendsms.cn;

import java.util.List;

import activity.cn.MainActivity;
import android.app.PendingIntent;
import android.content.Intent;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class sendsms {
	@SuppressWarnings("deprecation")
	
	
	
	public static Boolean send(String date){
		
		
		/* ����  */
		String strNo = activity.cn.MainActivity.telNu;
		Log.v("tag", strNo);
		/* ����  */
		String strManager = date;
		Log.v("tag", date);
		SmsManager smsManager = SmsManager.getDefault();
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0);
		smsManager.sendTextMessage(strNo, null, strManager, null, null);
		return true;
	}

}

/*
 * 	Э��:   �ֻ�---> ������               
 * 						��ѯȫ��״̬��	FF*11*keting*p10*EE
 * 						�������		FF*10*keting*p10*1*EE
 * 						�ص����		FF*10*keting*p10*0*EE
 * 			������ ---> �ֻ�
 * 						���ǿ��ŵģ�	FF*11*keting*1*EE
 * 						���ǹ��ŵģ�	FF*11*keting*0*EE
 */



