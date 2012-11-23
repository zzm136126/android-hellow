package activity.cn;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PosOverlay extends Overlay {
	// 定义该 PosOverLay 所绘制的位图
	Bitmap posbitmap;
	// 定义该 PosOverLay 绘制位图的位置
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
			// 获取MapView的Projection对象
			Projection projection = mapView.getProjection();
			Point point = new Point();
			// 将真实的地理坐标转化为屏幕上的坐标
			projection.toPixels(gp, point);
			// 在指定位置绘制图片
			canvas.drawBitmap(posbitmap, point.x - posbitmap.getWidth()/2,
					point.y - posbitmap.getHeight(), null);
		}
		
	}
}
