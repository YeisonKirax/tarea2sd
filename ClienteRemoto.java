
import java.rmi.*;

interface ClienteRemoto extends Remote {
    void notificacion(String apodo, String m) throws RemoteException;
    void yotengotoken(int id, int seq) throws RemoteException;
    void takeToken(Token token) throws RemoteException;
    void waitToken() throws RemoteException;
    void killprocess() throws RemoteException;
}
