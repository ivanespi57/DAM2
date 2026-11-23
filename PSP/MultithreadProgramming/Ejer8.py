import threading
import queue
import time

cola = queue.Queue(maxsize=5)


def productor(id):
    for i in range(1, 11):
        item = f"P{id}-{i}"
        cola.put(item)  # Se bloquea si la cola está llena
        print(f"[Productor {id}] ha puesto {item} (en cola: {cola.qsize()}/5)")
        time.sleep(0.1)


def consumidor():
    for _ in range(20):  # 2 productores x 10 items
        item = cola.get()  # Se bloquea si la cola está vacía
        print(f"[Consumidor] procesa {item}")
        time.sleep(0.3)
        cola.task_done()


if __name__ == "__main__":
    p1 = threading.Thread(target=productor, args=(1,))
    p2 = threading.Thread(target=productor, args=(2,))
    c = threading.Thread(target=consumidor)

    p1.start()
    p2.start()
    c.start()

    p1.join()
    p2.join()
    c.join()

    print("El consumidor ha procesado los 20 items.")