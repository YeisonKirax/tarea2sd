
import java.rmi.*;

interface ClienteRemoto extends Remote {
    void notificacion(String apodo, String m) throws RemoteException;
}
