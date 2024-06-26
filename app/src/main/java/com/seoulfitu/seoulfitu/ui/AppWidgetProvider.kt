package com.seoulfitu.seoulfitu.ui

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.seoulfitu.seoulfitu.R
import com.seoulfitu.seoulfitu.ui.facility.SportsFacilityActivity
import com.seoulfitu.seoulfitu.ui.sports_service_list.SportsServiceListActivity

class AppWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val views = RemoteViews(context.packageName, R.layout.layout_appwidget)

        setBtnClickListener(
            views,
            context,
            R.id.btn_go_sports_facility_activity,
            SportsFacilityActivity.getIntent(context),
        )

        setBtnClickListener(
            views,
            context,
            R.id.btn_go_service_activity,
            SportsServiceListActivity.getIntent(context),
        )

        appWidgetManager.updateAppWidget(appWidgetIds, views)
    }

    private fun setBtnClickListener(
        views: RemoteViews,
        context: Context,
        btnId: Int,
        intent: Intent,
    ) {
        views.setOnClickPendingIntent(
            btnId,
            PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
            )
        )
    }
}
