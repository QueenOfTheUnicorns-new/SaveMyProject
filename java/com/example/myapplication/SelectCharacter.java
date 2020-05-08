package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class SelectCharacter extends AppCompatActivity {
    View view;

    Button character_1, character_2, character_3;
    ImageButton back;
    Character hero;
    ArrayList<Item> heroItems;
    ArrayList<Skill> skills;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);



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

        back = findViewById(R.id.btn_back);
        character_1 = findViewById(R.id.select_first_character);
        character_2 = findViewById(R.id.select_second_character);
        character_3 = findViewById(R.id.select_third_character);

        heroItems = new ArrayList<>();

        skills = new ArrayList<>();

        ButtonTreatment bt = new ButtonTreatment();

        back.setOnClickListener(bt);
        character_1.setOnClickListener(bt);
        character_2.setOnClickListener(bt);
        character_3.setOnClickListener(bt);
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
                case R.id.btn_back:
                    back.setEnabled(false);
                    i = new Intent(SelectCharacter.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    break;

                case R.id.select_first_character:
                    character_1.setEnabled(false);
                    i = new Intent(SelectCharacter.this, Locate.class);
                    hero = new Character(100, 100, 25, 5, "ttt",
                            0, 10, 1, 0, heroItems, skills);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
                    break;

                case R.id.select_second_character:
                    character_2.setEnabled(false);
                    i = new Intent(SelectCharacter.this, Locate.class);
                    hero = new Character(130, 130, 15, 8, "ttt",
                            0, 10, 1, 0, heroItems, skills);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
                    break;

                case R.id.select_third_character:
                    character_3.setEnabled(false);
                    i = new Intent(SelectCharacter.this, Locate.class);
                    hero = new Character(60, 60, 30, 10, "ttt",
                            0, 30, 1, 0, heroItems, skills);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
                    break;
            }
        }
    }

}
