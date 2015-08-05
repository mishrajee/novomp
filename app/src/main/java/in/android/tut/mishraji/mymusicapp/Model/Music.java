package in.android.tut.mishraji.mymusicapp.Model;

import java.io.Serializable;

/**
 * Created by abhinava on 4/8/15.
 */
public class Music implements Serializable{
    private String songName;
    private String artistName;
    private String albumName;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Music(String songName, String artistName, String fileName,String albumName) {
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.fileName= fileName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
