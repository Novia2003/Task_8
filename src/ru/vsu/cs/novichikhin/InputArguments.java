package ru.vsu.cs.novichikhin;

public class InputArguments {
    boolean isError;
    boolean isHelp;
    boolean isCalendar;
    boolean isWindow;

    public InputArguments(boolean isError, boolean isHelp, boolean isCalendar, boolean isWindow) {
        this.isError = isError;
        this.isHelp = isHelp;
        this.isCalendar = isCalendar;
        this.isWindow = isWindow;
    }
}
