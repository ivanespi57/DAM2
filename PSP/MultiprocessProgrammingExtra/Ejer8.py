from multiprocessing import Process, Lock, current_process
import time

def escribir_log(lock, mensajes):
    for i in range(mensajes):
        with lock:
            with open("log.txt", "a") as f:
                f.write(f"{current_process().name} escribe el mensaje {i + 1}\n")
        time.sleep(0.1)

if __name__ == '__main__':
    open("log.txt", "w").close()

    lock = Lock()
    procesos = []

    for _ in range(3):
        p = Process(target=escribir_log, args=(lock, 5))
        procesos.append(p)
        p.start()

    for p in procesos:
        p.join()

    print("Contenido de log.txt:")
    with open("log.txt") as f:
        print(f.read())