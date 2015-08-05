package in.android.tut.mishraji.mymusicapp.Events;

/**
 * Created by abhinava on 5/8/15.
 */
public class MusicStatus {
    public Boolean getIsPlaying() {
        return isPlaying;
    }

    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public MusicStatus(Boolean isPlaying) {

        this.isPlaying = isPlaying;
    }

    private Boolean isPlaying;
}
