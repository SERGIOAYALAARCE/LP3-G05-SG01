def convertir_a_segundos(horas, minutos, segundos):
    return horas * 3600 + minutos * 60 + segundos


# Programa principal
h = int(input("Ingrese horas: "))
m = int(input("Ingrese minutos: "))
s = int(input("Ingrese segundos: "))

print("Equivalente en segundos:", convertir_a_segundos(h, m, s))
