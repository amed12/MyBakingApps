package com.sun3toline.mybakingapps.Widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by coldware on 9/24/17.
 */

public class BakingAppWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        BakingAppDataProvider data = new BakingAppDataProvider(this, intent);
        return data;
    }
}