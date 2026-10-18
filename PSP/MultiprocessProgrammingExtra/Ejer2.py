from multiprocessing import Process
import os
import time

def hijo(nombre, timeout):
    print(f"{nombre} (PID {os.getpid()}) empieza y esperará {timeout} s")
    time.sleep(timeout)
    print(f"{nombre} (PID {os.getpid()}) ha terminado tras {timeout} s")

if __name__ == '__main__':
    timeouts = [2, 4, 6]
    procesos = []

    for i, t in enumerate(timeouts, start=1):
        p = Process(target=hijo, args=(f"Hijo {i}", t))
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print("El padre ha comprobado que los tres hijos han terminado.")