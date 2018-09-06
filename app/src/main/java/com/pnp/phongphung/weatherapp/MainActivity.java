package com.pnp.phongphung.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    EditText edtSearch;
    Button btnSearch, btnChangeActivity;
    TextView txtName, txtCountry, txtTemp, txtStatus, txtHumidity, txtCloud, txtGio, txtDay;
    ImageView imgIcon;
    String city1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        GetCurrentWeatherData("Hanoi");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edtSearch.getText().toString();

                if (city.equals("")) {
                    city1 = "Hanoi";
                    GetCurrentWeatherData(city1);
                } else {
                    city1 = city;
                    GetCurrentWeatherData(city1);
                }

            }
        });

        btnChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = edtSearch.getText().toString();
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("name", city);
                startActivity(intent);
            }
        });
    }

    public void GetCurrentWeatherData(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + data + "&units=metric&appid=53fbf527d52d4d773e828243b90c1f8e";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String day = jsonObject.getString("dt");
                    String name = jsonObject.getString("name");
                    txtName.setText("Tên thành phố : " + name);

                    long l = Long.valueOf(day);
                    Date date = new Date(l * 1000L);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd HH-mm-ss");
                    String Day = simpleDateFormat.format(date);
                    txtDay.setText(Day);

                    JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                    String status = jsonObjectWeather.getString("main");
                    String icon = jsonObjectWeather.getString("icon");

                    Picasso.with(MainActivity.this).load("http://openweathermap.org/img/w/" + icon + ".png").into(imgIcon);

                    txtStatus.setText(status);

                    JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                    String nhietDo = jsonObjectMain.getString("temp");
                    String doAm = jsonObjectMain.getString("humidity");

                    Double a = Double.valueOf(nhietDo);
                    String Nhietdo = String.valueOf(a.intValue());
                    txtTemp.setText(Nhietdo + "°C");
                    txtHumidity.setText(doAm + "%");

                    JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                    String gio = jsonObjectWind.getString("speed");
                    txtGio.setText(gio + "m/s");

                    JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                    String may = jsonObjectCloud.getString("all");
                    txtCloud.setText(may + "%");

                    JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                    String country = jsonObjectSys.getString("country");
                    txtCountry.setText("Tên quốc gia : " + country);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(stringRequest);
    }

    private void AnhXa() {
        edtSearch = (EditText) findViewById(R.id.edittextSearch);
        btnSearch = (Button) findViewById(R.id.buttonSearch);
        btnChangeActivity = (Button) findViewById(R.id.buttonChangeActivity);
        imgIcon = (ImageView) findViewById(R.id.imageIcon);
        txtName = (TextView) findViewById(R.id.textviewName);
        txtCountry = (TextView) findViewById(R.id.textviewCountry);
        txtTemp = (TextView) findViewById(R.id.textviewTemp);
        txtStatus = (TextView) findViewById(R.id.textviewStatus);
        txtHumidity = (TextView) findViewById(R.id.textviewHumility);
        txtCloud = (TextView) findViewById(R.id.textviewCloud);
        txtGio = (TextView) findViewById(R.id.textviewWind);
        txtDay = (TextView) findViewById(R.id.textviewDay);
    }
}
