package com.example.eggtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView;
    SeekBar seekBar;
    Button button;
    CountDownTimer countDownTimer;
    boolean active=true;

    public void botton(View view){
        if ( active){
            active = false;
            button.setText("Stopp");
            seekBar.setEnabled(false);
            Log.i("message", "button clicked");
            countDownTimer=new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    update((int) (millisUntilFinished / 1000));
                }

                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.a);
                    mediaPlayer.start();
                    imageView.animate().alpha(0);
                    ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
                    imageView2.animate().alpha(1);

                }
            }.start();
        }

       else {


            countDownTimer.cancel();
            button.setText("go");
            active=true;
            seekBar.setEnabled(true);


        }

    }

    public void update(int i){
        int min=i/60;
        int sec=i-(min*60);
        textView.setText(Integer.toString(min)+":"+Integer.toString(sec));

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        textView=(TextView) findViewById(R.id.textView);
        imageView=(ImageView)findViewById(R.id.imageView);
        button=(Button) findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setMin(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                update(i);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}