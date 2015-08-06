
package in.android.tut.mishraji.mymusicapp.Model;

import com.google.gson.annotations.Expose;

public class Collection1 {

    @Expose
    private AlbumImage albumImage;
    @Expose
    private SongName songName;
    @Expose
    private ArtistName artistName;
    @Expose
    private Integer index;
    @Expose
    private String url;

    /**
     * 
     * @return
     *     The albumImage
     */
    public AlbumImage getAlbumImage() {
        return albumImage;
    }

    /**
     * 
     * @param albumImage
     *     The albumImage
     */
    public void setAlbumImage(AlbumImage albumImage) {
        this.albumImage = albumImage;
    }

    /**
     * 
     * @return
     *     The songName
     */
    public SongName getSongName() {
        return songName;
    }

    /**
     * 
     * @param songName
     *     The songName
     */
    public void setSongName(SongName songName) {
        this.songName = songName;
    }

    /**
     * 
     * @return
     *     The artistName
     */
    public ArtistName getArtistName() {
        return artistName;
    }

    /**
     * 
     * @param artistName
     *     The artistName
     */
    public void setArtistName(ArtistName artistName) {
        this.artistName = artistName;
    }

    /**
     * 
     * @return
     *     The index
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 
     * @param index
     *     The index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
