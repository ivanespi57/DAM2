def main():
    nom = ["Ivan", "Ismael", "Raquel", "Alejandro", "Lucia", "Alvaro", "Anabel"]
 
    letra = input("Escriba la inicial a buscar (en mayúscula): ")
 
    cont = 0
    i = 0
    while i < len(nom):
        if nom[i][0] == letra:
            cont = cont + 1
        i = i + 1
 
    print(f"Hay {cont} nombres que empiezan por {letra}")
 
 
if __name__ == "__main__":
    main()
 