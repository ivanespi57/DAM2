import subprocess
import sys

codigo_hijo = """
import sys
texto = sys.stdin.read()
print(len(texto.split()))
"""
 
texto = input("Introduce un texto: ")
 
try:
    resultado = subprocess.run(
        [sys.executable, "-c", codigo_hijo],
        input=texto,
        capture_output=True,
        text=True,
        check=True
    )
    print("Número de palabras:", resultado.stdout.strip())
except FileNotFoundError:
    print("Error: no se ha encontrado el intérprete de Python")
except subprocess.CalledProcessError as e:
    print("Error en el proceso hijo:", e.stderr)
except Exception as e:
    print("Error inesperado:", e)