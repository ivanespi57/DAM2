# fork only works in Windows
from multiprocessing import Process
import os
def child():
    print("Parent: %d, Child: %d\n" % (os.getppid(), os.getpid()))
    os._exit(0)
def parent():
    while True:
        p = Process(target=child)
        p.start()
        print("\nNew child created ", p.pid)
        p.join()
        reply = input("Presiona 's' para continuar\n")

        if reply != 's':
            break

if __name__ == '__main__':
    parent()