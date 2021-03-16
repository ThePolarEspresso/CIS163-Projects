package GameRental;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;
import static org.junit.Assert.*;

/*****************************************************************
 * A class to test the GameRental project
 *
 * @author Anna Carvalho
 * @version 03/16/21
 *****************************************************************/

public class TestGameRental {
    /******************************************************************
     * Tests for correct filtering & sorting in DueWithinWeek screen.
     *******************************************************************/
    @Test
    public void testDueWithinWeek() {
        ListModel list = new ListModel();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2010");
            g1.setTime(d1);
            Date d2 = df.parse("1/27/2010");
            g2.setTime(d2);
            Date d3 = df.parse("6/10/2019");
            g3.setTime(d3);
            Date d4 = df.parse("6/16/2019");
            g4.setTime(d4);
            Date d5 = df.parse("6/24/2019");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Game game1 = new Game("Charlie", g1, g2, null,
                "Animal Crossing", ConsoleTypes.NintendoSwitch);
        Game game2 = new Game("Chris", g3, g4, null,
                "Overwatch", ConsoleTypes.PlayStation4Pro);
        Game game3 = new Game("Karin", g4, g5, null,
                "Titanfall", null);
        Console console1 = new Console("David", g1, g2,
                null, ConsoleTypes.PlayStation4Pro);
        Console console2 = new Console("Anna", g3, g4,
                null, ConsoleTypes.PlayStation4Pro);
        Console console3 = new Console("Daniel", g1, g2,
                g3, ConsoleTypes.PlayStation4Pro);

        list.add(game1); //should be sorted second
        list.add(game2); //should be sorted third
        list.add(game3); //should be filtered out bc daysBetween > 7
        list.add(console1); //should be sorted fourth
        list.add(console2); //should be sorted first
        list.add(console3); //should be filtered out bc rental was returned

        list.setDisplayForTesting(ScreenDisplay.DueWithinWeek);
        list.updateScreen();

        assertEquals(list.getFilteredLength(), 4);
        assertEquals(list.getFiltered(0).getNameOfRenter(), list.getUnfiltered(4)
                .getNameOfRenter());
        assertEquals(list.getFiltered(1).getNameOfRenter(), list.getUnfiltered(0)
                .getNameOfRenter());
        assertEquals(list.getFiltered(2).getNameOfRenter(), list.getUnfiltered(1)
                .getNameOfRenter());
        assertEquals(list.getFiltered(3).getNameOfRenter(), list.getUnfiltered(3)
                .getNameOfRenter());
    }

    /******************************************************************
     * Tests for correct filtering & sorting in WithinWeekGamesFirst screen.
     *******************************************************************/
    @Test
    public void testDueWithinWeekGamesFirst() {
        ListModel list = new ListModel();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2010");
            g1.setTime(d1);
            Date d2 = df.parse("1/27/2010");
            g2.setTime(d2);
            Date d3 = df.parse("6/10/2019");
            g3.setTime(d3);
            Date d4 = df.parse("6/16/2019");
            g4.setTime(d4);
            Date d5 = df.parse("6/24/2019");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Game game1 = new Game("Charlie", g1, g2, null,
                "Animal Crossing", ConsoleTypes.NintendoSwitch);
        Game game2 = new Game("Chris", g3, g4, null,
                "Overwatch", ConsoleTypes.PlayStation4Pro);
        Game game3 = new Game("Karin", g4, g5, null,
                "Titanfall", null);
        Console console1 = new Console("David", g1, g2,
                null, ConsoleTypes.PlayStation4Pro);
        Console console2 = new Console("Anna", g3, g4,
                null, ConsoleTypes.PlayStation4Pro);
        Console console3 = new Console("Daniel", g1, g2,
                g3, ConsoleTypes.PlayStation4Pro);


        list.add(game1); //should be sorted first
        list.add(game2); //should be sorted first after "Charlie"
        list.add(game3); //should be filtered out bc daysBetween > 7
        list.add(console1); //should be sorted last after "Anna"
        list.add(console2); //should be sorted last before "David"
        list.add(console3); //should be filtered out bc rental was returned

        list.setDisplayForTesting(ScreenDisplay.DueWithinWeekGamesFirst);
        list.updateScreen();

        assertEquals(list.getFilteredLength(), 4);
        assertEquals(list.getFiltered(0).getNameOfRenter(), list.getUnfiltered(0)
                .getNameOfRenter());
        assertEquals(list.getFiltered(1).getNameOfRenter(), list.getUnfiltered(1)
                .getNameOfRenter());
        assertEquals(list.getFiltered(2).getNameOfRenter(), list.getUnfiltered(4)
                .getNameOfRenter());
        assertEquals(list.getFiltered(3).getNameOfRenter(), list.getUnfiltered(3)
                .getNameOfRenter());
    }

    /******************************************************************
     * Tests for correct filtering & sorting in Cap14DaysOverdue screen.
     *******************************************************************/
    @Test
    public void testCap14DaysOverdue() {
        ListModel list = new ListModel();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/1/2010");
            g1.setTime(d1);
            Date d2 = df.parse("1/15/2010");
            g2.setTime(d2);
            Date d3 = df.parse("2/07/2019");
            g3.setTime(d3);
            Date d4 = df.parse("2/015/2019");
            g4.setTime(d4);
            Date d5 = df.parse("2/020/2019");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Game game1 = new Game("Karin", g1, g2, null,
                "Animal Crossing", ConsoleTypes.NintendoSwitch);
        Game game2 = new Game("Chris", g3, g4, null,
                "Overwatch", ConsoleTypes.PlayStation4Pro);
        Game game3 = new Game("David", g2, g3, null,
                "Titanfall", null);
        Console console1 = new Console("Ella", g3, g4,
                null, ConsoleTypes.PlayStation4Pro);
        Console console2 = new Console("Anna", g4, g5,
                null, ConsoleTypes.PlayStation4Pro);
        Console console3 = new Console("Daniel", g1, g2,
                g3, ConsoleTypes.PlayStation4Pro);


        list.add(game1); //should be sorted first and before "Chris"
        list.add(game2); //should be sorted first and after "Karin"
        list.add(game3); //should be sorted first
        list.add(console1); //should be sorted last
        list.add(console2); //should be filtered out bc daysBetween >= 7
        list.add(console3); //should be filtered out bc rental was returned

        list.setDisplayForTesting(ScreenDisplay.Cap14DaysOverdue);
        list.updateScreen();

        assertEquals(list.getFilteredLength(), 4);
        assertEquals(list.getFiltered(0).getNameOfRenter(), list.getUnfiltered(2)
                .getNameOfRenter());
        assertEquals(list.getFiltered(1).getNameOfRenter(), list.getUnfiltered(0)
                .getNameOfRenter());
        assertEquals(list.getFiltered(2).getNameOfRenter(), list.getUnfiltered(1)
                .getNameOfRenter());
        assertEquals(list.getFiltered(3).getNameOfRenter(), list.getUnfiltered(3)
                .getNameOfRenter());

    }

    /******************************************************************
     * Tests for correct filtering & sorting in LateRentals screen.
     *******************************************************************/
    @Test
    public void testLateRentals() {
        ListModel list = new ListModel();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();

        try {
            Date d1 = df.parse("1/20/2010");
            g1.setTime(d1);
            Date d2 = df.parse("1/27/2010");
            g2.setTime(d2);
            Date d3 = df.parse("2/07/2010");
            g3.setTime(d3);
            Date d4 = df.parse("8/02/2019");
            g4.setTime(d4);
            Date d5 = df.parse("8/09/2019");
            g5.setTime(d5);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Game game1 = new Game("Karin", g1, g2, null,
                "Animal Crossing", ConsoleTypes.NintendoSwitch);
        Game game2 = new Game("David", g2, g3, null,
                "Overwatch", ConsoleTypes.PlayStation4Pro);
        Game game3 = new Game("Daniel", g2, g3, null,
                "Titanfall", null);
        Console console1 = new Console("Ella", g1, g5,
                null, ConsoleTypes.PlayStation4Pro);
        Console console2 = new Console("Anna", g4, g5,
                null, ConsoleTypes.PlayStation4Pro);

        list.add(game1); //should be sorted first
        list.add(game2); //should be sorted last and after "Daniel"
        list.add(game3); //should be sorted first and before "David"
        list.add(console1); //should be sorted last and after "Anna"
        list.add(console2); //should be sorted last and before "Ella"

        list.setDisplayForTesting(ScreenDisplay.LateRentals);
        list.updateScreen();

        assertEquals(list.getFilteredLength(), 5);
        assertEquals(list.getFiltered(0).getNameOfRenter(), list.getUnfiltered(0)
                .getNameOfRenter());
        assertEquals(list.getFiltered(1).getNameOfRenter(), list.getUnfiltered(2)
                .getNameOfRenter());
        assertEquals(list.getFiltered(2).getNameOfRenter(), list.getUnfiltered(1)
                .getNameOfRenter());
        assertEquals(list.getFiltered(3).getNameOfRenter(), list.getUnfiltered(4)
                .getNameOfRenter());
        assertEquals(list.getFiltered(4).getNameOfRenter(), list.getUnfiltered(3)
                .getNameOfRenter());
    }
}
