package com.notsorryforbeingme.modul4gita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.notsorryforbeingme.modul4gita.CariGambar;
import com.notsorryforbeingme.modul4gita.ListMahasiswa;
import com.notsorryforbeingme.modul4gita.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonMahasiswa = (Button) findViewById(R.id.buttonFind);//btnMahasiswa
        final Button buttonGambar = (Button) findViewById(R.id.buttonGambar);//buttonGambar


        buttonMahasiswa.setOnClickListener(new View.OnClickListener() {//Apabila button mahasiswa di klik maka akan berpindah ke activity ListMahasiswa

            @Override
            public void onClick(View view) {
                Intent pindahMahasiswa = new Intent(MainActivity.this, ListMahasiswa.class);
                startActivity(pindahMahasiswa);
            }
        });

        buttonGambar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {//Apabila button gambar di klik maka akan berpindah ke activity CariGambar
                Intent pindahGambar = new Intent(MainActivity.this, CariGambar.class);
                startActivity(pindahGambar);
            }
        });

    }
}
