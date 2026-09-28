import os

def child():
    print('\n>>>>>>>>>> New child created with pid %d is going to finish <<<<<' % (os.getpid() % os.getppid()))
    os._exit(0)
def parent():
    numHijos = int(input("Cuántos hijos quieres?"))
    hijosCreados = 0
    while hijosCreados < numHijos:
        newpid = os.fork()

        if newpid == 0:
            child()
        else:
            pids = (os.getpid(), newpid)
            print("Parent: %d, Child: %d\n" % pids)
            reply = input("Presione 's' para continuar")

            if reply != 's':
                break

    for i in range(hijosCreados):
        os.wait()
parent()