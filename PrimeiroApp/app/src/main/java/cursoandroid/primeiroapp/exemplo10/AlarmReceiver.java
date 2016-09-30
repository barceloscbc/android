package cursoandroid.primeiroapp.exemplo10;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import cursoandroid.primeiroapp.R;

/**
 * Created by Mateus Dias on 04/06/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARME RECEBIDO!", Toast.LENGTH_LONG).show();

        NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent it = new Intent(context, AlarmActivity.class);
        it.putExtra("msg", "Obrigado por ver minha notificação!");
        PendingIntent p = PendingIntent.getActivity(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("Título");
        builder.setContentText("Minha primeira notificação!");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setAutoCancel(true);
        builder.setContentIntent(p);

        nm.notify(1, builder.build());

    }

}
