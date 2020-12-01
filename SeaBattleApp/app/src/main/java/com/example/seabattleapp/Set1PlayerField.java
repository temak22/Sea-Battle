package com.example.seabattleapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Set1PlayerField extends AppCompatActivity {

    private Button player_1[][] = new Button[10][10];
    private Button player_2[][] = new Button[10][10];
    private Button controlButtons[][];
    private int ships_1_placed = 0;
    private int ships_2_placed = 0;
    private boolean ships_1[][] = new boolean[10][10];
    private boolean ships_2[][] = new boolean[10][10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_set_field);
        CreatePlayer_1();

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button buttonBack = (Button) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    Intent intent = new Intent(Set1PlayerField.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (Exception e) {

                }
            }
        });
    }

    //системная кнопка "назад" - начало
    @Override
    public void onBackPressed () {

        try{
            Intent intent = new Intent(Set1PlayerField.this, MainActivity.class);
            startActivity(intent);
            finish();
        }catch (Exception e) {

        }
    }
    //системная кнопка "назад" - конец


    void CreatePlayer_1() {

        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(10);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                player_1[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                player_1[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Button tappedCell = (Button) v;
                        int tappedX = getX(tappedCell);
                        int tappedY = getY(tappedCell);
                        int color = ((ColorDrawable) player_1[tappedY][tappedX].getBackground()).getColor();

                        if (color == Color.WHITE && ships_1_placed < 20)
                        {
                            player_1[tappedY][tappedX].setBackgroundColor(Color.BLACK);
                            ships_1[tappedY][tappedX] = true;
                            ships_1_placed++;
                            if (ships_1_placed == 20) {
                                CreatePlayer_2();
                            }
                        } else if (color == Color.BLACK)
                        {
                            player_1[tappedY][tappedX].setBackgroundColor(Color.WHITE);
                            ships_1[tappedY][tappedX] = false;
                            ships_1_placed--;
                        }
                    }
                });
                player_1[i][j].setTag(i + "," + j);
                cellsLayout.addView(player_1[i][j]);
            }
        }
    }

    void CreatePlayer_2() {
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(10);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                player_2[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                player_2[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button tappedCell = (Button) v;
                        int tappedX = getX(tappedCell);
                        int tappedY = getY(tappedCell);
                        int color = ((ColorDrawable) player_2[tappedY][tappedX].getBackground()).getColor();

                        if (color == Color.WHITE) {
                            player_2[tappedY][tappedX].setBackgroundColor(Color.BLACK);
                            ships_2[tappedY][tappedX] = true;
                            ships_2_placed++;

                        } else {
                            player_2[tappedY][tappedX].setBackgroundColor(Color.WHITE);
                            ships_2[tappedY][tappedX] = false;
                            ships_2_placed--;
                        }
                    }
                });
                player_2[i][j].setTag(i + "," + j);
                cellsLayout.addView(player_2[i][j]);
            }
        }
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }
    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }
}