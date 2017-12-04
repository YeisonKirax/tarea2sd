import java.io.*;
import java.rmi.*;

interface Proceso extends Remote {
    public int getId() throws RemoteException; 
    public void modificarValorRN(int id, int nuevoValor) throws RemoteException;
    public int obtenerValorRN(int indice) throws RemoteException;
    public void removerValorRN(int indice) throws RemoteException;
    public void actualizarNsequencia() throws RemoteException;
    public void asignarIndicadorProceso(int valor) throws RemoteException;
}
