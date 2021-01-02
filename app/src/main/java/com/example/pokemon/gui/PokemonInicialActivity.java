package com.example.pokemon.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;

public class PokemonInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemoninicial);
    }

    public void irPerfil (View view) {
        Intent i = new Intent(this, PerfilActivity.class);
        startActivity(i);
    }
}