from multiprocessing import Process, Value, Lock, Event, current_process
import random
import time

def corredor(numero, ganador, lock, fin):
    pasos = 0
    while not fin.is_set():
        pasos += random.randint(1, 10)
        print(f"Corredor {numero} lleva {min(pasos, 100)} pasos")
        if pasos >= 100:
            with lock:
                if ganador.value == 0:
                    ganador.value = numero
                    fin.set()
            break
        time.sleep(random.uniform(0.01, 0.1))

if __name__ == '__main__':
    ganador = Value('i', 0)
    lock = Lock()
    fin = Event()
    procesos = []

    print("¡Empieza la carrera!")
    for n in range(1, 6):
        p = Process(target=corredor, args=(n, ganador, lock, fin))
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print(f"\n¡El ganador es el corredor {ganador.value}!")s