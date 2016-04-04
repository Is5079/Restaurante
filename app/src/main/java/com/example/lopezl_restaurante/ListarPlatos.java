package com.example.lopezl_restaurante;

import android.app.Activity;
import android.os.Bundle;

/**
 * Clase para mostrar la carta a los clientes del restaurante. Se lanza al
 * pulsar el bot�n 'Ver carta' de la actividad principal.
 * 
 * 
 */
public class ListarPlatos extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plato_lista);
	}
}
