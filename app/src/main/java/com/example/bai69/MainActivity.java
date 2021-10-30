package com.example.bai69;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button hinh,phim,nhac;
    ImageView photo;
    VideoView film;
    public final int RESULT_IMG=1;
    public final int RESULT_VIDEO=2;
    public final int RESULT_MP3=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        hinh = findViewById(R.id.btnImg);
        phim = findViewById(R.id.btnPhim);
        nhac = findViewById(R.id.btnNhac);

        photo = findViewById(R.id.imgHinh);
        film = findViewById(R.id.vdPhim);

        hinh.setOnClickListener(this);
        phim.setOnClickListener(this);
        nhac.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnImg:{
                Intent getimg = new Intent();
                getimg.setAction(Intent.ACTION_PICK);
                getimg.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(getimg,RESULT_IMG);

                break;
            }
            case R.id.btnPhim:{
                Intent getvideo = new Intent();
                getvideo.setAction(Intent.ACTION_GET_CONTENT);
                getvideo.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(getvideo,RESULT_VIDEO);
                break;
            }
            case R.id.btnNhac:{
                Intent getmp3 = new Intent();
                getmp3.setAction(Intent.ACTION_PICK);
                getmp3.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(getmp3,RESULT_MP3);
                break;
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&&data!=null){
            if(requestCode==RESULT_IMG){
                try{
                    Uri imgData = data.getData();
                    photo.setImageURI(imgData);
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
            if(requestCode==RESULT_VIDEO){
                try{
                    Uri videoData = data.getData();
                   film.setVideoURI(videoData);
                   film.start();
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
            if(requestCode==RESULT_MP3){
                try{
                    Uri imgData = data.getData();
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,imgData);
                    mediaPlayer.start();
                }catch (Exception e){

                    e.printStackTrace();
                }
            }


        }
    }
}