package com.example.lopezl_restaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Clase para parar el servicio de notificaciones y lanzar la actividad de
 * reservas de clientes.
 * 
 * 
 */
public class ServicioReserva extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Paramos el servicio de notificaci�n hasta recibir un nuevo SMS
		stopService(new Intent(ServicioReserva.this, NotificacionSMS.class));
		setContentView(R.layout.hacer_reserva);
		startActivity(new Intent(ServicioReserva.this, Reservar.class));

	}
}
