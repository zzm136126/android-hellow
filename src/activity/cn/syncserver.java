package activity.cn;

import activity.cn.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class syncserver extends Activity{
	// 收到短信后调用
	// 定义灯的按键
	private Button light_1;
//	private Button light_2;
	
	private static int control_state;// 灯的命令
	private static int state_light_show = 0 ; // 0：开灯选中；1：关灯选中  初始化可以为0
	private static int number_light;// 灯的号码
	
	private static String state_light; // 灯的状态
	
	private sendsms.cn.sendsms send_control = new sendsms.cn.sendsms();
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.v("tag", "syncserver");
		setContentView(R.layout.syncserver);
		// 初始化控件
		light_1 = (Button)findViewById(R.id.light_1);
		//	绘制小灯
        drawlight(state_light);
        
        // 监听按钮
        light_1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_light = 1;// 确定是第一盏灯
				new AlertDialog.Builder(syncserver.this)
				.setTitle("选择功能")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setSingleChoiceItems(new String[] {"开灯","关灯"},state_light_show,
						new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								control_state = which;
							}
						}
						).setPositiveButton("确定",new DialogInterface.OnClickListener() {
							// 点击“确定”
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								send_control();
							}
						})
						.setNegativeButton("取消", null)  
						.show();  
			}
		});

	}
	// 绘制小灯方法
	void drawlight(String type)
	{		
		if (type.equals("on")){light_1.setBackgroundResource(R.drawable.light_on);state_light_show=0;}
		else if (type.equals("off")){light_1.setBackgroundResource(R.drawable.light_off);state_light_show=1;}
	}
	// 记录状态，防止是开的再发开灯命令...
	public void setstate_light(String state_light)
	{
		syncserver light_state = new syncserver();
		light_state.state_light = state_light;
	}
	// 发送指令到服务器
	public void send_control()
	{
		if (control_state == state_light_show) {
			if (control_state == 1) {
				Toast.makeText(getApplicationContext(), "指令不能发送，当前灯已经是关着的了",
					     Toast.LENGTH_SHORT).show();
			}else
				Toast.makeText(getApplicationContext(), "指令不能发送，当前灯已经是开着的了",
					     Toast.LENGTH_SHORT).show();
			
		}else{
			if ( control_state == 1){ 
				// 发送关灯指令
				Log.v("tag", "关灯？");
				// 调用短信发送
				Boolean type = send_control.send("FF*10*keting*p10*0*EE");
				if (type == true ){
					Toast.makeText(getApplicationContext(), "关灯命令已经成功发送",
						     Toast.LENGTH_SHORT).show();
					}
				else
					Toast.makeText(getApplicationContext(), "关灯命令发送失败，请稍后重试",
						     Toast.LENGTH_SHORT).show();
				}
			else  // 发送开灯指令
				Log.v("tag", "开灯？");
				Boolean type = send_control.send("FF*10*keting*p10*1*EE");
				if (type == true ){
					Toast.makeText(getApplicationContext(), "开灯命令已经成功发送",
						     Toast.LENGTH_SHORT).show();
					}
				else
					Toast.makeText(getApplicationContext(), "开灯命令发送失败，请稍后重试",
						     Toast.LENGTH_SHORT).show();
			}
		}		
}
