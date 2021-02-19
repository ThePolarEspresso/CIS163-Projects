package Stopwatch;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/*****************************************************************
 * A Stopwatch Program
 *
 * @author Anna Carvalho
 * @version 02/09/21
 *****************************************************************/

public class StopWatch  {

	/******************************************************************
	 *  instance variable that represents minutes
	 *******************************************************************/

	private int minutes;

	/******************************************************************
	 *  instance variable that represents seconds
	 *******************************************************************/

	private int seconds;

	/******************************************************************
	 *  instance variable that represents milliseconds
	 *******************************************************************/

	private int milliseconds;

	/******************************************************************
	 *  instance variable that represents the switch to halt the mutating
	 *  of all StopWatch objects.
	 *******************************************************************/

	private static boolean suspend = false;

	/******************************************************************
	 * Default constructor that sets the StopWatch to zero.
	 *******************************************************************/

	public StopWatch() {
		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = 0;
	}

	/******************************************************************
	 *  A constructor that accepts a string as a parameter with the following
	 *  format: “1:21:300” where 1 indicates minutes, 21 indicates seconds,
	 *  and 300 indicates milliseconds. OR the format “15:200” where the 15
	 *  indicates seconds, and 200 indicates milliseconds. OR the format “300”
	 *  where 300 indicates milliseconds. If a value is not specified, then
	 *  it is set to zero.
	 *
	 *  @param startTime is the input string that represents the starting time.
	 *
	 * 	@throws IllegalArgumentException when the input string does not match
	 * 	the proper format.
	 *******************************************************************/

	public StopWatch(String startTime) {
		if (startTime == null)
			throw new IllegalArgumentException("constructor with string param");
		if (startTime.length() == 0)
			throw new IllegalArgumentException("constructor with string param");

		int count = 0;
		int minutes = 0;
		int seconds = 0;
		int milliseconds = 0;

		String[] s = startTime.split(":");

		for (int i = 0; i < startTime.length(); i++) {
			if (startTime.charAt(i) == ':') {
				count++;
			}
		}

		if (count >= s.length)
			throw new IllegalArgumentException("constructor ith string param");

		if (s.length == 3) {
			minutes = Integer.parseInt(s[0]);
			seconds = Integer.parseInt(s[1]);
			milliseconds = Integer.parseInt(s[2]);
		} else if (s.length == 2) {
			seconds = Integer.parseInt(s[0]);
			milliseconds = Integer.parseInt(s[1]);
		} else if (s.length == 1) {
			milliseconds = Integer.parseInt(s[0]);
		} else {
			throw new IllegalArgumentException("constructor with string param");
		}

		if ((minutes < 0) || (seconds < 0) || (milliseconds < 0) || (seconds > 59) || (milliseconds > 999))
			throw new IllegalArgumentException();

		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/******************************************************************
	 *  A constructor that initializes the instance variables with
	 *  the provided values.
	 *
	 *  @param minutes is the int input that represents minutes (0-∞).
	 *  @param seconds is the int input that represents seconds (0-59).
	 *  @param milliseconds is the int input that represents minutes
	 *  (0-999).
	 *
	 * 	@throws IllegalArgumentException when minutes/seconds/milliseconds
	 * 	is not within its specified range.
	 *******************************************************************/

	public StopWatch(int minutes, int seconds, int milliseconds) {
		if (minutes < 0)
			throw new IllegalArgumentException("constructor with 3 params");
		if (seconds < 0 || seconds > 59)
			throw new IllegalArgumentException("constructor with 3 params");
		if (milliseconds < 0 || milliseconds > 999)
			throw new IllegalArgumentException("constructor with 3 params");

		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/******************************************************************
	 *  A constructor that initializes the instance variables with
	 *  the provided values.
	 *
	 *  @param seconds is the int input that represents seconds (0-59)
	 *  @param milliseconds is the int input that represents milliseconds
	 *  (0-999)
	 *
	 * 	@throws IllegalArgumentException when seconds/milliseconds
	 * 	is not within its specified range.
	 *******************************************************************/

	public StopWatch(int seconds, int milliseconds) {
		if (seconds < 0 || seconds > 59)
			throw new IllegalArgumentException("constructor with 2 params");
		if (milliseconds < 0 || milliseconds > 999)
			throw new IllegalArgumentException("constructor with 2 params");

		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/******************************************************************
	 *  A constructor that initializes the instance variables with
	 *  the provided value.
	 *
	 *  @param milliseconds is the int input that represents milliseconds
	 *  (0-999)
	 *
	 * 	@throws IllegalArgumentException when milliseconds is not within
	 * 	its specified range.
	 *******************************************************************/

	public StopWatch(int milliseconds) {
		if (milliseconds < 0 || milliseconds > 999)
			throw new IllegalArgumentException("constructor with 1 param");

		this.milliseconds = milliseconds;
	}

	/******************************************************************
	 *  A constructor that initializes the instance variables with
	 *  the provided value.
	 *
	 *  @param stopWatch is the input StopWatch that represents an
	 *  existing StopWatch object.
	 *
	 * 	@throws IllegalArgumentException when stopWatch is equal to null.
	 *******************************************************************/

	public StopWatch(StopWatch stopWatch) {
		if (stopWatch == null)
			throw new IllegalArgumentException("constructor with StopWatch param");

		this.minutes = stopWatch.minutes;
		this.seconds = stopWatch.seconds;
		this.milliseconds = stopWatch.milliseconds;
	}

	/******************************************************************
	 *  A static method that returns true if StopWatch object stopwatch1
	 *  is exactly the same as StopWatch object stopWatch2.
	 *
	 *  @param stopWatch1 is the input StopWatch that represents an
	 * 	existing StopWatch object.
	 * 	@param stopWatch2 is the input StopWatch that represents an
	 * 	existing StopWatch object.
	 *
	 * 	@throws IllegalArgumentException when stopWatch1 or stopWatch2
	 * 	is equal to null.
	 *******************************************************************/

	public static boolean equals(StopWatch stopWatch1, StopWatch stopWatch2) {
		if (stopWatch1 == null || stopWatch2 == null)
			throw new IllegalArgumentException("equals method with StopWatch params");

		if (stopWatch1.minutes == stopWatch2.minutes)
			if (stopWatch1.seconds == stopWatch2.seconds)
				if (stopWatch1.milliseconds == stopWatch2.milliseconds)
					return true;
		return false;
	}

	/******************************************************************
	 *  A method that returns true if “this” StopWatch object is exactly
	 *  the same (minutes, seconds, milliseconds) as the other StopWatch
	 *  object.
	 *
	 *  @param other is the input Object that represents an existing Object
	 *  object.
	 *
	 * 	@throws IllegalArgumentException when other is equal to null.
	 *******************************************************************/

	public boolean equals(Object other) {
		if (other == null)
			throw new IllegalArgumentException("equals method with object param");

		if (other instanceof StopWatch) {
			StopWatch temp = (StopWatch) other;
			if (this.minutes == temp.minutes)
				if (this.seconds == temp.seconds)
					if (this.milliseconds == temp.milliseconds)
						return true;
		}
		return false;
	}

	/******************************************************************
	 *  A method that returns 1 if “this” StopWatch object is greater
	 *  than the other StopWatch object; returns -1 if the “this” StopWatch
	 *  object is less than the other StopWatch; returns 0 if the “this”
	 *  StopWatch object is equal to the other StopWatch object
	 *
	 *  @param other is the input StopWatch that represents an existing
	 *  StopWatch object.
	 *
	 * 	@throws IllegalArgumentException when other is equal to null.
	 *
	 * 	@return 1 if "this" StopWatch object is greater than "other" StopWatch object.
	 * 			-1 if "this" StopWatch object is less than "other" StopWatch object.
	 * 			0 if "this" StopWatch object is equal to the "other" StopWatch object.
	 *******************************************************************/

	public int compareTo(StopWatch other) {
		if (other == null)
			throw new IllegalArgumentException("compareTo method");

		if (this.minutes > other.minutes)
			return 1;
		else if (this.minutes < other.minutes)
			return -1;

		if (this.seconds > other.seconds)
			return 1;
		else if (this.seconds < other.seconds)
			return -1;

		if (this.milliseconds > other.milliseconds)
			return 1;
		else if (this.milliseconds < other.milliseconds)
			return -1;

		return 0;
	}

	/******************************************************************
	 *  A method that takes a StopWatch object and converts it to
	 *  milliseconds.
	 *
	 *  @param stopWatch is the input StopWatch that represents an
	 * 	existing StopWatch object.
	 *
	 * 	@throws IllegalArgumentException when stopWatch is equal to null.
	 *
	 * 	@return a StopWatch object in milliseconds.
	 *******************************************************************/

	private static int convertToMilli(StopWatch stopWatch) {
		return (stopWatch.minutes * 60000)
				+ (stopWatch.seconds * 1000)
				+ stopWatch.milliseconds;
	}

	/******************************************************************
	 *  A method that takes a value (milliseconds) and converts it to a
	 * 	StopWatch object.
	 *
	 *  @param tempMilliseconds is the input int that represents milliseconds.
	 *
	 * 	@throws IllegalArgumentException when tempMilliseconds is less than 0.
	 *******************************************************************/

	private void convertToStopWatch(int tempMilliseconds) {
		if (tempMilliseconds < 0)
			throw new IllegalArgumentException("convertToStopWatch method");

		minutes = tempMilliseconds / 60000;
		tempMilliseconds %= 60000;

		seconds = tempMilliseconds / 1000;
		tempMilliseconds %= 1000;

		milliseconds = tempMilliseconds;
	}

	/******************************************************************
	 *  A method that adds the number of milliseconds to “this” StopWatch
	 *  object.
	 *
	 *  @param milliseconds is the input int that represents milliseconds.
	 *
	 * 	@throws IllegalArgumentException when milliseconds is less than 0.
	 *******************************************************************/

	public void add(int milliseconds) {
		if (milliseconds < 0)
			throw new IllegalArgumentException("add method with int param");

		if (!suspend) {
			int tempMilli = convertToMilli(this);
			tempMilli += milliseconds;
			convertToStopWatch(tempMilli);
		}
	}

	/******************************************************************
	 *  A method that subtracts the number of milliseconds from “this”
	 *  StopWatch object.
	 *
	 *  @param milliseconds is the input int that represents milliseconds.
	 *
	 * 	@throws IllegalArgumentException when milliseconds is less than 0.
	 *******************************************************************/

	public void sub(int milliseconds) {
		if (milliseconds < 0)
			throw new IllegalArgumentException("sub method with int param");

		if (!suspend) {
			int tempMilli = convertToMilli(this);
			tempMilli -= milliseconds;
			convertToStopWatch(tempMilli);
		}
	}

	/******************************************************************
	 *  A method that adds StopWatch other to the “this” StopWatch.
	 *
	 *  @param stopWatch is the input StopWatch that represents an
	 * 	existing StopWatch object.
	 *
	 * 	@throws IllegalArgumentException when stopWatch is equal to null.
	 *******************************************************************/

	public void add(StopWatch stopWatch) {
		if (stopWatch == null)
			throw new IllegalArgumentException("add method with StopWatch param");

		if (!suspend) {
			int tempMilli = convertToMilli(stopWatch);
			add(tempMilli);
		}
	}

	/******************************************************************
	 *  A method that subtracts StopWatch other from the “this” StopWatch.
	 *
	 *  @param other is the input StopWatch that represents an
	 * 	existing StopWatch object.
	 *
	 * 	@throws IllegalArgumentException when stopWatch is equal to null.
	 *******************************************************************/

	public void sub(StopWatch other) {
		if (other == null)
			throw new IllegalArgumentException("sub method with StopWatch param");

		if (!suspend) {
			int tempMilli = convertToMilli(other);
			sub(tempMilli);
		}
	}

	/******************************************************************
	 *  A method that increments the “this” StopWatch by 1 millisecond.
	 *******************************************************************/

	public void inc() {
		this.add(1);
	}

	/******************************************************************
	 *  A method that decrements the “this” StopWatch by 1 millisecond.
	 *******************************************************************/

	public void dec() {
		this.sub(1);
	}

	/******************************************************************
	 *  A method that returns a string that represents a StopWatch with
	 *  the following format:  “0:00:000”.
	 *
	 * @return a StopWatch object as a string.
	 *******************************************************************/

	public String toString() {
		return this.minutes + ":"
				+ new DecimalFormat("00").format(this.seconds) + ":"
				+ new DecimalFormat("000").format(this.milliseconds);
	}

	/******************************************************************
	 *  A method that saves the “this” StopWatch to a file.
	 *
	 * @param filename is the input string that represents the file name.
	 *
	 * @throws IllegalArgumentException when filename is equal to null.
	 *******************************************************************/

	public void save(String filename) {
		if (filename == null)
			throw new IllegalArgumentException();

		PrintWriter out = null;

		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}

		out.println(minutes);
		out.println(seconds);
		out.println(milliseconds);
		out.println(minutes*seconds*milliseconds);
		out.close();
	}

	/******************************************************************
	 *  A method that loads the “this” StopWatch from a file.
	 *
	 * @param filename is the input string that represents the file name.
	 *
	 * @throws IllegalArgumentException when filename is equal to null.
	 *******************************************************************/

	public void load(String filename)  {
		if (filename == null)
			throw new IllegalArgumentException();

		try {
			Scanner scanner = new Scanner(new File(filename));

			int tempMinutes = scanner.nextInt();
			int tempSeconds = scanner.nextInt();
			int tempMilliseconds = scanner.nextInt();

			if ((tempMinutes * tempSeconds * tempMilliseconds == scanner.nextInt()))
				if (tempMinutes >= 0)
					if (tempSeconds >= 0 && tempSeconds < 59)
						if (tempMilliseconds >= 0 && tempMilliseconds < 999) {
							this.minutes = tempMinutes;
							this.seconds = tempSeconds;
							this.milliseconds = tempMilliseconds;
						}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}

	/******************************************************************
	 *  A method that turns on/off all StopWatch objects from mutating.
	 *
	 * 	@param suspend is the input boolean that represents the switch
	 * 	to halt the mutating of all StopWatch objects.
	 *******************************************************************/

	public static void setSuspend(boolean suspend) { StopWatch.suspend = suspend; }

	/******************************************************************
	 * 	A  method that returns true if suspend is true; false if it is false.
	 *
	 * 	@return true if isSuspend is true; false if isSuspend is false.
	 *******************************************************************/

	public static boolean isSuspended() {
		if (StopWatch.suspend)
			return true;

		return false;
	}

	/******************************************************************
	 *  A method that returns minutes.
	 *
	 * 	@return minutes.
	 *******************************************************************/

	public int getMinutes() { return this.minutes; }

	/******************************************************************
	 *  A method that sets the instance variable minutes to the provided
	 *  value.
	 *
	 *  @param minutes is the input that represents minutes (0-∞).
	 *
	 *	@throws IllegalArgumentException when minutes is not within its
	 *	specified range.
	 *******************************************************************/

	public void setMinutes(int minutes) {
		if (minutes < 0)
			throw new IllegalArgumentException("setMinutes method");

		this.minutes = minutes;
	}

	/******************************************************************
	 *  A method that returns seconds.
	 *
	 * 	@return seconds.
	 *******************************************************************/

	public int getSeconds() { return this.seconds; }

	/******************************************************************
	 *  A method that sets the instance variable seconds to the provided
	 *  value.
	 *
	 *  @param seconds is the input that represents seconds (0-59)
	 *
	 *	@throws IllegalArgumentException when seconds is not within its
	 *	specified range.
	 *******************************************************************/

	public void setSeconds(int seconds) {
		if (seconds < 0 || seconds > 59)
			throw new IllegalArgumentException("setSeconds method");

		this.seconds = seconds;
	}

	/******************************************************************
	 *  A method that returns milliseconds.
	 *
	 * 	@return milliseconds.
	 *******************************************************************/

	public int getMilliseconds() { return this.milliseconds; }

	/******************************************************************
	 *  A method that sets the instance variable milliseconds to the provided
	 *  value.
	 *
	 *  @param milliseconds is the input that represents milliseconds (0-999)
	 *
	 *	@throws IllegalArgumentException when milliseconds is not within its
	 *	specified range.
	 *******************************************************************/

	public void setMilliseconds(int milliseconds) {
		if (milliseconds < 0 || milliseconds > 999)
			throw new IllegalArgumentException("setMilliseconds method");

		this.milliseconds = milliseconds;
	}
}