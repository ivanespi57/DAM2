def main():
    nom = ["Ivan", "Ismael", "Raquel", "Alejandro", "Lucia", "Alvaro", "Anabel"]
 
    cont = 0
    i = 0
    while i < len(nom):
        if nom[i][0] == "a" or nom[i][0] == "A":
            cont = cont + 1
        i = i + 1
 
    print(f"Hay {cont} nom que empiezan por a")
 
 
if __name__ == "__main__":
    main()
 