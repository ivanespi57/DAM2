class Cadena:
    def __init__(self):
        self.cad = ""
 
    def pedir_cadena(self):
        self.cad = input("Escriba una cadena: ")
 
    def imprimir_mayusculas(self):
        mayusculas = ""
        i = 0
        while i < len(self.cad):
            letra = self.cad[i]
            cod = ord(letra)

            min = cod >= 97 and cod <= 122
            acent = cod >= 224 and cod <= 254 and cod != 247
            if min or acent:
                letra = chr(cod - 32)
            mayusculas = mayusculas + letra
            i = i + 1
        print(mayusculas)
 
    def invertir_palabras(self):
        palabras = []
        palabra = ""
        i = 0
        while i < len(self.cad):
            letra = self.cad[i]
            if letra == " ":
                if palabra != "":
                    palabras = palabras + [palabra]
                    palabra = ""
            else:
                palabra = palabra + letra
            i = i + 1
        if palabra != "":
            palabras = palabras + [palabra]

        res = ""
        i = len(palabras) - 1
        while i >= 0:
            res = res + palabras[i]
            if i > 0:
                res = res + " "
            i = i - 1
        return res
 
def main():
    texto = Cadena()
    texto.pedir_cadena()
    texto.imprimir_mayusculas()
    print(texto.invertir_palabras())
 
 
if __name__ == "__main__":
    main()