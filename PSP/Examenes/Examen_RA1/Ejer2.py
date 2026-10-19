from random import random
from multiprocessing import Process, Pipe
import os
import sys

def hijo():
    numeros = [random.randint(1, 3) for _ in range(3)]
    print("Intento 1: números recibidos: ", numeros, file=sys.stderr)
    print(' '.join(str(n) for n in numeros))
    
if __name__ == '__main__':
    timeouts = [2, 4, 6]
    procesos = []

    for i, t in enumerate(timeouts, start=1):
        p = Process(target=hijo, args=(f"Hijo {i}", t)) 
        procesos.append(p)
        p.start() 

    for p in procesos:
        p.join()

    print("El padre ha comprobado que los tres hijos han terminado.")

        

