import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class CurrencyExchangeUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Currency Exchange");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        // Components
        JLabel amountLabel = new JLabel("Amount to Exchange:");
        JTextField amountField = new JTextField(10);

        JLabel originalCurrencyLabel = new JLabel("Original Currency:");
        String[] popularCurrencies = {
            "USD - United States Dollar",
            "EUR - Euro",
            "GBP - British Pound Sterling",
            "JPY - Japanese Yen",
            "AUD - Australian Dollar",
            "CAD - Canadian Dollar",
            "CHF - Swiss Franc",
            "CNY - Chinese Yuan",
            "INR - Indian Rupee",
            "BRL - Brazilian Real",
            "RUB - Russian Ruble",
            "ZAR - South African Rand",
            "NZD - New Zealand Dollar",
            "SGD - Singapore Dollar",
            "HKD - Hong Kong Dollar",
            "KRW - South Korean Won",
            "MXN - Mexican Peso",
            "SEK - Swedish Krona",
            "NOK - Norwegian Krone",
            "TRY - Turkish Lira",
            "SAR - Saudi Riyal",
            "AED - UAE Dirham",
            "PLN - Polish Zloty",
            "IDR - Indonesian Rupiah",
            "THB - Thai Baht",
            "MYR - Malaysian Ringgit",
            "EGP - Egyptian Pound",
            "ILS - Israeli New Shekel",
            "PKR - Pakistani Rupee",
            "IQD - Iraqi Dinar",
            "DKK - Danish Krone",
            "CZK - Czech Koruna",
            "HUF - Hungarian Forint",
            "PHP - Philippine Peso",
            "CLP - Chilean Peso",
            "COP - Colombian Peso",
            "ARS - Argentine Peso",
            "VND - Vietnamese Dong",
            "BDT - Bangladeshi Taka",
            "LKR - Sri Lankan Rupee",
            "KWD - Kuwaiti Dinar",
            "QAR - Qatari Riyal",
            "OMR - Omani Rial",
            "JOD - Jordanian Dinar",
            "MAD - Moroccan Dirham",
            "TWD - Taiwan Dollar",
            "UAH - Ukrainian Hryvnia",
            "RON - Romanian Leu",
            "BGN - Bulgarian Lev",
            "HRK - Croatian Kuna"
        };
        JComboBox<String> originalCurrencyDropdown = new JComboBox<>(popularCurrencies);

        JLabel targetCurrencyLabel = new JLabel("Target Currency:");
        JComboBox<String> targetCurrencyDropdown = new JComboBox<>(popularCurrencies);

        JButton calculateButton = new JButton("Calculate");
        JLabel resultLabel = new JLabel("Converted Amount: ");
        JLabel dateTimeLabel = new JLabel("Date & Time: ");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float amount = Float.parseFloat(amountField.getText());
                    // Extract only the currency code (first 3 letters before " - ")
                    String originalCurrency = ((String) originalCurrencyDropdown.getSelectedItem()).split(" - ")[0];
                    String targetCurrency = ((String) targetCurrencyDropdown.getSelectedItem()).split(" - ")[0];

                    float rate = Exchange.getRate(originalCurrency, targetCurrency);
                    float convertedAmount = amount * rate;

                    resultLabel.setText("Converted Amount: " + convertedAmount);
                    String dateTime = Exchange.getDateTime();
                    dateTimeLabel.setText("Date & Time: " + dateTime);

                    // Save to file
                    saveExchangeToFile(dateTime, amount, originalCurrency, targetCurrency, convertedAmount);
                } catch (Exception ex) {
                    resultLabel.setText("Error: " + ex.getMessage());
                    dateTimeLabel.setText("Date & Time: ");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(originalCurrencyLabel);
        panel.add(originalCurrencyDropdown);
        panel.add(targetCurrencyLabel);
        panel.add(targetCurrencyDropdown);
        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(dateTimeLabel);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void saveExchangeToFile(String dateTime, float amount, String originalCurrency, String targetCurrency, float convertedAmount) {
        String fileName = "exchange_history.txt";
        String entry = String.format(
            "Date & Time: %s | Amount: %.2f %s -> %.2f %s%n",
            dateTime, amount, originalCurrency, convertedAmount, targetCurrency
        );
        try (FileWriter writer = new FileWriter(fileName, true)) { // append mode
            writer.write(entry);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }
}