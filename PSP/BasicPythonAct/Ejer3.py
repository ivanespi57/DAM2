def filtrar_palabras(palab, n):
    res = []
    i = 0
    while i < len(palab):
        if len(palab[i]) > n:
            res = res + [palab[i]]
        i = i + 1
    return res
 
 
def main():
    palab = ["hola", "adiós", "palabra", "caracol", "sol"]
    n = 4
 
    print(f"Palabras con más de {n} caracteres:")
    print(filtrar_palabras(palab, n))
 
 
if __name__ == "__main__":
    main()
 