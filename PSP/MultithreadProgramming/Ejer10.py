import threading
import time

barrera = threading.Barrier(4)


def trabajador(id, tiempo_fase1):
    print(f"[Hilo {id}] Fase 1: trabajando ({tiempo_fase1}s)...")
    time.sleep(tiempo_fase1)
    print(f"[Hilo {id}] Fase 1 terminada. Esperando en la barrera...")

    try:
        llegada = barrera.wait()  # Nadie pasa hasta que lleguen los 4

        # El hilo que recibe 0 imprime el resumen una sola vez
        if llegada == 0:
            print("\n>>> Resumen: los 4 hilos han terminado la Fase 1. Empieza la Fase 2.\n")

        print(f"[Hilo {id}] Fase 2: empezando...")
    except threading.BrokenBarrierError:
        print(f"[Hilo {id}] Error: la barrera se ha roto.")


if __name__ == "__main__":
    hilos = []
    for i in range(4):
        t = threading.Thread(target=trabajador, args=(i + 1, i + 1))
        hilos.append(t)
        t.start()

    for t in hilos:
        t.join()

    print("\nPrograma principal terminado.")