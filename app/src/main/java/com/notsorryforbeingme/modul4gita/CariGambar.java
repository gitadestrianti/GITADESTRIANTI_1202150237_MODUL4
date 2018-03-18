package com.notsorryforbeingme.modul4gita;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.notsorryforbeingme.modul4gita.R;

import java.io.InputStream;
import java.net.URL;

public class CariGambar extends AppCompatActivity {
    ImageView imageViewURL;
    Bitmap bit;
    Button buttonFind;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari_gambar);

        imageViewURL = (ImageView)findViewById(R.id.imageView);
        buttonFind = (Button)findViewById(R.id.buttonFind);
        editText = (EditText)findViewById(R.id.editText);


        buttonFind.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {//Apabila button find di klik maka..
                // Image link from internet
                new DownloadImageFromInternet((ImageView) findViewById(R.id.imageView))
                        .execute(editText.getText().toString());//BIAR BISA AMBIL URL DR EDITTEXT
            }
        });
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few moment...", Toast.LENGTH_SHORT).show();
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}

