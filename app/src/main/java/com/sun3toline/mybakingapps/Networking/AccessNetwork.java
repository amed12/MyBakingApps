package com.sun3toline.mybakingapps.Networking;

import com.sun3toline.mybakingapps.Model.Bahan;
import com.sun3toline.mybakingapps.Model.Resep;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by coldware on 9/24/17.
 */

public interface AccessNetwork {
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Resep>> getAllRecips();

}
