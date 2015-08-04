package in.android.tut.mishraji.mymusicapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by abhinava on 4/8/15.
 */
public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("std", "SecondFragment oncreateView()");

        return inflater.inflate(R.layout.activity_viewpager_secondfragment,container,false);

    }
}
