package Stopwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Write a description  here.
 *
 * @author Anna Carvalho
 * @version February 10, 2021
 */

public class StopWatchPanel extends JPanel {

    private StopWatch watch;
    private Timer javaTimer;

    JButton startButton, stopButton, loadButton, saveButton, addButton, subButton, newButton, continueButton;
    JTextField minField, secField, milliField, addField, subField, newField;

    JLabel lblTime;

    public StopWatchPanel() {
        watch = new StopWatch();
        javaTimer = new Timer(5, new TimerListener());

        setLayout(new GridLayout(10, 2));
        setBackground(Color.lightGray);

        add(new JLabel("Minutes:"));
        minField = new JTextField("0", 3);
        add(minField);

        add(new JLabel("Seconds:"));
        secField = new JTextField("0", 3);
        add(secField);

        add(new JLabel("Milliseconds:"));
        milliField = new JTextField("0", 3);
        add(milliField);

        startButton = new JButton("Start");
        add(startButton);

        stopButton = new JButton("Stop");
        add(stopButton);

        saveButton = new JButton("Save");
        add(saveButton);

        loadButton = new JButton("Load");
        add(loadButton);

        addButton = new JButton("Add");
        add(addButton);

        addField = new JTextField("0", 3);
        add(addField);

        subButton = new JButton("Sub");
        add(subButton);

        subField = new JTextField("0", 3);
        add(subField);

        newButton = new JButton("New");
        add(newButton);

        newField = new JTextField("0:00:000", 3);
        add(newField);

        continueButton = new JButton("Continue");
        add(continueButton);

        add(new JLabel(""));

        add(new JLabel("Time:"));
        lblTime = new JLabel();
        lblTime.setText(watch.toString());
        add(lblTime);

        startButton.addActionListener(new ButtonListener());
        stopButton.addActionListener(new ButtonListener());
        continueButton.addActionListener(new ButtonListener());
        loadButton.addActionListener(new ButtonListener());
        saveButton.addActionListener(new ButtonListener());
        addButton.addActionListener(new ButtonListener());
        subButton.addActionListener(new ButtonListener());
        newButton.addActionListener(new ButtonListener());
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int mins, sec, milli;

            if (event.getSource() == startButton) {
                try {
                    mins = Integer.parseInt(minField.getText());
                    sec = Integer.parseInt(secField.getText());
                    milli = Integer.parseInt(milliField.getText());
                    watch = new StopWatch(mins, sec, milli);
                    javaTimer.start();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == stopButton) {
                try {
                    javaTimer.stop();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == continueButton) {
                try {
                    javaTimer.start();
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == saveButton) {
                try {
                    watch.save("file1");
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == loadButton) {
				try {
					watch.load("file1");
				} catch (NumberFormatException io) {
					JOptionPane.showMessageDialog(null, "Error in file");
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Error in file1");
				}
			}

            if (event.getSource() == addButton) {
                try {
                    milli = Integer.parseInt(addField.getText());
                    watch.add(milli);
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == subButton) {
                try {
                    milli = Integer.parseInt(subField.getText());
                    watch.sub(milli);
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            if (event.getSource() == newButton) {
                try {
                    watch = new StopWatch(newField.getText());
                } catch (NumberFormatException io) {
                    JOptionPane.showMessageDialog(null, "Enter an integer in all fields");
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error in field");
                }
            }

            lblTime.setText(watch.toString());
        }
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            watch.add(1);
            watch.add(1);
            watch.add(1);
            watch.add(1);
            watch.add(1);
            lblTime.setText(watch.toString());
        }
    }
}
