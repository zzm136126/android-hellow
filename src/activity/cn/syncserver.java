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
	// �յ����ź����
	// ����Ƶİ���
	private Button light_1;
//	private Button light_2;
	
	private static int control_state;// �Ƶ�����
	private static int state_light_show = 0 ; // 0������ѡ�У�1���ص�ѡ��  ��ʼ������Ϊ0
	private static int number_light;// �Ƶĺ���
	
	private static String state_light; // �Ƶ�״̬
	
	private sendsms.cn.sendsms send_control = new sendsms.cn.sendsms();
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.v("tag", "syncserver");
		setContentView(R.layout.syncserver);
		// ��ʼ���ؼ�
		light_1 = (Button)findViewById(R.id.light_1);
		//	����С��
        drawlight(state_light);
        
        // ������ť
        light_1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number_light = 1;// ȷ���ǵ�һյ��
				new AlertDialog.Builder(syncserver.this)
				.setTitle("ѡ����")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setSingleChoiceItems(new String[] {"����","�ص�"},state_light_show,
						new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								control_state = which;
							}
						}
						).setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {
							// �����ȷ����
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								send_control();
							}
						})
						.setNegativeButton("ȡ��", null)  
						.show();  
			}
		});

	}
	// ����С�Ʒ���
	void drawlight(String type)
	{		
		if (type.equals("on")){light_1.setBackgroundResource(R.drawable.light_on);state_light_show=0;}
		else if (type.equals("off")){light_1.setBackgroundResource(R.drawable.light_off);state_light_show=1;}
	}
	// ��¼״̬����ֹ�ǿ����ٷ���������...
	public void setstate_light(String state_light)
	{
		syncserver light_state = new syncserver();
		light_state.state_light = state_light;
	}
	// ����ָ�������
	public void send_control()
	{
		if (control_state == state_light_show) {
			if (control_state == 1) {
				Toast.makeText(getApplicationContext(), "ָ��ܷ��ͣ���ǰ���Ѿ��ǹ��ŵ���",
					     Toast.LENGTH_SHORT).show();
			}else
				Toast.makeText(getApplicationContext(), "ָ��ܷ��ͣ���ǰ���Ѿ��ǿ��ŵ���",
					     Toast.LENGTH_SHORT).show();
			
		}else{
			if ( control_state == 1){ 
				// ���͹ص�ָ��
				Log.v("tag", "�صƣ�");
				// ���ö��ŷ���
				Boolean type = send_control.send("FF*10*keting*p10*0*EE");
				if (type == true ){
					Toast.makeText(getApplicationContext(), "�ص������Ѿ��ɹ�����",
						     Toast.LENGTH_SHORT).show();
					}
				else
					Toast.makeText(getApplicationContext(), "�ص������ʧ�ܣ����Ժ�����",
						     Toast.LENGTH_SHORT).show();
				}
			else  // ���Ϳ���ָ��
				Log.v("tag", "���ƣ�");
				Boolean type = send_control.send("FF*10*keting*p10*1*EE");
				if (type == true ){
					Toast.makeText(getApplicationContext(), "���������Ѿ��ɹ�����",
						     Toast.LENGTH_SHORT).show();
					}
				else
					Toast.makeText(getApplicationContext(), "���������ʧ�ܣ����Ժ�����",
						     Toast.LENGTH_SHORT).show();
			}
		}		
}
