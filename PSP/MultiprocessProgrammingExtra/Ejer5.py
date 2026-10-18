from multiprocessing import Process, Value, Lock

def incrementar(contador, lock, veces):
    for _ in range(veces):
        with lock:
            contador.value += 1

if __name__ == '__main__':
    contador = Value('i', 0)
    lock = Lock()

    p1 = Process(target=incrementar, args=(contador, lock, 10000))
    p2 = Process(target=incrementar, args=(contador, lock, 10000))
    p1.start()
    p2.start()
    p1.join()
    p2.join()

    print("Valor final del contador:", contador.value)