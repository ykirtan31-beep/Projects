#include <iostream>
#include <cmath>   // For pow() and sqrt()
using namespace std;

int main() {

    int choice;
    double num1, num2, result;

    cout << "=====================================\n";
    cout << "       ADVANCED CALCULATOR APP       \n";
    cout << "=====================================\n";

    do {

        // Display Menu
        cout << "\nChoose an Operation:\n";
        cout << "1. Addition\n";
        cout << "2. Subtraction\n";
        cout << "3. Multiplication\n";
        cout << "4. Division\n";
        cout << "5. Modulus\n";
        cout << "6. Power\n";
        cout << "7. Square Root\n";
        cout << "8. Exit\n";

        cout << "\nEnter your choice: ";
        cin >> choice;

        switch(choice) {

            case 1:
                cout << "\nEnter two numbers: ";
                cin >> num1 >> num2;

                result = num1 + num2;

                cout << "Result = " << result << endl;
                break;

            case 2:
                cout << "\nEnter two numbers: ";
                cin >> num1 >> num2;

                result = num1 - num2;

                cout << "Result = " << result << endl;
                break;

            case 3:
                cout << "\nEnter two numbers: ";
                cin >> num1 >> num2;

                result = num1 * num2;

                cout << "Result = " << result << endl;
                break;

            case 4:
                cout << "\nEnter two numbers: ";
                cin >> num1 >> num2;

                if(num2 == 0) {
                    cout << "Error! Division by zero is not allowed.\n";
                }
                else {
                    result = num1 / num2;
                    cout << "Result = " << result << endl;
                }

                break;

            case 5:
            {
                int a, b;

                cout << "\nEnter two integers: ";
                cin >> a >> b;

                if(b == 0) {
                    cout << "Error! Modulus by zero is not allowed.\n";
                }
                else {
                    cout << "Result = " << a % b << endl;
                }

                break;
            }

            case 6:
                cout << "\nEnter base number: ";
                cin >> num1;

                cout << "Enter power: ";
                cin >> num2;

                result = pow(num1, num2);

                cout << "Result = " << result << endl;
                break;

            case 7:
                cout << "\nEnter a number: ";
                cin >> num1;

                if(num1 < 0) {
                    cout << "Error! Square root of negative number is not possible.\n";
                }
                else {
                    result = sqrt(num1);
                    cout << "Square Root = " << result << endl;
                }

                break;

            case 8:
                cout << "\nThank you for using Calculator App!\n";
                break;

            default:
                cout << "\nInvalid Choice! Please try again.\n";
        }

    } while(choice != 8);

    return 0;
}