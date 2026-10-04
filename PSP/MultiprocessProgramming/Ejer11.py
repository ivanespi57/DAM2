import asyncio
 
 
async def open_application(application_name):
    try:
        proc = await asyncio.create_subprocess_exec(application_name)
        print(f"{application_name} abierta con PID {proc.pid}")
    except FileNotFoundError:
        print(f"Error: la aplicación '{application_name}' no existe")
    except Exception as e:
        print("Error inesperado:", e)
 
 
async def main():
    try:
        n = int(input("¿Cuántas aplicaciones quieres abrir? "))
    except ValueError:
        print("Debes introducir un número entero")
        return
 
    aplicaciones = []
    for i in range(n):
        aplicaciones.append(input(f"Nombre de la aplicación {i + 1} (notepad, gedit, cmd...): "))
 
    await asyncio.gather(*(open_application(app) for app in aplicaciones))
 
    input("Todas las aplicaciones abiertas. Pulsa Enter para cerrar el programa...")
 
 
asyncio.run(main())