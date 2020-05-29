package com.bsu.famcs.anytask.entity;

public enum Label {
    NEW("new"),
    REOPEN("reopen"),
    IN_PROGRESS("in progress"),
    DONE("done"),
    READY_FOR_REVIEW("ready for review");

    private final String label;

    Label(String string) {
        this.label=string;
    }

    @Override
    public String toString() {
        return label;
    }
}
