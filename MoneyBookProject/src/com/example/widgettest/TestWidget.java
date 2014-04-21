package com.example.widgettest;


import com.example.swipetest.MainActivity;
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
	 private static final String BTN1_CLICKED    = "btn1Click";
	 private static final String BTN2_CLICKED    = "btn2Click";
	 private static final String BTN3_CLICKED    = "btn3Click";
	 private static final String TEXTVIEW_CLICKED    = "textViewClick";


	    @Override
	    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	        RemoteViews remoteViews;
	        ComponentName watchWidget;

	        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
	        watchWidget = new ComponentName(context, TestWidget.class);

	        Intent activityIntent = new Intent(context, MainActivity.class);
	        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, activityIntent, 0);

	        remoteViews.setOnClickPendingIntent(R.id.targetText, configPendingIntent);	        
	        remoteViews.setOnClickPendingIntent(R.id.btn1, getPendingSelfIntent(context, BTN1_CLICKED));
	        remoteViews.setOnClickPendingIntent(R.id.btn2, getPendingSelfIntent(context, BTN2_CLICKED));
	        remoteViews.setOnClickPendingIntent(R.id.btn3, getPendingSelfIntent(context, BTN3_CLICKED));
	        	
	        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
	    }

	    @Override
	    public void onReceive(Context context, Intent intent) {
	        // TODO Auto-generated method stub
	        super.onReceive(context, intent);
	        Log.i("recieve", intent.getAction());
	        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews;
            ComponentName watchWidget;

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            watchWidget = new ComponentName(context, TestWidget.class);
            
	        if( "android.appwidget.action.APPWIDGET_UPDATE".equals(intent.getAction())){
	        
	            Bundle bdl = intent.getExtras();
	            
	            remoteViews.setTextViewText(R.id.targetText, (CharSequence) bdl.get("money"));

	            appWidgetManager.updateAppWidget(watchWidget, remoteViews);

	        }
	        
	        if (BTN1_CLICKED.equals(intent.getAction())) {

	            remoteViews.setTextViewText(R.id.targetText, "BTN1 CLICKED");

	            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
	        }
	        
	        if (BTN2_CLICKED.equals(intent.getAction())) {

	            
	            remoteViews.setTextViewText(R.id.targetText, "BTN2 CLICKED");

	            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
	        }
	        
	        if (BTN3_CLICKED.equals(intent.getAction())) {

	            remoteViews.setTextViewText(R.id.targetText, "BTN3 CLICKED");

	            appWidgetManager.updateAppWidget(watchWidget, remoteViews);
	        }
	   
	    }

	    protected PendingIntent getPendingSelfIntent(Context context, String action) {
	        Intent intent = new Intent(context, getClass());
	        intent.setAction(action);
	        return PendingIntent.getBroadcast(context, 0, intent, 0);
	    }
}
