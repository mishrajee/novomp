package in.android.tut.mishraji.mymusicapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

/**
 * Created by abhinava on 4/8/15.
 */
public class ListOfMusicActivity extends FragmentActivity{

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        viewPager = (ViewPager)findViewById(R.id.activity_viewpager_viewpager);
    }
}
