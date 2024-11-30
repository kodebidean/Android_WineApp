
# WineApp 🍷

WineApp es un proyecto educativo de Android diseñado para aprender e implementar conceptos clave del desarrollo móvil moderno, incluyendo:
- **Retrofit** para consumo de APIs.
- **SwipeRefreshLayout** para recarga manual de contenido.
- **Glide** para cargar imágenes de forma eficiente.
- **RecyclerView** con `ListAdapter` para listas dinámicas.
- Y mucho más.

Este proyecto se desarrolla en etapas progresivas para explorar cada tecnología y técnica utilizada en aplicaciones Android.

---

## Índice 📚

1. [Acerca del Proyecto](#acerca-del-proyecto)
2. [Tecnologías y Versiones](#tecnologías-y-versiones)
3. [Pasos Implementados](#pasos-implementados)
4. [Requisitos del Sistema](#requisitos-del-sistema)
5. [Ejecución del Proyecto](#ejecución-del-proyecto)
6. [Licencia](#licencia)

---

## Acerca del Proyecto

WineApp es una aplicación simple que muestra una lista de vinos mediante el consumo de una API de terceros (actualmente simulada). Los vinos se presentan en un diseño de cuadrícula usando **RecyclerView** y se cargan dinámicamente con **Retrofit**. 

Se implementa soporte para:
- Actualización manual del contenido mediante **SwipeRefreshLayout**.
- Manejo de imágenes con **Glide**.
- Control de errores en las llamadas a la API.

---

## Tecnologías y Versiones

- **Lenguaje:** Kotlin 1.9.10
- **Android SDK:** Compile SDK 35, Min SDK 24, Target SDK 34
- **Android Gradle Plugin (AGP):** 8.2.0
- **Retrofit:** 2.9.0
- **Glide:** 4.16.0
- **Kotlin Coroutines:** 1.6.4
- **SwipeRefreshLayout:** Versión estándar de AndroidX
- **Jetpack Components:**
  - RecyclerView
  - ViewBinding
  - Lifecycle (para `lifecycleScope`)

---

## Pasos Implementados

Actualmente, la documentación detallada de cada paso se encuentra en desarrollo y estará disponible próximamente. Aquí tienes un resumen de los pasos principales:
1. **PASO 01:** Selección de API para el consumo de datos.
2. **PASO 02:** Configuración inicial del proyecto en Android Studio.
3. **PASO 03:** Creación de las clases de datos (`DataClass`) necesarias.
4. **PASO 04:** Diseño del archivo `item.xml` para la interfaz de cada elemento.
5. **PASO 05:** Configuración de `RecyclerView` para mostrar listas de manera eficiente.
6. **PASO 06:** Implementación de `ListAdapter` para manejar las listas dinámicas.
7. **PASO 07:** Configuración de la lógica principal en `MainActivity`.
8. **PASO 08:** Uso de **Glide** para cargar imágenes.
9. **PASO 09:** Mejoras en la interfaz de usuario.
10. **PASO 10:** Consumo de datos desde una API con **Retrofit**.
11. **PASO 11:** Implementación de **SwipeRefreshLayout** para recargar datos manualmente.

---

## Requisitos del Sistema

- **Sistema Operativo:** Windows, macOS o Linux
- **Android Studio:** Arctic Fox (2020.3.1) o superior
- **Gradle:** 8.2.0
- **JDK:** Java 11 o superior
- **Dispositivo:** Teléfono o emulador con Android 8.0 (API 24) o superior

---

## Ejecución del Proyecto

1. Clona el repositorio:
   ```bash
   git clone https://github.com/kodebidean/Android_WineApp.git
   ```
2. Abre el proyecto en Android Studio.
3. Sincroniza el proyecto con Gradle.
4. Ejecuta la aplicación en un emulador o dispositivo físico.

---

## Licencia

El proyecto está bajo la [Licencia MIT](./LICENSE). Puedes usarlo, modificarlo y distribuirlo con total libertad. 😊

---

### Nota

Este proyecto está diseñado exclusivamente para fines educativos y para explorar las tecnologías mencionadas.

---


