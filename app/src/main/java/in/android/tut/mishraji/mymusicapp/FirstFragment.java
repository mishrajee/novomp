package in.android.tut.mishraji.mymusicapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import in.android.tut.mishraji.mymusicapp.Model.Music;

public class FirstFragment extends Fragment {


    private List<Music> musicList = new ArrayList<>();
    private MusicAdapter musicAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname2","songartisi","songalbum"));

        musicList.add(new Music("dongname3","songartisi","songalbum"));

        musicList.add(new Music("dongname4","songartisi","songalbum"));
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname5","songartisi","songalbum"));
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname1","songartisi","songalbum"));
        musicList.add(new Music("dongname6","songartisi","songalbum"));
        musicList.add(new Music("dongname9","songartisi","songalbum"));



    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d("std", "oncreateView in fragment 1");

        View view = inflater.inflate(R.layout.activity_viewpger_firstfragment, container, false);

        ListView listView = (ListView) view.findViewById(R.id.fragment_first_list);

        musicAdapter = new MusicAdapter(getActivity(),musicList);
        listView.setAdapter(musicAdapter);

        return view;

    }
}
