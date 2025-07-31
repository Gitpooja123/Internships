//Task--->4
// CURRENCY CONVERTER
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================");
        System.out.println("       Currency Converter     ");
        System.out.println("===============================");

        System.out.println("Available currencies: INR(₹), USD($), EUR(€)");
        System.out.print("Enter base currency: ");
        String base = scanner.next().toUpperCase();

        System.out.print("Enter target currency: ");
        String target = scanner.next().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double rate = getExchangeRate(base, target);

        if (rate == 0.0) {
            System.out.println("Conversion not available for selected currencies.");
        } else {
            double result = amount * rate;
            System.out.println("  " + amount + " " + base + " = " + result + " " + target);
        }

        scanner.close();
    }

    //exchange rates
    public static double getExchangeRate(String base, String target) {
        if (base.equals("INR") && target.equals("USD")) {
            return 0.012;
        } else if (base.equals("USD") && target.equals("INR")) {
            return 83.0;
        } else if (base.equals("INR") && target.equals("EUR")) {
            return 0.011;
        } else if (base.equals("EUR") && target.equals("INR")) {
            return 90.0;
        } else if (base.equals("USD") && target.equals("EUR")) {
            return 0.92;
        } else if (base.equals("EUR") && target.equals("USD")) {
            return 1.08;
        } else {
            return 0.0; // Not available
        }
    }
}
