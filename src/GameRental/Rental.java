package GameRental;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public abstract class
Rental implements Serializable {

    /** What is the purpose of this variable (SEARCH GOOGLE!!!) */
    private static final long serialVersionUID = 1L;

    /** The Name of person that is reserving the Rental*/
    protected String nameOfRenter;

    /** The date the Rental was rented on */
    protected GregorianCalendar rentedOn;

    /** The date the Rental was dueBack on */
    protected GregorianCalendar dueBack;

    /** The actual date the Rental was returned on */
    protected GregorianCalendar actualDateReturned;

    public Rental() {
    }

    public abstract double getCost(GregorianCalendar checkOut);

    public Rental(String nameOfRenter,
                  GregorianCalendar rentedOn,
                  GregorianCalendar dueBack,
                  GregorianCalendar actualDateReturned) {
        this.setNameOfRenter(nameOfRenter);
        this.setRentedOn(rentedOn);
        this.setDueBack(dueBack);
        this.setActualDateReturned(actualDateReturned);
    }

    public String getNameOfRenter() {
        return nameOfRenter;
    }

    public void setNameOfRenter(String nameOfRenter) {
        if (nameOfRenter == null)
            throw new IllegalArgumentException("setNameOfRenter in Rental: nameOfRenter equals null");
        if (nameOfRenter.length() == 0)
            throw new IllegalArgumentException("setNameOfRenter in Rental: String length 0");

        this.nameOfRenter = nameOfRenter;
    }

    public GregorianCalendar getRentedOn() {
        return rentedOn;
    }

    public void setRentedOn(GregorianCalendar rentedOn) {
        if (rentedOn == null)
            throw new IllegalArgumentException("setRentedOn in Rental: rentedOn equals null");
//        if (this.dueBack != null) {
//            if (this.rentedOn.compareTo(dueBack) == 1) {
//                throw new IllegalArgumentException("setRentedOn in Rental: dueBack comes before rentedOn");
//            }
//        }

        this.rentedOn = rentedOn;
    }

    public GregorianCalendar getActualDateReturned() {
        return actualDateReturned;
    }

    public void setActualDateReturned(GregorianCalendar actualDateReturned) {
        if (this.rentedOn != null && actualDateReturned != null) {
            if (this.rentedOn.compareTo(actualDateReturned) == 1) {
                throw new IllegalArgumentException("setDueBack in Rental: actualDateReturned comes before rentedOn");
            }
        }

        this.actualDateReturned = actualDateReturned;
    }

    public GregorianCalendar getDueBack() {
        return dueBack;
    }

    public void setDueBack(GregorianCalendar dueBack) {
        if (dueBack == null)
            throw new IllegalArgumentException("setDueBack w GC param in in Rental: equals null");
//        if (this.rentedOn != null) {
//            if (this.rentedOn.compareTo(dueBack) == 1) {
//                throw new IllegalArgumentException("setDueBack in Rental: dueBack comes before rentedOn");
//            }
//        }

        this.dueBack = dueBack;
    }

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

        String acutaulDateReturnedStr;
        if (getActualDateReturned() == null)
            acutaulDateReturnedStr = "";
        else
            acutaulDateReturnedStr = formatter.format(getActualDateReturned().getTime());

        return "RentUnit{" +
                "guestName='" + nameOfRenter + ' ' +
                ", rentedOn =" + rentedOnStr +
                ", dueBack =" + estdueBackStr +
                ", actualDateReturned =" + acutaulDateReturnedStr +
                '}';
    }
}
