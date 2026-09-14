def main():
    numPar = int(input("Escriba un número par: "))

    
    if numPar % 2 == 0:

        numImpar = int(input("Escriba un número impar: "))

        if numImpar % 2 == 0:
            print("El valor no es correcto")
    else:
        print("El valor no es correcto")

if __name__ == "__main__":
    main()