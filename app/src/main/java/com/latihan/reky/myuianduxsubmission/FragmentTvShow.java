package com.latihan.reky.myuianduxsubmission;
import android.app.ProgressDialog;
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


public class FragmentTvShow extends Fragment {

    private ProgressDialog progressDialog;
    private TvShowAdapter tvShowAdapter;
    public FragmentTvShow() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tvshow_fragmnet, container, false);
        RecyclerView rvShow = view.findViewById(R.id.tvshow_rv);
        rvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Loading....");
        tvShowAdapter = new TvShowAdapter(requireContext(), new ArrayList<TvShow>());
        rvShow.setAdapter(tvShowAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TvShowViewModel tvShowViewModel = new ViewModelProvider(this).get(TvShowViewModel.class);
        tvShowViewModel.setData().observe(getViewLifecycleOwner(), new Observer<List<TvShow>>() {
            @Override
            public void onChanged(List<TvShow> shows) {
                tvShowAdapter.setListShow(shows);
            }
        });
        tvShowViewModel.getLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    progressDialog.show();
                } else {
                    progressDialog.dismiss();
                }
            }
        });
        tvShowViewModel.getDataShow(requireContext());
    }

}
