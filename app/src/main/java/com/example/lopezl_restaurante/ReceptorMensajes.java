package com.example.lopezl_restaurante;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Servicio para capturar la recepci�n de un mensaje.
 * 
 * 
 */
public class ReceptorMensajes extends BroadcastReceiver {

	final SmsManager sms = SmsManager.getDefault();

	@Override
	public void onReceive(Context context, Intent intent) {

		final Bundle bundle = intent.getExtras();

		try {

			if (bundle != null) {

				final Object[] pdusObj = (Object[]) bundle.get("pdus");

				String message = new String();

				for (int i = 0; i < pdusObj.length; i++) {

					SmsMessage currentMessage = SmsMessage
							.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage
							.getDisplayOriginatingAddress();

					String senderNum = phoneNumber;
					message = currentMessage.getDisplayMessageBody();

					Log.i("Recibidor de mensajes", "numero: " + senderNum
							+ "; mensaje: " + message);

					// Mostramos la alerta
					int duration = Toast.LENGTH_LONG;
					Toast toast = Toast.makeText(context, "Enviado por: "
							+ senderNum + ", mensaje: " + message, duration);
					toast.show();

				}

				String palabraClave = message.substring(0, 8);
				// S�lo lanzaran la actividad de reservas los SMS que contengan
				// delante la palabra RESERVAR
				if ("RESERVAR".equals(palabraClave)) {
					Intent miIntent = new Intent(context, NotificacionSMS.class);
					miIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startService(miIntent);
				}

			}

		} catch (Exception e) {
			Log.e("Recibidor de mensajes", "Se ha producido un error" + e);

		}
	}
}
