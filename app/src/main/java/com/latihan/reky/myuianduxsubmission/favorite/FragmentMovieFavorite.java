package com.latihan.reky.myuianduxsubmission.favorite;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.latihan.reky.myuianduxsubmission.Movie;
import com.latihan.reky.myuianduxsubmission.R;
import com.latihan.reky.myuianduxsubmission.database.MappingHelperMovie;
import com.latihan.reky.myuianduxsubmission.database.NoteHelperMovie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FragmentMovieFavorite extends Fragment implements LoadMovieCallback {

    private MovieFavoriteAdapter movieFavoriteAdapter;
    private RecyclerView rvMovieFav;
    private NoteHelperMovie noteHelperMovie;
    private static final String EXTRA_STATE_MOVIE = "EXTRA_STATE_MOVIE";
    private ArrayList<Movie> listNoteMovie = new ArrayList<>();
    public FragmentMovieFavorite(){}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_favorite,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovieFav = view.findViewById(R.id.movie_rv_favorite);
        rvMovieFav.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovieFav.setHasFixedSize(true);
        movieFavoriteAdapter = new MovieFavoriteAdapter(listNoteMovie,getContext());
        rvMovieFav.setAdapter(movieFavoriteAdapter);

        NoteHelperMovie noteHelperMovie = NoteHelperMovie.getInstance(getActivity());
        noteHelperMovie.open();

        if (savedInstanceState == null) {
            new LoadMovieAsync(noteHelperMovie, this).execute();

        } else {
            final ArrayList<Movie> listMov = savedInstanceState.getParcelableArrayList(EXTRA_STATE_MOVIE);
            assert listMov != null;
            listNoteMovie.addAll(listMov);
            movieFavoriteAdapter.setListNoteMovie(listMov);
        }


    }

    @Override
    public void onSaveInstanceState(@Nullable Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.getParcelableArrayList(EXTRA_STATE_MOVIE);

    }

    @Override
    public void preExecute() {

    }

    @Override
    public void PostExecute(ArrayList<Movie> movies) {
        movieFavoriteAdapter.setListNoteMovie(movies);
        rvMovieFav.setAdapter(movieFavoriteAdapter);
        listNoteMovie.addAll(movies);

    }

    private static class LoadMovieAsync extends AsyncTask<Void,Void, ArrayList<Movie>> {
        private final WeakReference<NoteHelperMovie> weakMovieReference;
        private final WeakReference<LoadMovieCallback> weakCallback;

        private LoadMovieAsync(NoteHelperMovie noteHelperMovie, LoadMovieCallback callback) {
            weakMovieReference = new WeakReference<>(noteHelperMovie);
            weakCallback = new WeakReference<>(callback);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Movie> doInBackground(Void... voids) {
            Cursor dataCursor = weakMovieReference.get().queryAll();
            return MappingHelperMovie.mapCursorToArrayList(dataCursor);
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            super.onPostExecute(movies);
            weakCallback.get().PostExecute(movies);
        }
    }
}

interface LoadMovieCallback{
    void preExecute();
    void PostExecute(ArrayList<Movie> movies);

}
