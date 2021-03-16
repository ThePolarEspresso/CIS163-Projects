package GameRental;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

/*****************************************************************
 * A class to sort and display rental objects
 *
 * @author Anna Carvalho
 * @version 03/16/21
 *****************************************************************/

public class ListModel extends AbstractTableModel {
    /******************************************************************
     * SimpleDateFormat object with format "MM/DD/YYYY"
     *******************************************************************/
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    /******************************************************************
     * Represents current date.
     *******************************************************************/
    private GregorianCalendar currentDate = new GregorianCalendar();

    /******************************************************************
     * holds all the rentals.
     *******************************************************************/
    private ArrayList<Rental> listOfRentals;

    /******************************************************************
     * holds only the rentals that are to be displayed.
     *******************************************************************/
    private ArrayList<Rental> filteredListRentals;

    /******************************************************************
     * current screen being displayed.
     *******************************************************************/
    private ScreenDisplay display = ScreenDisplay.CurrentRentalStatus;

    /******************************************************************
     * Column titles for CurrentRentalStatus, DueWithInWeek,
     * DueWithinWeekGamesFirst, & Cap14DaysOverDue screen.
     *******************************************************************/
    private String[] columnNamesCurrentRentals = {"Renter\'s Name", "Est. Cost",
            "Rented On", "Due Date ", "Console", "Name of the Game"};

    /******************************************************************
     * Column titles for ReturnedItems screen.
     *******************************************************************/
    private String[] columnNamesReturned = {"Renter\'s Name", "Rented On Date",
            "Due Date", "Actual date returned ", "Est. Cost", " Real Cost"};

    /******************************************************************
     * Column titles for Everything screen.
     *******************************************************************/
    private String[] columnEverything = {"Renter\'s Name", "Rented On Date",
            "Due Date", "Actual date returned ", "Est. Cost", " Real Cost",
            "Console", "Name of Game"};

    /******************************************************************
     * Column titles for lateRentals screen.
     *******************************************************************/
    private String[] lateRentals = {"Renter\'s Name", "Est. Cost",
            "Rented On", "Number of Days Late", "Due Date ", "Console",
            "Name of the Game"};

    /******************************************************************
     * public method that returns listOfRentals.
     *
     * @return listOfRentals.
     *******************************************************************/
    public ArrayList<Rental> getListOfRentals() {
        return listOfRentals;
    }

    /******************************************************************
     * public method that returns filteredListOfRentals.
     *
     * @return filteredListOfRentals.
     *******************************************************************/
    public ArrayList<Rental> getFilteredListRentals() {
        return filteredListRentals;
    }

    /******************************************************************
     * Default constructor.
     *******************************************************************/
    public ListModel() {
        display = ScreenDisplay.CurrentRentalStatus;
        listOfRentals = new ArrayList<>();
        filteredListRentals = new ArrayList<>();
        updateScreen();
    }

    /******************************************************************
     * Public method to set the display (for testing).
     *
     * @param selected - the selected ScreenDisplay option.
     *******************************************************************/
    public void setDisplayForTesting(ScreenDisplay selected) {
        display = selected;
    }

    /******************************************************************
     * Public method to set the display.
     *
     * @param selected - the selected ScreenDisplay option.
     *******************************************************************/
    public void setDisplay(ScreenDisplay selected) {
        display = selected;
        updateScreen();
    }

    /******************************************************************
     * Public method to display rentals filtered and sorted based on
     * the display value.
     *******************************************************************/
    public void updateScreen() {
        switch (display) {
            /******************************************************************
             * Displays rentals that have not yet been returned in alphabetical
             * order.
             *******************************************************************/
            case CurrentRentalStatus:
                filteredListRentals = (ArrayList<Rental>) listOfRentals.stream()
                        .filter(n -> n.actualDateReturned == null)
                        .collect(Collectors.toList());

                Collections.sort(filteredListRentals, (n1, n2) ->
                        n1.nameOfRenter.toLowerCase().compareTo(n2.nameOfRenter.toLowerCase()));
                break;

            /******************************************************************
             * Displays rentals that have been returned in alphabetical order.
             *******************************************************************/
            case ReturnedItems:
                filteredListRentals = (ArrayList<Rental>) listOfRentals.stream()
                        .filter(n -> n.actualDateReturned != null)
                        .collect(Collectors.toList());

                Collections.sort(filteredListRentals, (n1, n2) ->
                        n1.nameOfRenter.toLowerCase().compareTo(n2.nameOfRenter.toLowerCase()));
                break;

            /******************************************************************
             * Displays rentals that have not yet been returned with <= 7 days
             * between rentedOut date and dueDate in alphabetical order.
             *******************************************************************/
            case DueWithinWeek:
                filteredListRentals = (ArrayList<Rental>) listOfRentals.stream()
                        .filter(n -> n.actualDateReturned == null
                                && daysBetween(n.rentedOn, n.dueBack) <= 7)
                        .collect(Collectors.toList());

                Collections.sort(filteredListRentals, (n1, n2) ->
                        n1.nameOfRenter.toLowerCase().compareTo(n2.nameOfRenter.toLowerCase()));
                break;

            /******************************************************************
             * Displays rentals that have not yet been returned with <= 7 days
             * between rentedOut date and dueDate in alphabetical order; Displays
             * games before consoles.
             *******************************************************************/
            case DueWithinWeekGamesFirst:
                filteredListRentals = (ArrayList<Rental>) listOfRentals.stream()
                        .filter(n -> n.actualDateReturned == null
                                && daysBetween(n.rentedOn, n.dueBack) <= 7)
                        .collect(Collectors.toList());

                Collections.sort(filteredListRentals, (n1, n2) -> {
                    if (n1 instanceof Game) {
                        if (n2 instanceof Console)
                            return -1;
                    }
                    if (n1 instanceof Console) {
                        if (n2 instanceof Game)
                            return 1;
                    }

                    return n1.nameOfRenter.toLowerCase().compareTo(n2.nameOfRenter.toLowerCase());
                });
                break;

            /******************************************************************
             * Displays rentals that have not yet been returned with > 7 days
             * between rentedOut date and dueDate; Displays rentals that have >= 14
             * days between rentedOut date and dueDate first.
             *******************************************************************/
            case Cap14DaysOverdue:
                filteredListRentals = (ArrayList<Rental>) listOfRentals.stream()
                        .filter(n -> n.actualDateReturned == null
                                && daysBetween(n.rentedOn, n.dueBack) > 7)
                        .collect(Collectors.toList());

                Collections.sort(filteredListRentals, (n1, n2) -> {
                    if (daysBetween(n1.rentedOn, n1.dueBack) >= 14) {
                        if (daysBetween(n2.rentedOn, n2.dueBack) < 14)
                            return -1;
                    }
                    if (daysBetween(n1.rentedOn, n1.dueBack) < 14) {
                        if (daysBetween(n2.rentedOn, n2.dueBack) >= 14)
                            return 1;
                    }
                    return n1.nameOfRenter.toLowerCase().compareTo(n2.nameOfRenter.toLowerCase());
                });
                break;

            /******************************************************************
             * Displays rentals that are overdue from most days late to least
             * and in alphabetical order; Displays games before consoles.
             *******************************************************************/
            case LateRentals:
                filteredListRentals = (ArrayList<Rental>) listOfRentals.stream()
                        .filter(n -> n.actualDateReturned == null
                                && daysBetween(n.dueBack, currentDate) > 0)
                        .collect(Collectors.toList());

                Collections.sort(filteredListRentals, (n1, n2) -> {
                    if (n1 instanceof Game) {
                        if (n2 instanceof Console)
                            return -1;
                    }
                    if (n1 instanceof Console) {
                        if (n2 instanceof Game)
                            return 1;
                    }
                    if (daysBetween(n1.dueBack, currentDate)
                            > daysBetween(n2.dueBack, currentDate))
                        return -1;
                    if (daysBetween(n1.dueBack, currentDate)
                            < daysBetween(n2.dueBack, currentDate))
                        return 1;

                    return n1.nameOfRenter.toLowerCase().compareTo(n2.nameOfRenter.toLowerCase());
                });
                break;

            /******************************************************************
             * Displays all rentals in alphabetical order.
             *******************************************************************/
            case Everything:
                filteredListRentals = (ArrayList<Rental>) listOfRentals.stream()
                        .collect(Collectors.toList());

                Collections.sort(filteredListRentals, (n1, n2) ->
                        n1.nameOfRenter.toLowerCase().compareTo(n2.nameOfRenter.toLowerCase()));
                break;

            default:
                throw new RuntimeException("upDate is in undefined state: "
                        + display);
        }
        fireTableStructureChanged();
    }

    /******************************************************************
     * Private helper method to count the number of days between two
     * GregorianCalendar dates.
     * Note that this is the proper way to do this; trying to use other
     * classes/methods likely won't properly account for leap days.
     *
     * @param startDate - the beginning/starting day.
     * @param endDate - the last/ending day.
     *
     * @return int for the number of days between startDate and endDate.
     *******************************************************************/
    private int daysBetween(GregorianCalendar startDate,
                            GregorianCalendar endDate) {
        GregorianCalendar gTemp = new GregorianCalendar();
        gTemp = (GregorianCalendar) endDate.clone();
        int daysBetween = 0;
        while (gTemp.compareTo(startDate) > 0) {
            gTemp.add(Calendar.DATE, -1);
            daysBetween++;
        }
        return daysBetween;
    }

    /******************************************************************
     * Public method that returns the value of the input col based on
     * display value.
     *
     * @param col - the number of columns.
     *
     * @return the value in the specified column.
     *******************************************************************/
    @Override
    public String getColumnName(int col) {
        switch (display) {
            case CurrentRentalStatus:
                return columnNamesCurrentRentals[col];
            case ReturnedItems:
                return columnNamesReturned[col];
            case DueWithinWeek:
                return columnNamesCurrentRentals[col];
            case Cap14DaysOverdue:
                return columnNamesCurrentRentals[col];
            case DueWithinWeekGamesFirst:
                return columnNamesCurrentRentals[col];
            case Everything:
                return columnEverything[col];
            case LateRentals:
                return lateRentals[col];
        }
        throw new RuntimeException("Undefined state for Col Names: " + display);
    }

    /******************************************************************
     * Public method that returns the length of the selected screen
     * display.
     *
     * @return the length of selected display.
     *******************************************************************/
    @Override
    public int getColumnCount() {
        switch (display) {
            case CurrentRentalStatus:
                return columnNamesCurrentRentals.length;
            case ReturnedItems:
                return columnNamesReturned.length;
            case DueWithinWeek:
                return columnNamesCurrentRentals.length;
            case Cap14DaysOverdue:
                return columnNamesCurrentRentals.length;
            case DueWithinWeekGamesFirst:
                return columnNamesCurrentRentals.length;
            case Everything:
                return columnEverything.length;
            case LateRentals:
                return lateRentals.length;
        }
        throw new IllegalArgumentException();
    }

    /******************************************************************
     * Method to return the number of items in the arraylist.
     *
     * @return number of items in the arraylist.
     *******************************************************************/
    @Override
    public int getRowCount() { return filteredListRentals.size(); }

    /******************************************************************
     * Method to return the value of the row, col in the selected display.
     *
     * @param row - represents the row number.
     * @param col - represents the column number.
     *
     * @return the value at the row, col in the selected display.
     *******************************************************************/
    @Override
    public Object getValueAt(int row, int col) {
        switch (display) {
            case CurrentRentalStatus:
                return currentRentScreen(row, col);
            case ReturnedItems:
                return rentedOutScreen(row, col);
            case DueWithinWeek:
                return currentRentScreen(row, col);
            case Cap14DaysOverdue:
                return currentRentScreen(row, col);
            case DueWithinWeekGamesFirst:
                return currentRentScreen(row, col);
            case Everything:
                return everythingScreen(row, col);
            case LateRentals:
                return lateRentalsScreen(row, col);
        }
        throw new IllegalArgumentException();
    }

    /******************************************************************
     * Method to display rental information for the CurrentRentalStatus,
     * DueWithInWeek, DueWithinWeekGamesFirst, & Cap14DaysOverDue screen.
     *
     * @param row - represents the row number.
     * @param col - represents the column number.
     *
     * @return the value to display in row, col.
     *******************************************************************/
    private Object currentRentScreen(int row, int col) {
        switch (col) {
            case 0:
                if (display == ScreenDisplay.Cap14DaysOverdue) {
                    if (daysBetween(filteredListRentals.get(row).rentedOn,
                            filteredListRentals.get(row).dueBack) >= 14)
                        return (filteredListRentals.get(row).nameOfRenter
                                .toUpperCase());
                    return (filteredListRentals.get(row).nameOfRenter);
                }

                return (filteredListRentals.get(row).nameOfRenter);

            case 1:
                return (filteredListRentals.get(row).getCost(filteredListRentals.
                        get(row).dueBack));

            case 2:
                return (formatter.format(filteredListRentals.get(row)
                        .rentedOn.getTime()));

            case 3:
                if (filteredListRentals.get(row).dueBack == null)
                    return "-";

                return (formatter.format(filteredListRentals.get(row)
                        .dueBack.getTime()));

            case 4:
                if (filteredListRentals.get(row) instanceof Console)
                    return (((Console) filteredListRentals.get(row))
                            .getConsoleType());
                else {
                    if (filteredListRentals.get(row) instanceof Game)
                        if (((Game) filteredListRentals.get(row)).getConsole()
                                != null)
                            return ((Game) filteredListRentals.get(row))
                                    .getConsole();
                        else
                            return "-";
                }

            case 5:
                if (filteredListRentals.get(row) instanceof Game)
                    return (((Game) filteredListRentals.get(row)).getNameGame());
                else
                    return "-";
            default:
                throw new RuntimeException("Row,col out of range: " + row
                        + " " + col);
        }
    }

    /******************************************************************
     * Method to display rental information for the ReturnedItems
     * screen.
     *
     * @param row - represents the row number.
     * @param col - represents the column number.
     *
     * @return the value to display in row, col.
     *******************************************************************/
    private Object rentedOutScreen(int row, int col) {
        switch (col) {
            case 0:
                return (filteredListRentals.get(row).nameOfRenter);

            case 1:
                return (formatter.format(filteredListRentals.get(row).rentedOn.
                        getTime()));
            case 2:
                return (formatter.format(filteredListRentals.get(row).dueBack.
                        getTime()));
            case 3:
                return (formatter.format(filteredListRentals.get(row).
                        actualDateReturned.getTime()));

            case 4:
                return (filteredListRentals.
                        get(row).getCost(filteredListRentals.get(row).dueBack));

            case 5:
                return (filteredListRentals.
                        get(row).getCost(filteredListRentals.get(row).
                        actualDateReturned
                ));

            default:
                throw new RuntimeException("Row,col out of range: " + row
                        + " " + col);
        }
    }

    /******************************************************************
     * Method to display rental information for the LateRentals screen.
     *
     * @param row - represents the row number.
     * @param col - represents the column number.
     *
     * @return the value to display in row, col.
     *******************************************************************/

    private Object lateRentalsScreen(int row, int col) {
        switch (col) {
            case 0:
                return (filteredListRentals.get(row).nameOfRenter);

            case 1:
                return (filteredListRentals.get(row).getCost(filteredListRentals.
                        get(row).dueBack));

            case 2:
                return (formatter.format(filteredListRentals.get(row).rentedOn
                        .getTime()));

            case 3:
                return daysBetween(filteredListRentals.get(row).dueBack,
                        currentDate);

            case 4:
                if (filteredListRentals.get(row).dueBack == null)
                    return "-";

                return (formatter.format(filteredListRentals.get(row).dueBack
                        .getTime()));

            case 5:
                if (filteredListRentals.get(row) instanceof Console)
                    return (((Console) filteredListRentals.get(row))
                            .getConsoleType());
                else {
                    if (filteredListRentals.get(row) instanceof Game)
                        if (((Game) filteredListRentals.get(row)).getConsole()
                                != null)
                            return ((Game) filteredListRentals.get(row))
                                    .getConsole();
                        else
                            return "-";
                }

            case 6:
                if (filteredListRentals.get(row) instanceof Game)
                    return (((Game) filteredListRentals.get(row)).getNameGame());
                else
                    return "-";
            default:
                throw new RuntimeException("Row,col out of range: " + row
                        + " " + col);
        }
    }

    /******************************************************************
     * Method to display rental information for the Everything screen.
     *
     * @param row - represents the row number.
     * @param col - represents the column number.
     *
     * @return the value to display in row, col.
     *******************************************************************/
    private Object everythingScreen(int row, int col) {
        switch (col) {
            case 0:
                return (filteredListRentals.get(row).nameOfRenter);

            case 1:
                return (formatter.format(filteredListRentals.get(row).rentedOn.
                        getTime()));

            case 2:
                return (formatter.format(filteredListRentals.get(row).dueBack.
                        getTime()));

            case 3:
                if (filteredListRentals.get(row).actualDateReturned == null)
                    return "-";

                return (formatter.format(filteredListRentals.get(row).
                        actualDateReturned.getTime()));

            case 4:
                return (filteredListRentals.get(row).getCost(filteredListRentals.
                        get(row).dueBack));

            case 5:
                if (filteredListRentals.get(row).actualDateReturned == null)
                    return "-";

                return (filteredListRentals.
                        get(row).getCost(filteredListRentals.get(row).
                        actualDateReturned
                ));

            case 6:
                if (filteredListRentals.get(row) instanceof Console)
                    return (((Console) filteredListRentals.get(row))
                            .getConsoleType());
                else {
                    if (filteredListRentals.get(row) instanceof Game)
                        if (((Game) filteredListRentals.get(row)).getConsole()
                                != null)
                            return ((Game) filteredListRentals.get(row))
                                    .getConsole();
                        else
                            return "-";
                }

            case 7:
                if (filteredListRentals.get(row) instanceof Game)
                    return (((Game) filteredListRentals.get(row))
                            .getNameGame());
                else
                    return "-";

            default:
                throw new RuntimeException("Row,col out of range: " + row
                        + " " + col);
        }
    }

    /******************************************************************
     * Public method to add a rental object to listOfRentals.
     *
     * @param a - represents a rental object.
     *******************************************************************/
    public void add(Rental a) {
        listOfRentals.add(a);
        updateScreen();
    }

    /******************************************************************
     * Public method to get a rental from index i from filteredListRentals.
     *
     * @param i - represents the index.
     *
     * @return the rental object at index i.
     *******************************************************************/
    public Rental getFiltered(int i) { return filteredListRentals.get(i); }

    /******************************************************************
     * Public method to get a rental from index i from listOfRentals.
     *
     * @param i - represents the index.
     *
     * @return the rental object at index i.
     *******************************************************************/
    public Rental getUnfiltered(int i) {
        return listOfRentals.get(i);
    }

    /******************************************************************
     * Public method to get the size of filterListRentals.
     *
     * @return the size of filteredListRentals.
     *******************************************************************/
    public int getFilteredLength() { return filteredListRentals.size(); }

    /******************************************************************
     * Public method to update the screen.
     *
     * @param index - represents the index.
     * @param unit - represents the rental object.
     *******************************************************************/
    public void update(int index, Rental unit) {
        updateScreen();
    }

    /******************************************************************
     * A method that saves a rental database to a file.
     *
     * @param filename is the input string that represents the file name.
     *
     * @throws RuntimeException
     * @throws IllegalArgumentException if filename is null or empty.
     *******************************************************************/
    public void saveDatabase(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("filename equals null.");
        if (filename.equals(""))
            throw new IllegalArgumentException("filename is empty.");

        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            System.out.println(listOfRentals.toString());
            os.writeObject(listOfRentals);
            os.close();
        } catch (IOException ex) {
            throw new RuntimeException("Saving problem! " + display);
        }
    }

    /******************************************************************
     * A method that loads a rental database from a file.
     *
     * @param filename is the input string that represents the file name.
     *
     * @throws RuntimeException
     *******************************************************************/
    public void loadDatabase(String filename) {
        listOfRentals.clear();

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream is = new ObjectInputStream(fis);

            listOfRentals = (ArrayList<Rental>) is.readObject();
            updateScreen();
            is.close();
        } catch (Exception ex) {
            throw new RuntimeException("Loading problem: " + display);

        }
    }

    /******************************************************************
     * A method that saves a rental database as a text file.
     *
     * @param filename is the input string that represents the file name.
     *
     * @throws RuntimeException
     * @throws IllegalArgumentException if filename is null or empty.
     *******************************************************************/
    public boolean saveAsText(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("filename equals null.");
        if (filename.equals(""))
            throw new IllegalArgumentException("filename is empty.");

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter(filename)));
            out.println(listOfRentals.size());
            for (int i = 0; i < listOfRentals.size(); i++) {
                Rental unit = listOfRentals.get(i);
                out.println(unit.getClass().getName());
                out.println("Name is " + unit.getNameOfRenter());
                out.println("Rented on " + formatter.format(unit.rentedOn
                        .getTime()));
                out.println("DueDate " + formatter.format(unit.dueBack
                        .getTime()));

                if (unit.getActualDateReturned() == null)
                    out.println("Not returned!");
                else
                    out.println(formatter.format(unit.actualDateReturned
                            .getTime()));

                if (unit instanceof Game) {
                    out.println(((Game) unit).getNameGame());
                    if (((Game) unit).getConsole() != null)
                        out.println(((Game) unit).getConsole());
                    else
                        out.println("No Console");
                }

                if (unit instanceof Console)
                    out.println(((Console) unit).getConsoleType());
            }
            out.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /******************************************************************
     * A method that loads a rental database from a text file.
     *
     * @param filename is the input string that represents the file name.
     *
     * @throws IllegalArgumentException if there is an invalid input.
     *******************************************************************/
    public void loadFromText(String filename) {
        listOfRentals.clear();

        try {
            Scanner scanner = new Scanner(new File(filename));
            int count = Integer.parseInt(scanner.nextLine().trim());


            Game game;
            Console console;

            for (int i = 0; i < count; i++) {

                String rentalType = scanner.nextLine().trim().substring(11)
                        .toLowerCase();

                if (!rentalType.equals("game") && !rentalType.equals("console"))
                    throw new IllegalArgumentException("Invalid rental type");

                String renterName = scanner.nextLine().trim().substring(8);

                if (renterName == null || renterName.equals(""))
                    throw new IllegalArgumentException("Invalid renter name");

                String tempRentedOn = scanner.nextLine().trim().substring(10);
                String tempDueDate = scanner.nextLine().trim().substring(8);
                String tempDateReturned = scanner.nextLine().trim();

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

                GregorianCalendar rentedOn = new GregorianCalendar();
                GregorianCalendar dueBack = new GregorianCalendar();
                GregorianCalendar dateReturned = new GregorianCalendar();

                try {
                    Date rentedOnTemp = df.parse(tempRentedOn);
                    rentedOn.setTime(rentedOnTemp);

                    Date dueBackTemp = df.parse(tempDueDate);
                    dueBack.setTime(dueBackTemp);

                    if (!tempDateReturned.equals("Not returned!")) {
                        Date dateReturnedTemp = df.parse(tempDueDate);
                        dateReturned.setTime(dateReturnedTemp);
                    } else {
                        dateReturned = null;
                    }

                } catch (ParseException e1) {
                    System.out.println("date not in format of MM/DD/YYYY");
                } catch (IllegalArgumentException e1) {
                    throw new IllegalArgumentException("Problem with dates for "
                            + renterName + ", " + rentalType);
                }

                String gameName = "";
                if (rentalType.equals("game")) {
                    gameName = scanner.nextLine().trim();
                    if (gameName.equals(""))
                        throw new IllegalArgumentException("Invalid game name for "
                                + renterName + ", " + rentalType);
                }

                String tempConsole = scanner.nextLine().trim();

                ConsoleTypes consoleType;
                switch (tempConsole) {
                    case "No Console":
                        consoleType = ConsoleTypes.NoSelection; break;
                    case "PlayStation4":
                        consoleType = ConsoleTypes.PlayStation4; break;
                    case "XBoxOneS":
                        consoleType = ConsoleTypes.XBoxOneS; break;
                    case "PlayStation4Pro":
                        consoleType = ConsoleTypes.PlayStation4Pro; break;
                    case "NintendoSwitch":
                        consoleType = ConsoleTypes.NintendoSwitch; break;
                    case "SegaGenesisMini":
                        consoleType = ConsoleTypes.SegaGenesisMini; break;
                    default:
                        throw new IllegalArgumentException("No correct console for "
                                + renterName + ", " + rentalType);
                }

                if (rentalType.equals("game")) {
                    game = new Game(renterName, rentedOn, dueBack, dateReturned,
                            gameName, consoleType);
                    listOfRentals.add(game);
                }

                if (rentalType.equals("console")) {
                    console = new Console(renterName, rentedOn, dueBack,
                            dateReturned, consoleType);
                    listOfRentals.add(console);
                }

            }
        } catch (IOException ex) {
            throw new RuntimeException("Loading problem: " + display);
        }
        updateScreen();
    }

    /******************************************************************
     * A method that generates rental objects.
     *
     * @throws RuntimeException if the date format is invalid.
     *******************************************************************/
    public void createList() {
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
            Date d1 = df.parse("1/20/2020");
            g1.setTime(d1);
            Date d2 = df.parse("12/22/2020");
            g2.setTime(d2);
            Date d3 = df.parse("12/20/2019");
            g3.setTime(d3);
            Date d4 = df.parse("7/02/2020");
            g4.setTime(d4);
            Date d5 = df.parse("1/20/2010");
            g5.setTime(d5);
            Date d6 = df.parse("9/29/2020");
            g6.setTime(d6);
            Date d7 = df.parse("7/25/2020");
            g7.setTime(d7);
            Date d8 = df.parse("7/29/2020");
            g8.setTime(d8);

            Console console1 = new Console("Anna", g4, g6,
                    null, ConsoleTypes.PlayStation4);
            Console console2 = new Console("Chris", g5, g3,
                    null, ConsoleTypes.PlayStation4);
            Console console3 = new Console("Josh", g4, g8,
                    null, ConsoleTypes.SegaGenesisMini);
            Console console4 = new Console("Karin", g4, g7,
                    null, ConsoleTypes.SegaGenesisMini);
            Console console5 = new Console("David", g5, g4,
                    g3, ConsoleTypes.XBoxOneS);

            Game game1 = new Game("Sanders", g3, g2,
                    null, "title1", ConsoleTypes.PlayStation4);
            Game game2 = new Game("Michael", g3, g1,
                    null, "title2", ConsoleTypes.PlayStation4);
            Game game3 = new Game("Hayes", g5, g3,
                    null, "title2", ConsoleTypes.SegaGenesisMini);
            Game game4 = new Game("Rivers", g4, g8,
                    null, "title2", ConsoleTypes.PlayStation4Pro);
            Game game5 = new Game("DJ", g3, g1,
                    g1, "aaa", ConsoleTypes.XBoxOneS);
            Game game6 = new Game("Mac", g4, g7,
                    null, "title1", ConsoleTypes.NintendoSwitch);
            Game game7 = new Game("Sullivan", g4, g8,
                    null, "title1", ConsoleTypes.NintendoSwitch);

            add(game1);
            add(game4);
            add(game5);
            add(game2);
            add(game3);
            add(game6);
            add(game7);

            add(console1);
            add(console2);
            add(console5);
            add(console3);
            add(console4);

            // create a bunch of them.
            int count = 0;
            Random rand = new Random(13);
            String guest = null;

            while (count < 30) {
                Date date = df.parse("7/"
                        + (rand.nextInt(10) + 2) + "/2020");
                GregorianCalendar g = new GregorianCalendar();
                g.setTime(date);
                if (rand.nextBoolean()) {
                    guest = "Game" + rand.nextInt(5);
                    Game game;
                    if (count % 2 == 0)
                        game = new Game(guest, g4, g, null,
                                "title2", ConsoleTypes.NintendoSwitch);
                    else
                        game = new Game(guest, g4, g, null,
                                "title2", null);
                    add(game);


                } else {
                    guest = "Console" + rand.nextInt(5);
                    date = df.parse("7/"
                            + (rand.nextInt(20) + 2) + "/2020");
                    g.setTime(date);
                    Console console = new Console(guest, g4, g,
                            null, getOneRandom(rand));
                    add(console);
                }

                count++;
            }
        } catch (ParseException e) {
            throw new RuntimeException("Error in testing, creation of list");
        }
    }

    /******************************************************************
     * A method that generates a random console type.
     *
     * @param rand represents a random object.
     *
     * @return Console type.
     *******************************************************************/
    public ConsoleTypes getOneRandom(Random rand) {
        int number = rand.nextInt(ConsoleTypes.values().length - 1);
        switch (number) {
            case 0:
                return ConsoleTypes.PlayStation4;
            case 1:
                return ConsoleTypes.XBoxOneS;
            case 2:
                return ConsoleTypes.PlayStation4Pro;
            case 3:
                return ConsoleTypes.NintendoSwitch;
            default:
                return ConsoleTypes.SegaGenesisMini;
        }
    }
}