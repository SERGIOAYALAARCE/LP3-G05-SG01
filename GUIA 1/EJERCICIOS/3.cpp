#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

int main() {
    int freq[6] = {0};
    srand(time(0));
    for (int i = 0; i < 20000; i++) {
        int cara = rand() % 6; // 0 a 5
        freq[cara]++;
    }
    for (int i = 0; i < 6; i++) cout << "Cara " << i+1 << ": " << freq[i] << endl;
}
