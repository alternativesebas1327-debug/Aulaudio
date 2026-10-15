# AulaAudio (Android)

AulaAudio es una aplicación nativa para Android construida con **Kotlin** y **Jetpack Compose**, diseñada siguiendo los principios estéticos y funcionales del sistema de diseño **Nordic Precision**.

Proporciona un entorno minimalista, silencioso y de alta fidelidad para la grabación de clases, organización académica por asignaturas y reproducción interactiva con marcadores cronometrados y visualizador zen de ondas de audio.

---

## Características Principales

1. **Pantalla Principal (Grabaciones)**:
   - Encabezado editorial con fecha dinámica y badge minimalista con punto activo.
   - Buscador en tiempo real por título, asignatura o fecha.
   - Pestañas horizontales de filtrado por materias ("Todas", "Cálculo Integral", "Física II", etc.).
   - Tarjetas de sesión con metadatos de duración, fecha, estado de procesamiento de audio y conteo de marcadores.
   - Botón directo de reproducción rápida y gestión de favoritos.

2. **Selección de Asignatura (Modal Bottom Sheet)**:
   - Hoja inferior deslizante con control táctil para iniciar sesiones.
   - Selector por asignatura con radio indicators, aula y profesor/a a cargo.
   - Buscador de asignaturas y botón rápido (+) para registrar nuevas materias al instante.
   - Botón de acción primario con pulso visual en carmesí para iniciar grabación en vivo.

3. **Grabador Escultural y Waveform Zen**:
   - Cronómetro digital esculpido en alta tipografía (`00:42`).
   - Visualizador de ondas zen con animación reactiva a la amplitud del micrófono.
   - Soporte para pausar y reanudar sesiones en tiempo real.
   - Fijación interactiva de marcadores y notas en instantes clave durante la clase.
   - Alerta de confirmación para descartar y botón de guardado con generación automática de metadatos.

4. **Reproductor de Sesión (Player Screen)**:
   - Pista de onda de audio interactiva y scrubbable con arrastre directo a cualquier punto temporal.
   - Control de velocidad variable (`1.0x`, `1.25x`, `1.5x`, `2.0x`).
   - Salto rápido de retroceso (-15s) y adelanto (+15s).
   - Botón central de reproducción/pausa.
   - Filtro de voz y reducción de ruido ambiental.
   - Lista de "Marcadores" clave con salto directo al segundo exacto de la clase al tocarlos.
   - Exportación y compartición de clase vía Android Share Sheet (`Intent.ACTION_SEND`).

5. **Gestión de Materias**:
   - Catálogo completo de asignaturas con conteo de sesiones registradas.
   - Alta de nuevas asignaturas con aula y nombre del docente.
   - Inicio directo de grabación por asignatura.

6. **Ajustes y Preferencias**:
   - Opciones de ecualización y procesamiento de voz limpia.
   - Información de almacenamiento de grabaciones y especificaciones de audio MPEG-4 AAC (128 kbps).

---

## Arquitectura y Tecnologías

- **Lenguaje**: Kotlin 2.2.10
- **UI Framework**: Jetpack Compose con Material Design 3 (M3)
- **Audio Engine**: `android.media.MediaRecorder` para captura de audio y `android.media.MediaPlayer` para reproducción y control de velocidad con `PlaybackParams`.
- **Persistencia**: Repositorio local reactivo con `StateFlow` y persistencia en almacenamiento interno (`filesDir`).
- **Iconografía**: Iconos vectoriales personalizados `AulaIcons` con estilo minimalista nórdico.
- **Icono Adaptativo**: Icono adaptativo oficial de AulaAudio con anillos acústicos concéntricos y punto carmesí.
