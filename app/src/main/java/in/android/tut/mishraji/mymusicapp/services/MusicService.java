package in.android.tut.mishraji.mymusicapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import de.greenrobot.event.EventBus;
import in.android.tut.mishraji.mymusicapp.Events.MusicCompletedEvent;
import in.android.tut.mishraji.mymusicapp.Events.MusicStatus;
import in.android.tut.mishraji.mymusicapp.Model.Music;

/**
 * Created by abhinava on 5/8/15.
 */
public class MusicService extends Service {

    private static MediaPlayer mediaPlayer;
    private Music music;

    public static final String KEY_METHOD = "method";

    public static final String KEY_START = "method_start";

    public static final String KEY_PLAY = "method_play";
    public static final String KEY_PAUSE = "method_pause";
    public static final String KEY_STOP = "method_stop";
    public static final String KEY_FF = "method_ff";
    public static final String KEY_RW = "method_rw";


    public static int getCurrentPosition(){
        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying()){
                return mediaPlayer.getCurrentPosition();
            }
        }
        return -1;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("std", "service onStartCommand()");

        String method = intent.getStringExtra(KEY_METHOD);
        Log.d("std","reached checkpoint 1");

        if(method.equals(KEY_START)){
           music = (Music)intent.getSerializableExtra("musicName");
           if(mediaPlayer!=null){
               mediaPlayer.stop();
           }
           mediaPlayer = MediaPlayer.create(this, this.getResources().getIdentifier(music.getFileName(),"raw",this.getPackageName()));

            Log.d("music","Song name is "+music.getSongName());
           playSong();

        }
        //if required
        if(method.equals(KEY_PLAY)){
            playSong();
        }
        if(method.equals(KEY_PAUSE)){
            pauseSong();
        }
        if(method.equals(KEY_STOP)){
            stopSong();
        }

        if(method.equals(KEY_RW)){
            rwSong();
        }
        if(method.equals(KEY_FF)){
            fwSong();
        }

        if(mediaPlayer!=null){
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    EventBus.getDefault().post(new MusicCompletedEvent(music.getSongName(),music.getArtistName()));
                }
            });
        }


        return super.onStartCommand(intent, flags, startId);
    }

    public static void rwSong() {
        if(mediaPlayer.getCurrentPosition()>2000) {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 2000);
        }
        else{
            mediaPlayer.seekTo(0);
        }

    }

    public static void fwSong() {

        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+2000);
    }

    public static void stopSong() {

        mediaPlayer.stop();
        EventBus.getDefault().post(new MusicStatus(false));
        EventBus.getDefault().post(new MusicCompletedEvent("random","value"));

    }

    public static void pauseSong() {
        mediaPlayer.pause();
        EventBus.getDefault().post(new MusicStatus(false));
    }

    public static void playSong() {

        mediaPlayer.start();
        EventBus.getDefault().post(new MusicStatus(true));
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static int getDuration() {
        if(mediaPlayer!=null){
            return mediaPlayer.getDuration();
        }
        return  -1;
    }

    public static void seekTo(int progress){
        if(mediaPlayer!=null){
            mediaPlayer.seekTo(progress);
        }
    }
}
