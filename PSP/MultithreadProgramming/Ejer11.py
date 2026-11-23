import threading
import time

rlock = threading.RLock()


def countdown(n):
    nombre = threading.current_thread().name
    with rlock:  # El mismo hilo puede adquirirlo en cada nivel sin bloquearse
        print(f"[{nombre}] {n}")
        time.sleep(0.5)
        if n > 1:
            countdown(n - 1)
        print(f"[{nombre}] libera el nivel {n}")


if __name__ == "__main__":
    t1 = threading.Thread(target=countdown, args=(3,), name="Hilo-A")
    t2 = threading.Thread(target=countdown, args=(5,), name="Hilo-B")

    t1.start()
    t2.start()

    t1.join()
    t2.join()

    print("Fin del programa")