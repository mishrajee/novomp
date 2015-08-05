package in.android.tut.mishraji.mymusicapp;


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
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.android.tut.mishraji.mymusicapp.Model.Music;
import in.android.tut.mishraji.mymusicapp.Provider.MusicDBHelper;
import in.android.tut.mishraji.mymusicapp.services.MusicService;

public class FirstFragment extends Fragment {


    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;
    private Music music;

    private MusicDBHelper musicDBHelper;
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("badshah","abhijeet","badshah","http://i.imgur.com/Qp1vKMJ.jpg"));
        musicList.add(new Music("chaccaron","El Mundo","chaccaron","https://upload.wikimedia.org/wikipedia/en/e/ef/Chacarron.jpg"));

        musicList.add(new Music("hud hud Daband","Sukhwinder Singh","hudhud","http://3.bp.blogspot.com/-U52ugxF6_no/TeCV4DLmEqI/AAAAAAAAAAU/W9DfRwktH6Y/s1600/Dabangg+Poster.jpg"));

        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));

        musicDBHelper = new MusicDBHelper(getActivity());
        db = musicDBHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(MusicDBHelper.MUSIC_COLOUMN.ALBUM_URL,"http://i.imgur.com/Qp1vKMJ.jpg");
        cv.put(MusicDBHelper.MUSIC_COLOUMN.ARTIST_NAME,"abhijeet");
        cv.put(MusicDBHelper.MUSIC_COLOUMN.SONG,"badshah");
        cv.put(MusicDBHelper.MUSIC_COLOUMN.FILE,"badshah");

        db.insert(MusicDBHelper.TABLE.MUSIC,null,cv);

        Cursor cursor = db.rawQuery("select * from "+MusicDBHelper.TABLE.MUSIC,null);
        if(cursor.moveToFirst()){
            do{
                Log.d("db","Value from db is "+cursor.getString(0));

            }while(cursor.moveToNext());
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("std", "oncreateView in fragment 1");

        View view = inflater.inflate(R.layout.activity_viewpger_firstfragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.fragment_first_list);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg ="The song to be played is at "+ position;
                Log.d("std",msg);
                Map<String, String> param = new HashMap<String, String>();
                FlurryAgent.setLogLevel(Log.VERBOSE);

                param.put("position",String.valueOf(position));

                FlurryAgent.logEvent("Song_Clicked",param);


                SharedPreferences sp = getActivity().getSharedPreferences("music-file",Context.MODE_PRIVATE);
                String file = musicList.get(position).getFileName();
                Log.d("std","file to be shared is "+file);
                sp.edit().putString("filename_value", file);

                Intent intent = new Intent(getActivity(),MainActivity.class).putExtra("music",musicList.get(position));

                Intent i = new Intent(getActivity(),MusicService.class);
                i.putExtra(MusicService.KEY_METHOD,MusicService.KEY_START);
                i.putExtra("musicName",musicList.get(position));

                getActivity().startService(i);

                startActivity(intent);
            }


        });

        musicAdapter = new MusicAdapter(getActivity(),musicList);
        listView.setAdapter(musicAdapter);


        return view;

    }
}
