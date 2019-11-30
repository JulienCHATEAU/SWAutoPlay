package com.example.swautoplay;

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
    public boolean[] availableRivals;

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
        this.availableRivals = new boolean[RIVALS_COUNT];
        if (temp != null) {
            String[] splitRivals = temp.split("");
            for (int i = 0; i<RIVALS_COUNT; i++) {
                this.availableRivals[i] = "1".equals(splitRivals[i]);
            }
        }

        temp = InstrumentationRegistry.getArguments().getString("HoH");
        this.isHoH = (temp == null) ? false : Boolean.parseBoolean(temp);
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
