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

    import in.android.tut.mishraji.mymusicapp.Model.Music;


    public class MainActivity extends ActionBarActivity {
        Button mPlay, mFF, mRW;
        MediaPlayer mediaPlayer;
        SeekBar seekBar;
        private Boolean isPlaying;
        private List<Music> musicList = new ArrayList<>();
        private int position;
        private ImageView imageView;


        private static String TAG = "std";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);

            initializeMusicList();

            imageView = (ImageView) findViewById(R.id.activity_main_image);

            SharedPreferences sp = getSharedPreferences("music-file",Context.MODE_PRIVATE);
            String fileName = sp.getString("filename_value",null);

            Log.d("std","value of fileName is "+fileName);

            position = getIntent().getIntExtra("musicName", 0);
            Log.d("std","After intent value of fileName is "+musicList.get(position).getFileName());
            fileName=musicList.get(position).getFileName();


            Picasso
                    .with(this)
                    .load(musicList.get(position).getAlbumName())
                    .error(R.drawable.ic_launcher)
                    .into(imageView);

            mediaPlayer = MediaPlayer.create(this, this.getResources().getIdentifier(fileName,"raw",this.getPackageName()));
            Log.d("std","mediaPlayer initialized");

            mPlay = (Button) findViewById(R.id.activity_main_play);
            mFF = (Button) findViewById(R.id.activity_main_fast);
            mRW = (Button) findViewById(R.id.activity_main_rewind);
            seekBar = (SeekBar) findViewById(R.id.activity_main_seeked);

            final MusicHandler musicHandler = new MusicHandler();

            seekBar.setMax(mediaPlayer.getDuration());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                    if (fromUser == true) {


                        Log.d("value", "value of getProgress and getMax" + seekBar.getProgress() + " " + seekBar.getMax());

                        mediaPlayer.seekTo(progress);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            mediaPlayer.start();
            isPlaying = true;


            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(MainActivity.this, "Song completed", Toast.LENGTH_SHORT).show();

                }
            });

            mPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Play is clicked", Toast.LENGTH_SHORT).show();
                    if(isPlaying){
                        //Song should pause now
                        mediaPlayer.pause();
                        mPlay.setText("Play");
                        isPlaying=false;

                    }
                    else{
                        //Song should Play now
                        mediaPlayer.start();
                        mPlay.setText("Pause");
                        isPlaying=true;

                    }


                }
            });


            mFF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 1000);
                }
            });

            mRW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 2000);
                }
            });


        }

        private void initializeMusicList() {
            musicList.add(new Music("badshah","abhijeet","badshah","http://i.imgur.com/Qp1vKMJ.jpg"));
            musicList.add(new Music("chaccaron","El Mundo","chaccaron","https://upload.wikimedia.org/wikipedia/en/e/ef/Chacarron.jpg"));

            musicList.add(new Music("hud hud Daband","Sukhwinder Singh","hudhud","http://3.bp.blogspot.com/-U52ugxF6_no/TeCV4DLmEqI/AAAAAAAAAAU/W9DfRwktH6Y/s1600/Dabangg+Poster.jpg"));

            musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));

        }

        @Override
        protected void onStart() {
            super.onStart();
            Log.d(TAG,"onStart() is called");
        }

        @Override
        protected void onPause() {
            super.onPause();
            Log.d(TAG,"onPause() is called");
        }


        @Override
        protected void onResume() {
            super.onResume();
            Log.d(TAG,"onResume() is called");
        }

        @Override
        protected void onStop() {
            super.onStop();
            mediaPlayer.pause();
            isPlaying=false;

            Log.d(TAG,"onStop() is called");
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            Log.v(TAG,"onDestroy() is called");
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();
            mediaPlayer.pause();
            isPlaying=false;


            Log.d(TAG,"onBackPressed() is called");
        }

        @Override
        protected void onPostCreate(Bundle savedInstanceState) {
            super.onPostCreate(savedInstanceState);
            Log.d(TAG,"onPostCreate() is called");
        }

        @Override
        protected void onPostResume() {
            super.onPostResume();
            Log.d(TAG,"onPostResume() is called");
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



        class MusicHandler extends Handler {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == SEEK_SLEEP) {
                    if (mediaPlayer != null) {
                        if (mediaPlayer.isPlaying()) {
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            sendEmptyMessageDelayed(SEEK_SLEEP,200);

                        }
                    }
                }

            }

        }

    }

