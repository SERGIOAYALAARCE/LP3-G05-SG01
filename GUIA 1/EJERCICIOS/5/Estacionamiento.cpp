#include <iostream>
using namespace std;

double calcularCargo(int horas) {
    if (horas <= 0) return 0.0;

    double cargo;
    if (horas == 1) {
        cargo = 3.0;
    } else {
        cargo = 3.0 + (horas - 1) * 0.50;
    }

    // Máximo diario
    if (cargo > 12.0) {
        cargo = 12.0;
    }

    return cargo;
}

int main() {
    int horas;
    cout << "Ingrese el número de horas en el estacionamiento: ";
    cin >> horas;

    double cargo = calcularCargo(horas);
    cout << "El cargo es: S/" << cargo << endl;

    return 0;
}
