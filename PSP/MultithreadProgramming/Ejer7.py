import threading
import time
import random

lock = threading.Lock()
carrera_terminada = threading.Event()
ganador = None


def corredor(id):
    global ganador
    pasos = 0
    while not carrera_terminada.is_set():
        pasos += random.randint(1, 10)
        print(f"Corredor {id} lleva {pasos} pasos")

        if pasos >= 100:
            with lock:
                # Solo el primero que llegue aquí es declarado ganador
                if not carrera_terminada.is_set():
                    ganador = id
                    carrera_terminada.set()  # Avisa al resto de que pare
                    print(f"\n*** ¡El corredor {id} ha ganado la carrera! ***\n")
            break

        time.sleep(random.uniform(0.05, 0.2))


if __name__ == "__main__":
    hilos = [threading.Thread(target=corredor, args=(i,)) for i in range(1, 6)]

    for h in hilos:
        h.start()
    for h in hilos:
        h.join()

    print(f"Carrera terminada. Ganador: corredor {ganador}")