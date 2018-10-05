package com.example.ashutoshshrivastava.librarymanagementsystem;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArtistList extends ArrayAdapter<UsersClass> {
    private Activity context;
    List<UsersClass> artists;

    public ArtistList(Activity context, List<UsersClass> artists) {
        super(context, R.layout.list_layout, artists);
        this.context = context;
        this.artists = artists;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.extiing);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.numberrrrrr);
        UsersClass artist = artists.get(position);
        textViewName.setText(artist.getName());
        textViewGenre.setText(artist.getMobnumberText());

        return listViewItem;
    }
}