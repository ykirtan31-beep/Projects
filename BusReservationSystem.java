import java.util.*;

public class BusReservationSystem {

    // ArrayList stores all buses and bookings
    static ArrayList<Bus> buses = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    static int nextPNR = 1001;
    static Scanner sc = new Scanner(System.in);

    // Bus class
    static class Bus {
        int id;
        String source, destination;
        double fare;
        boolean[] seats;
        Queue<String> waitingList = new LinkedList<>();

        Bus(int id, String source, String destination,
            double fare, int totalSeats) {
            this.id = id;
            this.source = source;
            this.destination = destination;
            this.fare = fare;
            seats = new boolean[totalSeats];
        }

        int availableSeats() {
            int count = 0;
            for (boolean seat : seats) {
                if (!seat) count++;
            }
            return count;
        }
    }

    // Booking class
    static class Booking {
        int pnr, busId, seatNo;
        String passenger;
        boolean active = true;

        Booking(int pnr, int busId, int seatNo, String passenger) {
            this.pnr = pnr;
            this.busId = busId;
            this.seatNo = seatNo;
            this.passenger = passenger;
        }
    }

    // Add sample buses
    static void addSampleBuses() {
        buses.add(new Bus(101, "Shahdol", "Bhopal", 650, 3));
        buses.add(new Bus(102, "Shahdol", "Jabalpur", 350, 3));
        buses.add(new Bus(103, "Bhopal", "Indore", 400, 3));
    }

    // Display buses
    static void displayBuses() {
        System.out.println("\n--- AVAILABLE BUSES ---");

        for (Bus b : buses) {
            System.out.println(
                "Bus ID: " + b.id +
                " | " + b.source + " -> " + b.destination +
                " | Fare: Rs." + b.fare +
                " | Seats: " + b.availableSeats()
            );
        }
    }

    // Linear search
    static Bus searchBus(int id) {
        for (Bus b : buses) {
            if (b.id == id) {
                return b;
            }
        }
        return null;
    }

    // Sort buses by fare
    static void sortBusesByFare() {
        buses.sort(Comparator.comparingDouble(b -> b.fare));
        System.out.println("\nBuses sorted by fare:");
        displayBuses();
    }

    // Book a ticket
    static void bookTicket() {
        System.out.print("Enter Bus ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        Bus bus = searchBus(id);

        if (bus == null) {
            System.out.println("Bus not found!");
            return;
        }

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();

        if (bus.availableSeats() == 0) {
            bus.waitingList.offer(name);
            System.out.println("Bus is full. Added to waiting list.");
            return;
        }

        int seatNo = -1;

        for (int i = 0; i < bus.seats.length; i++) {
            if (!bus.seats[i]) {
                bus.seats[i] = true;
                seatNo = i + 1;
                break;
            }
        }

        Booking booking =
            new Booking(nextPNR++, bus.id, seatNo, name);

        bookings.add(booking);

        System.out.println("\nBooking successful!");
        System.out.println("Passenger: " + name);
        System.out.println("Seat Number: " + seatNo);
        System.out.println("PNR: " + booking.pnr);
        System.out.println("Fare: Rs." + bus.fare);
    }

    // Display bookings
    static void displayBookings() {
        System.out.println("\n--- BOOKING DETAILS ---");
        boolean found = false;

        for (Booking b : bookings) {
            if (b.active) {
                System.out.println(
                    "PNR: " + b.pnr +
                    " | Passenger: " + b.passenger +
                    " | Bus ID: " + b.busId +
                    " | Seat: " + b.seatNo
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No active bookings found.");
        }
    }

    // Cancel booking and serve waiting list
    static void cancelTicket() {
        System.out.print("Enter PNR: ");
        int pnr = sc.nextInt();

        for (Booking b : bookings) {
            if (b.pnr == pnr && b.active) {
                b.active = false;

                Bus bus = searchBus(b.busId);
                bus.seats[b.seatNo - 1] = false;

                System.out.println("Ticket cancelled successfully.");

                // Queue: first passenger gets the available seat
                if (!bus.waitingList.isEmpty()) {
                    String name = bus.waitingList.poll();

                    bus.seats[b.seatNo - 1] = true;

                    Booking newBooking = new Booking(
                        nextPNR++, bus.id, b.seatNo, name
                    );
                    bookings.add(newBooking);

                    System.out.println(
                        name + " moved from waiting list to seat " +
                        b.seatNo + ". New PNR: " + newBooking.pnr
                    );
                }
                return;
            }
        }

        System.out.println("Active booking not found!");
    }

    // Main method
    public static void main(String[] args) {
        addSampleBuses();

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("     BUS RESERVATION SYSTEM");
            System.out.println("================================");
            System.out.println("1. View All Buses");
            System.out.println("2. Search Bus");
            System.out.println("3. Sort Buses by Fare");
            System.out.println("4. Book Ticket");
            System.out.println("5. View Bookings");
            System.out.println("6. Cancel Ticket");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayBuses();
                    break;

                case 2:
                    System.out.print("Enter Bus ID: ");
                    int id = sc.nextInt();
                    Bus bus = searchBus(id);

                    if (bus != null) {
                        System.out.println(
                            bus.id + ": " + bus.source + " -> " +
                            bus.destination + ", Fare: Rs." + bus.fare
                        );
                    } else {
                        System.out.println("Bus not found!");
                    }
                    break;

                case 3:
                    sortBusesByFare();
                    break;

                case 4:
                    bookTicket();
                    break;

                case 5:
                    displayBookings();
                    break;

                case 6:
                    cancelTicket();
                    break;

                case 0:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}