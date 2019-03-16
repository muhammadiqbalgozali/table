package com.example.ilgozali.table_pizzana.api;

import com.example.ilgozali.table_pizzana.model.ModelData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {
    @GET("pesanan")
    Call<List<ModelData>> getDataTable();

}
