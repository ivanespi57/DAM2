from multiprocessing import Process, Queue

def producto_parcial(inicio, fin, q):
    resultado = 1
    for n in range(inicio, fin + 1):
        resultado *= n
    print(f"Rango {inicio}-{fin}: producto parcial = {resultado}")
    q.put(resultado)

if __name__ == '__main__':
    N = 20
    num_procesos = 4
    tam = N // num_procesos

    q = Queue()
    procesos = []

    for i in range(num_procesos):
        inicio = i * tam + 1
        fin = (i + 1) * tam if i < num_procesos - 1 else N
        p = Process(target=producto_parcial, args=(inicio, fin, q))
        procesos.append(p)
        p.start()

    factorial = 1
    for _ in range(num_procesos):
        factorial *= q.get()

    for p in procesos:
        p.join()

    print(f"{N}! = {factorial}")