def calcular_cargo(horas):
    if horas <= 0:
        return 0.0

    if horas == 1:
        cargo = 3.0
    else:
        cargo = 3.0 + (horas - 1) * 0.50

    # Máximo diario
    if cargo > 12.0:
        cargo = 12.0

    return cargo


# Programa principal
horas = int(input("Ingrese el número de horas en el estacionamiento: "))
print("El cargo es: S/", calcular_cargo(horas))
