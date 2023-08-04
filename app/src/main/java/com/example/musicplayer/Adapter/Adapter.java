package com.example.musicplayer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.musicplayer.Class.Song;
import com.example.musicplayer.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Song> songArrayList;

    public Adapter(Context context, int layout, ArrayList<Song> songArrayList) {
        this.context = context;
        this.layout = layout;
        this.songArrayList = songArrayList;
    }

    @Override
    public int getCount() {
        return songArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tv_song_name, tv_song_singer;
        ImageView img_anh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(layout, null);

            holder.tv_song_name = (TextView) view.findViewById(R.id.tv_song_name);
            holder.tv_song_singer = (TextView) view.findViewById(R.id.tv_song_singer);
            holder.img_anh = (ImageView) view.findViewById(R.id.img_anh);

            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Song song = songArrayList.get(i);

        holder.tv_song_name.setText(song.getNameSong());
        holder.tv_song_singer.setText(song.getNameSinger());
        holder.img_anh.setImageResource(song.getPicture());
        return view;
    }
}
