package com.dcvs.nac.dcvswidget;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class NACRadio extends AppCompatActivity {
private boolean playPause;
private MediaPlayer mediaPlayer;
    /**
     * remain false till media is not completed
     */
    private boolean initialStage = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nacradio);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }

    public void PlayBTNClick(View v) {
        if (initialStage)
            new Player()
                    .execute("http://janus.cdnstream.com:5189/stream2");
        else {
            if (!mediaPlayer.isPlaying())
                mediaPlayer.start();
        }
        playPause = true;
    }

    public void PauseBTNClick(View v) {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
        playPause = false;
    }

class Player extends AsyncTask<String, Void, Boolean> {
    private ProgressDialog progress;

    @Override
    protected Boolean doInBackground(String... params){
        Boolean prepared;
        try {
            mediaPlayer.setDataSource(params[0]);

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    initialStage = true;
                    playPause = false;
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
            mediaPlayer.prepare();
            prepared = true;
        } catch (IllegalArgumentException e) {
            Log.d("IllegalArgument", e.getMessage());
            prepared = false;
            e.printStackTrace();
        } catch (SecurityException e) {
            prepared = false;
            e.printStackTrace();
        } catch (IllegalStateException e) {
            prepared = false;
            e.printStackTrace();
        } catch (IOException e) {
            prepared = false;
            e.printStackTrace();
        }
        return prepared;
    }

    @Override
    protected void onPostExecute(Boolean result){
        super.onPostExecute(result);
        if(progress.isShowing()) {
            progress.cancel();
        }
        Log.d("Prepared", "//" + result);
        mediaPlayer.start();

        initialStage = false;
    }

    public Player() {
        progress = new ProgressDialog(NACRadio.this);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        this.progress.setMessage("Buffering...");
        this.progress.show();
    }
}

@Override
    protected void onPause(){
    super.onPause();
    if (mediaPlayer != null) {
        mediaPlayer.reset();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}

}
