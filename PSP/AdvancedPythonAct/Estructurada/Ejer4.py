def filter_words(palab, n):
    resultado = []
    i = 0
    while i < len(palab):
        if len(palab[i]) > n:
            resultado = resultado + [palab[i]]
        i = i + 1
    return resultado
 
 
def main():
    palab = ["hola", "adiós", "palabra", "caracol", "sol"]
    n = 4
 
    print(f"Palabras con más de {n} caracteres:")
    print(filter_words(palab, n))
 
 
if __name__ == "__main__":
    main()
 