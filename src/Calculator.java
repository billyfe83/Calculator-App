import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {

    // width and height of the calculator
    int borderWidth = 360;
    int borderHeight = 540;

    // custom color(ac)
    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80,80,80);
    Color customBlack = new Color(28,28,28);
    Color customOrange = new Color(255, 149, 0);

    // declaring buttons
       String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};
   
    
    // window title/panel
    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();


    // For top symbols and this will save the input digit(A+B, A-B, A*B, A/B)
    String A = "0";
    String operator = null;
    String B = null;

    // Create constructor
    Calculator() {
        // frame.setVisible(true);
        frame.setSize(borderWidth, borderHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        // Setting a Label
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);


        // Setting a Panel
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH); // put it on the top "0"


        // Setting for Button panel
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);


        // Adding the button values
        for(int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack)); //Changes the border colors btn buttons


            // Applying the color to their according color
            if(Arrays.asList(topSymbols).contains(buttonValue)) {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.white);
            }
            else {
                button.setBackground(customDarkGray);
                button.setForeground(Color.white);
            }


            buttonsPanel.add(button);


            // Add functionality/action to the buttons
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();

                  
                    if(Arrays.asList(rightSymbols).contains(buttonValue)) {
                         if(buttonValue == "=") {
                            
                            if(A != null) {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if(operator == "+") {
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                else if(operator == "-") {
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                else if(operator == "×") {
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                else if(operator == "÷") {
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }
                                clearAll();
                            }

                         }
                         else if("+-×÷".contains(buttonValue)) {
                               if(operator == null) {
                                  A = displayLabel.getText();
                                  displayLabel.setText("0");
                                  B = "0";
                               }
                               operator = buttonValue;
                         }
                    }


                    else if(Arrays.asList(topSymbols).contains(buttonValue)) {
                        if(buttonValue == "AC") {
                            clearAll();
                            displayLabel.setText("0");
                          }
                        else if(buttonValue == "+/-") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay)); // check down function

                         }
                        else if(buttonValue == "%") {
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }


                    }

                    else {// digits or "."
                        if(buttonValue == ".") {
                           // for decimal
                           if(!displayLabel.getText().contains(buttonValue)) {
                             displayLabel.setText(displayLabel.getText() + buttonValue);
                           }
                        }
                        else if("0123456789".contains(buttonValue)) {
                            if(displayLabel.getText() == "0") {
                                displayLabel.setText(buttonValue);
                            }
                            else {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        }

                    }
                }
            });

            frame.setVisible(true); // this should fix display problem while adding fn
        }
    }


    // create variables for clearAll function
    void clearAll() {
        A = "0";
        operator = null;
        B = "0";
    }
   

    //Define the function for removing zero for whole number
    String removeZeroDecimal(double numDisplay) {

        if(numDisplay % 1 == 0) {
           return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
      
    }
}
