package com.example.retrofitapitincoder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitapitincoder.api.ApiService;
import com.example.retrofitapitincoder.model.Currency;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvTerms, tvSource, tvUsdVnd;
    private Button btnCallApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTerms = findViewById(R.id.tv_terms);
        tvSource = findViewById(R.id.tv_sources);
        tvTerms = findViewById(R.id.tv_usd_vnd);
        btnCallApi = findViewById(R.id.btn_call_api);

        btnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallApi();
            }
        });
    }

    // Link api http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    private void clickCallApi() {
        ApiService.apiService.convertUsdToVnd(
            "843d4d34ae72b3882e3db642c51e28e6",
            "VND",
            "USD",
            1
        ).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();

                Currency currency = response.body();
                if (currency != null && currency.isSuccess()) {
                    tvTerms.setText(currency.getTerms());
                    tvSource.setText(currency.getSources());
                }
                // tvUsdVnd.setText(String.valueOf(currency.getQuotes().getusdVnd()));

            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}