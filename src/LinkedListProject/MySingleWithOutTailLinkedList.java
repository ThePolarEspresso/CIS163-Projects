package LinkedListProject;

import java.io.Serializable;
import java.util.Random;

/*****************************************************************
 * A linked list Program
 *
 * @author Anna Carvalho
 * @version 04/13/21
 *****************************************************************/

public class MySingleWithOutTailLinkedList implements Serializable
{
    /** A node object that represents the top of the list */
    private Node top;

    /** A parameterless constructor the sets top to null */
    public MySingleWithOutTailLinkedList() {
        top = null;
    }

    /******************************************************************
     * A method that calculates the size of the list.
     *
     * @return the size of the list.
     *******************************************************************/
    public int size() {
        if (top == null)
            return 0;

        int total = 0;
        Node temp = top;
        while (temp != null) {
            total++;
            temp = temp.getNext();
        }
        
        return total;
    }

    /******************************************************************
     * A method that clears a random number of list items.
     *******************************************************************/
    public void clear () {
        Random rand = new Random();
        while (size() > 0) {
            int number = rand.nextInt(size());
            remove(number);
        }
    }

    /******************************************************************
     * A method that adds a rental object to the list ordered by games
     * first, then due date, then alphabetically.
     *
     * @param rental - represents a rental object.
     *******************************************************************/
    public void add(Rental rental) {
        /** No list */
        if (top == null)
            top = new Node(rental, null);

        /** Add to top */
        else if (rental.compareTo(top.getData()) <= 0)
            top = new Node(rental, top);

        /** Add to middle or bottom */
        else {
            Node temp = top;

            while (temp.getNext() != null && rental.compareTo(temp.getNext().getData()) > 0)
                temp = temp.getNext();

            temp.setNext(new Node(rental, temp.getNext()));
        }


//        /** Alternative Solution */
//        /** No list */
//        if (top == null) {
//            top = new Node(rental, null);
//            return;
//        }
//
//        /** Rental goes on top */
//        if (rental instanceof Game && top.getData() instanceof Console) {
//            top = new Node(rental, top);
//            return;
//        }
//        if (rental instanceof Game && top.getData() instanceof Game ||
//                rental instanceof Console && top.getData() instanceof Console) {
//            if (rental.dueBack.before(top.getData().dueBack)) {
//                top = new Node(rental, top);
//                return;
//            }
//            if (rental.dueBack.equals(top.getData().dueBack)) {
//                if (rental.getNameOfRenter().compareToIgnoreCase(top.getData().getNameOfRenter()) <= 0) {
//                    top = new Node(rental, top);
//                    return;
//                }
//            }
//        }
//
//        /** Rental goes in middle */
//        Node temp = top;
//        while (temp.getNext() != null) {
//            if (rental instanceof Game && temp.getNext().getData() instanceof Console) {
//                temp.setNext(new Node(rental, temp.getNext()));
//                return;
//            }
//            if (rental instanceof Game && temp.getNext().getData() instanceof Game ||
//            rental instanceof Console && temp.getNext().getData() instanceof Console) {
//                if (rental.dueBack.before(temp.getNext().getData().dueBack)) {
//                    temp.setNext(new Node(rental, temp.getNext()));
//                    return;
//                }
//                if (rental.dueBack.equals(temp.getNext().getData().dueBack)) {
//                    if (rental.getNameOfRenter().compareToIgnoreCase(temp.getNext().getData().getNameOfRenter()) <= 0) {
//                        temp.setNext(new Node(rental, temp.getNext()));
//                        return;
//                    }
//                }
//            }
//            temp = temp.getNext();
//        }
//
//        /** Rental goes on bottom */
//        temp.setNext(new Node(rental, null));
    }

    /******************************************************************
     * A method that removes a rental object from the list.
     *
     * @param index - represents the index of the list
     *
     * @return the rental unit being removed.
     *******************************************************************/
    public Rental remove(int index) {
        /** No list */
    	if (top == null)
    	    return null;

        /** Remove top */
    	Node temp = top;
    	if (index == 0) {
    	    top = top.getNext();
    	    return temp.getData();
        }

        /** Remove from middle or bottom */
    	try {
            for (int i = 0; i < index - 1; i++)
                temp = temp.getNext();
        } catch (Exception e) {
    	    return null;
        }

    	Rental tRental = temp.getNext().getData();
    	temp.setNext(temp.getNext().getNext());
    	return tRental;
    }

    /******************************************************************
     * A method that gets a rental object from the list.
     *
     * @param index - represents the index of the list
     *
     * @return the rental unit.
     *******************************************************************/
    public Rental get(int index) {
        /** No list */
        if (top == null)
            return null;

        /** List exists */
        Node temp = top;
        try {
            for (int i = 0; i < index; i++)
                temp = temp.getNext();
        } catch (Exception e) {
            return null;
        }

        return temp.getData();
    }

    /******************************************************************
     * A method that displays the list.
     *******************************************************************/
    public void display() {
        Node temp = top;
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.getNext();
        }
    }

    /******************************************************************
     * A method returns a string the represents the list of rentals in
     * the format: "LL {top=[top], size=[size()]}"
     *******************************************************************/
    public String toString() {
        return "LL {" +
                "top=" + top +
                ", size=" + size() +
                '}';
    }
}

