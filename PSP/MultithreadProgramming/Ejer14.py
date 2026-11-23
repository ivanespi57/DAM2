import threading
import time


class Worker(threading.Thread):
    def __init__(self, nombre, num_tareas):
        super().__init__(name=nombre)
        self.num_tareas = num_tareas

    def run(self):
        for i in range(1, self.num_tareas + 1):
            print(f"[{self.name}] Tarea {i}/{self.num_tareas}")
            time.sleep(1)
        print(f"[{self.name}] Ha terminado todas sus tareas.")


def monitor(intervalo, etiqueta="MONITOR"):
    # Recibe los datos con args (intervalo) y kwargs (etiqueta)
    while True:
        print(f"[{etiqueta}] Hilos vivos: {[t.name for t in threading.enumerate()]}")
        time.sleep(intervalo)


if __name__ == "__main__":
    # Hilo monitor en segundo plano (daemon)
    hilo_monitor = threading.Thread(
        target=monitor,
        args=(0.5,),
        kwargs={"etiqueta": "MONITOR"},
        name="Monitor"
    )
    hilo_monitor.daemon = True  # Se asigna ANTES de start()
    hilo_monitor.start()

    workers = [
        Worker("Worker-1", 3),
        Worker("Worker-2", 4),
        Worker("Worker-3", 5),
    ]

    for w in workers:
        w.start()
    for w in workers:
        w.join()

    print("Programa principal terminado. El monitor se cerrará solo (daemon).")
    