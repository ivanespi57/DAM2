from multiprocessing import Process
import os
 
 
def hijo():
    print("Child: %d, Parent: %d" % (os.getpid(), os.getppid()))
 
 
def padre():
    try:
        total = int(input("¿Cuántos procesos hijos quieres crear? "))
    except ValueError:
        print("Debes introducir un número entero")
        return
 
    procesos = []
    for i in range(total):
        p = Process(target=hijo)
        p.start()
        procesos.append(p)
        if i < total - 1:
            reply = input("Pulsa 'y' para crear otro proceso (otra tecla para parar): ")
            if reply != 'y':
                break
 
    for p in procesos:
        p.join()
    print("Todos los procesos hijos han terminado")
 
 
if __name__ == '__main__':
    padre()
 