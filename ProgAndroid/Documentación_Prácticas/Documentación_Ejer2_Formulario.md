# Práctica 2: Formulario de registro de usuario

**PMDM - UP5 Componentes básicos - 2º DAM**

## 1. Qué hace la app

Es una pantalla de registro hecha con Jetpack Compose. Pide nombre, apellidos, género, contraseña y tiene un Switch para aceptar las condiciones de uso. Al final hay un botón "Registrarse" que solo se puede pulsar cuando todo está completo.

## 2. Cómo está hecho el código

Hay una función principal, `FormularioPadre`, que guarda todos los datos del formulario. Después hay una función para cada componente:

- `MiFormulario`: campo del nombre.
- `MiSegundoFormulario`: campo de los apellidos.
- `MiRadioButtonForm`: elegir Masculino o Femenino.
- `MiFormularioPassword`: campo de la contraseña.
- `MiSwitchCondiciones`: Switch de aceptar condiciones.
- `MiBotonRegistro`: botón de registrarse.

Los datos se guardan en el padre y se los paso a cada función hija. Cuando el usuario cambia algo, la hija avisa al padre para que actualice el valor.

## 3. Explicación de cada parte

**Estados:** uso `rememberSaveable` con `mutableStateOf` para guardar lo que escribe el usuario. Así no se pierde la información, por ejemplo al girar el móvil.

**Género:** guardo el género en una sola variable (`genero`). Como los dos RadioButton usan la misma variable, solo se puede marcar uno.

**Contraseña:** uso `PasswordVisualTransformation` para que se vean puntos en vez de letras. Hay un texto "Mostrar/Ocultar" que cambia una variable para enseñar u ocultar la contraseña. También puse `KeyboardType.Password` para que salga el teclado de contraseñas.

**Switch:** guarda si se han aceptado las condiciones (`true` o `false`).

**Botón:** está habilitado solo si el nombre, los apellidos, el género y la contraseña no están vacíos y el Switch está activado. Esa condición la guardo en `formularioCompleto` y se la paso al botón con `enabled`. Con `ButtonDefaults.buttonColors` lo pongo verde cuando está habilitado y gris cuando no.

**Mensaje:** al pulsar el botón, la variable `registrado` pasa a `true` y sale un texto que confirma el registro.

**Responsive:** la `Column` usa `fillMaxWidth()` para ocupar todo el ancho y `verticalScroll` para poder hacer scroll si no cabe todo en la pantalla.

## 4. Conclusión

Con esta práctica he aprendido a usar TextField, RadioButton, Switch y Button en Compose, y a guardar todo el estado en el composable padre para que los hijos solo muestren los datos y avisen de los cambios.