import java.util.*;
import java.io.*;

// WeatherData Class
class WeatherData {
    private double temperature;
    private double humidity;
    private String alert;

    // Constructor
    public WeatherData(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.alert = generateAlert();
    }

    // Getter methods
    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getAlert() {
        return alert;
    }

    // Alert logic
    private String generateAlert() {
        String result = "";

        if (temperature > 40) {
            result += "High Temperature Alert ";
        } else if (temperature < 10) {
            result += "Low Temperature Alert ";
        }

        if (humidity > 80) {
            result += "High Humidity Alert ";
        }

        if (result.equals("")) {
            result = "Weather is Normal";
        }

        return result;
    }

    // Display method
    public void display() {
        System.out.println("---------------------------------");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Alert: " + alert);
    }

    // Convert to string (for file saving)
    public String toFileString() {
        return temperature + "," + humidity + "," + alert;
    }
}

// Main Application Class
public class WeatherApp {

    static Scanner sc = new Scanner(System.in);
    static WeatherData[] records = new WeatherData[100];
    static int count = 0;

    // Add new record
    public static void addRecord() {
        try {
            System.out.print("Enter Temperature: ");
            double temp = sc.nextDouble();

            System.out.print("Enter Humidity: ");
            double hum = sc.nextDouble();

            WeatherData wd = new WeatherData(temp, hum);
            records[count++] = wd;

            System.out.println("Record Added Successfully!");

        } catch (Exception e) {
            System.out.println("Invalid Input! Please enter numbers.");
            sc.nextLine();
        }
    }

    // Display all records
    public static void displayRecords() {
        if (count == 0) {
            System.out.println("No Records Found!");
            return;
        }

        System.out.println("\n===== Weather Records =====");
        for (int i = 0; i < count; i++) {
            records[i].display();
        }
    }

    // Search record by temperature
    public static void searchRecord() {
        System.out.print("Enter temperature to search: ");
        double key = sc.nextDouble();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            if (records[i].getTemperature() == key) {
                records[i].display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No record found!");
        }
    }

    // Save data to file
    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("weather.txt");

            for (int i = 0; i < count; i++) {
                fw.write(records[i].toFileString() + "\n");
            }

            fw.close();
            System.out.println("Data saved to file successfully!");

        } catch (IOException e) {
            System.out.println("Error saving file!");
        }
    }

    // Load data from file
    public static void loadFromFile() {
        try {
            File file = new File("weather.txt");

            if (!file.exists()) {
                System.out.println("No file found!");
                return;
            }

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                double temp = Double.parseDouble(parts[0]);
                double hum = Double.parseDouble(parts[1]);

                records[count++] = new WeatherData(temp, hum);
            }

            fileScanner.close();
            System.out.println("Data loaded successfully!");

        } catch (Exception e) {
            System.out.println("Error loading file!");
        }
    }

    // Menu
    public static void menu() {
        while (true) {
            System.out.println("\n===== Weather Monitoring System =====");
            System.out.println("1. Add Weather Record");
            System.out.println("2. Display All Records");
            System.out.println("3. Search Record");
            System.out.println("4. Save to File");
            System.out.println("5. Load from File");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addRecord();
                    break;
                case 2:
                    displayRecords();
                    break;
                case 3:
                    searchRecord();
                    break;
                case 4:
                    saveToFile();
                    break;
                case 5:
                    loadFromFile();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        menu();
    }
}