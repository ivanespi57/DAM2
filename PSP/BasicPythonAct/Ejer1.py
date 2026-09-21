def max_in_list(lista):
    max = lista[0]
    i = 0
    while i < len(lista):
        if lista[i] > max:
            max = lista[i]
        i = i + 1
    return max


def main():
    num = [4, 17, -3, 9, 12]

    print(f"El mayor es {max_in_list(num)}")


if __name__ == "__main__":
    main()