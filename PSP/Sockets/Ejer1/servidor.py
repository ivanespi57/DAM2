import socket
import threading

HOST = '127.0.0.1'
PORT = 6000


conexiones = []
alias_lista = []
lock = threading.Lock()  


def enviar_a_todos(mensaje, origen=None):

    with lock:
        for c in list(conexiones):
            if c is not origen:
                try:
                    c.sendall(mensaje.encode())
                except OSError:
                    pass  


def manejar_cliente(conn, addr):
    alias = None
    try:
        conn.sendall("Elige tu alias: ".encode())
        alias = conn.recv(1024).decode().strip()
        if not alias:
            alias = f"Anonimo-{addr[1]}"

        with lock:
            conexiones.append(conn)
            alias_lista.append(alias)

        print(f"[Servidor] {alias} conectado desde {addr}")
        enviar_a_todos(f"*** {alias} se ha unido al chat ***", conn)

        while True:
            data = conn.recv(1024)
            if not data:
                break
            texto = data.decode().strip()
            if texto.lower() == "salir":
                break
            print(f"[Servidor] {alias}: {texto}")
            enviar_a_todos(f"{alias}: {texto}", conn)
    except OSError:
        pass
    finally:
        with lock:
            if conn in conexiones:
                i = conexiones.index(conn)
                conexiones.pop(i)
                alias_lista.pop(i)
        conn.close()
        if alias:
            print(f"[Servidor] {alias} desconectado")
            enviar_a_todos(f"*** {alias} ha salido del chat ***")