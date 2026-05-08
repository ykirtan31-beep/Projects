#include <stdio.h>

// Function to calculate total bill
float calculateBill(int qty[], float price[], int size)
{
    float total = 0;
    int i;

    for (i = 0; i < size; i++)
    {
        total += qty[i] * price[i];
    }

    return total;
}

int main()
{
    int i;
    int items = 5;

    // 1D arrays for menu items and prices
    char menu[5][20] = {"Burger", "Pizza", "Pasta", "Sandwich", "Coffee"};
    float price[5] = {120.0, 250.0, 180.0, 100.0, 60.0};
    int quantity[5];

    printf("====== ONLINE FOOD ORDER MENU ======\n");
    printf("Item\t\tPrice (Rs)\n");

    for (i = 0; i < items; i++)
    {
        printf("%d. %s\t\t%.2f\n", i + 1, menu[i], price[i]);
    }

    // Taking quantity input
    printf("\nEnter quantity for each item:\n");
    for (i = 0; i < items; i++)
    {
        printf("%s: ", menu[i]);
        scanf("%d", &quantity[i]);
    }

    // Function call to calculate total bill
    float totalBill = calculateBill(quantity, price, items);

    // Display total bill
    printf("\n===================================\n");
    printf("Total Bill Amount: Rs %.2f\n", totalBill);
    printf("Thank you for ordering!\n");

    return 0;
}
