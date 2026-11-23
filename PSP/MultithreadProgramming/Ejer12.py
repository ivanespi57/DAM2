import threading
import time

lista = []
condicion = threading.Condition()


def esperar_lista():
    nombre = threading.current_thread().name
    with condicion:
        while len(lista) < 5:
            print(f"[{nombre}] La lista tiene {len(lista)} elementos, esperando...")
            condicion.wait()  # Libera el lock y espera la notificación
        print(f"[{nombre}] ¡La lista ya tiene 5 elementos!: {lista}")


def anadir_elementos():
    for i in range(1, 6):
        time.sleep(1)
        with condicion:
            lista.append(i)
            print(f"[Añadidor] Añadido {i}. Notificando...")
            condicion.notify()


if __name__ == "__main__":
    t1 = threading.Thread(target=esperar_lista, name="Esperador")
    t2 = threading.Thread(target=anadir_elementos, name="Añadidor")

    t1.start()
    t2.start()

    t1.join()
    t2.join()