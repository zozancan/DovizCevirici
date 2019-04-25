package com.zozancan.dovizcevirici;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView cadText;
    TextView chfText;
    TextView usdText;
    TextView jpyText;
    TextView tryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cadText = findViewById(R.id.cadText);
        chfText = findViewById(R.id.chfText);
        usdText = findViewById(R.id.usdText);
        jpyText = findViewById(R.id.jpyText);
        tryText = findViewById(R.id.tryText);
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

            // System.out.println("Alınan data : " +s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                String rates = jsonObject.getString("rates");

                JSONObject jsonObject1 = new JSONObject(rates);

                String turkLirasi =jsonObject1.getString("TRY");
                tryText.setText("TRY : " + turkLirasi);

                String usd =jsonObject1.getString("USD");
                usdText.setText("USD : " + usd);

                String cad =jsonObject1.getString("CAD");
                cadText.setText("CAD : " + cad);

                String jpy =jsonObject1.getString("JPY");
                jpyText.setText("JPY : " + jpy);

                String chf =jsonObject1.getString("CHF");
                chfText.setText("CHF : " + chf);



            } catch (Exception e) {

            }
        }
    }
}
