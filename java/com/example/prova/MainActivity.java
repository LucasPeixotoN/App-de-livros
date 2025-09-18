package com.example.prova;


import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        List<String> livros = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, livros);

        TextView txt = findViewById(R.id.textViewMensagem);
        txt.setText("Qual livro voce leu hoje");

        EditText editTextNome = findViewById(R.id.editTextNome);
        editTextNome.setHint("Titulo");

        EditText editTextAut = findViewById(R.id.editTextAutor);
        editTextAut.setHint("Autor");

        CheckBox checkBoxLido = findViewById(R.id.checkBox);

        ListView lista =  findViewById(R.id.lista);
        lista.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = editTextNome.getText().toString();
                String autor = editTextAut.getText().toString();
                boolean lido = checkBoxLido.isChecked();

                String statusLido = lido ? "Lido" : "Não Lido";
                String infoLivro = "Título: " + titulo + "\nAutor: " + autor + "\nStatus: " + statusLido;

                livros.add(infoLivro);
                adapter.notifyDataSetChanged();


                editTextNome.setText("");
                editTextAut.setText("");
                checkBoxLido.setChecked(false);

                Toast.makeText(MainActivity.this, "Livro adicionado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}