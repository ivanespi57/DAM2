def main():
    numPar = int(input("Escriba un número par: "))
    numImpar = int(input("Escriba un número impar: "))
    
    if (numPar % 2 == 0) and (numImpar % 2 != 0):
        print("Está todo bien")
    else:
        print("Algún número no es el correcto")

if __name__ == "__main__":
    main()