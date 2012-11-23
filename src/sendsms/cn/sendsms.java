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
		
		
		/* 号码  */
		String strNo = activity.cn.MainActivity.telNu;
		Log.v("tag", strNo);
		/* 内容  */
		String strManager = date;
		Log.v("tag", date);
		SmsManager smsManager = SmsManager.getDefault();
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, new Intent(), 0);
		smsManager.sendTextMessage(strNo, null, strManager, null, null);
		return true;
	}

}

/*
 * 	协议:   手机---> 服务器               
 * 						查询全灯状态：	FF*11*keting*p10*EE
 * 						开灯命令：		FF*10*keting*p10*1*EE
 * 						关灯命令：		FF*10*keting*p10*0*EE
 * 			服务器 ---> 手机
 * 						灯是开着的：	FF*11*keting*1*EE
 * 						灯是关着的：	FF*11*keting*0*EE
 */



