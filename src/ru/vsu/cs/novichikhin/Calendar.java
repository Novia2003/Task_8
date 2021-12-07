package ru.vsu.cs.novichikhin;

public class Calendar {
    int month;
    int year;

    public Calendar(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public int[][] getCalendar() {

        int[] numberDaysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int weekDay = findWeekDayBeginningMonth(year, month, numberDaysInMonth);
        return createArray(weekDay, month, year, numberDaysInMonth);
    }

    private int findWeekDayBeginningMonth(int year, int month, int[] quantityDays) {
        int weekDay;
        int days;

        if (year >= 1996) {
            days = (year - 1996) * 365;

            for (int i = 0; i < month - 1; i++) {
                days += quantityDays[i];
            }

            days += ((year - 1996) / 4) + 1;

            if (year % 4 == 0 && month < 3) {
                days -= 1;
            }
            weekDay = days % 7;
        } else {
            days = (1996 - year - 1) * 365;

            for (int i = quantityDays.length - 1; i > month - 2; i--) {
                days += quantityDays[i];
            }
            days += ((1996 - year) / 4);

            if (year % 4 == 0 && month > 2) {
                days -= 1;
            }
            weekDay = 7 - (days % 7);

            if (weekDay == 7) {
                weekDay = 0;
            }
        }

        return weekDay + 1;
    }

    private int[][] createArray(int weekDay, int month, int year, int[] quantityDays) {
        int date;
        int[][] array;

        if (weekDay == 1 && month == 2 && year % 4 != 0) {
            date = 1;
            array = new int[4][7];

            for (int r = 0; r < array.length; r++) {
                date = fillStringArray(0, array[r].length, r, array, date, -1);
            }
        } else {
            if (year % 4 == 0) {
                quantityDays[1] = 29;
            }

            if (weekDay - 1 + quantityDays[month - 1] > 35) {
                array = new int[6][7];
            } else {
                array = new int[5][7];
            }

            if (month != 1) {
                date = quantityDays[month - 2] - weekDay + 2;
            } else {
                date = quantityDays[11] - weekDay + 2;
            }
            date = fillStringArray(0, weekDay - 1, 0, array, date, 1);
            date = fillStringArray(weekDay - 1, 7, 0, array, date, -1);

            for (int r = 1; r < array.length - 1; r++) {
                date = fillStringArray(0, array[r].length, r, array, date, -1);
            }

            int dataBeginningLastWeek = date - 1;

            date = fillStringArray(0, quantityDays[month - 1] - dataBeginningLastWeek,
                    array.length - 1, array, date, 1);

            fillStringArray(quantityDays[month - 1] - dataBeginningLastWeek, 7,
                    array.length - 1, array, date, -1);
        }

        return array;
    }

    private int fillStringArray(int indexFirstElement, int indexSecondElement,
                                int r, int[][] array, int date, int num) {

        for (int c = indexFirstElement; c < indexSecondElement; c++) {
            array[r][c] = date;
            date++;
        }
        if (num != -1) {
            return num;
        }
        return date;
    }
}
