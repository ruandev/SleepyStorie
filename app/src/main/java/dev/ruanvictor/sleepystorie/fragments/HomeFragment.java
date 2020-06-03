package dev.ruanvictor.sleepystorie.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import dev.ruanvictor.sleepystorie.MainActivity;
import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.containers.RecommendationContainer;
import dev.ruanvictor.sleepystorie.model.Book;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    List<Book> books;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        Button buttonInfo = view.findViewById(R.id.buttonInfo);

        buttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book("Amoras", "Emicida", "Lorem", R.drawable.amoras_capa);
                DetailBookFragment detailBookFragment = new DetailBookFragment();
                Bundle params = new Bundle();
                params.putSerializable("book", book);
                detailBookFragment.setArguments(params);

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, detailBookFragment, detailBookFragment.getTag())
                        .commit();

            }
        });

        Button buttonPlay = view.findViewById(R.id.buttonPlay);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayFragment playFragment = new PlayFragment();

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, playFragment, playFragment.getTag())
                        .commit();

            }
        });

        RecommendationContainer.showGridRecommendation(view, getContext(), getFragmentManager());

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.new_item) {
            mAuth.signOut();
            startActivity(new Intent(getContext(), MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
