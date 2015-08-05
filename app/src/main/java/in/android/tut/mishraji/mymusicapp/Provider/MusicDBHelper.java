package in.android.tut.mishraji.mymusicapp.Provider;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by abhinava on 5/8/15.
 */
public class MusicDBHelper extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DATABASE_NAME = "myDataBase";


    public MusicDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public interface TABLE{
        String MUSIC = "music";
    }
    public interface MUSIC_COLOUMN{
        String ARTIST_NAME = "artistname";
        String ALBUM_URL = "albumurl";
        String SONG = "songname";
        String FILE = "filename";
    }

    final String CREATE_QUERY = "create table "+ TABLE.MUSIC+ " ("
            +BaseColumns._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
            +MUSIC_COLOUMN.ALBUM_URL+" TEXT NOT NULL,"
            +MUSIC_COLOUMN.ARTIST_NAME+" TEXT NOT NULL,"
            +MUSIC_COLOUMN.FILE+ " TEXT NOT NULL,"
            +MUSIC_COLOUMN.SONG+" TEXT NOT NULL);";


    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
