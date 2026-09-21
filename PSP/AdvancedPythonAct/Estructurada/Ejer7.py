def main():
    pers = {
        "Ivan": 21,
        "Lucia": 22,
        "Ismael": 17,
        "Raquel": 52,
        "Alejandro": 53,
        "Alvaro": 15,
        "Anabel": 47,
        "Salvador": 55,
        "Eloy": 20,
        "Ali": 23,
    }
 
    edadMin = int(input("Escriba una edad: "))
 
    nom = list(pers)
    mayores = []
    i = 0
    while i < len(nom):
        if pers[nom[i]] > edadMin:
            mayores = mayores + [nom[i]]
        i = i + 1
 
    print(f"Personas mayores de {edadMin} años:")
    print(mayores)
 
 
if __name__ == "__main__":
    main()
 