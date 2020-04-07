package com.example.myapplication;

import java.io.Serializable;

public class Skill implements Serializable {
    private String skill;
    private boolean flag;

    public Skill(String skill, boolean flag) {
        this.skill = skill;
        this.flag = flag;
    }

    public String getSkill() {
        return skill;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
