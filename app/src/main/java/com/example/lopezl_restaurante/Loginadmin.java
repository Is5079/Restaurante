package com.example.lopezl_restaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Kc on 28/03/2016.
 */
public class Loginadmin extends Activity {
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tipo);
    }
    public void irlogina (View v) {
        Intent sig = new Intent(this,Login1.class);
        startActivity(sig);
    }
}