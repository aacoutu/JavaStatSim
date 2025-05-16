import javax.swing.*;
import java.awt.*;

// simple statistical simulation with GUI: input event probability and attempt duration, output total duration for event to occur
public class StatSim {
    public static void main(String[] args) {

        // tracks whether the user wants to run another simulation
        boolean keepSimulating = true;

        // tracks whether the user wants to input new values
        boolean keepInputs = true;

        // tracks whether the user input valid entries
        boolean validInput = false;

        // use Time class to later store user input individual attempt time
        Time attemptTime = new Time(0);

        // will store user input event probability
        double eventChance = -1.0;

        // stores the number of attempts currently executed
        int attemptCount = 0;

        // keep looping the GUI sequence until the user chooses to stop
        while (keepSimulating) {

            String input = "";
            validInput = false;

            // exits this loop once valid event probability is provided
            while (!validInput) {

                // GUI display asking the user to input an event probability
                input = JOptionPane.showInputDialog("What is the percent chance of the event?");

                // properly concludes if user cancels
                if (input == null) {
                    System.exit(1);
                }

                try {
                    eventChance = Double.parseDouble(input);

                    // check that the input probability is a number between 0 and 100
                    if (eventChance < 0.0 | eventChance > 100.0) {
                        throw new IllegalArgumentException();
                    }

                    // will exit loop now
                    validInput = true;

                // gives the user an error until a valid input is provided
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a decimal number from 0 to 100.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            // reset input validity checker
            validInput = false;

            // exits this loop once valid event duration is provided
            while (!validInput) {

                // GUI display asking the user to input an event duration in natural sec, min, hour format
                JTextField hoursField = new JTextField(5);
                JTextField minutesField = new JTextField(5);
                JTextField secondsField = new JTextField(5);

                JPanel panel = new JPanel(new GridLayout(3, 2));
                panel.add(new JLabel("Hours:"));
                panel.add(hoursField);
                panel.add(new JLabel("Minutes:"));
                panel.add(minutesField);
                panel.add(new JLabel("Seconds:"));
                panel.add(secondsField);

                int choice = JOptionPane.showConfirmDialog(null, panel, "How long does one attempt take?", JOptionPane.OK_CANCEL_OPTION);

                // parses the time and feeds it into Time class once user clicks "OK"
                if (choice == JOptionPane.OK_OPTION) {
                    try {
                        attemptTime = new Time(
                                Integer.parseInt(hoursField.getText()),
                                Integer.parseInt(minutesField.getText()),
                                Integer.parseInt(secondsField.getText())
                        );

                        // will exit loop now
                        validInput = true;

                    // gives the user an error until a valid input is provided
                    } catch (IllegalArgumentException e) {
                        JOptionPane.showMessageDialog(null, "Invalid input! Please enter only integers.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // properly concludes if user cancels
                else {
                    System.exit(1);
                }
            }

            // resets to keep inputs if user just changed them
            keepInputs = true;

            // loops through rerunning the simulation with the given inputs as long as the user chooses to
            while (keepInputs) {

                // the simple statistical simulation: runs attempts to produce event until it occurs
                boolean occured = false;
                attemptCount = 0;
                while (!occured) {
                    attemptCount++;
                    if (Math.random() < (eventChance / 100.0)) {
                        occured = true;
                    }
                }
                // records how long it took to produce the event
                Time totTime = new Time(attemptCount * attemptTime.getTotSeconds());

                // prepares strings to display
                String results = String.format("Time to achieve random event: %d hours, %d minutes, %d seconds.", totTime.getHours(), totTime.getMinutes(), totTime.getSeconds());
                String[] options = {"Simulate Again", "Change Inputs", "Cancel"};

                // displays the result and options for moving forward
                int choice = JOptionPane.showOptionDialog(
                        null,
                        results,
                        "Results",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                // rerun simulation with same inputs
                if (choice == 0) {
                    continue;

                // provide new inputs, then rerun
                } else if (choice == 1) {
                    keepInputs = false;

                // end
                } else {
                    keepInputs = false;
                    keepSimulating = false;
                }
            }
        }
    }
}
