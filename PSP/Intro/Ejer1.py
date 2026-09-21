def main():
    num = [4, 17, -3, 9, 12]

    max = num[0]
    for numero in num:
        if numero > max:
            max = numero

    print(f"El mayor es {max}")


if __name__ == "__main__":
    main()