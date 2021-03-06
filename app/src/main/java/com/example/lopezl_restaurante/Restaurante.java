package com.example.lopezl_restaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Clase principal del restaurante. Permite ver la carta del restaurante, pedir
 * comandas, calcular la cuenta de una mesa, cobrar mesas y gestionar el alta y
 * baja de reservas de clientes.
 * 
 * 
 */
public class Restaurante extends Activity {

	private Button boton;
	public GestorBDRestaurante gbdRest = new GestorBDRestaurante(this, "platos", null, 1);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurante);

		gbdRest.cargarDatos();

		boton = (Button) findViewById(R.id.Button01);
		boton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				lanzarPlatos(null);
			}
		});

		boton = (Button) findViewById(R.id.Button02);
		boton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				lanzarpedido_comensal(null);
			}
		});

		boton = (Button) findViewById(R.id.Button03);
		boton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				lanzarCalcularPedido(null);
			}
		});


		boton = (Button) findViewById(R.id.Button06);
		boton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				lanzarsalir(null);
			}
		});

		boton = (Button) findViewById(R.id.Button04);
		boton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				lanzarConsultaChef(null);
			}
		});

		boton = (Button) findViewById(R.id.calpecom);
		boton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View view) {
				calpecom(null);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurante, menu);
		return true;
	}

	public void calpecom(View view){
		Intent i = new Intent(this,CalcularPedido_com.class);
		startActivity(i);
	}

	public void lanzarPlatos(View view) {
		Intent i = new Intent(this, ListarPlatos.class);
		startActivity(i);
	}

	public void lanzarpedido_comensal(View view) {

		Intent i = new Intent(this, pedido_comensal.class);
		startActivity(i);
	}

	public void lanzarCalcularPedido(View view) {
		Intent i = new Intent(this, CalcularPedido.class);
		startActivity(i);
	}

	public void lanzarReserva(View view) {
		Intent i = new Intent(this, Reservar.class);
		startActivity(i);
	}

	public void lanzarEliminarReserva(View view) {
		Intent i = new Intent(this, EliminarReserva.class);
		startActivity(i);
	}

	public void lanzarsalir(View view) {

		Intent i = new Intent(this, Tipo.class);
		startActivity(i);
	}

	public void lanzarConsultaChef(View view) {
		Intent i = new Intent(this, Consultar.class);
		startActivity(i);
	}

}
