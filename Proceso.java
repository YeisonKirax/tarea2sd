import java.io.*;
import java.rmi.*;

interface Proceso extends Remote {
    //getters
    public int getId() throws RemoteException;
    public int getSN() throws RemoteException;
    public int getInitialDelay()  throws RemoteException;
    public Boolean getBearer() throws RemoteException;
    public Token getToken() throws RemoteException;
    public int getEstado()  throws RemoteException;

    //Lista
    public void modificarValorRN(int id, int nuevoValor) throws RemoteException;
    public int obtenerValorRN(int indice) throws RemoteException;
    public void removerValorRN(int indice) throws RemoteException;
    public void setBearer(Boolean val) throws RemoteException;
    //Extra
    public void actualizarNsequencia() throws RemoteException;
    public void asignarIndicadorProceso(int valor) throws RemoteException;
    public void cambiarEstado(int nuevoestado) throws RemoteException;

    //Token
    public void modificarToken(Token token) throws RemoteException;
}
