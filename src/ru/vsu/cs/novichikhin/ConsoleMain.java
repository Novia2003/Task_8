package ru.vsu.cs.novichikhin;

import ru.vsu.cs.util.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Objects;

public class ConsoleMain {

    public static void main(String[] args) throws FileNotFoundException {

        CmdArgs action = new CmdArgs();
        CmdArgs.CmdParams params = action.parseArgs(args);

        if (params.help) {
            printUsage(params);
        } else {
            if (params.window) {
                GuiMain.winMain();
            } else {
                makeCalendar(params);
            }
        }
    }

    private static void printUsage(CmdArgs.CmdParams params) {
        PrintStream out = params.error ? System.err : System.out;
        out.println("Usage:");
        out.println("  <cmd> -i <input-file> -o <output-file>");
        out.println("  <cmd> -i <input-file>");
        out.println("  <cmd> --help");
        out.println(" <cmd> --window");
        System.exit(params.error ? 1 : 0);
    }

    private static void makeCalendar(CmdArgs.CmdParams params) throws FileNotFoundException {
        int[] yearsAndMonth = ArrayUtils.readIntArrayFromFile(params.inputFile);
        Objects.requireNonNull(yearsAndMonth);

        if (yearsAndMonth.length != 2) {
            printError(1);
        }
        if (yearsAndMonth[0] > 12 || yearsAndMonth[0] < 1) {
            printError(2);
        }
        if (yearsAndMonth[1] < 1) {
            printError(3);
        }

        Calendar calendar = new Calendar(yearsAndMonth[0], yearsAndMonth[1]);
        int[][] array = calendar.getCalendar();

        PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
        out.println(ArrayUtils.toString(array));
        out.close();
    }

    private static void printError(int number) {
        switch (number) {
            case 1 -> System.err.println("The data in the input file is incorrect " +
                    "(you must specify the month and year numbers)");
            case 2 -> System.err.println("The month number must be positive and no more than 12.");
            case 3 -> System.err.println("The year number must be positive");
        }
        System.exit(1);
    }
}
