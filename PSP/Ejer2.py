def main():
    alt = float(input("Escriba la altura (en kg): "))
    peso = float(input("Escriba el peso (en m): "))
    
    res = (peso/(alt**2))
    
    print(res)

if __name__ == "__main__":
    main()