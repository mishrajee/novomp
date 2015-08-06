package in.android.tut.mishraji.mymusicapp.Networking;

import javax.security.auth.callback.Callback;

import in.android.tut.mishraji.mymusicapp.Model.MusicApiResponseClass;
import retrofit.RestAdapter;
import retrofit.http.GET;

/**
 * Created by abhinava on 6/8/15.
 */
public class MusicAPI {
    private static final String URL ="https://www.kimonolabs.com/api";
    private static MusicInterface musicInterface = null;

    public static MusicInterface getAPI(){
        if(musicInterface==null){
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(URL)
                    .build();
            musicInterface = restAdapter.create(MusicInterface.class);
        }
        return musicInterface;
    }

    public interface MusicInterface{

        @GET("/az8b2fe0?apikey=84SdVb8c8BnG2Ao8IXBSPWJIWi3sCx4f")
        MusicApiResponseClass getMusicList();

        @GET("/az8b2fe0?apikey=84SdVb8c8BnG2Ao8IXBSPWJIWi3sCx4f")
        void getMusicList(retrofit.Callback<MusicApiResponseClass> musicApiResponseClassCallback);

    }
}
