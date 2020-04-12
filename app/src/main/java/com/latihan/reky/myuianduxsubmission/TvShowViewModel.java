package com.latihan.reky.myuianduxsubmission;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class TvShowViewModel extends ViewModel {
    private MutableLiveData<List<TvShow>> listShow = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();

    void getDataShow(Context context) {
        loading.postValue(true);
        String JSON_URL = "https://api.themoviedb.org/3/discover/tv?api_key=5db7d5b8c75b4e604d6156764ae2d513&language=en-US";
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<TvShow> listShowItem = new ArrayList<>();
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);
                                String titleTv = result.getString("name");
                                String overviewTv = result.getString("overview");
                                String imageTv = result.getString("poster_path");

                                listShowItem.add(new TvShow(titleTv,overviewTv,imageTv));
                            }
                            listShow.postValue(listShowItem);
                            loading.postValue(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.postValue(false);
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestQueue.add(request);
    }

    MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    LiveData<List<TvShow>> setData(){
        return listShow;
    }
}

