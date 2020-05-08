package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class Locate extends AppCompatActivity {
    View view;

    Button level_1;
    Button level_2;
    Button level_3;
    Button level_4;
    Button level_5;
    Button go_improve_hero;

    Character hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);


        view = getWindow().getDecorView();

        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    view.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        });


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (MainActivity.getLevel() == null) {
            MainActivity.setLevel(1);
        }
        if (MainActivity.getStage() == null) {
            MainActivity.setStage(1);
        }

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);
        level_4 = findViewById(R.id.level_4);
        level_5 = findViewById(R.id.level_5);
        go_improve_hero = findViewById(R.id.go_improve_hero);

        ButtonTreatment bt = new ButtonTreatment();

        level_1.setOnClickListener(bt);
        level_2.setOnClickListener(bt);
        level_3.setOnClickListener(bt);
        level_4.setOnClickListener(bt);
        level_5.setOnClickListener(bt);
        go_improve_hero.setOnClickListener(bt);
    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }




    private class ButtonTreatment implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.level_1:
                    if (MainActivity.getLevel() == 1) {
                        Intent i = new Intent(Locate.this, Fight.class);
                        i.putExtra("hero_get", hero);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Вы уже прошли этот этап." +
                                " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.level_2:
                    if (MainActivity.getLevel() == 2) {
                        Intent i = new Intent(Locate.this, Fight.class);
                        i.putExtra("hero_get", hero);
                        startActivity(i);
                        finish();
                    } else {
                        if (MainActivity.getLevel() < 2) {
                            Toast.makeText(getApplicationContext(), "Вы ещё не прошли предыдущий этап." +
                                    " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Вы уже прошли этот этап." +
                                    " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                        }
                    }
                    break;

                case R.id.level_3:
                    if (MainActivity.getLevel() == 3) {
                        Intent i = new Intent(Locate.this, Shop.class);
                        i.putExtra("hero_get", hero);
                        startActivity(i);
                        finish();
                    } else {
                        if (MainActivity.getLevel() < 3) {
                            Toast.makeText(getApplicationContext(), "Вы ещё не прошли предыдущий этап." +
                                    " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Вы уже прошли этот этап." +
                                    " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                        }
                    }
                    break;

                case R.id.level_4:
                    if (MainActivity.getLevel() == 4) {
                        Intent i = new Intent(Locate.this, Fight.class);
                        i.putExtra("hero_get", hero);
                        startActivity(i);
                        finish();
                    } else {
                        if (MainActivity.getLevel() < 4) {
                            Toast.makeText(getApplicationContext(), "Вы ещё не прошли предыдущий этап." +
                                    " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Вы уже прошли этот этап." +
                                    " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                        }
                    }
                    break;

                case R.id.level_5:
                    if (MainActivity.getLevel() == 5) {
                        Intent i = new Intent(Locate.this, Fight.class);
                        i.putExtra("hero_get", hero);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Вы ещё не прошли предыдущий этап." +
                                " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
                    }
                    break;

                case R.id.go_improve_hero:
                    Intent i = new Intent(Locate.this, ImproveHero.class);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
            }
        }
    }

}
