Integrantes: Jordan Esquivel
             Yeison Fernandez

##Ejecucion

Para ejecutar el programa, es necesario que todos los archivos esten en una
carpeta, luego desde la carpeta en la terminal n° 1 ejecutar:

1. **make**
2. **make run-rmiregistry**

Importante considerar que con "make run-rmiregistry" inicia el rmiregistry
con un puerto predeterminado, el cual es 54321, tener en consideracion tener
este puerto libre. Por defecto este se ejecutará en segundo plano, si desea
matar este proceso deberá hacerlo manualmente.
Continuando con la ejecucion:

3. **make run-server**
Este se encarga de correr el servidor con los metodos remotos, es importante
dejarlo corriendo en una terminal por separado pues en esa terminal se podrá
observar como cambian los estados (colores del semaforo) de los procesos.

Luego en terminales distintas, segun la cantidad de procesos que ejecutará,
ejecutar:

4. **make run-process id=valor_1 n=valor_2 initialDelay=valor_3 bearer=valor_4**
Donde valor_n con n :{1,2,3,4} corresponde a los datos solicitados en el
enunciado de la tarea.


##Consideracione

1. Se espera que los inputs entregados sean los ideales, por ejemplo:
id es un entero, n es un entero, initialDelay es un entero(que representa
  los milisegundos) y bearer es un booleano (true, false).
2. Para apreciar mejor el delay de cada proceso, seria bueno que el initialDelay
   fuese del orden de los segundos(1000 ms, para tener tiempo de ejecutar
     manualmente los procesos)
3. Para hacer un waitToken(), se asume que el tiempo que deberá un proceso
esperar será igual al initialDelay.
4. Se asume que siempre abrá inicialmente un proceso con el token, el cual se
ira trasladando entre procesos.
5. La funcion kill() mata solo los "ClienteProceso"(lo ejecutado con
  run-process), para matar al server debe ir a la terminal n°1 y hacer un ctrl+c.
6. No se realizó el bonus.

## Clases definidas

1. **Token**: corresponde a una clase Token, la cual es serializable. Contiene
metodos utiles para manipular el Token.
2. **ClienteProceso**: corresponde a la clase main correspondiente a los Clientes
En ella se hace un lookup de los metodos remotos del servidor y se instancia el
proceso creado.
3. **Proceso**: corresponde a una interfaz remota que representa a un proceso, en
el codigo se pueden ver los metodos de esta.
4. **ProcesoImpl**: corresponde a una clase que extiende la clase
UnicastRemoteObject y implementa la interfaz **Proceso**. En ella se puede
ver las variables y metodos definidos en un proceso.
5. **Servicios**: corresponde a una interfaz remota que tiene los metodos
remotos del servidor. Es importante destacar que aparte de los metodos exigidos
en la tarea, se crearon mas facilitar la realizacion de esta, los cuales fueron:
crearProceso, agregarProceso, cambiarVengodeCola, obtenerProcesos y PrintColors
6. **ServiciosImpl**: corresponde a una clase que extiende la clase
UnicastRemoteObject y implementa la interfaz **Servicios**, se crean y definen
los metodos necesarios.
7. **Servidor**: corresponde a la clase del servidor, en el se realiza el
*naming.rebind* de los metodos remotos establecidos.
