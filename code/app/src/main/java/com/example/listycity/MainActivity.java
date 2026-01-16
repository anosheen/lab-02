package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;



import androidx.activity.EdgeToEdge;
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

    Button addCityButton;
    Button deleteCityButton;
    int selectedIndex = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addCityButton = findViewById(R.id.add_city);
        deleteCityButton = findViewById(R.id.delete_city);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList=findViewById(R.id.city_list);
        String []cities={"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);



        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedIndex = position;
        });

        addCityButton.setOnClickListener(v -> {
            EditText input = new EditText(MainActivity.this);
            input.setHint("City name");

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Add City")
                    .setView(input)
                    .setPositiveButton("OK", (dialog, which) -> {
                        String city = input.getText().toString().trim();
                        if (!city.isEmpty()) {
                            dataList.add(city);
                            cityAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

        deleteCityButton.setOnClickListener(v -> {
            if (selectedIndex != -1) {
                dataList.remove(selectedIndex);
                cityAdapter.notifyDataSetChanged();
                selectedIndex = -1;
            }
        });





    }


}