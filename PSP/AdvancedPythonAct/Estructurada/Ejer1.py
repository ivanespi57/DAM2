def main():
    cant = int(input("¿Cuántas palabras quiere escribir? "))
 
    palab = []
    i = 1
    while i <= cant:
        palabra = input(f"Escriba la palabra {i}: ")
        palab = palab + [palabra]
        i = i + 1
 
    print(f"La lista es: {palab}")
 
 
if __name__ == "__main__":
    main()
 