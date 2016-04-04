package com.example.lopezl_restaurante;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;


public class GestorBDRestaurante extends SQLiteOpenHelper {
	// M�todos de SQLiteOpenHelper
	public GestorBDRestaurante(Context context, String platos, Object o, int i) {
		super(context, "platos", null, 1);
	}

	/**
	 * M�todo para crear las tablas necesarias en la BD.
	 * 
	 * 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE usuarios ("
				+ "id_usr INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre_usr TEXT, "
				+ "pass ");
		db.execSQL("CREATE TABLE platos ("
				+ "id_plato INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre TEXT, precio FLOAT)");
		db.execSQL("CREATE TABLE mesas ("
				+ "id_mesa INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "num_comensales INTEGER)");
		db.execSQL("CREATE TABLE cuenta ("
				+ "id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "total_cuenta FLOAT, id_mesa INTEGER, "
				+ "FOREIGN KEY (id_mesa) REFERENCES mesas (id_mesa))");

		db.execSQL("CREATE TABLE pedido (id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "num_platos INTEGER, cobrado INTEGER, id_plato INTEGER, id_cuenta INTEGER, id_mesa INTEGER, "
				+ "FOREIGN KEY(id_plato) REFERENCES platos(id_plato),"
				+ "FOREIGN KEY(id_cuenta) REFERENCES cuenta(id_cuenta),"
				+ "FOREIGN KEY(id_mesa) REFERENCES mesas(id_mesa))");

		db.execSQL("CREATE TABLE reserva ("
				+ "id_reserva INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nombre_cliente TEXT, num_clientes INTEGER, fecha TEXT, id_mesa INTEGER, "
				+ "FOREIGN KEY (id_mesa) REFERENCES mesas (id_mesa))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * M�todo para indicar el plato a dar de alta en la BD
	 * 
	 * 
	 */
	private void guardarPlato(String nombre, float precio) {
		SQLiteDatabase db = getWritableDatabase();
		guardarPlatos(db, nombre, precio);
	}

	/**
	 * M�todo para introducir el plato en la BD
	 * 
	 * 
	 */
	private void guardarPlatos(SQLiteDatabase db, String nombre, float precio) {
		ContentValues cv = new ContentValues();
		cv.put("nombre", nombre);
		cv.put("precio", precio);
		db.insertOrThrow("platos", null, cv);
	}

	/**
	 * M�todo para indicar la mesa a dar de alta en la BD
	 * 
	 * 
	 */
	private void guardarMesa(int max_comensales) {
		SQLiteDatabase db = getWritableDatabase();
		guardarMesas(db, max_comensales);
	}

	/**
	 * M�todo para introducir la mesa en la BD
	 * 
	 * 
	 */
	private void guardarMesas(SQLiteDatabase db, int max_comensales) {
		ContentValues cv = new ContentValues();
		cv.put("num_comensales", max_comensales);
		db.insertOrThrow("mesas", null, cv);
	}

	/**
	 * M�todo para cargar los datos iniciales en la BD. Se llama al iniciar la
	 * actividad principal.
	 * 
	 * 
	 */
	public void cargarDatos() {
		guardarPlato("gazpacho", 9.00f);
		guardarPlato("carpaccio_ternera", 9.00f);
		guardarPlato("carpaccio_pescado", 11.00f);
		guardarPlato("ensalada_caprese", 7.50f);
		guardarPlato("ensalada_remolacha", 9.00f);
		guardarPlato("espagueti_carbonara", 9.00f);
		guardarPlato("pasta_pesto", 9.00f);
		guardarPlato("ravioli_setas", 11.00f);
		guardarPlato("rissotto_carne", 11.00f);
		guardarPlato("rissotto_setas", 11.50f);
		guardarPlato("ternera_atun", 15.00f);
		guardarPlato("tiras_carne", 13.00f);
		guardarPlato("salmon_ris", 15.00f);
		guardarPlato("piccata", 12.00f);
		guardarPlato("polenta", 11.50f);
		guardarPlato("pizza_caprese", 11.00f);
		guardarPlato("pizza_vegetariana", 11.00f);
		guardarPlato("pizza_calzone", 10.50f);
		guardarPlato("pizza_peperoni", 11.00f);
		guardarPlato("pizza_casa", 12.50f);
		guardarPlato("cannoli", 6.50f);
		guardarPlato("tiramisu", 5.50f);
		guardarPlato("pina_chocolate", 5.50f);
		guardarPlato("helado_limon", 4.50f);
		guardarPlato("pannacotta", 5.50f);
		guardarPlato("agua", 1.50f);
		guardarPlato("refresco_cola", 2.00f);
		guardarPlato("refresco_limon", 2.00f);
		guardarPlato("vino_tinto", 10.50f);
		guardarPlato("lambrusco", 8.50f);
		guardarMesa(4);
		guardarMesa(4);
		guardarMesa(4);
		guardarMesa(4);
		guardarMesa(4);
		guardarMesa(4);
		guardarMesa(4);
		guardarMesa(4);
		guardarMesa(6);
		guardarMesa(6);
		guardarMesa(6);
		guardarMesa(6);
	}

	/**
	 * M�todo para introducir una nueva cuenta en la BD. Se llama desde el
	 * m�todo crearPedido de la clase PedirPlatos.
	 * 
	 * 
	 * @param id_mesa
	 *            : n�mero de la mesa asociada a la cuenta.
	 * @return long: n�mero de fila de la BD donde se ha insertado la cuenta.
	 */

	public long crearCuenta(int id_mesa) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("total_cuenta", 0.00f);
		cv.put("id_mesa", id_mesa);
		return db.insertOrThrow("cuenta", null, cv);

	}

	/**
	 * M�todo para introducir un nuevo pedido en la BD. Se llama desde el m�todo
	 * crearPedido de la clase PedirPlatos.
	 * 
	 * 
	 * @param id_plato
	 *            : identificador del n�mero de plato.
	 * @param num_platos
	 *            : cantidad de un plato determinado.
	 * @param id_cuenta
	 *            : cuenta asociada al pedido.
	 */
	public void hacerPedido(int id_plato, int num_platos, int id_cuenta,
			int id_mesa) {
		SQLiteDatabase db = getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("num_platos", num_platos);
		cv.put("cobrado", 0);
		cv.put("id_cuenta", id_cuenta);
		cv.put("id_plato", id_plato);
		cv.put("id_mesa", id_mesa);
		db.insertOrThrow("pedido", null, cv);

	}
	/**
	 * M�todo para calcular el total a pagar de una mesa. Se llama desde el
	 * m�todo calcularTotalMesa de la clase CalcularPedido.
	 * 
	 * 
	 * @param id_mesa
	 *            : identificador de la mesa a calcular.
	 * @return float: precio total a pagar.
	 */
	public float calcularCuenta(int id_mesa) {
		float precioTotal = 0.00f;
		SQLiteDatabase db = getWritableDatabase();

		String[] param = new String[1];
		param[0] = Integer.toString(id_mesa, 10);

		Cursor cursor = db.rawQuery("SELECT id_plato, num_platos FROM "
				+ "pedido WHERE cobrado=0 AND id_mesa=?", param);

		// Obtenemos los �ndices de las columnas
		int cantidadColumn = cursor.getColumnIndex("num_platos");
		int id_platoColumn = cursor.getColumnIndex("id_plato");

		if (cursor.moveToFirst() == false) { // el cursor est� vac�o
		} else {

			// Recorremos el cursor
			for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
					.moveToNext()) {

				int cantidad = cursor.getInt(cantidadColumn);
				int id_plato = cursor.getInt(id_platoColumn);
				String[] param2 = new String[1];
				param2[0] = Integer.toString(id_plato, 10);

				Cursor cursor2 = db.rawQuery(
						"SELECT precio from platos WHERE id_plato=?", param2);
				// Obtenemos los �ndices de las columnas
				int precioColumn = cursor2.getColumnIndex("precio");

				if (cursor2.moveToFirst() == false) { // el cursor est� vac�o
				} else {
					// Recorremos el cursor
					for (cursor2.moveToFirst(); !cursor2.isAfterLast(); cursor2
							.moveToNext()) {
						float precio = cursor2.getFloat(precioColumn);
						precioTotal += precio * cantidad;

					}

				}

			}

		}

		return precioTotal;

	}

	/**
	 * M�todo para indicar que los clientes de una mesa han pagado sus cuentas.
	 * Se llama desde el m�todo cobrarMesa de la clase CalcularPedido.
	 * 
	 * 
	 * @param id_mesa
	 *            : identificador de la mesa a calcular.
	 */
	public void cobrarMesa(int id_mesa) {
		SQLiteDatabase db = getWritableDatabase();

		String[] param = new String[1];
		param[0] = Integer.toString(id_mesa, 10);

		ContentValues cv = new ContentValues();
		cv.put("cobrado", 1);
		String where = "id_mesa=?";

		db.update("pedido", cv, where, param);
	}

	/**
	 * M�todo para introducir una nueva reserva en la BD. Se llama desde el
	 * m�todo crearReserva de la clase Reservar.
	 * 
	 * 
	 * @param nombre
	 *            : nombre del cliente (deber�a ser �nico).
	 * @param num_clientes
	 *            : n�mero total de clientes.
	 * @param fecha
	 *            : dia de la reserva.
	 */
	public boolean hacerReserva(String nombre, int num_clientes, String fecha) {
		SQLiteDatabase db = getWritableDatabase();
		boolean reservaRealizada = false;

		String[] param = new String[1];
		param[0] = Integer.toString(num_clientes, 10);

		Cursor cursor = db.rawQuery("SELECT id_mesa FROM "
				+ "mesas WHERE num_comensales>=?", param);

		// Obtenemos los �ndices de las columnas
		int id_mesaColumn = cursor.getColumnIndex("id_mesa");

		if (cursor.moveToFirst()) {

			do { // id_mesa donde pueden caber los clientes
					// Recorremos el cursor

				int id_mesa = cursor.getInt(id_mesaColumn);
				String[] param2 = new String[1];
				param2[0] = Integer.toString(id_mesa, 10);

				Cursor cursor2 = db.rawQuery(
						"SELECT fecha from reserva WHERE id_mesa=?", param2); // Obtenemos
																				// todas
																				// las
																				// reservas
																				// de
																				// esa
																				// mesa
				// Obtenemos los �ndices de las columnas
				int fechaColumn = cursor2.getColumnIndex("fecha");

				if (cursor2.moveToFirst() == false) { // el cursor est� vac�o
					// No hay reserva en ninguna fecha para esta mesa. Podemos
					// reservar
					// Introducimos la reserva
					ContentValues cv1 = new ContentValues();
					cv1.put("nombre_cliente", nombre);
					cv1.put("num_clientes", num_clientes);
					cv1.put("fecha", fecha);
					cv1.put("id_mesa", id_mesa);
					db.insertOrThrow("reserva", null, cv1);
					reservaRealizada = true;

				} else {
					// Recorremos el cursor
					boolean diaReservado = false;
					for (cursor2.moveToFirst(); !cursor2.isAfterLast(); cursor2
							.moveToNext()) {
						String fecha1 = cursor2.getString(fechaColumn);
						if (fecha.equals(fecha1)) { // Si para alguna de las
													// reservas de la mesa la
													// fecha coincide
							diaReservado = true; // Ya lo est�
						}
					}

					if (!diaReservado) {
						// No hay reserva en esta fecha para esta mesa. Podemos
						// reservar
						// Introducimos la reserva
						ContentValues cv1 = new ContentValues();
						cv1.put("nombre_cliente", nombre);
						cv1.put("num_clientes", num_clientes);
						cv1.put("fecha", fecha);
						cv1.put("id_mesa", id_mesa);
						db.insertOrThrow("reserva", null, cv1);
						reservaRealizada = true;
					}

				}

			} while (cursor.moveToNext() && !reservaRealizada); // Mientras haya
																// mesas y la
																// reserva no se
																// haya
																// realizado
		}

		return reservaRealizada;

	}

	/**
	 * M�todo para eliminar una reserva de un cliente. Se llama desde el m�todo
	 * eliminarReserva de la clase EliminarReserva.
	 * 
	 * 
	 * @param id_mesa
	 *            : identificador de la mesa a calcular.
	 */
	public int eliminarReserva(String nombre, String fecha) {
		SQLiteDatabase db = getWritableDatabase();

		String[] param = new String[2];
		param[0] = nombre;
		param[1] = fecha;

		String where = "nombre_cliente=? AND fecha=?";

		return db.delete("reserva", where, param);

	}

}
