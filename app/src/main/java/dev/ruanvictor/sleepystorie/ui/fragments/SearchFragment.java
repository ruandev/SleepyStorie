package dev.ruanvictor.sleepystorie.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.data.model.Book;
import dev.ruanvictor.sleepystorie.listeners.OnBookListener;
import dev.ruanvictor.sleepystorie.ui.adapter.SearchAdapter;
import dev.ruanvictor.sleepystorie.utils.UIUtil;
import dev.ruanvictor.sleepystorie.viewmodel.BooksViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements OnBookListener {

    private List<Book> allBooks;
    private BooksViewModel booksViewModel;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView listSearch = view.findViewById(R.id.listSearch);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listSearch.setLayoutManager(layoutManager);
        SearchAdapter searchAdapter = new SearchAdapter(this);
        listSearch.setAdapter(searchAdapter);

        booksViewModel = new ViewModelProvider(this).get(BooksViewModel.class);
        booksViewModel.getBooks().observe(getViewLifecycleOwner(), books -> {
            Log.d("SEARCH", "onCreateView: "+books.size());
            allBooks = books;
            searchAdapter.add(books);
        });

        return view;
    }

    @Override
    public void onBookClick(int position) {
        Book book = allBooks.get(position);

        UIUtil.openBookDetails(book, getFragmentManager());
    }

}
