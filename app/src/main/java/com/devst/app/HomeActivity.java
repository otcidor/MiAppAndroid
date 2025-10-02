package com.devst.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;



public class HomeActivity extends AppCompatActivity {

    //Creamos variables encapsuladas
    private String emailUsuario = "";
    private TextView tvBienvenida;

    //Cargar la activity para los datos extraidos
    private final ActivityResultLauncher<Intent> editarPerfilLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
                if (result.getResultCode() == RESULT_OK && result.getData() != null){
                    String nombre = result.getData().getStringExtra("nombre_editado");
                    if (nombre != null){
                        tvBienvenida.setText("Hola, " + nombre);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        //Referencias
        tvBienvenida = findViewById(R.id.tvBienvenida);
        Button btnIrPerfil = findViewById(R.id.btnIrPerfil);
        Button btnAbrirWeb = findViewById(R.id.btnAbrirWeb);
        Button btnEnviarCorreo = findViewById(R.id.btnEnviarCorreo);
        Button btnCompartir = findViewById(R.id.btnCompartir);
        Button btnAcercaDe = findViewById(R.id.btnAcercaDe);

        //Cargamos el nombre del usuario (Correo)
        emailUsuario = getIntent().getStringExtra("email_usuario");
        if(emailUsuario == null) emailUsuario = "";
        tvBienvenida.setText("Bienvenida: " + emailUsuario);

        //Funcionamiento de botón explícito perfil
        btnIrPerfil.setOnClickListener(View ->{
            Intent perfil = new Intent(HomeActivity.this, PerfilActivity.class);
                    perfil.putExtra("email_usuario", emailUsuario); //Extrer variable
            editarPerfilLauncher.launch(perfil);
        });

        //Funciones implícitas
        btnAbrirWeb.setOnClickListener(v ->{
            Uri url = Uri.parse("http://www.youtube.cl");
            Intent viewWeb = new Intent(Intent.ACTION_VIEW, url);
            startActivity(viewWeb);
        });

        //Funciones Intent Implicitas Emails ((Creando un objeto dentro de una instancia))
        btnEnviarCorreo.setOnClickListener(v ->{
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:")); //SOLO CORREO
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailUsuario});
            email.putExtra(Intent.EXTRA_SUBJECT, "Prueba desde la APP");
            email.putExtra(Intent.EXTRA_TEXT, "Hola, estamos realizando pruebas!");
            startActivity(Intent.createChooser(email, "Enviar información de prueba"));
        });
        //Eventos Intent Implícitos para compartir
        btnCompartir.setOnClickListener(v->{
            Intent compartit = new Intent(Intent.ACTION_SEND);
            compartit.setType("text/plain");
            compartit.putExtra(Intent.EXTRA_TEXT, "Hola mundo desde el btn compartir");
            startActivity(Intent.createChooser(compartit, "Compartir Saludo"));
        });
        //Eventos Intent Implícitos para acerca de para ir a la web de GitHub
        btnAcercaDe.setOnClickListener(v ->{
            Uri url = Uri.parse("https://github.com/otcidor");
            Intent viewWeb = new Intent(Intent.ACTION_VIEW, url);
            startActivity(viewWeb);
        });

    }
}