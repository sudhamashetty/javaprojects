package project2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class UnitConvertor extends JFrame {
    private final JTextField inputfield, outputfield;
    private final JComboBox<String> unitFrom, unitTo;

    public UnitConvertor() {
        setTitle("Unit Convertor");
        setSize(400, 250);
        setLayout(null); //use manual layout
        setDefaultCloseOperation(EXIT_ON_CLOSE);//exit on program close
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel inputlabel = new JLabel("Enter the value");
        inputlabel.setBounds(45, -110, 100, 300);
        add(inputlabel);

        inputfield = new JTextField();
        inputfield.setBounds(150, 30, 150, 30);
        add(inputfield);

        String[] units = {"Meters", "Kilometers", "Miles", "Feet", "Inches","Grams","Kilograms","Pounds","Celsius","Fahrenheit","Kelvin"};
        unitFrom = new JComboBox<>(units);
        unitFrom.setBounds(50, 70, 120, 30);
        add(unitFrom);

        unitTo = new JComboBox<>(units);
        unitTo.setBounds(180, 70, 120, 30);
        add(unitTo);

       JButton convertButton = new JButton("convertor");
        convertButton.setBounds(100, 120, 150, 30);
        add(convertButton);

        JLabel inputlabel1 = new JLabel("Result");
        inputlabel1.setBounds(45, 160, 100, 30);
        add(inputlabel1);

        outputfield = new JTextField();
        outputfield.setBounds(100, 160, 50, 30);
        outputfield.setEditable(false);
        add(outputfield);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });
        setVisible(true);
    }

        private void convert () {
        try {
            double input = Double.parseDouble(inputfield.getText());
            String fromunit = (String) unitFrom.getSelectedItem();
            String tounit = (String) unitTo.getSelectedItem();


            double result = convertUnits(input, fromunit, tounit);
            outputfield.setText(String.format("%.4f", result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Invalid Input. Please enter a number.");
        }catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
    public double convertUnits(double value,String from,String to) {
        String[] lengthunits = {"Meters", "Kilometers", "Miles", "Feet", "Inches"};
        String[] weightunits = {"Grams", "Kilograms", "Pounds"};
        String[] tempunits = {"Celsius", "Kelvin", "Fahrenheit"};

        //check category from and to units
        boolean islength = Arrays.asList(lengthunits).contains(from) && Arrays.asList(lengthunits).contains(to);
        boolean isweight = Arrays.asList(weightunits).contains(from) && Arrays.asList(weightunits).contains(to);
        boolean istempunits = Arrays.asList(tempunits).contains(from) && Arrays.asList(tempunits).contains(to);

        if (!islength && !isweight && !istempunits) {
            throw new IllegalArgumentException("Cannot convert " + from + " to " + to);
        }
        if (islength) {
            double meters = switch (from) {
                case "Meters" -> value;
                case "Kilometers" -> value * 1000;
                case "Miles" -> value * 1609.34;
                case "Feet" -> value * 0.3048;
                case "Inches" -> value * 0.0254;
                default -> value;
            };

            return switch (to) {
                case "Meters" -> meters;
                case "Kilometers" -> meters / 1000;
                case "Miles" -> meters / 1609.34;
                case "Feet" -> meters / 0.3048;
                case "Inches" -> meters / 0.0254;
                default -> meters;

            };
        }

        if (isweight) {
            double grams = switch (from) {
                case "Grams" -> value;
                case "Kilograms" -> value * 1000;
                case "Pounds" -> value * 453.592;
                default -> value;
            };

            return switch (to) {
                case "Grams" -> grams;
                case "Kilograms" -> grams / 1000;
                case "Pounds" -> grams / 453.592;
                default -> value;
            };
        }
        if (istempunits) {
            if (from.equals(to)) return value;
            return switch (from) {
                case "Celsius" -> switch (to) {
                    case "Fahrenheit" -> (value * 9 / 5 )+ 32;
                    case "Kelvin" -> value + 273.15;
                    default -> throw new IllegalArgumentException("invalid temp unit" + to);
                };

                case "Fahrenheit" -> switch (to) {
                    case "Celsius" -> (value - 32) * 5 / 9;
                    case "Kelvin" -> (value - 32) * 5 / 9 + 273.15;
                    default -> throw new IllegalArgumentException("invalid temp unit" + to);
                };

                case "Kelvin" -> switch (to) {
                    case "Celsius" -> value - 273.15;
                    case "Fahrenheit" -> (value - 273.15) * 9 / 5 - 32;
                    default -> throw new IllegalArgumentException("invalid temp unit" + to);
                };
                default -> throw new IllegalArgumentException("invalid temp unit" + from);
            };
        }
        throw new IllegalArgumentException("unknown error while converting");
    }

public static void main (String[] args) {
        new UnitConvertor();
    }
}