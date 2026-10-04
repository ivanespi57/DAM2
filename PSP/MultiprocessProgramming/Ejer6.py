import psutil
 
for proc in psutil.process_iter():
    try:
        print(proc.name(), ' :: ', proc.pid, ' :: ', proc.nice())
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        print("error")