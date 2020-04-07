package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ImproveHero extends AppCompatActivity {

    Button back_improve_hero;
    Button improve_1;
    Button improve_2;
    Button improve_3;
    TextView improve_info;
    Button improve_button;

    Skill skill;

    Character hero;

    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_hero);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        back_improve_hero = findViewById(R.id.back_improve_hero);
        improve_1 = findViewById(R.id.improve_1);
        improve_2 = findViewById(R.id.improve_2);
        improve_3 = findViewById(R.id.improve_3);
        improve_info = findViewById(R.id.improve_info);
        improve_button = findViewById(R.id.improve_button);

        back_improve_hero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ImproveHero.this, Locate.class);
                i.putExtra("hero_get", hero);
                startActivity(i);
                finish();
            }
        });

        improve_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hero.getImpPoint() >= 1) {
                    hero.setAtk((int) (hero.getAtk() * 1.25));
                    ArrayList<Skill> hs = hero.getSkills();
                    skill = new Skill("sss", true);
                    hs.add(skill);
                    hero.setSkills(hs);
                    hero.setImpPoint(hero.getImpPoint() - 1);
                } else {
                    Toast.makeText(ImproveHero.this, "Недостаточно" +
                            " очков", Toast.LENGTH_SHORT).show();
                }
            }
        });

        improve_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < hero.getSkills().size(); i++) {
                    if (hero.getSkills().get(i).getSkill().equals("sss")) {
                        flag = true;
                    }
                }
                if (hero.getImpPoint() >= 1) {
                    if (flag) {
                        hero.setDef((int) (hero.getDef() * 1.25));
                        ArrayList<Skill> hs = hero.getSkills();
                        skill = new Skill("ttt", true);
                        hs.add(skill);
                        hero.setSkills(hs);
                        hero.setImpPoint(hero.getImpPoint() - 1);
                        flag = false;
                    } else {
                        Toast.makeText(ImproveHero.this, "Выучите предыдуий" +
                                "навык", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ImproveHero.this, "Недостаточно" +
                            " очков", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
