package in.android.tut.mishraji.mymusicapp;

import android.app.Application;

import com.flurry.android.FlurryAgent;

/**
 * Created by abhinava on 5/8/15.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlurryAgent.init(this,AppEnvironment.FLURRY_KEY);

    }
}
