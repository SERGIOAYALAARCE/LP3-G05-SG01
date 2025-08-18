arr = []
for i in range(10):
    while True:
        num = int(input(f"Ingrese número {i+1}: "))
        if i == 0 or num > arr[-1]:
            arr.append(num)
            break
        else:
            print("El número debe ser mayor al anterior.")
print(arr)


