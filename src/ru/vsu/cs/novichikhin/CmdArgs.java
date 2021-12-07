package ru.vsu.cs.novichikhin;

public class CmdArgs {

    public static class CmdParams {
        String inputFile;
        String outputFile;
        boolean error;
        boolean help;
        boolean window;

    }

    public CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();

        if (args.length == 1 || args.length == 2 || args.length == 4) {

            if (args.length == 1) {
                if (args[0].equals("--help")) {
                    params.help = true;
                } else {
                    if (args[0].equals("--window")) {
                        params.window = true;
                    } else {
                        returnError(params);
                    }
                }
            } else {

                if (args.length == 2 && args[0].equals("-i")) {
                    params.inputFile = args[1];
                    params.outputFile = null;
                } else {

                    if (args[0].equals("-i") && args[2].equals("-o")) {
                        params.inputFile = args[1];
                        params.outputFile = args[3];
                    } else {
                        returnError(params);
                    }
                }
            }
        } else {
            returnError(params);
        }
        return params;
    }

    private void returnError(CmdParams params) {
        params.help = true;
        params.error = true;
    }
}