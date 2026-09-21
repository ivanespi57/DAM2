def imprimir_menu(caract, textos, salida):
    print()
    i = 0
    while i < len(caract):
        print(f"{caract[i]}) {textos[i]}")
        i = i + 1
    print(f"{salida}) Salir")
 
 
def main():
    cant = int(input("¿Cuántas opciones tiene el menú? "))
 
    caract = []
    textos = []
    i = 1
    while i <= cant:
        caracter = input(f"Carácter de la opción {i}: ")
        texto = input(f"Texto de la opción {i}: ")
        caract = caract + [caracter]
        textos = textos + [texto]
        i = i + 1
 
    salida = input("Carácter para salir del menú: ")
 
    opc = ""
    while opc != salida:
        imprimir_menu(caract, textos, salida)
        opc = input("Elija una opción: ")
 
        encontrada = False
        i = 0
        while i < len(caract):
            if opc == caract[i]:
                print(f"Ha elegido: {textos[i]}")
                encontrada = True
            i = i + 1
 
        if opc != salida and encontrada == False:
            print("Opción no válida")
 
    print("Adiós")
 
 
if __name__ == "__main__":
    main()