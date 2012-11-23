package activity.cn;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PosOverlay extends Overlay {
	// ����� PosOverLay �����Ƶ�λͼ
	Bitmap posbitmap;
	// ����� PosOverLay ����λͼ��λ��
	GeoPoint gp;
	public PosOverlay(GeoPoint gp, Bitmap posbitmap)
	{
		super();
		this.gp = gp;
		this.posbitmap = posbitmap;		
	}
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		if (!shadow) {
			// ��ȡMapView��Projection����
			Projection projection = mapView.getProjection();
			Point point = new Point();
			// ����ʵ�ĵ�������ת��Ϊ��Ļ�ϵ�����
			projection.toPixels(gp, point);
			// ��ָ��λ�û���ͼƬ
			canvas.drawBitmap(posbitmap, point.x - posbitmap.getWidth()/2,
					point.y - posbitmap.getHeight(), null);
		}
		
	}
}
