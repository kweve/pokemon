package com.example.pokemon.gui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.R;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class ConfigurarPerfil extends AppCompatActivity {
    private EditText etElegirNombre;
    private ImageView foto;
    private Spinner spGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_perfil);

        etElegirNombre = findViewById(R.id.etElegirNombre);
        foto = findViewById(R.id.iGaleria);
        spGenero = findViewById(R.id.spElegirGenero);

        ArrayList<String> arrayList = new ArrayList<String>(asList("Selecciona un género ...","Masculino","Femenino","Otro"));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,arrayList);
        spGenero.setAdapter(arrayAdapter);

        spGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    String GeneroSeleccionado = parent.getSelectedItem().toString();
                    Intent i = new Intent(view.getContext(), PerfilActivity.class);
                    i.putExtra("genero", GeneroSeleccionado);
                    startActivity(i);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    protected void onPause(){
        //Preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Editor
        SharedPreferences.Editor editor = prefs.edit();
        //Asignar valores y determinar el tipo de dato
        editor.putString("nombre", etElegirNombre.getText().toString());

        editor.apply();
        //Se ejecuta en estado onPause
        super.onPause();
    }

    @Override
    protected void onResume(){
        //Preference
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Asignar dato recogido de preferencias
        etElegirNombre.setText(prefs.getString("nombre", ""));
        super.onResume();
    }

    // Seleccionar foto de galería
    public void galeria (View view) {
        Intent abrirGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        abrirGaleria.setType("image/");
        startActivityForResult(abrirGaleria.createChooser(abrirGaleria, "Seleccione aplicación"), 10);
    }

    // Obtención de la foto y atribuir al ImageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            foto.setImageURI(path);
        }
    }

    public void irPerfil (View view) {
        Intent i = new Intent(this, PokemonInicialActivity.class);
        startActivity(i);
    }
}