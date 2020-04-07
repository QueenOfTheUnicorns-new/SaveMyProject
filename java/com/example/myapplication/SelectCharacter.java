package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SelectCharacter extends AppCompatActivity {

    Button back;

    Button character_1;
    Button character_2;
    Button character_3;
    Character hero;
    ArrayList<Item> heroItems;
    ArrayList<Skill> skills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        back = (Button) findViewById(R.id.btn_back);
        character_1 = (Button) findViewById(R.id.select_first_character);
        character_2 = (Button) findViewById(R.id.select_second_character);
        character_3 = (Button) findViewById(R.id.select_third_character);

        heroItems = new ArrayList<>();

        skills = new ArrayList<>();

        ButtonTreatment bt = new ButtonTreatment();

        back.setOnClickListener(bt);
        character_1.setOnClickListener(bt);
        character_2.setOnClickListener(bt);
        character_3.setOnClickListener(bt);
    }

    private class ButtonTreatment implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_back:
                    Intent i1 = new Intent(SelectCharacter.this, MainActivity.class);
                    startActivity(i1);
                    finish();
                    break;

                case R.id.select_first_character:
                    Intent i2 = new Intent(SelectCharacter.this, Locate.class);
                    hero = new Character(100, 100, 25, 5, "ttt",
                            0, 10, 1, 0, heroItems, skills);
                    i2.putExtra("hero_get", hero);
                    startActivity(i2);
                    finish();
                    break;

                case R.id.select_second_character:
                    Intent i3 = new Intent(SelectCharacter.this, Locate.class);
                    hero = new Character(130, 130, 15, 8, "ttt",
                            0, 10, 1, 0, heroItems, skills);
                    i3.putExtra("hero_get", hero);
                    startActivity(i3);
                    finish();
                    break;

                case R.id.select_third_character:
                    Intent i4 = new Intent(SelectCharacter.this, Locate.class);
                    hero = new Character(60, 60, 30, 10, "ttt",
                            0, 30, 1, 0, heroItems, skills);
                    i4.putExtra("hero_get", hero);
                    startActivity(i4);
                    finish();
                    break;
            }
        }
    }

}
