class IntToRoman:
    def int_to_roman(self, num):
        valores = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
        simbolos = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX",
                    "V", "IV", "I"]
 
        romano = ""
        i = 0
        while i < len(valores):
            while num >= valores[i]:
                romano = romano + simbolos[i]
                num = num - valores[i]
            i = i + 1
        return romano
 
 
def main():
    num = int(input("Escriba un número entero (1-3999): "))
 
    if num < 1 or num > 3999:
        print("El número tiene que estar entre 1 y 3999")
    else:
        conv = IntToRoman()
        print(f"{num} en romano es {conv.int_to_roman(num)}")
 
 
if __name__ == "__main__":
    main()