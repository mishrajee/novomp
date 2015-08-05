package in.android.tut.mishraji.mymusicapp.Events;

/**
 * Created by abhinava on 5/8/15.
 */
public class MusicCompletedEvent {
    private String songName;
    private String artistName;

    public MusicCompletedEvent(String songName, String artistName) {
        this.songName = songName;
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbumName() {
        return artistName;
    }

    public void setAlbumName(String artistName) {
        this.artistName = artistName;
    }
}
