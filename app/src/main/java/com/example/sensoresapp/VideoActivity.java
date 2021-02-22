package com.example.sensoresapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ImageButton btnretunrC = (ImageButton)findViewById(R.id.imgVoverV);
        btnretunrC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnc = new Intent(VideoActivity.this, MainActivity.class);
                startActivity(returnc);
                finish();
            }
        });

        videoView = (VideoView)findViewById(R.id.videoView);
        videoView.setVideoURI(Uri.parse("android.resource://"+ getPackageName() + "/" + R.raw.video));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        videoView.start();
    }

}