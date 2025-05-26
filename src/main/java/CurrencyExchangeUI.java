import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CurrencyExchangeUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(CurrencyExchangeUI::showWelcomeMenu);
    }

    // Welcome menu before login
    private static void showWelcomeMenu() {
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(400, 200);

        JLabel welcomeLabel = new JLabel("Welcome to Srushti Qaladiza Currency Exchange App!", SwingConstants.CENTER);
        JButton loginButton = new JButton("Log In");
        JButton quitButton = new JButton("Quit");

        // Make the buttons less wide
        Dimension buttonSize = new Dimension(80, 30);
        loginButton.setMaximumSize(buttonSize);
        loginButton.setPreferredSize(buttonSize);
        quitButton.setMaximumSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);

        // Use BoxLayout to stack buttons vertically and center them
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Center the buttons horizontally
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createVerticalStrut(10)); // space between buttons
        buttonPanel.add(quitButton);
        buttonPanel.add(Box.createVerticalGlue());

        welcomeFrame.setLayout(new BorderLayout());
        welcomeFrame.add(welcomeLabel, BorderLayout.CENTER);
        welcomeFrame.add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(e -> {
            welcomeFrame.dispose();
            showLoginMenu();
        });

        quitButton.addActionListener(e -> {
            welcomeFrame.dispose();
            System.exit(0);
        });

        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setVisible(true);
    }

    // Login menu
    private static void showLoginMenu() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(500, 350); // Increased size
        loginFrame.setLayout(new GridLayout(5, 1, 10, 10));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JLabel loginStatus = new JLabel("", SwingConstants.CENTER);

        loginFrame.add(new JLabel("Username:", SwingConstants.CENTER));
        loginFrame.add(usernameField);
        loginFrame.add(new JLabel("Password:", SwingConstants.CENTER));
        loginFrame.add(passwordField);
        loginFrame.add(loginButton);
        loginFrame.add(loginStatus);

        loginFrame.setLayout(new GridLayout(5, 1, 10, 10));

        loginButton.addActionListener(e -> {
            // Simple login check (accept any non-empty input)
            if (!usernameField.getText().trim().isEmpty() && passwordField.getPassword().length > 0) {
                loginFrame.dispose();
                showMainMenu();
            } else {
                loginStatus.setText("Invalid credentials!");
            }
        });

        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    // Main menu after login
    private static void showMainMenu() {
        JFrame mainMenuFrame = new JFrame("Main Menu");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(500, 350); // Increased size

        JButton exchangeButton = new JButton("Currency Exchange");
        JButton reportButton = new JButton("Financial Reports");
        JButton customerButton = new JButton("Customer Data");
        JButton quitButton = new JButton("Quit"); // 4th button

        // Set preferred size for smaller buttons
        Dimension buttonSize = new Dimension(180, 50);
        exchangeButton.setPreferredSize(buttonSize);
        reportButton.setPreferredSize(buttonSize);
        customerButton.setPreferredSize(buttonSize);
        quitButton.setPreferredSize(buttonSize);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.add(exchangeButton);
        panel.add(reportButton);
        panel.add(customerButton);
        panel.add(quitButton);

        // Center the buttons in the panel
        JPanel outerPanel = new JPanel(new GridBagLayout());
        outerPanel.add(panel);

        exchangeButton.addActionListener(e -> {
            mainMenuFrame.dispose();
            createAndShowGUI();
        });

        reportButton.addActionListener(e -> {
            mainMenuFrame.dispose();
            showReportMenu();
        });

        customerButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainMenuFrame, "Customer Data functionality coming soon!");
        });

        quitButton.addActionListener(e -> {
            mainMenuFrame.dispose();
            System.exit(0);
        });

        mainMenuFrame.add(outerPanel);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
    }

    // Financial report menu UI
    private static void showReportMenu() {
        JFrame reportFrame = new JFrame("Financial Reports");
        reportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reportFrame.setSize(600, 400); // Increased size

        JLabel startLabel = new JLabel("Start Date:");
        JLabel endLabel = new JLabel("End Date:");

        // Simple date selection using combo boxes (for demonstration)
        String[] years = {"2022", "2023", "2024", "2025"};
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JComboBox<String> startYear = new JComboBox<>(years);
        JComboBox<String> startMonth = new JComboBox<>(months);
        JComboBox<String> startDay = new JComboBox<>(days);

        JComboBox<String> endYear = new JComboBox<>(years);
        JComboBox<String> endMonth = new JComboBox<>(months);
        JComboBox<String> endDay = new JComboBox<>(days);

        JPanel startPanel = new JPanel();
        startPanel.add(startYear);
        startPanel.add(startMonth);
        startPanel.add(startDay);

        JPanel endPanel = new JPanel();
        endPanel.add(endYear);
        endPanel.add(endMonth);
        endPanel.add(endDay);

        panel.add(startLabel);
        panel.add(startPanel);
        panel.add(endLabel);
        panel.add(endPanel);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");
        panel.add(submitButton);
        panel.add(backButton);

        submitButton.addActionListener(e -> {
            String startDate = startDay.getSelectedItem() + "-" + startMonth.getSelectedItem() + "-" + startYear.getSelectedItem();
            String endDate = endDay.getSelectedItem() + "-" + endMonth.getSelectedItem() + "-" + endYear.getSelectedItem();
            generateFinancialReport(startDate, endDate);
        });

        backButton.addActionListener(e -> {
            reportFrame.dispose();
            showMainMenu();
        });

        reportFrame.add(panel);
        reportFrame.setLocationRelativeTo(null);
        reportFrame.setVisible(true);
    }

    // Exchange calculation menu (existing UI)
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Currency Exchange");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Increased size

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
        JButton backButton = new JButton("Back");

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    float amount = Float.parseFloat(amountField.getText());
                    String originalCurrency = ((String) originalCurrencyDropdown.getSelectedItem()).split(" - ")[0];
                    String targetCurrency = ((String) targetCurrencyDropdown.getSelectedItem()).split(" - ")[0];

                    float rate = Exchange.getRate(originalCurrency, targetCurrency);
                    float convertedAmount = amount * rate;
                    float fee = amount * 0.01f; // 1% fee

                    resultLabel.setText("Converted Amount: " + convertedAmount + " (Fee: " + String.format("%.2f", fee) + " " + originalCurrency + ")");
                    String dateTime = Exchange.getDateTime();
                    dateTimeLabel.setText("Date & Time: " + dateTime);

                    // Save to file, now also saving the fee
                    saveExchangeToFile(dateTime, amount, originalCurrency, targetCurrency, convertedAmount, fee);
                } catch (Exception ex) {
                    resultLabel.setText("Error: " + ex.getMessage());
                    dateTimeLabel.setText("Date & Time: ");
                }
            }
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            showMainMenu();
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.add(amountLabel);
        panel.add(amountField);
        panel.add(originalCurrencyLabel);
        panel.add(originalCurrencyDropdown);
        panel.add(targetCurrencyLabel);
        panel.add(targetCurrencyDropdown);
        panel.add(calculateButton);
        panel.add(resultLabel);
        panel.add(dateTimeLabel);
        panel.add(backButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void saveExchangeToFile(String dateTime, float amount, String originalCurrency, String targetCurrency, float convertedAmount, float fee) {
        String fileName = "exchange_history.txt";
        String entry = String.format(
            "Date & Time: %s | Amount: %.2f %s -> %.2f %s | Fee: %.2f %s%n",
            dateTime, amount, originalCurrency, convertedAmount, targetCurrency, fee, originalCurrency
        );
        try (FileWriter writer = new FileWriter(fileName, true)) { // append mode
            writer.write(entry);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    private static void generateFinancialReport(String startDate, String endDate) {
        String fileName = "exchange_history.txt";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        StringBuilder report = new StringBuilder();
        float totalProfit = 0.0f;

        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            LocalDateTime start = LocalDateTime.parse(startDate + " 00:00:00", formatter);
            LocalDateTime end = LocalDateTime.parse(endDate + " 23:59:59", formatter);

            for (String line : lines) {
                int idx = line.indexOf("Date & Time: ");
                if (idx != -1) {
                    int dateStart = idx + "Date & Time: ".length();
                    int dateEnd = line.indexOf(" | Amount:");
                    if (dateEnd > dateStart) {
                        String dateStr = line.substring(dateStart, dateEnd).trim();
                        try {
                            LocalDateTime entryDate = LocalDateTime.parse(dateStr, formatter);
                            if ((entryDate.isEqual(start) || entryDate.isAfter(start)) &&
                                (entryDate.isEqual(end) || entryDate.isBefore(end))) {
                                report.append(line).append("\n");
                                // Extract fee from the line
                                int feeIdx = line.indexOf("| Fee:");
                                if (feeIdx != -1) {
                                    String feePart = line.substring(feeIdx + 6).trim();
                                    String[] feeTokens = feePart.split(" ");
                                    try {
                                        totalProfit += Float.parseFloat(feeTokens[0]);
                                    } catch (Exception ignore) {}
                                }
                            }
                        } catch (Exception ex) {
                            // Ignore lines with invalid date format
                        }
                    }
                }
            }
        } catch (Exception e) {
            report.append("Could not read report: ").append(e.getMessage());
        }

        if (report.length() == 0) {
            report.append("No records found for the selected period.\n");
        }
        report.append("\nTotal Profit: ").append(String.format("%.2f", totalProfit));

        JTextArea textArea = new JTextArea(report.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        JOptionPane.showMessageDialog(null, scrollPane, "Financial Report", JOptionPane.INFORMATION_MESSAGE);
    }
}