package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nim, nama, prodi, kelas, kelompok;

    private Button tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisilisasi
        nim = (EditText) findViewById(R.id.editTextNim);
        nama = (EditText) findViewById(R.id.editTextNama);
        prodi = (EditText) findViewById(R.id.editTextProdi);
        kelas = (EditText) findViewById(R.id.editTextKelas);
        kelompok = (EditText) findViewById(R.id.editTextKelompok);

        tambah = (Button) findViewById(R.id.buttonAdd);

        tambah.setOnClickListener(this);

    }

    //Dibawah ini merupakan perintah untuk Menambahkan mahasiswa (CREATE)
    private void addEmployee(){

        final  String NIM = nim.getText().toString().trim();
        final String Nama = nama.getText().toString().trim();
        final  String Prodi = prodi.getText().toString().trim();
        final  String Kelas = kelas.getText().toString().trim();
        final String Kelompok = kelompok.getText().toString().trim();

        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_NIM, NIM);
                params.put(Konfigurasi.KEY_EMP_nama, Nama);
                params.put(Konfigurasi.KEY_EMP_prodi,Prodi);
                params.put(Konfigurasi.KEY_EMP_kelas, Kelas);
                params.put(Konfigurasi.KEY_EMP_kelompok,Kelompok);



                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }




        @Override
    public void onClick(View v) {

            if(v == tambah){
                addEmployee();
            }

    }
}