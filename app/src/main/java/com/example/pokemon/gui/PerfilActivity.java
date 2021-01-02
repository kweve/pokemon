package com.example.pokemon.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;
import com.example.pokemon.MainActivity;

public class PerfilActivity extends AppCompatActivity {
    TextView GeneroElegido;
    String GeneroRecibido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        GeneroElegido = findViewById(R.id.tGeneroElegido);
        Intent i = getIntent();
        GeneroRecibido = i.getStringExtra("genero");
        GeneroElegido.setText(GeneroRecibido);
    }

    public void configurarPerfil (View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}