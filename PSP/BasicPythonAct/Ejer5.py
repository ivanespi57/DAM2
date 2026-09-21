def main():
    bin = input("Escriba un número binario: ")
 
    entero = 0
    i = 0
    while i < len(bin):
        entero = entero * 2 + int(bin[i])
        i = i + 1
 
    print(f"{bin} en decimal es {entero}")
 
 
if __name__ == "__main__":
    main()
 