from multiprocessing import Process, Queue
 
 
def contar(n, q):
    suma = 0
    for i in range(1, n + 1):
        suma += i
    print(f"Hijo: la suma total es {suma}")
    q.put(suma)
 
 
if __name__ == '__main__':
    try:
        n = int(input("¿Hasta qué número (N) debe contar el hijo? "))
    except ValueError:
        print("Debes introducir un número entero")
    else:
        q = Queue()
        p = Process(target=contar, args=(n, q))
        p.start()
        suma = q.get()
        p.join()
        print(f"Padre: la suma total obtenida por el hijo es {suma}")