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
    View view;

    Button back_improve_hero, improve_1, improve_2, improve_3, improve_button;
    TextView improve_info, next_improve_info, level_improve_info, improve_this_skill, improve_next_skill;

    Skill skill;
    String needSkill, improveSkill;

    Integer cost, maxSkillLevel, needHeroLevel, skillLevel;

    Character hero;

    Generator generator;

    View btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_hero);

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


        hero = (Character) getIntent().getSerializableExtra("hero_get");

        generator = new Generator(MainActivity.getStage(), MainActivity.getLevel());

        back_improve_hero = findViewById(R.id.back_improve_hero);
        improve_1 = findViewById(R.id.improve_1);
        improve_2 = findViewById(R.id.improve_2);
        improve_3 = findViewById(R.id.improve_3);
        improve_info = findViewById(R.id.improve_info);
        next_improve_info = findViewById(R.id.next_improve_info);
        level_improve_info = findViewById(R.id.level_improve_info);
        improve_this_skill = findViewById(R.id.improve_this_skill);
        improve_next_skill = findViewById(R.id.improve_next_skill);
        improve_button = findViewById(R.id.improve_button);

        ButtonTreatment bt = new ButtonTreatment();

        back_improve_hero.setOnClickListener(bt);
        improve_button.setOnClickListener(bt);
        improve_1.setOnClickListener(bt);
        improve_2.setOnClickListener(bt);
        improve_3.setOnClickListener(bt);
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
                case R.id.back_improve_hero:
                    Intent intent = new Intent(ImproveHero.this, Locate.class);
                    intent.putExtra("hero_get", hero);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.improve_button:
                    if (skill != null) {
                        for (int i = 0; i < hero.getSkills().size(); i++) {
                            if (hero.getSkills().get(i).getSkill().equals(skill.getSkill()) &&
                                    hero.getSkills().get(i).getLevel() == maxSkillLevel) {
                                Toast.makeText(ImproveHero.this, "Навык уже" +
                                        " изучен", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        if (needSkill == null) {
                            if (hero.getLvl() >= needHeroLevel) {
                                if (hero.getImpPoint() >= cost) {
                                    hero.setImpPoint(hero.getImpPoint() - cost);
                                    ArrayList<Skill> hs = hero.getSkills();
                                    for (int i1 = 0; i1 < hs.size(); i1++) {
                                        if (hs.get(i1).getSkill().equals(improveSkill)) {
                                            hs.remove(i1);
                                            break;
                                        }
                                    }
                                    hs.add(skill);
                                    hero.setSkills(hs);
                                    btn.callOnClick();
                                    Toast.makeText(ImproveHero.this, "Навык" +
                                            " изучен", Toast.LENGTH_SHORT).show();
                                    return;
                                } else {
                                    Toast.makeText(ImproveHero.this, "Недостаточно" +
                                            " очков", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            } else {
                                Toast.makeText(ImproveHero.this, "Недостаточный" +
                                        " уровень", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        for (int i = 0; i < hero.getSkills().size(); i++) {
                            if (hero.getSkills().get(i).getSkill().equals(needSkill)) {
                                if (hero.getLvl() >= needHeroLevel) {
                                    if (hero.getImpPoint() >= cost) {
                                        hero.setImpPoint(hero.getImpPoint() - cost);
                                        ArrayList<Skill> hs = hero.getSkills();
                                        for (int i1 = 0; i1 < hs.size(); i1++) {
                                            if (hs.get(i1).getSkill().equals(improveSkill)) {
                                                hs.remove(i1);
                                                break;
                                            }
                                        }
                                        hs.add(skill);
                                        hero.setSkills(hs);
                                        btn.callOnClick();
                                        Toast.makeText(ImproveHero.this, "Навык" +
                                                " изучен", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        Toast.makeText(ImproveHero.this, "Недостаточно" +
                                                " очков", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                } else {
                                    Toast.makeText(ImproveHero.this, "Недостаточный" +
                                            " уровень", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }
                        Toast.makeText(ImproveHero.this, "Выучите предыдущий" +
                                " навык", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ImproveHero.this, "Выберете" +
                                " навык", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.improve_1:
                    btn = improve_1;
                    maxSkillLevel = 3;
                    improveSkill = "1.phs_up";
                    skillLevel = 0;
                    for (int i = 0; i < hero.getSkills().size(); i++) {
                        Skill skill = hero.getSkills().get(i);
                        if (skill.getSkill().equals(improveSkill)) {
                            if (skill.getLevel() == 1) {
                                skillLevel = 1;
                            }
                            if (skill.getLevel() == 2) {
                                skillLevel = 2;
                            }
                            if (skill.getLevel() == 3) {
                                skillLevel = 3;
                            }
                        }
                    }
                    if (skillLevel == 0) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.p);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.phis_skill_1);
                        needSkill = null;
                        needHeroLevel = 1;
                        cost = 0;
                        skill = new Skill("1.phs_up", 0, 0, 1, true);
                    } else if (skillLevel == 1) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.phis_skill_1);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.phis_skill_2);
                        needSkill = null;
                        needHeroLevel = 1;
                        cost = 0;
                        skill = new Skill("1.phs_up", 0, 0, 2, true);
                    } else if (skillLevel == 2) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.phis_skill_2);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.phis_skill_3);
                        needSkill = null;
                        needHeroLevel = 1;
                        cost = 0;
                        skill = new Skill("1.phs_up", 0, 0, 3, true);
                    } else {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.phis_skill_3);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.p);
                        skill = new Skill("1.phs_up", 0, 0, 3, true);
                    }
                    level_improve_info.setText(generator.skillLevel(skillLevel, maxSkillLevel));
                    break;

                case R.id.improve_2:
                    btn = improve_2;
                    maxSkillLevel = 3;
                    improveSkill = "2.skill_atk";
                    skillLevel = 0;
                    for (int i = 0; i < hero.getSkills().size(); i++) {
                        Skill skill = hero.getSkills().get(i);
                        if (skill.getSkill().equals(improveSkill)) {
                            if (skill.getLevel() == 1) {
                                skillLevel = 1;
                            }
                            if (skill.getLevel() == 2) {
                                skillLevel = 2;
                            }
                            if (skill.getLevel() == 3) {
                                skillLevel = 3;
                            }
                        }
                    }
                    if (skillLevel == 0) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.p);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.second_atk_skill_1);
                        needSkill = "1.phs_up";
                        skill = new Skill("2.skill_atk", 0, 2, 1, true);
                        needHeroLevel = 1;
                        cost = 0;
                    } else if (skillLevel == 1) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.second_atk_skill_1);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.second_atk_skill_2);
                        needSkill = "1.phs_up";
                        skill = new Skill("2.skill_atk", 0, 2, 2, true);
                        needHeroLevel = 1;
                        cost = 0;
                    } else if (skillLevel == 2) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.second_atk_skill_2);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.second_atk_skill_3);
                        needSkill = "1.phs_up";
                        skill = new Skill("2.skill_atk", 0, 2, 3, true);
                        needHeroLevel = 1;
                        cost = 0;
                    } else {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.second_atk_skill_3);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.p);
                        skill = new Skill("2.skill_atk", 0, 2, 3, true);
                    }
                    level_improve_info.setText(generator.skillLevel(skillLevel, maxSkillLevel));
                    break;

                case R.id.improve_3:
                    btn = improve_3;
                    maxSkillLevel = 4;
                    improveSkill = "2.shadow_atk";
                    skillLevel = 0;
                    for (int i = 0; i < hero.getSkills().size(); i++) {
                        Skill skill = hero.getSkills().get(i);
                        if (skill.getSkill().equals(improveSkill)) {
                            if (skill.getLevel() == 1) {
                                skillLevel = 1;
                            }
                            if (skill.getLevel() == 2) {
                                skillLevel = 2;
                            }
                            if (skill.getLevel() == 3) {
                                skillLevel = 3;
                            }
                            if (skill.getLevel() == 4) {
                                skillLevel = 4;
                            }
                        }
                    }
                    if (skillLevel == 0) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.p);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.shadow_atk_1);
                        needSkill = "1.phs_up";
                        needHeroLevel = 1;
                        cost = 0;
                        skill = new Skill("2.shadow_atk", 0, 3, 1, true);
                    } else if (skillLevel == 1) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.shadow_atk_1);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.shadow_atk_2);
                        needSkill = "1.phs_up";
                        needHeroLevel = 1;
                        cost = 0;
                        skill = new Skill("2.shadow_atk", 0, 3, 2, true);
                    } else if (skillLevel == 2) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.shadow_atk_2);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.shadow_atk_3);
                        needSkill = "1.phs_up";
                        needHeroLevel = 1;
                        cost = 0;
                        skill = new Skill("2.shadow_atk", 0, 3, 3, true);
                    } else if (skillLevel == 3) {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.shadow_atk_3);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.shadow_atk_4);
                        needSkill = "1.phs_up";
                        needHeroLevel = 1;
                        cost = 0;
                        skill = new Skill("2.shadow_atk", 0, 3, 4, true);
                    } else {
                        improve_this_skill.setText(R.string.this_skill);
                        improve_info.setText(R.string.shadow_atk_4);
                        improve_next_skill.setText(R.string.next_skill);
                        next_improve_info.setText(R.string.p);
                        skill = new Skill("2.shadow_atk", 0, 3, 4, true);
                    }
                    level_improve_info.setText(generator.skillLevel(skillLevel, maxSkillLevel));
                    break;
            }
        }
    }
}
