# Práctica 5: Pedido de comida rápida — Dificultades y soluciones

**Alumno:** Iván Espí Asins

---

Al principio parecía fácil porque cada componente lo habíamos visto por separado en los apuntes. Pero juntarlos todos en una sola pantalla ha sido más difícil.

---

## 1. Dificultades y cómo las solucioné

### 1.1. El enunciado pedía un RangeSlider, pero se hacía con un Slider

El enunciado hablaba de un `RangeSlider`, pero el picante es un único nivel (de 0 a 10), no un rango con dos valores. Por eso lo hice con un `Slider` normal, que además es más sencillo de manejar.

---

### 1.2. El menú desplegable (DropdownMenu)

**El problema:** No tenía claro cómo se conectaban el botón y el menú. Probé a poner el `DropdownMenu` suelto y no se veía donde yo quería.

**Cómo lo solucioné:** El `DropdownMenu` se "ancla" a la vista que tiene alrededor, así que lo metí dentro de un `Box` junto con el `Button`:

```kotlin
Box {
    Button(onClick = { expandido = true }) { Text(...) }
    DropdownMenu(expanded = expandido, onDismissRequest = { expandido = false }) {
        // los DropdownMenuItem
    }
}
```

Otros detalles que tuve que pensar:

- Necesitaba una variable `expandido` para saber si estaba abierto o cerrado.
- Al elegir una opción hay que hacer **dos cosas**: guardar la opción en el padre (`onSeleccion(opcion)`) y **plegar el menú** (`expandido = false`). Al principio se me olvidaba lo segundo y el menú se quedaba abierto.
- Para generar los items sin repetir código usé una lista y `forEach`, igual que hicimos con los checkbox.
- Si todavía no se ha elegido nada, el botón muestra "Elegir hamburguesa"; cuando se elige, muestra el nombre de la hamburguesa.

---

### 1.3. Que el resumen se actualice "en directo"

**El problema:** Tenía miedo de tener que programar algo especial para que el resumen cambiara al tocar los componentes.

**Solución:** No hizo falta nada especial. Como el resumen (`MiResumenComida`) recibe los valores del padre por parámetro, cada vez que cambia un estado Compose **recompone** y el resumen se actualiza solo. Esto fue lo que mejor me hizo entender para qué sirven los estados y la recomposición.

Además, para la hamburguesa uso un `if` dentro del texto: si no hay ninguna elegida muestra "Sin elegir", y para el extra de queso muestra "Sí" o "No".

---

## 2. Qué he aprendido

- Una variable normal **no vale** para guardar lo que el usuario escribe; hay que usar estados (`mutableStateOf`).
- `remember` guarda entre recomposiciones, pero `rememberSaveable` también aguanta la **rotación** de pantalla.
- Los componentes se pueden reutilizar cambiando solo los parámetros (como el `MiTextFieldComida`).