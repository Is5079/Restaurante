package com.example.lopezl_restaurante;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Servicio para notificar al usuario si se ha recibido un mensaje con la
 * palabra clave RESERVAR.
 * 
 * 
 */
public class NotificacionSMS extends Service {
	private NotificationManager nm;
	private static final int ID_NOTIFICACION_CREAR = 1;
	private Notification notificacion;

	private Intent myIntent;

	@Override
	public void onCreate() {

		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificacion = new Notification(R.drawable.icon_reserva,
				"Notificando reserva", System.currentTimeMillis());
		Context context = getApplicationContext();
		String notificationTitle = "Abriendo la aplicacion Restaurante";
		String notificationText = "Lanzando reservas";
		myIntent = new Intent(this, Reservar.class);

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				myIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		notificacion.defaults |= Notification.DEFAULT_SOUND;
		notificacion.flags |= Notification.FLAG_AUTO_CANCEL;
		notificacion.setLatestEventInfo(context, notificationTitle,
				notificationText, pendingIntent);

		nm.notify(ID_NOTIFICACION_CREAR, notificacion);

	}

	@Override
	public void onDestroy() {
		// Si el servicio deja de estar activo se elimina la notificacion
		nm.cancel(ID_NOTIFICACION_CREAR);
		Toast.makeText(this, "Servicio de notificacion detenido",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
