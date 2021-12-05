package ru.vsu.cs.novichikhin;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.cs.util.ArrayUtils;

public class CalendarTest {

    @Test
    public void test1() {
        int[] yearsAndMonth = ArrayUtils.readIntArrayFromFile("TestFiles/TestCase/input01.txt");
        int[][] trueCalendar = ArrayUtils.readIntArray2FromFile("TestFiles/TestResult/output01.txt");

        assert yearsAndMonth != null;

        Calendar calendar = new Calendar(yearsAndMonth[0], yearsAndMonth[1]);

        Assert.assertArrayEquals(calendar.getCalendar(), trueCalendar);
    }

    @Test
    public void test2() {
        int[] yearsAndMonth = ArrayUtils.readIntArrayFromFile("TestFiles/TestCase/input02.txt");
        int[][] trueCalendar = ArrayUtils.readIntArray2FromFile("TestFiles/TestResult/output02.txt");

        assert yearsAndMonth != null;

        Calendar calendar = new Calendar(yearsAndMonth[0], yearsAndMonth[1]);

        Assert.assertArrayEquals(calendar.getCalendar(), trueCalendar);
    }

    @Test
    public void test3() {
        int[] yearsAndMonth = ArrayUtils.readIntArrayFromFile("TestFiles/TestCase/input03.txt");
        int[][] trueCalendar = ArrayUtils.readIntArray2FromFile("TestFiles/TestResult/output03.txt");

        assert yearsAndMonth != null;

        Calendar calendar = new Calendar(yearsAndMonth[0], yearsAndMonth[1]);

        Assert.assertArrayEquals(calendar.getCalendar(), trueCalendar);
    }

    @Test
    public void test4() {
        int[] yearsAndMonth = ArrayUtils.readIntArrayFromFile("TestFiles/TestCase/input04.txt");
        int[][] trueCalendar = ArrayUtils.readIntArray2FromFile("TestFiles/TestResult/output04.txt");

        assert yearsAndMonth != null;

        Calendar calendar = new Calendar(yearsAndMonth[0], yearsAndMonth[1]);

        Assert.assertArrayEquals(calendar.getCalendar(), trueCalendar);
    }

    @Test
    public void test5() {
        int[] yearsAndMonth = ArrayUtils.readIntArrayFromFile("TestFiles/TestCase/input05.txt");
        int[][] trueCalendar = ArrayUtils.readIntArray2FromFile("TestFiles/TestResult/output05.txt");

        assert yearsAndMonth != null;

        Calendar calendar = new Calendar(yearsAndMonth[0], yearsAndMonth[1]);

        Assert.assertArrayEquals(calendar.getCalendar(), trueCalendar);
    }
}