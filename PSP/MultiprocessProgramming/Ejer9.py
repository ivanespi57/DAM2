import time
import psutil
 
for proc in psutil.process_iter():
    try:
        print(proc.name(), ' :: ', proc.pid)
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        pass
 
try:
    pid = int(input("PID del proceso seleccionado: "))
    proc = psutil.Process(pid)
    reply = input(f"¿Quieres matar el proceso {proc.name()} ({pid})? (s/n): ")
    if reply == 's':
        proc.kill()

        while proc.is_running() and proc.status() != psutil.STATUS_ZOMBIE:
            print("El proceso sigue activo...")
            time.sleep(0.2)
        print("El proceso ha terminado")
    else:
        print("No se ha terminado el proceso")
except ValueError:
    print("Debes introducir un número entero")
except psutil.NoSuchProcess:
    print("El proceso ya ha terminado o no existe")
except psutil.AccessDenied:
    print("Error: no tienes permisos para terminar el proceso")