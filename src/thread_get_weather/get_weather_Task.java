package thread_get_weather;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

public class get_weather_Task extends AsyncTask<String, Integer, String> {
	// ���ϼ������ڷֱ��ǲ��� �� �� ����ֵ����	
	
	public static String cityname = null;
//	String cityname = activity.cn.MainActivity.cName;
	static final String NAMESPACE = "http://WebXml.com.cn/";
	// WebService ��ַ
	static String URL = "http://www.webxml.com.cn/webservices/weatherwebservice.asmx";
	static final String METHOD_NAME = "getWeatherbyCityName";
	static String SOAP_ACTION = "http://WebXml.com.cn/getWeatherbyCityName";
	String weatherToday;
	SoapObject detail;
	//
	static String day;
	static String today_weather;
	static String wind;
	//
	
	@Override
	protected String doInBackground(String... params) {
		// �ڶ���ִ�еķ�����onPreExecute()ִ�����ִ��  
		// TODO Auto-generated method stub
		try {
			this.cityname = params[0];
			Log.v("tag",cityname);
			Log.v("tag", "thread in the get weather ...");
			SoapObject rpc = new SoapObject(NAMESPACE, METHOD_NAME);
			rpc.addProperty("theCityName", cityname);
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = rpc;
			envelope.dotNet = true;
			envelope.setOutputSoapObject(rpc);
			HttpTransportSE ht = new HttpTransportSE(URL);
			ht.debug = true;
			ht.call(SOAP_ACTION, envelope);
			detail =(SoapObject) envelope.getResponse();			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String params) {
		// doInBackground����ʱ���������仰˵������doInBackgroundִ����󴥷�
		// TODO Auto-generated method stub
		super.onPostExecute(params);
		String str = detail.getProperty(6).toString();
		day = str.split(" ")[0];
		// ����
		today_weather = str.split(" ")[1];
		// �缶
		wind = detail.getProperty(7).toString().split(" ")[0];
		
		Log.v("tag", cityname);
		Log.v("tag", day);
		Log.v("tag", today_weather);
		Log.v("tag", wind);
		activity.cn.MainActivity.cityName.setText(cityname);
		activity.cn.MainActivity.date.setText(day);
		activity.cn.MainActivity.weather.setText(today_weather);
		activity.cn.MainActivity.wind.setText(wind);
	}
	
}
