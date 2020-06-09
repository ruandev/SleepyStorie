package dev.ruanvictor.sleepystorie.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

import dev.ruanvictor.sleepystorie.listeners.OnBookListener;
import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.data.model.Book;

public class SearchAdapter extends Adapter<SearchAdapter.MyViewHolder> {
    private List<Book> books = new ArrayList<>();
    private OnBookListener onBookListener;

    public SearchAdapter(OnBookListener onBookListener) {
        this.onBookListener = onBookListener;
    }

    public void add(List<Book> items) {
        this.books.clear();
        this.books.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        return new SearchAdapter.MyViewHolder(itemList, onBookListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = this.books.get(position);
        holder.cover.setImageResource(book.getCover());
        holder.cover.setContentDescription(book.getTitle());
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.description.setText(book.getDescription());
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView cover;
        private TextView title;
        private TextView author;
        private TextView description;
        private OnBookListener onBookListener;

        public MyViewHolder(@NonNull View itemView, OnBookListener onBookListener) {
            super(itemView);

            cover = itemView.findViewById(R.id.coverList);
            title = itemView.findViewById(R.id.titleList);
            author = itemView.findViewById(R.id.authorList);
            description = itemView.findViewById(R.id.descriptionList);
            this.onBookListener = onBookListener;

            cover.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBookListener.onBookClick(getAdapterPosition());
        }
    }

}
