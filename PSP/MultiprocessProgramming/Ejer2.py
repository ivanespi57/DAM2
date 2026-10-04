import os
import time
 
 
def hijo():
    for i in range(1, 6):
        print(i)
        time.sleep(1)
    os._exit(0)
 
 
newpid = os.fork()
if newpid == 0:
    hijo()
else:
    os.wait() 
    print("The child process has finished.")