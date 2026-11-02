import threading
import queue
import time

q = queue.Queue()


def productor():
    for i in range(1, 6):
        print(f"Productor: genera {i}")
        q.put(i)
        time.sleep(0.5)


def consumidor():
    for _ in range(5):
        n = q.get()
        print(f"Consumidor: procesa {n} -> {n * 2}")
        time.sleep(1)


if __name__ == "__main__":
    tp = threading.Thread(target=productor)
    tc = threading.Thread(target=consumidor)

    tp.start()
    tc.start()

    tp.join()
    tc.join()

    print("Fin del programa")