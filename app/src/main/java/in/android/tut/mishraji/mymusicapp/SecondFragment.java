package in.android.tut.mishraji.mymusicapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import in.android.tut.mishraji.mymusicapp.Model.Music;
import in.android.tut.mishraji.mymusicapp.services.MusicService;

/**
 * Created by abhinava on 4/8/15.
 */
public class SecondFragment extends Fragment {
    private List<Music> musicList = new ArrayList<>();
    private GridView gridView;
    private MusicAdapter musicAdapter;
    private Music music;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("badshah","abhijeet","badshah","http://i.imgur.com/Qp1vKMJ.jpg"));
        musicList.add(new Music("chaccaron","El Mundo","chaccaron","https://upload.wikimedia.org/wikipedia/en/e/ef/Chacarron.jpg"));

        musicList.add(new Music("hud hud Daband","Sukhwinder Singh","hudhud","http://3.bp.blogspot.com/-U52ugxF6_no/TeCV4DLmEqI/AAAAAAAAAAU/W9DfRwktH6Y/s1600/Dabangg+Poster.jpg"));

        musicList.add(new Music("cheez mast","Udit Narayan","mohra","https://upload.wikimedia.org/wikipedia/en/e/ed/Mohra.jpg"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("std", "SecondFragment oncreateView()");

        View view = inflater.inflate(R.layout.activity_viewpager_secondfragment,container,false);

        gridView = (GridView) view.findViewById(R.id.fragment_second_grid);
        musicAdapter = new MusicAdapter(getActivity(),musicList);
        gridView.setAdapter(musicAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg ="The song to be played is at "+ position;
                Log.d("std",msg);

                String file = musicList.get(position).getFileName();
                Log.d("std","In grid file to be shared is "+file);

                Intent intent = new Intent(getActivity(),MainActivity.class).putExtra("music",musicList.get(position));

                Intent i = new Intent(getActivity(),MusicService.class);

                i.putExtra(MusicService.KEY_METHOD,MusicService.KEY_START);
                i.putExtra("musicName",musicList.get(position));

                getActivity().startService(i);

                startActivity(intent);

            }
        });

        return view;

    }
}
