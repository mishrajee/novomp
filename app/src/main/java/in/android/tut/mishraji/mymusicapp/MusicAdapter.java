package in.android.tut.mishraji.mymusicapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import in.android.tut.mishraji.mymusicapp.Model.Collection1;
import in.android.tut.mishraji.mymusicapp.Model.Music;

/**
 * Created by abhinava on 4/8/15.
 */
public class MusicAdapter extends BaseAdapter {

    WeakReference<Context> context;

    private List<Music> musicList;
    private List<Collection1> collection;

    private class ViewHolder {
        TextView songTextView;
        TextView artistTextView;
        ImageView albumImageView;

    }

    public MusicAdapter(Context context, List<Collection1> collection) {
        this.context = new WeakReference<Context> (context);
        this.collection = collection;
    }



    @Override
    public int getCount() {
        return collection.size();
    }

    @Override
    public Object getItem(int position) {
        return collection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;


        ViewHolder viewHolder = null;

        if(convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context.get());
            view = layoutInflater.inflate(R.layout.music_view,parent,false);

            viewHolder = new ViewHolder();

            viewHolder.albumImageView = (ImageView) view.findViewById(R.id.musicview_albumName);
            viewHolder.artistTextView = (TextView) view.findViewById(R.id.musicview_artistName);
            viewHolder.songTextView = (TextView) view.findViewById(R.id.musicview_songName);

            view.setTag(viewHolder);

        }

        if(viewHolder ==null){
            viewHolder = (ViewHolder)view.getTag();
        }

        Collection1 musicCollection = (Collection1)getItem(position);

        Picasso
                .with(context.get())
                .load(musicCollection.getAlbumImage().getSrc())
                .error(R.drawable.ic_launcher)
                .into(viewHolder.albumImageView);

        viewHolder.artistTextView.setText(musicCollection.getArtistName().getText());
        viewHolder.songTextView.setText(musicCollection.getSongName().getText());



        return view;
    }
}
