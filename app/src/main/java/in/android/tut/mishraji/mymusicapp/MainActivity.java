    package in.android.tut.mishraji.mymusicapp;

    import android.content.Context;
    import android.media.MediaPlayer;
    import android.os.Message;
    import android.support.annotation.NonNull;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.util.AttributeSet;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.SeekBar;
    import android.widget.Toast;

    import android.os.Handler;

    import java.util.logging.LogRecord;


    public class MainActivity extends ActionBarActivity {
        Button mPlay, mPause, mFF, mRW;
        MediaPlayer mediaPlayer;
        SeekBar seekBar;

        private static String TAG = "std";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mediaPlayer = MediaPlayer.create(this, R.raw.a);

            mPlay = (Button) findViewById(R.id.activity_main_play);
            mPause = (Button) findViewById(R.id.activity_main_pause);
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
                    mediaPlayer.start();
                    musicHandler.sendEmptyMessage(SEEK_SLEEP);

                }
            });

            mPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "Pause is clicked", Toast.LENGTH_SHORT).show();
                    mediaPlayer.pause();
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

