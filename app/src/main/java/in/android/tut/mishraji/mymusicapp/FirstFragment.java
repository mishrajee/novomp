package in.android.tut.mishraji.mymusicapp;


import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import in.android.tut.mishraji.mymusicapp.Events.MusicStatus;
import in.android.tut.mishraji.mymusicapp.Model.Collection1;
import in.android.tut.mishraji.mymusicapp.Model.Music;
import in.android.tut.mishraji.mymusicapp.Model.MusicApiResponseClass;
import in.android.tut.mishraji.mymusicapp.Model.Results;
import in.android.tut.mishraji.mymusicapp.Networking.MusicAPI;
import in.android.tut.mishraji.mymusicapp.Provider.MusicDBHelper;
import in.android.tut.mishraji.mymusicapp.services.MusicService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FirstFragment extends Fragment {


    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;
    private Music music;

    private MusicDBHelper musicDBHelper;
    private SQLiteDatabase db;
    private Button musicBarButton;
    private TextView musicBarSongName;
    private Boolean isPLaying;
    private List<Collection1> collection = new ArrayList<>();
    private MusicCursorAdapter musicCursorAdapter;
    Cursor cursor;

    ListView listView;
    MusicAPI.MusicInterface musicInterface;
    ViewGroup musicBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
        isPLaying = false;

        musicList.add(new Music("badshah","abhijeet","badshah","http://i.imgur.com/Qp1vKMJ.jpg"));
        musicList.add(new Music("chaccaron","El Mundo","chaccaron","https://upload.wikimedia.org/wikipedia/en/e/ef/Chacarron.jpg"));

        musicList.add(new Music("hud hud Daband","Sukhwinder Singh","hudhud","http://3.bp.blogspot.com/-U52ugxF6_no/TeCV4DLmEqI/AAAAAAAAAAU/W9DfRwktH6Y/s1600/Dabangg+Poster.jpg"));

        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));
        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));

        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));
        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));
        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));
        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));
        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));


        musicBarButton = (Button) getActivity().findViewById(R.id.music_bar_button);

        musicBarSongName = (TextView) getActivity().findViewById(R.id.music_bar_song);


        musicBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPLaying){
                    //song needs to pause
                    MusicService.pauseSong();

                }
                else{
                    //song needs to play
                    MusicService.playSong();
                }

            }
        });



       musicBarSongName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MainActivity.class).putExtra("music",music);
                startActivity(intent);
            }
        });





        musicDBHelper = new MusicDBHelper(getActivity());
        db = musicDBHelper.getReadableDatabase();

        ContentValues cv = new ContentValues();


        cursor = db.rawQuery("select * from "+MusicDBHelper.TABLE.MUSIC,null);
        if(cursor.moveToFirst()){
            do{
                Log.d("db","Value from db is "+cursor.getString(0));

            }while(cursor.moveToNext());
        }

        musicBar = (ViewGroup) getActivity().findViewById(R.id.music_bar);
        musicBar.getLayoutParams().height = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("std", "oncreateView in fragment 1");

        View view = inflater.inflate(R.layout.activity_viewpger_firstfragment, container, false);
        listView = (ListView) view.findViewById(R.id.fragment_first_list);

        musicCursorAdapter = new MusicCursorAdapter(getActivity(),cursor);
        listView.setAdapter(musicCursorAdapter);

/*
        musicInterface = MusicAPI.getAPI();
        musicInterface.getMusicList(new Callback<MusicApiResponseClass>() {
            @Override
            public void success(MusicApiResponseClass musicApiResponseClass, Response response) {
                collection.addAll(musicApiResponseClass.getResults().getCollection1());
                musicAdapter = new MusicAdapter(getActivity(),collection);
                listView.setAdapter(musicAdapter);

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
*/




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg ="The song to be played is at "+ position;
                Log.d("std",msg);
                Map<String, String> param = new HashMap<String, String>();
                FlurryAgent.setLogLevel(Log.VERBOSE);

                param.put("position",String.valueOf(position));

                FlurryAgent.logEvent("Song_Clicked",param);

                music = musicList.get(position);

                SharedPreferences sp = getActivity().getSharedPreferences("music-file",Context.MODE_PRIVATE);
                String file = musicList.get(position).getFileName();
                Log.d("std","file to be shared is "+file);
                sp.edit().putString("filename_value", file);

                Intent intent = new Intent(getActivity(),MainActivity.class).putExtra("music",musicList.get(position));

                Intent i = new Intent(getActivity(),MusicService.class);
                i.putExtra(MusicService.KEY_METHOD,MusicService.KEY_START);
                i.putExtra("musicName",musicList.get(position));

                getActivity().startService(i);

                musicBar.getLayoutParams().height=200;
                musicBarSongName.setText(music.getSongName());

                //musicBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
                musicBar.requestLayout();
                //startActivity(intent);

            }


        });



        return view;

    }

    public void onEvent(MusicStatus musicStatus){
        if(musicStatus.getIsPlaying()){
            musicBarButton.setText("Pause");
            isPLaying=true;

        }
        else{
            musicBarButton.setText("Play");
            isPLaying=false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
