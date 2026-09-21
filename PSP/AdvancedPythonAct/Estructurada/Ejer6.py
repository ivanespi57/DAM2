def main():
    anyoAct = int(input("Escriba el año en curso: "))
 
    i = 1
    while i <= 3:
        nom = input(f"Nombre de la persona {i}: ")
        anyoNac = int(input(f"Año de nacimiento de {nom}: "))
 
        edad = anyoAct - anyoNac
 
        print(f"{nom} cumplirá {edad} años en {anyoAct}")
        i = i + 1
 
if __name__ == "__main__":
    main()
