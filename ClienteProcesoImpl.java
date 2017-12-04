import java.rmi.*;
import java.rmi.server.*;

class ClienteProcesoImpl extends UnicastRemoteObject implements ClienteRemoto {
    ClienteProcesoImpl() throws RemoteException {
    }
    public void notificacion(String apodo, String m) throws RemoteException {
	System.out.println("\n" + apodo + "> " + m);
    }
    public void yotengotoken(int id, int seq, ArrayList t) throws RemoteException{
        for()

    }
    public void takeToken(Token token) throws RemoteException{
        System.out.println("2");

    }
    public void waitToken() throws RemoteException{
        System.out.println("3");

    }
    public void killprocess() throws RemoteException{
        System.out.println("4");

    }
}
