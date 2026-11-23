import concurrent.futures


def producto_rango(inicio, fin):
    # Calcula el producto parcial de inicio a fin (ambos incluidos)
    resultado = 1
    for i in range(inicio, fin + 1):
        resultado *= i
    return resultado


if __name__ == "__main__":
    n = 20
    rangos = [(1, 5), (6, 10), (11, 15), (16, 20)]

    with concurrent.futures.ThreadPoolExecutor(max_workers=4) as executor:
        futuros = [executor.submit(producto_rango, ini, fin) for ini, fin in rangos]
        parciales = [f.result() for f in futuros]

    print(f"Productos parciales: {parciales}")

    # El hilo principal combina los resultados
    factorial = 1
    for p in parciales:
        factorial *= p

    print(f"Factorial de {n}: {factorial}")