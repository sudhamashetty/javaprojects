package project3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SimpleCalculator extends JFrame {
    private final JTextField inputfield1, inputfield2, outputfield;
    private final JComboBox<String> symbols;
    private final JButton convertButton;


    public SimpleCalculator() {
        setTitle("Simple Calculator ");
        setSize(400, 250);
        setLayout(null); //use manual layout
        setDefaultCloseOperation(EXIT_ON_CLOSE);//exit on program close
        setResizable(false);
        setLocationRelativeTo(null);

        //adds a input label
        JLabel inputlabel1 = new JLabel("input A");
        inputlabel1.setBounds(20, 0, 100, 30);
        add(inputlabel1);

        //adds a input field
        inputfield1 = new JTextField();
        inputfield1.setBounds(20, 30, 50, 30);
        add(inputfield1);

        //adds a dropdown menu
        String[] units = {"+", "-", "/", "*", "%"};
        symbols = new JComboBox<>(units);
        symbols.setBounds(100, 90, 150, 30);
        add(symbols);

        //adds input B label
        JLabel inputlabel2 = new JLabel("input B");
        inputlabel2.setBounds(100, 0, 50, 30);
        add(inputlabel2);

        //input B field
        inputfield2 = new JTextField();
        inputfield2.setBounds(100, 30, 50, 30);
        add(inputfield2);

        //adds convert button
        convertButton = new JButton("calculate");
        convertButton.setBounds(100, 120, 150, 30);
        add(convertButton);

        //ouput label
        JLabel outputlabel = new JLabel("Result");
        outputlabel.setBounds(45, 160, 150, 30);
        add(outputlabel);


        //outputfield
        outputfield = new JTextField();
        outputfield.setBounds(100, 160, 150, 30);
        outputfield.setEditable(false);
        add(outputfield);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });
        setVisible(true);
    }

    private void calculate() {
        try {
            double inputA = Double.parseDouble(inputfield1.getText());
            double inputB = Double.parseDouble(inputfield2.getText());
            String symbol = (String) symbols.getSelectedItem();

            if (symbol.equals("/") && inputB == 0) {
                throw new ArithmeticException("Erro:" + "cannot divide by zero...!!");
            }

            double result = convertUnits(inputA, inputB, symbol);
            outputfield.setText(String.format("%.4f", result));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error:" + "Invalid Input. Please enter a valid input.", "invalid input", JOptionPane.ERROR_MESSAGE);
        } catch (ArithmeticException e) {
             JOptionPane.showMessageDialog(this,e.getMessage(),"Math error",JOptionPane.ERROR_MESSAGE);
        }catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,e.getMessage(),"Invalid  operation",JOptionPane.ERROR_MESSAGE);
        }
    }

    public double convertUnits(double valueA, double valueB, String symbol) {
        double x;
        switch (symbol) {
            case ("+") -> x = valueA + valueB;
            case ("-") -> x = valueA - valueB;
            case ("*") -> x = valueA * valueB;
            case ("/") -> x = valueA / valueB;
            case ("%") -> x = valueA % valueB;
            default ->throw new IllegalArgumentException("invalid operation");
        };
        return x;
    }

    public static void main(String[] args) {
        new project3.SimpleCalculator();


    }
}