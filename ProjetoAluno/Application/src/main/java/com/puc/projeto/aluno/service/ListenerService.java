

package com.puc.projeto.aluno.service;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.puc.projeto.aluno.ui.DetailActivity;
import com.puc.projeto.aluno.common.Constants;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;


public class ListenerService extends WearableListenerService {
    private static final String TAG = ListenerService.class.getSimpleName();

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.v(TAG, "onMessageReceived: " + messageEvent);

        if (Constants.CLEAR_NOTIFICATIONS_PATH.equals(messageEvent.getPath())) {
            // Request for this device to clear its notifications
            UtilityService.clearNotification(this);
        } else if (Constants.START_ATTRACTION_PATH.equals(messageEvent.getPath())) {
            // Request for this device open the attraction detail screen
            // to a specific tourist attraction
            String attractionName = new String(messageEvent.getData());
            Intent intent = DetailActivity.getLaunchIntent(this, attractionName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (Constants.START_NAVIGATION_PATH.equals(messageEvent.getPath())) {
            // Request for this device to start Maps walking navigation to
            // specific tourist attraction
            String attractionQuery = new String(messageEvent.getData());
            Uri uri = Uri.parse(Constants.MAPS_NAVIGATION_INTENT_URI + Uri.encode(attractionQuery));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
