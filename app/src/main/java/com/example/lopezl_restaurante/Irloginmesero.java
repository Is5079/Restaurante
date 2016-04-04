package com.example.lopezl_restaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Kc on 28/03/2016.
 */
public class Irloginmesero extends Activity {
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipo);
    }
    public void irloginm (View v) {
        Intent sig = new Intent(this,Login.class);
        startActivity(sig);
    }
}
