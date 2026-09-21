def count_vowels(palab):
    cont_a = 0
    cont_e = 0
    cont_i = 0
    cont_o = 0
    cont_u = 0
 
    i = 0
    while i < len(palab):
        letra = palab[i]
        if letra == "a" or letra == "A":
            cont_a = cont_a + 1
        elif letra == "e" or letra == "E":
            cont_e = cont_e + 1
        elif letra == "i" or letra == "I":
            cont_i = cont_i + 1
        elif letra == "o" or letra == "O":
            cont_o = cont_o + 1
        elif letra == "u" or letra == "U":
            cont_u = cont_u + 1
        i = i + 1
 
    print(f"a: {cont_a}")
    print(f"e: {cont_e}")
    print(f"i: {cont_i}")
    print(f"o: {cont_o}")
    print(f"u: {cont_u}")
 
 
def main():
    palab = input("Escriba una palabra: ")
 
    count_vowels(palab)
 
 
if __name__ == "__main__":
    main()