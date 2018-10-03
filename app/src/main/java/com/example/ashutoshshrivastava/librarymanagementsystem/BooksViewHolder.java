package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BooksViewHolder extends RecyclerView.ViewHolder {

    TextView author,issuer,rate,genreValue,bookName;
    ImageView coverPic;

    public BooksViewHolder(View itemView) {
        super(itemView);
        author=itemView.findViewById(R.id.author);
        issuer=itemView.findViewById(R.id.issuer);
        rate=itemView.findViewById(R.id.rate);
        genreValue=itemView.findViewById(R.id.genreValue);
        bookName=itemView.findViewById(R.id.bookName);
        coverPic=itemView.findViewById(R.id.coverPic);
    }
}
