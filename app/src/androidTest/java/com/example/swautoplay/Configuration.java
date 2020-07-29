package com.example.swautoplay;

import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import static com.example.swautoplay.SwAutoPlayTest.RIVALS_COUNT;

public class Configuration {

    public String dungeonName;
    public String startTestPosition;
    public String refillState;
    public String difficulty;

    public int averageDungeonTime;
    public int runCount;
    public int startStage;
    public int level;

    public boolean isHoH;
    public boolean isDoubleReward;
    public boolean[] availableRivals;

    public Configuration() {
        this.dungeonName = "";
        this.startTestPosition = "";
        this.refillState = "";
        this.difficulty = "";

        this.averageDungeonTime = 0;
        this.runCount = 0;
        this.startStage = 0;
        this.level = 0;

        this.isHoH = false;
        this.isDoubleReward = false;
        this.availableRivals = new boolean[RIVALS_COUNT];
    }

    public Configuration parse() {
        this.dungeonName = InstrumentationRegistry.getArguments().getString("DungeonName");
        this.startTestPosition = InstrumentationRegistry.getArguments().getString("StartTestPosition");
        this.refillState = InstrumentationRegistry.getArguments().getString("Refill");
        this.difficulty = InstrumentationRegistry.getArguments().getString("Difficulty");


        String temp = InstrumentationRegistry.getArguments().getString("AverageDungeonTime");
        this.averageDungeonTime = (temp == null) ? -1 : Integer.parseInt(temp);
        temp = InstrumentationRegistry.getArguments().getString("RunCount");
        this.runCount = (temp == null) ? -1 : Integer.parseInt(temp);
        temp = InstrumentationRegistry.getArguments().getString("StartStage");
        this.startStage = (temp == null) ? -1 : Integer.parseInt(temp);
        temp = InstrumentationRegistry.getArguments().getString("Level");
        this.level = (temp == null) ? -1 : Integer.parseInt(temp);

        temp = InstrumentationRegistry.getArguments().getString("RivalsState");
        if (temp != null) {
            String[] splitRivals = temp.split("");
            for (int i = 1; i<RIVALS_COUNT+1; i++) {
                this.availableRivals[i-1] = "1".equals(splitRivals[i]);
            }
        }


        temp = InstrumentationRegistry.getArguments().getString("HoH");
        this.isHoH = (temp == null) ? false : Boolean.parseBoolean(temp);

        temp = InstrumentationRegistry.getArguments().getString("DoubleReward");
        this.isDoubleReward = (temp == null) ? false : Boolean.parseBoolean(temp);
        return this;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dungeonName='" + dungeonName + '\'' +
                ", startTestPosition='" + startTestPosition + '\'' +
                ", refillState='" + refillState + '\'' +
                ", difficulty='" + difficulty + '\'' +

                ", averageDungeonTime='" + averageDungeonTime + '\'' +
                ", runCount='" + runCount + '\'' +
                ", startStage='" + startStage + '\'' +
                '}';
    }
}
