from multiprocessing import Process, Queue
import os

def hijo1(n1, q):
    suma1 = 0
    for i in range(1, 6):
        suma1 += i
    print(f"[Proceso 1] PID Hijo: {os.getpid()} | PID Padre: {os.getppid()} | Intervalo: 1-5 | Suma parcial: {suma1}")
    q.put(suma1) 


def hijo2(n2, q):
    suma2 = 0
    for i in range(6, 11):
        suma2 += i
    print(f"[Proceso 2] PID Hijo: {os.getpid()} | PID Padre: {os.getppid()} | Intervalo: 1-5 | Suma parcial: {suma2}")
    q.put(suma2) 



if __name__ == '__main__':
    try:
        n1 = 5
        n2 = 10
    except ValueError:
        print("Debes introducir un número entero")
    else:
        print("=== Coordinador de cálculo ===")
        print("Se crearán 2 procesos para sumar los números del 1 al 10.")
        q = Queue() 
        p1 = Process(target=hijo1, args=(n1, q))
        p1.start()
        suma1 = q.get()
        p1.join()
        print(f"[Coordinador] Resultado recibido para el intervalo 1-5: {suma1}")

        p2 = Process(target=hijo2, args=(n2, q))
        p2.start()
        suma2 = q.get()
        p2.join()
        print(f"[Coordinador] Resultado recibido para el intervalo 6-10: {suma2}")
        
        total = suma1 + suma2
        print(f"[Coodinador] Suma total: {total}")
        print(f"Los dos procesos han terminado")
        print(f"=== Fin del programa ===")
