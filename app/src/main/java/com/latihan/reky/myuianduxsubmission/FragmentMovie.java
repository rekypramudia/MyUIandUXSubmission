package com.latihan.reky.myuianduxsubmission;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;


public class FragmentMovie extends Fragment {

    private ProgressDialog progressDialog;
    private MovieAdapter movieAdapter;
    public FragmentMovie() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movies_fragment, container, false);
        RecyclerView rvMovies = view.findViewById(R.id.movie_rv);
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Loading....");
        movieAdapter = new MovieAdapter(requireContext(), new ArrayList<Movie>());
        rvMovies.setAdapter(movieAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieViewModel.setData().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setListMovie(movies);
            }
        });
        movieViewModel.getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        });
        movieViewModel.getDataMovie(requireContext());
    }
}
