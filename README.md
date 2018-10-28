# Asignatura: *FEM: Frontend para Móviles*
### [Máster en Ingeniería Web por la U.P.M.](http://miw.etsisi.upm.es)
## Persistencia de Datos
##### Autor: Alejandro Puebla Holguín


> En este proyecto se ha trabajado sobre los aspectos relacionados con la persistencia de datos (empleando
preferencias, ficheros y SQLite). Para realizar estas funcionalidades se ha desarrollado el juego Solitario Celta,
que consiste en ir saltando piezas en horizontal o en vertical con el objetivo de que finalmente quede el menor número de piezas en el tablero.

## Tareas
#### 1 - Reiniciar partida
* Al pulsar el botón "reiniciar" se mostrará un diálogo de confirmación. En caso de respuesta afirmativa se procederá a reiniciar la partida actual.

#### 2 - Guardar partida
* Esta opción permite guardar la situación actual del tablero. Sólo es necesario guardar una única partida y se empleará el sistema de ficheros del dispositivo.

#### 3 - Recuperar partida
* Si existe, recupera el estado de una partida guardada (si se hubiera modificado la partida actual, se solicitará confirmación)

#### 4 - Guardar puntuación
* Al finalizar cada partida se deberá guardar la información necesaria para generar un listado de resultados. Dicho listado deberá incluir -al menos- el nombre del jugador, el día y hora de la partida y el número de piezas que quedaron en el tablero. La información se deberá almacenar en una base de datos.

#### 5 - Mejores resultados
* Esta opción mostrará el histórico con los mejores resultados obtenidos ordenados por número de piezas. Incluirá una opción -con confirmación- para eliminar todos los resultados guardados.

#### OPCIONAL
* Mostrar el número de tokens restantes
* Cuando acaba una partida, se le pide al usuario un nombre para guardar esta en base de datos


