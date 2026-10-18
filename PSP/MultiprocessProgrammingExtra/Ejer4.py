from multiprocessing import Process, Pipe

def emisor(conn):
    mensaje = "Hola, este es el mensaje enviado por el primer proceso"
    conn.send(mensaje)
    print(f"Emisor (proceso 1) ha enviado: {mensaje}")
    conn.close()

def receptor(conn):
    mensaje = conn.recv()
    print(f"Receptor (proceso 2) ha recibido: {mensaje}")
    conn.close()

if __name__ == '__main__':
    extremo_recibir, extremo_enviar = Pipe()

    p1 = Process(target=emisor, args=(extremo_enviar,))
    p2 = Process(target=receptor, args=(extremo_recibir,))
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("Comunicación terminada.")