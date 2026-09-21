def es_bisiesto(anyo):
    if (anyo % 4 == 0 and anyo % 100 != 0) or anyo % 400 == 0:
        return True
    else:
        return False
 
 
def main():
    anyo = int(input("Escriba un año: "))
 
    if es_bisiesto(anyo):
        print(f"{anyo} es bisiesto")
    else:
        print(f"{anyo} no es bisiesto")
 
 
if __name__ == "__main__":
    main()
 