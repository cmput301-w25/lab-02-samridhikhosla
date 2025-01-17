package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText cityInput;
    Button addCityButton, deleteCityButton, confirmButton;
    int clickedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.city_input);
        addCityButton = findViewById(R.id.add_city);
        deleteCityButton = findViewById(R.id.delete_city);
        confirmButton = findViewById(R.id.confirm_button);


        String[] cities = {"Edmonton", "Paris", "London"};
        dataList = new ArrayList<>();

        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);


        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //nothing happens when item is clicked for now
                clickedPosition = position;

            }
        });

        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedPosition != -1) {
                    dataList.remove(clickedPosition);
                    clickedPosition = -1;
                    cityAdapter.notifyDataSetChanged();
            }
        }});

        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //https://stackoverflow.com/questions/70759471/how-to-use-visibility-on-android-studio
                cityInput.setVisibility(View.VISIBLE);
                confirmButton.setVisibility(View.VISIBLE);
                cityInput.setText("");
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityInput.getText().toString().trim();
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
                cityInput.setVisibility(View.GONE);
                confirmButton.setVisibility(View.GONE);
                }

        });

    }

}