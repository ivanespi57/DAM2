def main():
    edades = (5, 10, 15, 20, 25, 30, 35, 40, 45, 50)
    
 
    cont = 0
    i = 0
    while i < len(edades):
        if edades[i] > 20:
            cont = cont + 1
        i = i + 1
 
    print(f"Hay {cont} personas con más de 20 años")
 
 
if __name__ == "__main__":
    main()
 