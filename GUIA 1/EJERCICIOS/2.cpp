#include <iostream>
using namespace std;

int main() {
    int arr[10];
    for (int i = 0; i < 10; i++) {
        int num;
        do {
            cout << "Ingrese número " << i+1 << ": ";
            cin >> num;
        } while (i > 0 && num <= arr[i-1]);
        arr[i] = num;
    }
}
