import java.rmi.*;
import java.rmi.server.*;

class ClienteProcesoImpl extends UnicastRemoteObject implements Cliente {
    ClienteProcesoImpl() throws RemoteException {
    }
    public void notificacion(String apodo, String m) throws RemoteException {
	System.out.println("\n" + apodo + "> " + m);
    }
}
