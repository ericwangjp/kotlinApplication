package com.example.httplibrary.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 检查网络状态 、 GPS 的工具类
 * Normal : ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE
 *
 */
public class NetworkUtils {

	/**
	 * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
	 * 
	 * @param context
	 * @return
	 */
	public static int isNetworkAvailable(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return 0;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return 1;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							String extraInfo = netWorkInfo.getExtraInfo();
							if ("cmwap".equalsIgnoreCase(extraInfo)
									|| "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
								return 2;
							}
							return 3;
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 判断当前网络是否可用
	 * 
	 * @param context
	 * @return
	 */
	public static boolean checkNet(Context context) {

		ConnectivityManager manager = ServiceManager.getConnectivityManager(context);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isAvailable() && info.isConnected()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取 IPv4地址
	 */
	public static String getIP_v4() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
				 en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr
						.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException ex) {
//			LogManager.e("testAndroid1", ex.toString());
		}
		return null;
	}

	/**
	 * 获取当前设备连接WiFi名称
	 * @param context
	 * @return
	 */
	public static String getWifiName(Context context){
		if(NetworkUtils.checkNet(context)){
			if(String.valueOf(NetworkUtils.isNetworkAvailable(context)).equals("1")){
				WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
				WifiInfo wifiInfo = wifiManager.getConnectionInfo();
				String SSID = wifiInfo != null ? wifiInfo.getSSID() : "";
				//只去两端的"",安卓4.0以后取到的WiFi名称系统默认加上一对双引号
				// 处理后的WiFi名称
				String str;str = SSID.startsWith("\"")?SSID.substring(1,SSID.length()-1):SSID;
				str=str.endsWith("\"")?str.substring(0,str.length()-1):str;
				return str;
			}else{
				return "";
			}
		}
		return "";
	}

}
