package ru.vsu.cs.novichikhin;

public class ActionWithInputArguments {

    public InputArguments parseCommandArguments(String[] args) {

        InputArguments params = new InputArguments(false, false, false, false);

        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.isHelp = true;
            }

            if (args[0].equals("-i") && args[2].equals("-o")) {
                params.isCalendar = true;
            }
            if (args[0].equals("--window")) {
                params.isWindow = true;
            }
        } else {
            params.isHelp = true;
            params.isError = true;
        }
        return params;
    }
}