import random

frecuencia = [0]*6
for _ in range(20000):
    cara = random.randint(1,6)
    frecuencia[cara-1] += 1

print(frecuencia)
