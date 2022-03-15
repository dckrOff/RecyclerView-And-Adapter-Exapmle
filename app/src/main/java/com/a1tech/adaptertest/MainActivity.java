package com.a1tech.adaptertest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<State> states = new ArrayList<State>();
    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private StateAdapter adapter;
    private EditText stateName, cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setInitialData();

        // создаем адаптер
        adapter = new StateAdapter(this, states);

        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        btnOnClick();
    }

    private void initViews() {
        // начальная инициализация списка
        recyclerView = findViewById(R.id.list);
        fab = findViewById(R.id.fab);
        stateName = findViewById(R.id.stateName);
        cityName = findViewById(R.id.cityName);
    }

    private void btnOnClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogDemo();
            }
        });
    }

    private void setInitialData() {
        states.add(new State("Бразилия", "Бразилиа", R.drawable.brazil));
        states.add(new State("Аргентина", "Буэнос-Айрес", R.drawable.argentina));
        states.add(new State("Колумбия", "Богота", R.drawable.colombia));
        states.add(new State("Уругвай", "Монтевидео", R.drawable.uruguay));
        states.add(new State("Чили", "Сантьяго", R.drawable.chile));
    }

    private void alertDialogDemo() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.custom_alert_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final TextView nameOfState = dialogView.findViewById(R.id.stateName);
        final TextView nameOfCity = dialogView.findViewById(R.id.cityName);

        builder
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        states.add(new State(nameOfState.getText().toString(), nameOfCity.getText().toString(), R.drawable.uzbekistan));
                        recyclerView.setAdapter(adapter);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}