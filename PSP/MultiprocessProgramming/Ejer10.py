import subprocess
import asyncio
 
 
def execute_synchronous_command(command):
    try:
        resultado = subprocess.run(command, capture_output=True, text=True, check=True)
        print(resultado.stdout)
    except FileNotFoundError:
        print("Error: el comando no existe")
    except subprocess.CalledProcessError as e:
        print("Error: el comando terminó con código", e.returncode)
    except Exception as e:
        print("Error inesperado:", e)
 
 
async def execute_asynchronous_command(command):
    try:
        proc = await asyncio.create_subprocess_exec(
            *command,
            stdout=asyncio.subprocess.PIPE,
            stderr=asyncio.subprocess.PIPE
        )
        stdout, stderr = await proc.communicate()
        print(stdout.decode())
        if proc.returncode != 0:
            print("Error: el comando terminó con código", proc.returncode)
    except FileNotFoundError:
        print("Error: el comando no existe")
    except Exception as e:
        print("Error inesperado:", e)
 
 
async def main():
    comando = input("Introduce el comando (ej: ping www.google.com -c 4): ").split()
 
    print("--- Ejecución síncrona ---")
    execute_synchronous_command(comando)
 
    print("--- Ejecución asíncrona ---")
    await execute_asynchronous_command(comando)
 
 
asyncio.run(main())