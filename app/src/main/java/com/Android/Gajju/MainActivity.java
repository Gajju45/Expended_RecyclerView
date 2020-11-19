package com.Android.Gajju;

import android.os.Bundle;
import android.util.Log;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Product> products;
    ArrayList<Product> products2;


    private static String JSON_URL = "http://deboxcrm.com/ecommerce/app/json_category.php";
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.songsList);
        products = new ArrayList<>();

        //extend
        products2 = new ArrayList<>();
        extractSongs();
    }

    private void extractSongs() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("category");
                    for (int i = 0; i <jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Product product = new Product();

                        product.setTitle(obj.getString("name").toString());
                        product.setCoverImage(obj.getString("categoryImage"));

                        products.add(product);
                    }


                    //expended
                    JSONArray jsonArray2 = response.getJSONArray("products");
                    for (int i = 0; i <jsonArray2.length(); i++) {
                        JSONObject obj2 = jsonArray2.getJSONObject(i);
                        Product product2 = new Product();
                        product2.setExpName(obj2.getString("name").toString());
                        product2.setExpImg(obj2.getString("productImage"));


                        Log.d("Shubham", "onResponse: Title is: " +
                                obj2.getString("name"));
                        Log.d("Shubham", "onResponse: productImage is: " +
                                obj2.getString("productImage"));
                        products2.add(product2);

                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), products,products2);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(request);

    }
}