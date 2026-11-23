import threading
import time

database = "Datos iniciales"  # Recurso compartido

db_lock = threading.Lock()       # Exclusión mutua para el escritor
counter_lock = threading.Lock()  # Protege el contador de lectores
readers_count = 0                # Cuántos lectores hay dentro


def lector(id):
    global readers_count
    with counter_lock:
        readers_count += 1
        if readers_count == 1:
            db_lock.acquire()  # El primer lector bloquea a los escritores

    # --- Lectura (varios lectores a la vez) ---
    print(f"[Lector {id}] Leyendo: {database} (lectores dentro: {readers_count})")
    time.sleep(1)
    # --- Fin de lectura ---

    with counter_lock:
        readers_count -= 1
        if readers_count == 0:
            db_lock.release()  # El último lector deja pasar al escritor
    print(f"[Lector {id}] Ha terminado de leer.")


def escritor(id):
    global database
    print(f"  [Escritor {id}] Intentando acceder para escribir...")
    with db_lock:  # El escritor necesita acceso exclusivo
        print(f"  [Escritor {id}] Escribiendo nuevos datos...")
        database = f"Datos modificados por el escritor {id}"
        time.sleep(2)
        print(f"  [Escritor {id}] Ha terminado de escribir.")


if __name__ == "__main__":
    hilos = []

    # 3 lectores iniciales
    for i in range(1, 4):
        hilos.append(threading.Thread(target=lector, args=(i,)))

    # 1 escritor
    hilos.append(threading.Thread(target=escritor, args=(1,)))

    # 2 lectores que llegan tarde
    for i in range(4, 6):
        hilos.append(threading.Thread(target=lector, args=(i,)))

    # Arrancan los 3 lectores y el escritor
    for i in range(4):
        hilos[i].start()

    time.sleep(2)  # Los dos lectores llegan tarde

    hilos[4].start()
    hilos[5].start()

    for t in hilos:
        t.join()

    print("--- Todos los hilos han terminado. ---")
    print(f"Estado final de la base de datos: {database}")