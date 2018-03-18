package com.notsorryforbeingme.modul4gita;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.notsorryforbeingme.modul4gita.R;

import java.util.ArrayList;

public class ListMahasiswa extends AppCompatActivity {

    //inisialisasi Array
    private String[] names = {"gita","abimanyu","nabila",
            "rafti","linda","ellsa","anggie","ipir","nissa","noorza","adrian","diki","widi","cepot"};

    //deklarasi Widget
    private Button start;
    private ListView Listview;
    private ProgressBar progressBar;
    private AddItemToListView AddItemToListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);


        //Inisialisasi berdasarkan ID
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Listview = (ListView) findViewById(R.id.listView);
        start = (Button) findViewById(R.id.buttonFind);
        Listview.setVisibility(View.GONE);

        //setAdapter untuk listView, agar listview bisa tersambung dengan data yang ada di array.
        //adapter sebagai penghubung antara adapterView dengan Data sourcenya. untuk kasus ini yaitu listView dan ArrayList.
        Listview.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //Saat button start di klik maka perintah di bawah akan dieksekusi
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddItemToListView = new AddItemToListView(); //pembuatan objek AddItemToListView
                AddItemToListView.execute(); // perintah untuk menjalankan objek yang tadi dibuat
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> Adapter; //deklarasi widget adapter
        private int count=1; //deklarasi variabel count
        ProgressDialog ProgressDialog = new ProgressDialog(ListMahasiswa.this); //pembuatan objek ProgressDialog

        @Override
        protected void onPreExecute() {
            Adapter = (ArrayAdapter<String>) Listview.getAdapter(); //inisialisai adapter terhadap listview

            //pembuatan komponen apa saja yang akan ditampilkan oleh dialog
            ProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //set progress style dari ProgressDialog.STYLE_SPINNER
            ProgressDialog.setTitle("Loading Data"); //set title dari "loading data"
            ProgressDialog.setMessage("Please wait...."); // set massage isinya "please wait..."
            ProgressDialog.setCancelable(false); // set cancelable menjadi false agar tidak bisa di cancel
            ProgressDialog.setProgress(0); //set progress menjadi 0
            ProgressDialog.show(); //untuk menampilakn ProgressDialog (show)
        }

        //pengiriman/pengambilan data terjadi di sini. proses berjalan di background.
        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : names){ //perulangan for dengan isian String item : nama
                publishProgress(item); //publishprogress dengan item
                try { //mencoba melakukan perintah
                    Thread.sleep(200);
                }catch (Exception e){ //jika error akan ditangkap disini
                    e.printStackTrace(); //print error
                }
            }
            return null; //pengembalian nilai null
        }


        //bisa tampilkan UI untuk memberitahu pengambilan data sedang berlangsung.
        //Method untuk menghitung persentase progress dialog yang sedang berproses.
        @Override
        protected void onProgressUpdate(String... values) {
            Adapter.add(values[0]); //set Adapter.add(values[0]);
            Integer current_status = (int) ((count/(float)names.length)*100); // pembuatan variabel current_status isinya perhitungan ((count/(float)nama.length)*100)
            progressBar.setProgress(current_status); //set progress bar dengan current_status
            ProgressDialog.setProgress(current_status); //set progress dialog dengan current_status
            ProgressDialog.setMessage(String.valueOf(current_status+"%")); //set message dengan value dari current_status+"%"
            count++;
        }

        //Method ini untuk melakukan eksekusi setImageBitmap setelah method doInBackground dijalankan
        @Override
        protected void onPostExecute(Void aVoid) {

            Listview.setVisibility(View.VISIBLE); //set visiblity menjadi visible
            progressBar.setVisibility(View.GONE); //set visiblity menjadi gone
            ProgressDialog.dismiss(); //progressdialog dismiss
        }
    }
}
