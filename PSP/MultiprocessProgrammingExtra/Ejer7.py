from multiprocessing import Process, Queue

def suma_parcial(parte, q):
    s = sum(parte)
    print(f"Suma parcial de {parte[0]} a {parte[-1]}: {s}")
    q.put(s)

if __name__ == '__main__':
    lista = list(range(1, 1000001))
    num_procesos = 4
    tam = len(lista) // num_procesos

    q = Queue()
    procesos = []

    for i in range(num_procesos):
        parte = lista[i * tam:(i + 1) * tam]
        p = Process(target=suma_parcial, args=(parte, q))
        procesos.append(p)
        p.start()

    total = 0
    for _ in range(num_procesos):
        total += q.get()

    for p in procesos:
        p.join()

    print("Suma total:", total)