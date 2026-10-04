import psutil
 
try:
    pid = int(input("PID del proceso: "))
    nueva = int(input("Nueva prioridad (-20 a 19): "))
    if nueva < -20 or nueva > 19:
        print("La prioridad debe estar entre -20 y 19")
    else:
        proc = psutil.Process(pid)
        proc.nice(nueva)
        print("Nueva prioridad del proceso:", proc.nice())
except ValueError:
    print("Debes introducir números enteros")
except psutil.NoSuchProcess:
    print("Error: el proceso no existe")
except psutil.AccessDenied:
    print("Error: no tienes permisos para cambiar la prioridad")