import threading
import time

def my_message(message, times):

    for _ in range(times):
        print(message)
        time.sleep(1)

if __name__ == '__main__':

    t1 = threading.Thread(target=my_message, args=("Hilo 1", 3))
    t2 = threading.Thread(target=my_message, args=("Hilo 2", 5))
    t3 = threading.Thread(target=my_message, args=("Hilo 3", 7))
    inicio_start = time.time()

    t1.start()
    t2.start()
    t3.start()
    print(f"Hilos activos: {threading.active_count()}")

    t1.join()
    t2.join()
    t3.join()
    tiempo_start = time.time() - inicio_start

    print(f"El programa ha acabado de ejecutarse con start(). Tiempo total:", tiempo_start)
    
    t4 = threading.Thread(target=my_message, args=("Hilo 1", 3))
    t5 = threading.Thread(target=my_message, args=("Hilo 2", 5))
    t6 = threading.Thread(target=my_message, args=("Hilo 3", 7))
    inicio_run = time.time()

    t4.run()
    t5.run()
    t6.run()
    tiempo_run = time.time() - inicio_run
    
    print(f"Hilos activos: {threading.active_count()}")
    print(f"El programa ha acabado de ejecutarse con run(). Tiempo total:", tiempo_run)

    print("Diferencia entre los dos tiempos: ", tiempo_run - tiempo_start)