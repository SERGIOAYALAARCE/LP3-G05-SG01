#include <iostream>
using namespace std;

int sumaArreglo(int arr[], int n) {
    int suma = 0;
    for (int i = 0; i < n; i++) {
        suma += arr[i];
    }
    return suma;
}

int main() {
    int arr[] = {1,2,3,4,5};
    int n = 5;
    cout << sumaArreglo(arr, n);
}
