#include <iostream>
using namespace std;

float menor(float a, float b, float c) {
    return min(a, min(b, c));
}

int main() {
    cout << menor(4.2, 1.7, 9.8);
}
