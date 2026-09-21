def main():
    cant = int(input("¿Cuántas palabras quiere escribir? "))
 
    palab = []
    i = 1
    while i <= cant:
        palabra = input(f"Escriba la palabra {i}: ")
        palab = palab + [palabra]
        i = i + 1
 
    busc = input("Escriba la palabra a buscar: ")
 
    veces = 0
    i = 0
    while i < len(palab):
        if palab[i] == busc:
            veces = veces + 1
        i = i + 1
 
    print(f"La palabra '{busc}' aparece {veces} veces")
 
 
if __name__ == "__main__":
    main()
 