package in.android.tut.mishraji.mymusicapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import in.android.tut.mishraji.mymusicapp.Model.Collection1;
import in.android.tut.mishraji.mymusicapp.Model.Music;
import in.android.tut.mishraji.mymusicapp.Provider.MusicDBHelper;

/**
 * Created by abhinava on 6/8/15.
 */
public class MusicCursorAdapter extends CursorAdapter {

    private class ViewHolder {
        TextView songTextView;
        TextView artistTextView;
        ImageView albumImageView;

    }

    public MusicCursorAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.music_view,parent,false);

        ViewHolder viewHolder = new ViewHolder();

        viewHolder.albumImageView = (ImageView) view.findViewById(R.id.musicview_albumName);
        viewHolder.artistTextView = (TextView) view.findViewById(R.id.musicview_artistName);
        viewHolder.songTextView = (TextView) view.findViewById(R.id.musicview_songName);

        view.setTag(viewHolder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder viewHolder =(ViewHolder) view.getTag();
        String imageUrl = cursor.getString(cursor.getColumnIndex(MusicDBHelper.MUSIC_COLOUMN.ALBUM_URL));
        String songName = cursor.getString(cursor.getColumnIndex(MusicDBHelper.MUSIC_COLOUMN.SONG));
        String fileName = cursor.getString(cursor.getColumnIndex(MusicDBHelper.MUSIC_COLOUMN.FILE));
        String artistName = cursor.getString(cursor.getColumnIndex(MusicDBHelper.MUSIC_COLOUMN.ARTIST_NAME));


        Picasso
                .with(context)
                .load(imageUrl)
                .error(R.drawable.ic_launcher)
                .into(viewHolder.albumImageView);

        viewHolder.artistTextView.setText(artistName);
        viewHolder.songTextView.setText(songName);

        view.setTag(viewHolder);
    }
}
