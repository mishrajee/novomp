    package in.android.tut.mishraji.mymusicapp;

    import android.content.Context;
    import android.content.SharedPreferences;
    import android.content.res.Resources;
    import android.media.Image;
    import android.media.MediaPlayer;
    import android.os.Message;
    import android.preference.PreferenceManager;
    import android.support.annotation.NonNull;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.util.AttributeSet;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.SeekBar;
    import android.widget.Toast;

    import android.os.Handler;

    import com.squareup.picasso.Picasso;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.logging.LogRecord;

    import de.greenrobot.event.EventBus;
    import in.android.tut.mishraji.mymusicapp.Events.MusicCompletedEvent;
    import in.android.tut.mishraji.mymusicapp.Events.MusicStatus;
    import in.android.tut.mishraji.mymusicapp.Model.Music;
    import in.android.tut.mishraji.mymusicapp.services.MusicService;


    public class MainActivity extends ActionBarActivity {
        Button mPlay, mFF, mRW;
        //MediaPlayer mediaPlayer;
        SeekBar seekBar;
        private Boolean isPlaying;
        private List<Music> musicList = new ArrayList<>();
        private int position;
        private ImageView imageView;
        private Music music;

        private static String TAG = "std";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            EventBus.getDefault().register(this);

            initializeMusicList();

            imageView = (ImageView) findViewById(R.id.activity_main_image);

            Log.v("std","MainActivity is started");

            music=(Music) getIntent().getSerializableExtra("music");

            String fileName=music.getFileName();


            Picasso
                    .with(this)
                    .load(music.getAlbumName())
                    .error(R.drawable.ic_launcher)
                    .into(imageView);

            //mediaPlayer = MediaPlayer.create(this, this.getResources().getIdentifier(fileName,"raw",this.getPackageName()));
            //Log.d("std","mediaPlayer initialized");

            mPlay = (Button) findViewById(R.id.activity_main_play);
            mFF = (Button) findViewById(R.id.activity_main_fast);
            mRW = (Button) findViewById(R.id.activity_main_rewind);
            seekBar = (SeekBar) findViewById(R.id.activity_main_seeked);

            final MusicHandler musicHandler = new MusicHandler();

            seekBar.setMax(MusicService.getDuration());

            musicHandler.sendEmptyMessage(SEEK_SLEEP);/////////////////////////////////////////////////////////////////////////////

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    if (fromUser == true) {
                        Log.d("value", "value of getProgress and getMax" + seekBar.getProgress() + " " + seekBar.getMax());
                        MusicService.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            //mediaPlayer.start();
            isPlaying = true;


            mPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Play is clicked", Toast.LENGTH_SHORT).show();
                    if(isPlaying){
                        //Song should pause now
                        MusicService.pauseSong();
                    }
                    else{
                        //Song should Play now
                        MusicService.playSong();
                    }
                }
            });

            mFF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MusicService.fwSong();
                    }
            });

            mRW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MusicService.rwSong();
                    }
            });


        }

        private void initializeMusicList() {

        }

        @Override
        protected void onStart() {
            super.onStart();
            Log.d(TAG,"onStart() is called");
        }


        @Override
        protected void onStop() {
            super.onStop();
            EventBus.getDefault().unregister(this);
            MusicService.pauseSong();
            isPlaying=false;

            Log.d(TAG,"onStop() is called");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.v(TAG,"onDestroy() is called");
        }







        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


        int SEEK_SLEEP = 11;


        public void onEvent(MusicCompletedEvent musicCompletedEvent){
            seekBar.setProgress(0);
            isPlaying=false;
            mPlay.setText("Play");
        }

        public void onEvent(MusicStatus musicStatus){
            if(musicStatus.getIsPlaying()){
                mPlay.setText("Pause");
                isPlaying=true;
            }
            else
            {
                mPlay.setText("Play");
                isPlaying=false;
            }
        }

        class MusicHandler extends Handler {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == SEEK_SLEEP) {

                    seekBar.setProgress(MusicService.getCurrentPosition());
                    sendEmptyMessageDelayed(SEEK_SLEEP, 200);

                }
            }
        }

    }

