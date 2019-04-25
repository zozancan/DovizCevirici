package com.zozancan.dovizcevirici;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cevir (View view) {

        VeriIndir veriIndir = new VeriIndir();

        try {

            String url = "http://data.fixer.io/api/latest?access_key=4c1ce97fd4eb7e86fa35bc7316bb687b&format=1";

            veriIndir.execute(url);

        } catch (Exception e) {

        }

    }

    public class VeriIndir extends AsyncTask<String, Void,String> {

        @Override
        protected String doInBackground(String... strings) {

            String sonuc = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int veri = inputStreamReader.read();

                while (veri > 0) {

                    char karakter = (char) veri;
                    sonuc += karakter;

                    veri = inputStreamReader.read();
                }

                return sonuc;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            System.out.println("Alınan data : " +s);
        }
    }
}
