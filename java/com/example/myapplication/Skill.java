package com.example.myapplication;

import java.io.Serializable;

public class Skill implements Serializable {
    private String skill;
    private int needStep;
    private int Step;
    private int level;
    private boolean flag;

    public Skill(String skill, int needStep, int step, int level, boolean flag) {
        this.skill = skill;
        this.needStep = needStep;
        Step = step;
        this.level = level;
        this.flag = flag;
    }

    public String getSkill() {
        return skill;
    }

    public int getNeedStep() {
        return needStep;
    }

    public int getStep() {
        return Step;
    }

    public int getLevel() {
        return level;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setNeedStep(int needStep) {
        this.needStep = needStep;
    }

    public void setStep(int step) {
        Step = step;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
