class Persona:
    def __init__(self, nom=None, edad=None, dni=None):
        self.__nom = ""
        self.__edad = 0
        self.__dni = ""
        if nom != None:
            self.cambiar_nombre(nom)
        if edad != None:
            self.cambiar_edad(edad)
        if dni != None:
            self.cambiar_dni(dni)

    def cambiar_nombre(self, nom):
        if type(nom) == str and len(nom) > 0:
            self.__nom = nom
        else:
            print("Error: el nombre no puede estar vacío")

    def obtener_nombre(self):
        return self.__nom

    def cambiar_edad(self, edad):
        if type(edad) == int and edad >= 0 and edad <= 120:
            self.__edad = edad
        else:
            print("Error: la edad tiene que ser un entero entre 0 y 120")

    def obtener_edad(self):
        return self.__edad

    def cambiar_dni(self, dni):
        if type(dni) == str and len(dni) > 0:
            self.__dni = dni
        else:
            print("Error: el DNI no puede estar vacío")

    def obtener_dni(self):
        return self.__dni

    def mostrar(self):
        print(f"Nombre: {self.__nom}")
        print(f"Edad: {self.__edad}")
        print(f"DNI: {self.__dni}")

    def es_mayor_de_edad(self):
        return self.__edad >= 18


class Cuenta:
    def __init__(self, titular, cant=0):
        self.__titular = titular
        self.__cant = cant

    def cambiar_titular(self, titular):
        self.__titular = titular

    def obtener_titular(self):
        return self.__titular

    def obtener_cantidad(self):
        return self.__cant

    def mostrar(self):
        print("Titular:")
        self.__titular.mostrar()
        print(f"Cantidad: {self.__cant}")

    def ingresar(self, cant):
        if cant > 0:
            self.__cant = self.__cant + cant

    def retirar(self, cant):
        if cant > 0:
            self.__cant = self.__cant - cant


class CuentaJoven(Cuenta):
    def __init__(self, titular, cant=0, bonificacion=0):
        super().__init__(titular, cant)
        self.__bonificacion = 0
        self.cambiar_bonificacion(bonificacion)

    def cambiar_bonificacion(self, bonificacion):
        if bonificacion >= 0 and bonificacion <= 100:
            self.__bonificacion = bonificacion
        else:
            print("Error: la bonificación tiene que estar entre 0 y 100")

    def obtener_bonificacion(self):
        return self.__bonificacion

    def es_titular_valido(self):
        titular = self.obtener_titular()
        if titular.es_mayor_de_edad() and titular.obtener_edad() < 25:
            return True
        else:
            return False

    def retirar(self, cant):
        if self.es_titular_valido():
            super().retirar(cant)
        else:
            print("No se puede retirar dinero: el titular no es válido")

    def mostrar(self):
        return f"Cuenta Joven - Bonificación: {self.__bonificacion}%"


def main():
    ana = Persona("Ana", 22, "12345678A")
    ana.mostrar()
    print(f"¿Es mayor de edad? {ana.es_mayor_de_edad()}")
    ana.cambiar_edad(-5)

    print()
    cuenta = Cuenta(ana, 100.5)
    cuenta.ingresar(50)
    cuenta.ingresar(-20)
    cuenta.retirar(300)
    cuenta.mostrar()

    print()
    joven = CuentaJoven(ana, 200, 10)
    print(joven.mostrar())
    joven.retirar(50)
    print(f"Cantidad de la cuenta joven: {joven.obtener_cantidad()}")

    print()
    luis = Persona("Luis", 40, "87654321B")
    joven_luis = CuentaJoven(luis, 100, 5)
    print(f"¿Titular válido? {joven_luis.es_titular_valido()}")
    joven_luis.retirar(20)
    print(f"Cantidad: {joven_luis.obtener_cantidad()}")


if __name__ == "__main__":
    main()