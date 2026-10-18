from multiprocessing import Process, Queue
import time

def productor(q):
    for numero in range(1, 6):
        print(f"Productor: genera {numero}")
        q.put(numero)
        time.sleep(0.5)
    q.put(None)

def consumidor(q):
    while True:
        numero = q.get()
        if numero is None:
            break
        print(f"Consumidor: procesa {numero} -> {numero} al cuadrado = {numero ** 2}")

if __name__ == '__main__':
    q = Queue()
    p1 = Process(target=productor, args=(q,))
    p2 = Process(target=consumidor, args=(q,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("Productor y consumidor han terminado.")