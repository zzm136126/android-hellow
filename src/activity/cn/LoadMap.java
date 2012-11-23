package activity.cn;

import java.util.List;

import activity.cn.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class LoadMap extends MapActivity{
	// �ؼ�
	Button button_load;
	RadioGroup mapType;
	MapView mapView;
	EditText text_Lng,text_Lat;
	// ���� MapControler ����
	MapController controller;
	Bitmap posBitmap;
	Bitmap posBitmap1;

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.map);
		// ��λ��ͼƬ
		posBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pos);
		posBitmap1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.pos1);
		// ��ý����ϵ�MapView ����
		mapView = (MapView)findViewById(R.id.mv);
		// ��ý����ϵ������ı���
		text_Lng = (EditText)findViewById(R.id.lng);
		text_Lat = (EditText)findViewById(R.id.lat);
		// ������ʾ�Ŵ���С�Ŀ��ư�ť
		mapView.setBuiltInZoomControls(true);
		// ����MapController ����
		controller = mapView.getController();
		// ��ȡButton ����
		button_load = (Button)findViewById(R.id.loc);
		
		// ���� Button ��ť
		button_load.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��ȡ������ ���ȣ�γ��
				String lng = text_Lng.getText().toString().trim();
				String lat = text_Lat.getText().toString().trim();
				
				if (lng.equals("") || lat.equals("")) {
					Toast.makeText(LoadMap.this, "��Ч�ľ��Ȼ���γ��", Toast.LENGTH_SHORT).show();				
				}
				else {
					double dLong = Double.parseDouble(lng);
					double dLat = Double.parseDouble(lat);
					// ���� MapView ����
					updateMapView(dLong,dLat);
				}
			}
			// ���ݾ��ȡ�γ�Ƚ�MapView ��λ��ָ���ص�ķ���
			private void updateMapView(double dLong, double dLat) {
				// TODO Auto-generated method stub
				// ��γ�� ������Ϣ��װ��GeoPoint����
				GeoPoint gp = new GeoPoint((int)(dLat * 1E6),
						(int)(dLong * 1E6));
				GeoPoint gp1 = new GeoPoint((int)((dLat + 0.001) * 1E6),
						(int)((dLong + 0.001) * 1E6));
				// ����ͼ�ƶ���ָ���ĵ���λ��
				controller.animateTo(gp);
				// ���MapView ��ԭ�е�Overlay����
				List<Overlay> ol = mapView.getOverlays();
				// ���ԭ�е�Overlay ����
				ol.clear();
				// ���һ���µ�OverLay ����
				ol.add(new PosOverlay(gp, posBitmap));
				ol.add(new PosOverlay(gp, posBitmap1));
				
			}
		});
		// ������ť�ĵ����¼�
		button_load.performClick();
		// ���RadioGroup����
		mapType = (RadioGroup) findViewById(R.id.rg); 
		// ΪRadioGroup��ѡ��״̬�ı���Ӽ�����
		mapType.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		//@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{				
				switch(checkedId)
				{
					// �����ѡ����"������ͼ"�ĵ�ѡ��
					case R.id.normal: // 0
						mapView.setSatellite(false);
						break;
						// �����ѡ����"������ͼ"�ĵ�ѡ��
					case R.id.satellite: // 1
						mapView.setSatellite(true);
						break;
					}
				}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return true;
	}


}
