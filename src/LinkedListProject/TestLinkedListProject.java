package LinkedListProject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;

/*****************************************************************
 * A program to test MySingleWithOutTailLinkedList
 *
 * @author Anna Carvalho
 * @version 04/13/21
 *****************************************************************/

public class TestLinkedListProject {
    /******************************************************************
     * Tests the add method in MySingleWithOutTailLinkedList.
     *******************************************************************/
    @Test
    public void testAddMethod() {
        MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();
        GregorianCalendar g7 = new GregorianCalendar();
        GregorianCalendar g8 = new GregorianCalendar();

        try {
            Date d1 = df.parse("01/01/2010");
            g1.setTime(d1);
            Date d2 = df.parse("01/02/2010");
            g2.setTime(d2);
            Date d3 = df.parse("02/02/2010");
            g3.setTime(d3);
            Date d4 = df.parse("02/02/2020");
            g4.setTime(d4);
            Date d5 = df.parse("02/03/2020");
            g5.setTime(d5);
            Date d6 = df.parse("02/04/2020");
            g6.setTime(d6);
            Date d7 = df.parse("05/04/2020");
            g7.setTime(d7);
            Date d8 = df.parse("06/04/2020");
            g8.setTime(d8);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

            Game game1 = new Game("Hayes", g1, g1,
                    null, "title1", null);
            Game game2 = new Game("Mac", g1, g1,
                    null, "title2", null);
            Game game3 = new Game("Sanders", g1, g5,
                    null, "title3", null);
            Game game4 = new Game("sullivan", g1, g4,
                    null, "title4", null);
            Game game5 = new Game("Peter", g1, g3,
                    null, "title5", null);

            Console console1 = new Console("Anna", g1, g5,
                    null, ConsoleTypes.PlayStation4);
            Console console2 = new Console("Chris", g1, g5,
                    null, ConsoleTypes.PlayStation4);
            Console console3 = new Console("David", g1, g8,
                    null, ConsoleTypes.SegaGenesisMini);
            Console console4 = new Console("Ella", g1, g7,
                    null, ConsoleTypes.SegaGenesisMini);
            Console console5 = new Console("Karin", g1, g6,
                    null, ConsoleTypes.XBoxOneS);

            /** Add items in random order */
            list.add(game5);
            list.add(console3);
            list.add(game3);
            list.add(console5);
            list.add(game2);
            list.add(console4);
            list.add(console2);
            list.add(game4);
            list.add(game1);
            list.add(console1);

            assertEquals(game1, list.get(0));
            assertEquals(game2, list.get(1));
            assertEquals(game5, list.get(2));
            assertEquals(game4, list.get(3));
            assertEquals(game3, list.get(4));
            assertEquals(console1, list.get(5));
            assertEquals(console2, list.get(6));
            assertEquals(console5, list.get(7));
            assertEquals(console4, list.get(8));
            assertEquals(console3, list.get(9));
    }

    /******************************************************************
     * Tests the add method in MySingleWithOutTailLinkedList with null
     * items.
     *******************************************************************/
    @Test (expected = NullPointerException.class)
    public void testAddNull() {
        MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();

        Game game1 = null;
        Game game2 = null;

        list.add(game1);
        list.add(game2); //breaks here
    }

    /******************************************************************
     * Tests the remove method in MySingleWithOutTailLinkedList.
     *******************************************************************/
    @Test
    public void testRemoveMethod() {
        MySingleWithOutTailLinkedList list = new MySingleWithOutTailLinkedList();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        GregorianCalendar g1 = new GregorianCalendar();
        GregorianCalendar g2 = new GregorianCalendar();
        GregorianCalendar g3 = new GregorianCalendar();
        GregorianCalendar g4 = new GregorianCalendar();
        GregorianCalendar g5 = new GregorianCalendar();
        GregorianCalendar g6 = new GregorianCalendar();
        GregorianCalendar g7 = new GregorianCalendar();
        GregorianCalendar g8 = new GregorianCalendar();

        try {
            Date d1 = df.parse("01/01/2010");
            g1.setTime(d1);
            Date d2 = df.parse("01/02/2010");
            g2.setTime(d2);
            Date d3 = df.parse("02/02/2010");
            g3.setTime(d3);
            Date d4 = df.parse("02/02/2020");
            g4.setTime(d4);
            Date d5 = df.parse("02/03/2020");
            g5.setTime(d5);
            Date d6 = df.parse("02/04/2020");
            g6.setTime(d6);
            Date d7 = df.parse("05/04/2020");
            g7.setTime(d7);
            Date d8 = df.parse("06/04/2020");
            g8.setTime(d8);
        } catch (ParseException e) {
            throw new IllegalArgumentException();
        }

        Game game1 = new Game("Hayes", g1, g1,
                null, "title1", null);
        Game game2 = new Game("Mac", g1, g1,
                null, "title2", null);
        Game game3 = new Game("Sanders", g1, g5,
                null, "title3", null);
        Game game4 = new Game("sullivan", g1, g4,
                null, "title4", null);
        Game game5 = new Game("Peter", g1, g3,
                null, "title5", null);

        Console console1 = new Console("Anna", g1, g5,
                null, ConsoleTypes.PlayStation4);
        Console console2 = new Console("Chris", g1, g5,
                null, ConsoleTypes.PlayStation4);
        Console console3 = new Console("David", g1, g8,
                null, ConsoleTypes.SegaGenesisMini);
        Console console4 = new Console("Ella", g1, g7,
                null, ConsoleTypes.SegaGenesisMini);
        Console console5 = new Console("Karin", g1, g6,
                null, ConsoleTypes.XBoxOneS);

        /** Remove from single-item list */
        list.add(game1);
        list.remove(0);
        assertEquals(list.size(), 0);

        /** Remove from multi-item list */
        list.add(game5);
        list.add(game3);
        list.add(game2);
        list.add(game4);
        list.add(game1);
        list.add(console5);
        list.add(console3);
        list.add(console1);
        list.add(console4);
        list.add(console2);

        list.remove(0); //game1
        list.remove(3); //game3
        list.remove(5); //console5
        list.remove(list.size() - 1); //console 3

        assertEquals(game2, list.get(0));
        assertEquals(game5, list.get(1));
        assertEquals(game4, list.get(2));
        assertEquals(console1, list.get(3));
        assertEquals(console2, list.get(4));
        assertEquals(console4, list.get(5));
    }
}
