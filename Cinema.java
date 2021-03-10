package cinema;

import java.util.Scanner;
/*
Cinema Room Manager
 */
public class Cinema {

    /**
     * Method prints the current situation in Cinema, available and taken seats.
     * @param array
     */
    public static void showTheSeats(String[][] array) {
        System.out.println("Cinema:");
        for (int i = 0; i <= array[0].length; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();

        for (int i = 0; i < array.length; i++) {
            System.out.print((i + 1));
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(" " + array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Method starts a dialog for buying the tickets.
     * @param array
     */
    public static void buyTicket(String[][] array) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        while (!flag) {
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();
            System.out.println();

            //check if input is correct
            if ((rowNumber - 1) < 0 || (rowNumber - 1) >= array.length ||
                    (seatNumber - 1) < 0 || (seatNumber - 1) >= array[0].length) {
                System.out.println("Wrong input!\n");
            } else if (array[rowNumber - 1][seatNumber - 1].equals("B")) {
                System.out.println("That ticket has already been purchased!\n");
            } else {
                //if input is correct, check the price
                array[rowNumber - 1][seatNumber - 1] = "B";
                flag = true;
                if (array.length * array[1].length < 60) {
                    System.out.println("Ticket price: $10");
                } else if (rowNumber <= array.length / 2) {
                    System.out.println("Ticket price: $10");
                } else {
                    System.out.println("Ticket price: $8");
                }
            }
        }
        System.out.println();
    }

    /**
     * Method shows statistic: number of purchased tickets, percentage of occupation, income.
     * @param array
     */
    public static void showStatistics(String[][] array) {
        double percent = 0;
        int purchased = 0;
        int totalIncome = 0;
        int currentIncome = 0;
        int rows = array.length;
        int seats = array[0].length;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j].equals("B")) {
                    purchased++;
                }
            }
        }
        System.out.format("Number of purchased tickets: %d%n", purchased);

        if (purchased == 0) {
            System.out.format("Percentage: %.2f%%%n", (double) purchased);
        } else {
            percent = (double) purchased / ((double) (rows * seats) / 100);
            System.out.format("Percentage: %.2f%%%n", percent);
        }

        if (rows * seats < 60) {
            totalIncome = rows * seats * 10;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j].equals("B")) {
                        currentIncome += 8;
                    }
                }
            }
            System.out.format("Current income: $%d%n", currentIncome);
            System.out.format("Total income: $%d%n", totalIncome);
        } else {
            totalIncome = (rows / 2 * seats * 10) + ((rows - rows / 2) * seats * 8);
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j].equals("B") && i + 1 <= rows / 2) {
                        currentIncome += 10;
                    } else if (array[i][j].equals("B") && i + 1 > rows / 2) {
                        currentIncome += 8;
                    }
                }
            }
            System.out.format("Current income: $%d%n", currentIncome);
            System.out.format("Total income: $%d%n", totalIncome);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows;
        int seats;
        int key;

        System.out.println("Enter the number of rows:");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = scanner.nextInt();
        System.out.println();

        String[][] array = new String[rows][seats];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                array[i][j] = "S";
            }
        }

        //main dialog of manager
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            key = scanner.nextInt();
            System.out.println();

            switch (key) {
                case 1:
                    showTheSeats(array);
                    break;

                case 2:
                    buyTicket(array);
                    break;

                case 3:
                    showStatistics(array);
                    break;

                case 0:
                    return;
            }
        }
    }
}