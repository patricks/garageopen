package at.helmsdeep.garageopen;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		for (int appWidgetId : appWidgetIds) {
			Intent intent = new Intent(context, SMSReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, 0);

			RemoteViews views = new RemoteViews(context.getPackageName(),
					R.layout.widget_layout);
			views.setOnClickPendingIntent(R.id.widget_btn_open, pendingIntent);

			appWidgetManager.updateAppWidget(appWidgetId, views);
		}
	}
}
