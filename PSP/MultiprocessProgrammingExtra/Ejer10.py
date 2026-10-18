from multiprocessing import Process, Queue
import random
import time

def productor(nombre, q):
    for _ in range(5):
        numero = random.randint(1, 100)
        print(f"{nombre}: genera {numero}")
        q.put((nombre, numero))
        time.sleep(random.uniform(0.1, 0.5))
    q.put(None)

def consumidor(q, num_productores):
    terminados = 0
    while terminados < num_productores:
        dato = q.get()
        if dato is None:
            terminados += 1
        else:
            nombre, numero = dato
            print(f"Consumidor: procesa {numero} de {nombre} -> doble = {numero * 2}")
    print("Consumidor: todos los productores han terminado.")

if __name__ == '__main__':
    q = Queue()
    p1 = Process(target=productor, args=("Productor 1", q))
    p2 = Process(target=productor, args=("Productor 2", q))
    c = Process(target=consumidor, args=(q, 2))

    p1.start()
    p2.start()
    c.start()

    p1.join()
    p2.join()
    c.join()
    print("Fin del programa.")