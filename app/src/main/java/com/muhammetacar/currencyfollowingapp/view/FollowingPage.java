package com.muhammetacar.currencyfollowingapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.muhammetacar.currencyfollowingapp.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class FollowingPage extends AppCompatActivity {

    TextView tryText;
    TextView cadText;
    TextView usdText;
    TextView dateText;


   // ArrayList<CurrencyModel> CurrencyModels;
   // private String Base_Url="http://data.fixer.io/api/";
   // Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following_page);

       identifyVariable();
        updateDisplay();



        //Gson gson=new GsonBuilder().setLenient().create();

       // retrofit=new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create(gson)).build();
        //updateDisplay();

    }
//http://data.fixer.io/api/latest?access_key=24b6287582290423a46e5bab0b73c62f&format=1

public void identifyVariable(){
    tryText = findViewById(R.id.tryText);
    cadText = findViewById(R.id.cadText);
    usdText = findViewById(R.id.usdText);
    dateText=findViewById(R.id.date);


}
    private void takeData(){

        DownloadData downloadData = new DownloadData();

        try {

            String url = "http://data.fixer.io/api/latest?access_key=007dab3d85dddc648c4c68f8e6ecada7&format=1";

            downloadData.execute(url);


        } catch (Exception e) {

        }


       //     CurrencyApi currencyApi=retrofit.create(CurrencyApi.class);

//        Call<List<CurrencyModel>> call = currencyApi.getData();


//
//            call.enqueue(new Callback<List<CurrencyModel>>() {
//                             @Override
//                             public void onResponse(Call<List<CurrencyModel>> call, Response<List<CurrencyModel>> response) {
//
//
//                                if (response.isSuccessful()) {
//                                     System.out.println("yessssssssssssssss");
//                                     List<CurrencyModel> responseList = response.body();
//
//                                     CurrencyModels = new ArrayList<>(responseList);
//
//                                     for (CurrencyModel a : CurrencyModels) {
//                                         System.out.println(a.date);
//                                     }
//                                 }
//                            }
//
//
//
//
//            @Override
//            public void onFailure(Call<List<CurrencyModel>> call, Throwable t) {

//                t.printStackTrace();
//
//            }
//
//
//
//
//    });

}
    private class DownloadData extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();
                }

                return result;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //System.out.println("alınan data:" + s);

            try {

                JSONObject jsonObject = new JSONObject(s);



                String rates = jsonObject.getString("rates");

                JSONObject jsonObject1 = new JSONObject(rates);


                String turkishlira = jsonObject1.getString("TRY");
                tryText.setText("TRY: " + turkishlira);

                String usd = jsonObject1.getString("USD");
                usdText.setText("USD: " + usd);

                String cad = jsonObject1.getString("CAD");
                cadText.setText("CAD: " + cad);


                Timestamp ts=new Timestamp(System.currentTimeMillis());
                Date dates=ts;
                System.out.println(dates);



                        Date Date = Calendar.getInstance().getTime();
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                        String strDate = dateFormat.format(Date);



                dateText.setText(strDate);
            } catch (Exception e) {

            }


        }
    }

public void updateDisplay() {
    Timer timer = new Timer();
    timer.schedule(new TimerTask() {
        //@Override
        public void run() {
           takeData();
        }
    },0,2000);//Update text every 2 second

}
}