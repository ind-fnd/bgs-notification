package com.red_folder.phonegap.plugin.backgroundservice.sample;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.app.NotificationManager;
import android.app.Notification;
import android.R;
import android.content.Context;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;

public class MyService extends BackgroundService {

	private final static String TAG = MyService.class.getSimpleName();

	private String mHelloTo = "World";

	@Override
	protected JSONObject doWork() {
		JSONObject result = new JSONObject();

		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String now = df.format(new Date(System.currentTimeMillis()));

			String msg = "Hello " + this.mHelloTo + " - its currently " + now;

			//获取NotificationManager实例
			NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

			Notification noti = new Notification.Builder(this)
				.setContentTitle("最简单的Notification")
				.setContentText(msg)
				.setSmallIcon(R.drawable.sym_def_app_icon)
				.build();

			//实例化NotificationCompat.Builde并设置相关属性
			// Notification.Builder builder = new Notification.Builder(this)
			// 				//设置小图标
			// 				// .setSmallIcon(R.mipmap.sym_def_app_icon)
			// 				//设置通知标题
			// 				.setContentTitle("最简单的Notification")
			// 				//设置通知内容
			// 				.setContentText(msg);
			// 				//设置通知时间，默认为系统发出通知的时间，通常不用设置
							//.setWhen(System.currentTimeMillis());
			//通过builder.build()方法生成Notification对象,并发送通知,id=1
			notifyManager.notify(1, noti);

			result.put("Message", msg);

			Log.d(TAG, msg);
		} catch (JSONException e) {
		}

		return result;
	}

	@Override
	protected JSONObject getConfig() {
		JSONObject result = new JSONObject();

		try {
			result.put("HelloTo", this.mHelloTo);
		} catch (JSONException e) {
		}

		return result;
	}

	@Override
	protected void setConfig(JSONObject config) {
		try {
			if (config.has("HelloTo"))
				this.mHelloTo = config.getString("HelloTo");
		} catch (JSONException e) {
		}

	}

	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub

	}


}
