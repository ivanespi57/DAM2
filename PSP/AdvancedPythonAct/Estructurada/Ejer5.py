def main():
    cad = input("Escriba una cadena: ")
 
    cont = 0
    i = 0
    while i < len(cad):
        if cad[i] >= "A" and cad[i] <= "Z":
            cont = cont + 1
        i = i + 1
 
    print(f"La cadena tiene {cont} letras mayúsculas")
 
 
if __name__ == "__main__":
    main()