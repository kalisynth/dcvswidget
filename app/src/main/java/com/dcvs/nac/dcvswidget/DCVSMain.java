package com.dcvs.nac.dcvswidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class DCVSMain extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.dcvsmain);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        //Main / Home
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent homePendingIntent = PendingIntent.getActivity(context, appWidgetId, homeIntent, 0);
        views.setOnClickPendingIntent(R.id.HomeBTN, homePendingIntent);

        //Chat - Skype
        Intent chatIntent = new Intent();
        PackageManager managerChat = context.getPackageManager();
        chatIntent = managerChat.getLaunchIntentForPackage("com.skype.raider");
        chatIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent chatPendingIntent = PendingIntent.getActivity(context, appWidgetId, chatIntent, 0);
        views.setOnClickPendingIntent(R.id.chatBTN, chatPendingIntent);

        //Help - Quick Support
        Intent helpIntent = new Intent();
        PackageManager managerHelp = context.getPackageManager();
        helpIntent = managerHelp.getLaunchIntentForPackage("com.teamviewer.quicksupport.market");
        helpIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PendingIntent helpPendingIntent = PendingIntent.getActivity(context, appWidgetId, helpIntent, 0);
        views.setOnClickPendingIntent(R.id.helpBTN, helpPendingIntent);

        //Fun - Games
        Intent funIntent = new Intent();
        funIntent.setAction(Intent.ACTION_VIEW);
        funIntent.setClassName("appinventor.ai_sajbrfem.DCVS_Connect_V2_radio2", "appinventor.ai_sajbrfem.DCVS_Connect_V2_radio2.Games");
        PendingIntent funPendingIntent = PendingIntent.getActivity(context, appWidgetId, funIntent, 0);
        views.setOnClickPendingIntent(R.id.EntertainmentBTN, funPendingIntent);



        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

