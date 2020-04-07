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

public class Fight extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    Button atk;
    Button def;

    Character hero;

    Character tmpHero;

    Enemy enemy;

    Treatment.Fight fight;

    Item itemWin;

    int dmg;
    boolean defend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        defend = false;

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        atk = findViewById(R.id.atk);
        def = findViewById(R.id.def);

        Generator generator = new Generator(MainActivity.getStage(),MainActivity.getLevel());

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        if ((int)(Math.random() * 10) < 3) {
            itemWin = generator.itemForFightGenerator(hero.getHeroItems());
        }

        enemy = (Enemy) new Generator(MainActivity.getStage(), MainActivity.getLevel()).enemyGenerator();

        tmpHero = new Character(hero.getHp(),hero.getMaxHp(),hero.getAtk(),hero.getDef(),hero.getName(),
                hero.getExp(),hero.getMoney(),hero.getLvl(),hero.getImpPoint(),hero.getHeroItems(),
                hero.getSkills());

        tmpHero.setAll(new Treatment.Fight(tmpHero,hero.getHeroItems(),enemy).setFightIndicator());

        if (tmpHero.getHp() > tmpHero.getMaxHp()) {
            tmpHero.setHp(tmpHero.getMaxHp());
        }

        fight = new Treatment.Fight(tmpHero,hero.getHeroItems(),enemy);

        tv1.setText(enemy.getName() + " " + enemy.getHp());
        tv2.setText("Ваше здоровье: " + tmpHero.getHp() + "/" + tmpHero.getMaxHp() + "\n\n" +
                "защита: " + tmpHero.getDef() + " атака: " + tmpHero.getAtk() + " опыт: " +
                tmpHero.getExp() + " уровень: " + tmpHero.getLvl());

        ButtonTreatment bt = new ButtonTreatment();

        atk.setOnClickListener(bt);
        def.setOnClickListener(bt);

    }

    private class ButtonTreatment implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.atk:
                    enemy.setHp(fight.basAttack());

                    if (enemy.getHp() <= 0) {
                        hero.setHp(tmpHero.getHp());
                        hero.setAll(Treatment.battleWin(hero, MainActivity.getStage(), MainActivity.getLevel()));
                        Intent i = new Intent(Fight.this, Locate.class);
                        if (itemWin != null) {
                            ArrayList<Item> hi = hero.getHeroItems();
                            hi.add(itemWin);
                            hero.setHeroItems(hi);
                        }
                        i.putExtra("hero_get", hero);
                        startActivity(i);
                        tv1.setText(enemy.getName() + " " + enemy.getHp());
                        finish();
                        return;
                    }

                    tv1.setText(enemy.getName() + " " + enemy.getHp());

                    dmg = Math.max(enemy.getAtk() - tmpHero.getDef(), 0);
                    tmpHero.setHp(tmpHero.getHp() - dmg);

                    tv2.setText("Ваше здоровье: " + tmpHero.getHp() + "/" + tmpHero.getMaxHp() + "\n\n" +
                            "защита: " + tmpHero.getDef() + " атака: " + tmpHero.getAtk() + " опыт: " +
                            tmpHero.getExp() + " уровень: " + tmpHero.getLvl());

                    if (tmpHero.getHp() <= 0) {
                        MainActivity.setLevel(null);
                        Toast.makeText(getApplicationContext(), "Вы проиграли", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Fight.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    break;

                case R.id.def:
                    dmg = Math.max(enemy.getAtk() - tmpHero.getDef() * 3, 0);
                    tmpHero.setHp(tmpHero.getHp() - dmg);

                    tv2.setText("Ваше здоровье: " + tmpHero.getHp() + "/" + tmpHero.getMaxHp() + "\n\n" +
                            "защита: " + tmpHero.getDef() + " атака: " + tmpHero.getAtk() + " опыт: " +
                            tmpHero.getExp() + " уровень: " + tmpHero.getLvl());

                    if (tmpHero.getHp() <= 0) {
                        MainActivity.setLevel(null);
                        Toast.makeText(getApplicationContext(), "Вы проиграли", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Fight.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                    break;
            }
        }
    }

}
