package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String json = "{\"coord\": { \"lon\": 139,\"lat\": 35}, \"weather\": [ { \"id\": 800, \"main\": \"Clear\"," +
            " \"description\": \"clear sky\", \"icon\": \"01n\" } ], \"base\": \"stations\", " + "" + "\"" +
            "main\": { \"temp\": 289.92, \"pressure\": 1009, \"humidity\": 92, \"temp_min\": 288.71, " +
            "\"temp_max\": 290.93 },\"wind\": { \"speed\": 0.47, \"deg\": 107.538 }, \"clouds\": { \"all\": 2 }, \"dt\": 1560350192, " +
            "\"sys\": { \"type\": 3, \"id\": 2019346, \"message\": 0.0065, \"country\": \"JP\", " +
            "\"sunrise\": 1560281377, \"sunset\": 1560333478 }, \"timezone\": 32400, \"id\": 1851632, " +
            "\"name\": \"Shuzenji\", \"cod\": 200 } ";
    TextView tvJson1, tvJson2;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJson1 = findViewById(R.id.tvJson1);
        tvJson2 = findViewById(R.id.tvJson2);

        products = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(json);
            JSONObject ob2 = object.getJSONObject("main");

            String temp = ob2.getString("temp");
            String pressure = ob2.getString("pressure");
            String humidity = ob2.getString("humidity");
            String temp_min = ob2.getString("temp_min");
            String temp_max = ob2.getString("temp_max");

            tvJson1.setText("main:" + "\n" + "temp:" + temp + "\n" + "pressure:" + pressure + "\n"
                    + "humidity:" + humidity + "\n" + "temp_min:" + temp_min + "\n"
                    + "temp_max:" + temp_max);


        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            JSONObject object = new JSONObject(json);
            JSONArray jsonArray = object.getJSONArray("weather");
            for (int i = 1; i <= jsonArray.length(); i++) {

                String id = jsonArray.getJSONObject(0).getString("id");
                String main = jsonArray.getJSONObject(0).getString("main");
                String des = jsonArray.getJSONObject(0).getString("description");
                String icon = jsonArray.getJSONObject(0).getString("icon");

                Product product = new Product();
                product.setId(id);
                product.setDes(des);
                product.setMain(main);
                product.setIcon(icon);

                products.add(product);
            }

            tvJson2.setText(
                    "weather:" + "\n" + "id:" + products.get(0).getId() + "\n"
                            + "main:" + products.get(0).getMain() + "\n"
                            + "description:" + products.get(0).getDes() + "\n"
                            + "icon:" + products.get(0).getIcon());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
