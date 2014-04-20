package com.example.widgettest;


import com.example.swipetest.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class TestWidget extends AppWidgetProvider {
	 private static final String SYNC_CLICKED    = "automaticWidgetSyncButtonClick";

	    @Override
	    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	        RemoteViews remoteViews;
	        ComponentName watchWidget;

	        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	        watchWidget = new ComponentName(context, TestWidget.class);

	        remoteViews.setOnClickPendingIntent(R.id.btn1, getPendingSelfIntent(context, SYNC_CLICKED));
	        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
	    }

	    @Override
	    public void onReceive(Context context, Intent intent) {
	        // TODO Auto-generated method stub
	        super.onReceive(context, intent);
	        Log.i("recieve", intent.getAction());
	        
	        if( "android.appwidget.action.APPWIDGET_UPDATE".equals(intent.getAction())){
	            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
	            RemoteViews remoteViews;
	            ComponentName watchWidget;

	            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	            watchWidget = new ComponentName(context, TestWidget.class);
	            
	            Bundle bdl = intent.getExtras();
	            
	            remoteViews.setTextViewText(R.id.targetText, (CharSequence) bdl.get("money"));

	            appWidgetManager.updateAppWidget(watchWidget, remoteViews);

	        }
	        
	        if (SYNC_CLICKED.equals(intent.getAction())) {

	            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

	            RemoteViews remoteViews;
	            ComponentName watchWidget;

	            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	            watchWidget = new ComponentName(context, TestWidget.class);
	            
	            remoteViews.setTextViewText(R.id.targetText, "TESTING");

	            appWidgetManager.updateAppWidget(watchWidget, remoteViews);

	        }
	    }

	    protected PendingIntent getPendingSelfIntent(Context context, String action) {
	        Intent intent = new Intent(context, getClass());
	        intent.setAction(action);
	        return PendingIntent.getBroadcast(context, 0, intent, 0);
	    }
}
