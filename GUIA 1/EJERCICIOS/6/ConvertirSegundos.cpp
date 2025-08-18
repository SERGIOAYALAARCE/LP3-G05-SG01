#include <iostream>
using namespace std;

int convertirASegundos(int horas, int minutos, int segundos) {
    return horas * 3600 + minutos * 60 + segundos;
}

int main() {
    int h, m, s;
    cout << "Ingrese horas: ";
    cin >> h;
    cout << "Ingrese minutos: ";
    cin >> m;
    cout << "Ingrese segundos: ";
    cin >> s;

    int totalSegundos = convertirASegundos(h, m, s);
    cout << "Equivalente en segundos: " << totalSegundos << endl;

    return 0;
}
