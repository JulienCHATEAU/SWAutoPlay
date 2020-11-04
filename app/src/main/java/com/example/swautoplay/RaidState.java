package com.example.swautoplay;

enum RaidState {
    READY("READY"),
    NOT_READY("NOT_READY"),
    IN_BATTLE("IN_BATTLE"),
    WAIT_FOR_LOBBY("WAIT_FOR_LOBBY"),
    ERROR("ERROR");

    private String label;

    RaidState(String label) {
        this.label = label;
    }

    @Override
    public String toString(){
        return this.label;
    }
}
