import concurrent.futures


def suma_parcial(numeros):
    return sum(numeros)


if __name__ == "__main__":
    lista = list(range(1, 1000001))
    tam = len(lista) // 4
    partes = [lista[i * tam:(i + 1) * tam] for i in range(4)]

    with concurrent.futures.ThreadPoolExecutor(max_workers=4) as executor:
        futuros = [executor.submit(suma_parcial, parte) for parte in partes]
        parciales = [f.result() for f in futuros]

    print(f"Sumas parciales: {parciales}")
    print(f"Suma total: {sum(parciales)}")  # Debe ser 500000500000