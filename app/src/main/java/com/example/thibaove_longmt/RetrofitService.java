package com.example.thibaove_longmt;

import com.example.thibaove_longmt.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitService {
    @POST("/maps/api/place/nearbysearch/json")
    @FormUrlEncoded
    Call<Post> getAllPost(@Field("location") String location,
                          @Field("radius") String radius,
                          @Field("types") String types,
                          @Field("extras") String name
    );
}
