package LinkedListProject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/*****************************************************************
 * A Rental Program
 *
 * @author Anna Carvalho
 * @version 04/13/21
 *****************************************************************/

public abstract class
Rental implements Serializable , Comparable<Rental> {

    /** What is the purpose of this variable (search google) */
    private static final long serialVersionUID = 1L;

    /** The Name of person that is reserving the Rental*/
    protected String nameOfRenter;

    /** The date the Rental was rented on */
    protected GregorianCalendar rentedOn;

    /** The date the Rental was dueBack on */
    protected GregorianCalendar dueBack;

    /** The actual date the Rental was returned on */
    protected GregorianCalendar actualDateReturned;

    /** A parameterless constructor */
    public Rental() {
    }

    /******************************************************************
     * An abstract method that calculates the cost of a rental.
     *
     * @param checkOut - represents the date checked out.
     *
     * @return the cost.
     *******************************************************************/
    public abstract double getCost(GregorianCalendar checkOut);

    /******************************************************************
     * A constructor that initializes the instance variables with
     * the provided values.
     *
     * @param nameOfRenter - represents name of renter.
     * @param rentedOn - represents the date rented on.
     * @param dueBack - represents the date due back.
     * @param actualDateReturned - represents the actual date returned.
     *******************************************************************/
    public Rental(String nameOfRenter,
                  GregorianCalendar rentedOn,
                  GregorianCalendar dueBack,
                  GregorianCalendar actualDateReturned) {
        this.nameOfRenter = nameOfRenter;
        this.rentedOn = rentedOn;
        this.dueBack = dueBack;
        this.actualDateReturned = actualDateReturned;
    }

    /******************************************************************
     * A method that returns the renter's name.
     *
     * @return nameOfRenter.
     *******************************************************************/
    public String getNameOfRenter() {
        return nameOfRenter;
    }

    /******************************************************************
     * A method that sets the instance variable nameOfRenter to the
     * provided String value.
     *
     * @param nameOfRenter - represents the name of the renter.
     *******************************************************************/
    public void setNameOfRenter(String nameOfRenter) {
        this.nameOfRenter = nameOfRenter;
    }

    /******************************************************************
     * A method that returns date rented on.
     *
     * @return rentedOn.
     *******************************************************************/
    public GregorianCalendar getRentedOn() {
        return rentedOn;
    }

    /******************************************************************
     * A method that sets the instance variable rentedOn to the
     * provided GregorianCalendar value.
     *
     * @param rentedOn - represents the date rented on.
     *******************************************************************/
    public void setRentedOn(GregorianCalendar rentedOn) {
        this.rentedOn = rentedOn;
    }

    /******************************************************************
     * A method that returns the actual date returned.
     *
     * @return actualDateReturned.
     *******************************************************************/
    public GregorianCalendar getActualDateReturned() {
        return actualDateReturned;
    }

    /******************************************************************
     * A method that sets the instance variable actualDateReturned to the
     * provided GregorianCalendar value.
     *
     * @param actualDateReturned - represents the actual date returned.
     *******************************************************************/
    public void setActualDateReturned(GregorianCalendar actualDateReturned) {
        this.actualDateReturned = actualDateReturned;
    }

    /******************************************************************
     * A method that returns date dueBack.
     *
     * @return dueBack.
     *******************************************************************/
    public GregorianCalendar getDueBack() {
        return dueBack;
    }

    /******************************************************************
     * A method that sets the instance variable dueBack to the provided
     * GregorianCalendar value.
     *
     * @param dueBack - represents the date due back.
     *******************************************************************/
    public void setDueBack(GregorianCalendar dueBack) {
        this.dueBack = dueBack;
    }

    /******************************************************************
     * A method that returns a string that represents a Rental with
     * the following format:  "RentUnit{guestName='[nameOfRenter] ,
     * rentedOn =[rentedOnStr], dueBack =[estDueBackStr],
     * actualDateReturned =[actualDateReturnedStr]}"
     *
     * @return A string value
     *******************************************************************/
    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        String rentedOnStr;
        if (getRentedOn() == null)
            rentedOnStr = "";
        else
            rentedOnStr = formatter.format(getRentedOn().getTime());

        String estdueBackStr;
        if (getDueBack() == null)
            estdueBackStr = "";
        else
            estdueBackStr = formatter.format(getDueBack().getTime());

        String actualDateReturnedStr;
        if (getActualDateReturned() == null)
            actualDateReturnedStr = "";
        else
            actualDateReturnedStr = formatter.format(getActualDateReturned().getTime());

        return "RentUnit{" +
                "guestName='" + nameOfRenter + ' ' +
                ", rentedOn =" + rentedOnStr +
                ", dueBack =" + estdueBackStr +
                ", actualDateReturned =" + actualDateReturnedStr +
                '}';
    }

    /******************************************************************
     * Method to compare two rental objects based on: Type game vs type
     * console; due date; renter's name.
     *
     * @param rental - represents a Rental object.
     *
     * @return -1 if this comes before rental.
     *          1 if this comes after rental.
     *          0 if this is equal to rental.
     *******************************************************************/
    @Override
    public int compareTo(Rental rental) {
        if (this instanceof Game && rental instanceof Console)
                return -1;

        if (this instanceof Console && rental instanceof Game)
                return 1;

        if (this.dueBack.equals(rental.dueBack))
            return this.nameOfRenter.compareToIgnoreCase(rental.nameOfRenter);

        return this.dueBack.compareTo(rental.dueBack);
    }
}
