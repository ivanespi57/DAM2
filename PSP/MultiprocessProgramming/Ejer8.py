import psutil
 
for proc in psutil.process_iter():
    try:
        print(proc.name(), ' :: ', proc.pid)
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        pass
 
nombre = input("Nombre del proceso que quieres terminar: ")
encontrado = False
for proc in psutil.process_iter():
    try:
        if proc.name() == nombre:
            encontrado = True
            pid = proc.pid
            print("Killing process: ", nombre, ' ::: ', pid)
            proc.kill()
            print("Proceso", pid, "terminado correctamente")
    except psutil.NoSuchProcess:
        print("Error: el proceso ya no existe")
    except psutil.AccessDenied:
        print("Error: no tienes permisos para terminar el proceso")
    except psutil.ZombieProcess:
        print("Error: el proceso es un zombie")
 
if not encontrado:
    print("No existe ningún proceso con ese nombre")