package com.example.lopezl_restaurante;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.*;

public class Reportes extends Activity {

    private TextView txtResultado1;

    private Button btnConsultar1;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reportes);

        //Obtenemos las referencias a los controles
        txtResultado1 = (TextView) findViewById(R.id.txtResultado1);
        btnConsultar1 = (Button) findViewById(R.id.btnConsultar1);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        GestorBDRestaurante usdbh = new GestorBDRestaurante(this, "platos", null, 1);

        db = usdbh.getWritableDatabase();
        Toast.makeText(this, "Creando reporte. Para visualizarlo presione Consultar", Toast.LENGTH_SHORT).show();
        btnConsultar1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                //Alternativa 1: método rawQuery()
                Cursor c = db.rawQuery("SELECT num_platos, cobrado, id_plato, id_cuenta, id_mesa FROM pedido", null);
                Cursor c2 = db.rawQuery("SELECT precio FROM platos", null);
                //Alternativa 2: método delete()
                //String[] campos = new String[] {"codigo", "nombre"};
                //Cursor c = db.query("Usuarios", campos, null, null, null, null, null);

                //Recorremos los resultados para mostrarlos en pantalla
                txtResultado1.setText("");
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        String num_platos = c.getString(0);
                        String cobrado = c.getString(1);
                        String id_plato = c.getString(2);
                        String id_cuenta = c.getString(3);
                        String id_mesa = c.getString(4);

                                txtResultado1.append("# de cuenta:" + id_cuenta + " ¿cobrado?:" + cobrado + " mesa:" + id_mesa + "\n");
                                txtResultado1.setMovementMethod(new ScrollingMovementMethod());

                        } while (c.moveToNext());


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}
