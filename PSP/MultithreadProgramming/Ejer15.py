import threading
import time
import random

datos_locales = threading.local()


def tarea(numero):
    nombre = threading.current_thread().name

    # Cada hilo guarda su propio número
    datos_locales.mi_numero = numero
    print(f"[{nombre}] He guardado mi número: {datos_locales.mi_numero}")

    # Espera aleatoria para que los otros hilos tengan tiempo de guardar el suyo
    time.sleep(random.uniform(0.1, 1))

    valor_recuperado = datos_locales.mi_numero
    print(f"[{nombre}] Recupero mi número: {valor_recuperado}")

    # Comprobamos que nadie ha sobrescrito el valor
    if valor_recuperado == numero:
        print(f"[{nombre}] Verificado: mi valor no ha sido sobrescrito.")
    else:
        print(f"[{nombre}] ERROR: mi valor ha sido modificado por otro hilo.")


if __name__ == "__main__":
    hilos = []
    for i in range(1, 4):
        t = threading.Thread(target=tarea, args=(i * 10,), name=f"Hilo-{i}")
        hilos.append(t)
        t.start()

    for t in hilos:
        t.join()

    print("Main: todos los hilos han terminado.")