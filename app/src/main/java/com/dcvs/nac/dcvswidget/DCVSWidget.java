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
 * App Widget Configuration implemented in {@link DCVSWidgetConfigureActivity DCVSWidgetConfigureActivity}
 */

public class DCVSWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = DCVSWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.dcvswidget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        //Intent dcvsIntent = new Intent(context, DCVSWidgetConfigureActivity.class);
        //dcvsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        Intent dcvsIntent = new Intent();
        PackageManager managerDCVS = context.getPackageManager();
        dcvsIntent = managerDCVS.getLaunchIntentForPackage("appinventor.ai_sajbrfem.DCVS_Connect_V2_radio2");
        dcvsIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //dcvsIntent.setAction(Intent.ACTION_VIEW);
        //dcvsIntent.setClassName("appinventor.ai_sajbrfem.DCVS_Connect_V2_radio2", "appinventor.ai_sajbrfem.DCVS_Connect_V2_radio2.Screen1");
        PendingIntent dcvsPendingIntent = PendingIntent.getActivity(context, appWidgetId, dcvsIntent, 0);
        views.setOnClickPendingIntent(R.id.HomeBTN, dcvsPendingIntent);
        //dcvsIntent.setAction(ACTION_WIDGET_CONFIGURE + Integer.toString(appWidgetId));

        Intent chatIntent = new Intent();
        PackageManager managerChat = context.getPackageManager();
        chatIntent = managerChat.getLaunchIntentForPackage("com.skype.raider");
        chatIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //chatIntent.setAction(Intent.ACTION_VIEW);
        //chatIntent.setClassName("com.skype.raider", "com.skype.raider.Main");
        PendingIntent chatPendingIntent = PendingIntent.getActivity(context, appWidgetId, chatIntent, 0);
        views.setOnClickPendingIntent(R.id.chatBTN, chatPendingIntent);

        Intent helpIntent = new Intent();
        PackageManager managerHelp = context.getPackageManager();
        helpIntent = managerHelp.getLaunchIntentForPackage("com.teamviewer.quicksupport.market");
        helpIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        //helpIntent.setAction(Intent.ACTION_VIEW);
        //helpIntent.setClassName("com.teamviewer.quicksupport.market","com.teamviewer.quicksupport.ui.MainActivity");
        PendingIntent helpPendingIntent = PendingIntent.getActivity(context, appWidgetId, helpIntent, 0);
        views.setOnClickPendingIntent(R.id.helpBTN, helpPendingIntent);

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
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            DCVSWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
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
