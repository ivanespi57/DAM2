import subprocess
import sys

codigo_generador = """
import random, sys
numeros = [random.randint(1, 100) for _ in range(10)]
print("Números generados:", numeros, file=sys.stderr)
print(' '.join(str(n) for n in numeros))
"""

codigo_sumador = """
import sys
numeros = [int(n) for n in sys.stdin.read().split()]
print("Suma total de los números recibidos:", sum(numeros))
"""
 
try:
    p1 = subprocess.Popen([sys.executable, "-c", codigo_generador], stdout=subprocess.PIPE, text=True)
    p2 = subprocess.Popen([sys.executable, "-c", codigo_sumador], stdin=p1.stdout, text=True)
    p1.stdout.close()
    p2.wait()
    p1.wait()
except FileNotFoundError:
    print("Error: no se ha encontrado el intérprete de Python")
except Exception as e:
    print("Error inesperado:", e)