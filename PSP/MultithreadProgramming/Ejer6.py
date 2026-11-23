import threading
import time
import random

lock = threading.Lock()
contador_lineas = 0


def escribir_mensajes():
    global contador_lineas
    nombre = threading.current_thread().name
    for i in range(1, 6):
        with lock:
            contador_lineas += 1
            with open("log.txt", "a", encoding="utf-8") as f:
                f.write(f"{contador_lineas} - [{nombre}] Mensaje {i}\n")
        time.sleep(random.uniform(0.1, 0.5))  # Simula trabajo


if __name__ == "__main__":
    # Vaciamos el archivo antes de empezar
    open("log.txt", "w").close()

    hilos = [threading.Thread(target=escribir_mensajes, name=f"Hilo-{i}") for i in range(1, 4)]

    for h in hilos:
        h.start()
    for h in hilos:
        h.join()

    print("Contenido de log.txt:\n")
    with open("log.txt", "r", encoding="utf-8") as f:
        print(f.read())