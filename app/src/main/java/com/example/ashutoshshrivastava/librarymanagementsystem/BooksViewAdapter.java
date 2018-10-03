package com.example.ashutoshshrivastava.librarymanagementsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class BooksViewAdapter extends RecyclerView.Adapter<BooksViewHolder> {

    List<Books> booklist;
    Context context;


    public BooksViewAdapter(Context context, List<Books> booklist) {
        this.booklist = booklist;
        this.context = context;
    }
    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issued_section_design, parent, false);
        BooksViewHolder booksViewHolder= new BooksViewHolder(view);
        return booksViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder booksViewHolder, int i) {

        booksViewHolder.bookName.setText(booklist.get(i).booknameText);
        booksViewHolder.author.setText(booklist.get(i).authorText);
        booksViewHolder.rate.setText((booklist).get(i).ratingText);
        booksViewHolder.genreValue.setText((booklist).get(i).genreText);
        Picasso.get().load(booklist.get(i).getCoverPicTextURL()).into(booksViewHolder.coverPic);
        booksViewHolder.issuer.setText(booklist.get(i).issuerText);

    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }
}
