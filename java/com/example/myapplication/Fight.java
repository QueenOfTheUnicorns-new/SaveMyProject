package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Fight extends AppCompatActivity {
    View view;

    TextView tv1, tv2;
    Button atk, skill, def, shadow_atk;

    Character hero, tmpHero;

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



        defend = false;

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        atk = findViewById(R.id.atk);
        skill = findViewById(R.id.skill);
        shadow_atk = findViewById(R.id.shadow_atk);
        def = findViewById(R.id.def);

        Generator generator = new Generator(MainActivity.getStage(), MainActivity.getLevel());

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        if ((int) (Math.random() * 10) < 3) {
            itemWin = generator.itemForFightGenerator(hero.getHeroItems());
        }

        enemy = (Enemy) new Generator(MainActivity.getStage(), MainActivity.getLevel()).enemyGenerator();

        tmpHero = new Character(hero.getHp(), hero.getMaxHp(), hero.getAtk(), hero.getDef(), hero.getName(),
                hero.getExp(), hero.getMoney(), hero.getLvl(), hero.getImpPoint(), hero.getHeroItems(),
                hero.getSkills());

        tmpHero.setAll(new Treatment.Fight(tmpHero, hero.getHeroItems(), enemy).setFightIndicator());

        if (tmpHero.getHp() > tmpHero.getMaxHp()) {
            tmpHero.setHp(tmpHero.getMaxHp());
        }

        fight = new Treatment.Fight(tmpHero, hero.getHeroItems(), enemy);

        tv1.setText(enemy.getName() + " " + enemy.getHp());
        tv2.setText("Ваше здоровье: " + tmpHero.getHp() + "/" + tmpHero.getMaxHp() + "\n\n" +
                "защита: " + tmpHero.getDef() + " атака: " + tmpHero.getAtk() + " опыт: " +
                tmpHero.getExp() + " уровень: " + tmpHero.getLvl());

        ButtonTreatment bt = new ButtonTreatment();

        atk.setOnClickListener(bt);
        skill.setOnClickListener(bt);
        def.setOnClickListener(bt);
        shadow_atk.setOnClickListener(bt);

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
                case R.id.atk:
                    atk.setEnabled(false);
                    enemy.setHp(fight.basAttack());
                    tmpHero.setSkills(fight.skillsUpdateStep(""));

                    if (enemy.getHp() <= 0) {
                        hero.setHp(tmpHero.getHp());
                        hero.setSkills(fight.setSkillsAfterFight());
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
                    atk.setEnabled(true);
                    break;

                case R.id.skill:
                    skill.setEnabled(false);
                    if (fight.skillIsReady("skill_atk")) {
                        enemy.setHp(fight.secondAttack(hero.getSkills()));
                        tmpHero.setSkills(fight.skillsUpdateStep("skill_atk"));
                        tmpHero.setSkills(fight.setSkillStep("skill_atk"));
                        if (enemy.getHp() <= 0) {
                            hero.setHp(tmpHero.getHp());
                            hero.setSkills(fight.setSkillsAfterFight());
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
                    } else {
                        Toast.makeText(Fight.this, "Навык не готов", Toast.LENGTH_SHORT).show();
                    }
                    skill.setEnabled(true);
                    break;

                case R.id.shadow_atk:
                    shadow_atk.setEnabled(false);
                    if (fight.skillIsReady("shadow_atk")) {
                        enemy.setHp(fight.shadowAttack(hero.getSkills(), enemy));
                        tmpHero.setSkills(fight.skillsUpdateStep("shadow_atk"));
                        tmpHero.setSkills(fight.setSkillStep("shadow_atk"));
                        if (enemy.getHp() <= 0) {
                            hero.setHp(tmpHero.getHp());
                            hero.setSkills(fight.setSkillsAfterFight());
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
                    } else {
                        Toast.makeText(Fight.this, "Навык не готов", Toast.LENGTH_SHORT).show();
                    }
                    shadow_atk.setEnabled(true);
                    break;


                case R.id.def:
                    def.setEnabled(false);
                    dmg = Math.max(enemy.getAtk() - tmpHero.getDef() * 3, 0);
                    tmpHero.setHp(tmpHero.getHp() - dmg);
                    tmpHero.setSkills(fight.skillsUpdateStep(""));

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
                    def.setEnabled(true);
                    break;
            }
        }
    }

}
