package com.devst.app; // Asegúrate que el paquete sea el correctoimport androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PerfilActivity extends AppCompatActivity {

    // Declarar las vistas
    private TextView tvNombreUsuario, tvCorreo, tvContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // Inicializar las vistas
        tvNombreUsuario = findViewById(R.id.tv_nombre_usuario);
        tvCorreo = findViewById(R.id.tv_correo);
        tvContrasena = findViewById(R.id.tv_contrasena);

        // Cargar los datos del usuario que vienen del Intent
        cargarDatosUsuario();
    }

    private void cargarDatosUsuario() {
        // Obtener el Intent con el que se inició esta Activity
        Intent intent = getIntent();

        // Extraer los datos del Intent
        // Usamos "No disponible" como valor por defecto si no se encuentra el dato
        String nombre = intent.getStringExtra("nombre_usuario");
        String correo = intent.getStringExtra("email_usuario");
        String contrasena = intent.getStringExtra("pass_usuario");

        // Asignar los datos a los TextViews
        tvNombreUsuario.setText(nombre != null ? nombre : "Nombre no disponible");
        tvCorreo.setText(correo != null ? correo : "Correo no disponible");

        // Es una mala práctica pasar la contraseña como texto.
        // Lo ideal es mostrar asteriscos.
        if (contrasena != null) {
            tvContrasena.setText("************");
        } else {
            tvContrasena.setText("");
        }
    }
}
