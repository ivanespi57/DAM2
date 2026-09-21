def longer_word(palab):
    larga = palab[0]
    i = 0
    while i < len(palab):
        if len(palab[i]) > len(larga):
            larga = palab[i]
        i = i + 1
    return larga
 
 
def main():
    palab = ["hola", "adiós", "palabra", "caracol"]
    
 
    print(f"La palabra más larga es {longer_word(palab)}")
 
 
if __name__ == "__main__":
    main()
 