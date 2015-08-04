package in.android.tut.mishraji.mymusicapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import in.android.tut.mishraji.mymusicapp.Model.Music;

public class FirstFragment extends Fragment {


    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;


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

        Log.d("std", "oncreateView in fragment 1");

        View view = inflater.inflate(R.layout.activity_viewpger_firstfragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.fragment_first_list);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg ="The song to be played is at "+ position;
                Log.d("std",msg);

                SharedPreferences sp = getActivity().getSharedPreferences("music-file",Context.MODE_PRIVATE);
                String file = musicList.get(position).getFileName();
                Log.d("std","file to be shared is "+file);
                sp.edit().putString("filename_value",file);


                Intent i = new Intent(getActivity(),MainActivity.class).putExtra("fileName",file);
                startActivity(i);
            }


        });

        musicAdapter = new MusicAdapter(getActivity(),musicList);
        listView.setAdapter(musicAdapter);


        return view;

    }
}
