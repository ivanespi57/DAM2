import subprocess
 
while True:
    comando = input("Introduce un comando con sus parámetros (ej: ping www.google.com -c 5): ")
    try:
        subprocess.run(comando.split(), check=True)
    except FileNotFoundError:
        print("Error: el comando no existe")
    except subprocess.CalledProcessError as e:
        print("Error: el comando terminó con código", e.returncode)
    except Exception as e:
        print("Error inesperado:", e)
 
    otra = input("¿Quieres ejecutar otro comando? (s/n): ")
    if otra != 's':
        break