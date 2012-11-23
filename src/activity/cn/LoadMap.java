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
	// 控件
	Button button_load;
	RadioGroup mapType;
	MapView mapView;
	EditText text_Lng,text_Lat;
	// 定义 MapControler 对象
	MapController controller;
	Bitmap posBitmap;
	Bitmap posBitmap1;

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);
		setContentView(R.layout.map);
		// 定位点图片
		posBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.pos);
		posBitmap1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.pos1);
		// 获得界面上的MapView 对象
		mapView = (MapView)findViewById(R.id.mv);
		// 获得界面上的两个文本框
		text_Lng = (EditText)findViewById(R.id.lng);
		text_Lat = (EditText)findViewById(R.id.lat);
		// 设置显示放大，缩小的控制按钮
		mapView.setBuiltInZoomControls(true);
		// 创建MapController 对象
		controller = mapView.getController();
		// 获取Button 对象
		button_load = (Button)findViewById(R.id.loc);
		
		// 监听 Button 按钮
		button_load.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 获取输入框的 经度，纬度
				String lng = text_Lng.getText().toString().trim();
				String lat = text_Lat.getText().toString().trim();
				
				if (lng.equals("") || lat.equals("")) {
					Toast.makeText(LoadMap.this, "无效的经度或者纬度", Toast.LENGTH_SHORT).show();				
				}
				else {
					double dLong = Double.parseDouble(lng);
					double dLat = Double.parseDouble(lat);
					// 调用 MapView 方法
					updateMapView(dLong,dLat);
				}
			}
			// 根据经度、纬度将MapView 定位到指定地点的方法
			private void updateMapView(double dLong, double dLat) {
				// TODO Auto-generated method stub
				// 将纬度 经度信息包装成GeoPoint对象
				GeoPoint gp = new GeoPoint((int)(dLat * 1E6),
						(int)(dLong * 1E6));
				GeoPoint gp1 = new GeoPoint((int)((dLat + 0.001) * 1E6),
						(int)((dLong + 0.001) * 1E6));
				// 将地图移动到指定的地理位置
				controller.animateTo(gp);
				// 获得MapView 上原有的Overlay对象
				List<Overlay> ol = mapView.getOverlays();
				// 清除原有的Overlay 对象
				ol.clear();
				// 添加一个新的OverLay 对象
				ol.add(new PosOverlay(gp, posBitmap));
				ol.add(new PosOverlay(gp, posBitmap1));
				
			}
		});
		// 触发按钮的单击事件
		button_load.performClick();
		// 获得RadioGroup对象
		mapType = (RadioGroup) findViewById(R.id.rg); 
		// 为RadioGroup的选中状态改变添加监听器
		mapType.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		//@Override
		public void onCheckedChanged(RadioGroup group, int checkedId)
		{				
				switch(checkedId)
				{
					// 如果勾选的是"正常视图"的单选框
					case R.id.normal: // 0
						mapView.setSatellite(false);
						break;
						// 如果勾选的是"卫星视图"的单选框
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
