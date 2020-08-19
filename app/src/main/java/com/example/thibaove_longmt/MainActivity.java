package com.example.thibaove_longmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.thibaove_longmt.model.Post;
import com.example.thibaove_longmt.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private EditText Edtlocation, edtRudius, edtTypes, edtName;
    private ArrayList<Result> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Edtlocation = findViewById(R.id.Edtlocation);
        edtRudius = findViewById(R.id.edtRudius);
        edtTypes = findViewById(R.id.edtTypes);
        edtName = findViewById(R.id.edtName);
        Edtlocation.setText("33.8670522,151.1957362");
        edtRudius.setText("500");
        edtTypes.setText("food");
        edtName.setText("gas");
    }

    public void Loader() {
    }

    private void ConvertData(List<Result> postList) {
        Log.i("REMEMBER", "ConvertData");
        resultList.addAll(postList);
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("listMAP", resultList);
        startActivity(intent);
    }

    public void btnGet(View view) {

        double location = Double.parseDouble(Edtlocation.getText().toString());
        int rudius = Integer.parseInt(edtRudius.getText())
        Log.i("REMEMBER", "Loader");
        Retrofit retrofit = MyRetrofit.getInstance("https://maps.googleapis.com");
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        retrofitService.getAllPost(Edtlocation.getText().toString(), edtRudius.getText().toString(), edtTypes.getText().toString(), edtName.getText().toString()).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Log.i("REMEMBER", response.body().getStatus());
                Post post = response.body();
                List<Result> result = post.getResults();
                ConvertData(result);

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}