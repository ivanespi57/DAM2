import subprocess
import sys
 
try:
    resultado = subprocess.run(
        [sys.executable, "script.py"],
        capture_output=True,
        text=True,
        check=True
    )
    print("Salida del script:")
    print(resultado.stdout)
except FileNotFoundError:
    print("Error: no se ha encontrado el intérprete o el script")
except subprocess.CalledProcessError as e:
    print("Error al ejecutar el script:", e.stderr)
except Exception as e:
    print("Error inesperado:", e)