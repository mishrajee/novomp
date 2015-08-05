package in.android.tut.mishraji.mymusicapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;

import com.flurry.android.FlurryAgent;

/**
 * Created by abhinava on 4/8/15.
 */
public class ListOfMusicActivity extends FragmentActivity {

    private ViewPager viewPager;
    private final int NO_PAGES =2;
    private MusicListFragmentStartPagerAdaptor musicListFragmentStartPagerAdaptor;
    private SlidingPaneLayout mLayout;

    @Override
    protected void onStart() {
        super.onStart();
        FlurryAgent.onStartSession(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpager);

        viewPager = (ViewPager)findViewById(R.id.activity_viewpager_viewpager);
        musicListFragmentStartPagerAdaptor = new MusicListFragmentStartPagerAdaptor(getSupportFragmentManager());

        viewPager.setAdapter(musicListFragmentStartPagerAdaptor);



    }

    private class MusicListFragmentStartPagerAdaptor extends FragmentStatePagerAdapter {


        public MusicListFragmentStartPagerAdaptor(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch(i){
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NO_PAGES;
        }
    }



}
