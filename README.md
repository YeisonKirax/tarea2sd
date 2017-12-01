# tarea2sd
# Por el momento la forma de ejecucion en la consola es (se debe estar en la carpeta donde estan los arhivos:
#1)dir_archivos/xx/:-$ rmiregistry 54321 //
#2)dir_archivos/xx/:-$ javac *.java (en una nueva terminal en la misma direccion) //
#3)dir_archivos/xx/:-$ java -Djava.security.policy=ALL.permisos ServidorProceso 54321(en una nueva terminal) //
#4)dir_archivos/xx/:-$ java -Djava.security.policy=ALL.permisos ClienteProceso localhost 54321 adasd(en una nueva terminal) //

Por ahora es un programa simple que lo que hace es que el cliente envia un string ingresado (en este caso adasd) y invocando el metodo remoto del server, este ultimo lo regresa en mayusculas.
Notar que 54321 es un puerto y es necesario tanto para el rmiregistry y el servidor y cliente
