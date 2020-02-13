package com.muhammetacar.currencyfollowingapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.muhammetacar.currencyfollowingapp.view.FollowingPage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.giris)
    ImageView girisImage;


    private static int gosterim_suresi = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,FollowingPage.class);
                startActivity(intent);
                finish();
            }
        }, gosterim_suresi);
    }





}