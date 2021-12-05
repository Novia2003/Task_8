package ru.vsu.cs.novichikhin;

import ru.vsu.cs.util.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Objects;

public class ConsoleMain {

    public static void main(String[] args) throws FileNotFoundException {

        ActionWithInputArguments action = new ActionWithInputArguments();
        InputArguments params = action.parseCommandArguments(args);

        if (params.isHelp) {
            printUsage(params);
        }
        if (params.isCalendar) {
            int[] yearsAndMonth = ArrayUtils.readIntArrayFromFile(args[1]);

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

            ArrayUtils.writeArrayToFile(args[3], array);


        }
        if (params.isWindow) {
            GuiMain.winMain();
        }
    }

    private static void printUsage(InputArguments params) {
        PrintStream out = params.isError ? System.err : System.out;
        out.println("Usage:");
        out.println("  <cmd> -i <input-file> -o <output.txt-file>");
        out.println("  <cmd> --help");
        out.println(" <cmd> --window");
        System.exit(params.isError ? 1 : 0);
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
