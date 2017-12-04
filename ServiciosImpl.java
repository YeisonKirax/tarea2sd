import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServiciosImpl extends UnicastRemoteObject implements Servicios {
    List<Proceso> procesos;
    ServiciosImpl() throws RemoteException {
        procesos = new LinkedList<Proceso>();
    }
    public Proceso crearProceso(int id, int n, int initialDelay, Boolean bearer, Token token) throws RemoteException{
      Proceso pro = new ProcesoImpl(id, n, initialDelay, bearer, token);
      return pro;
    }
    public void agregarProceso(Proceso proc) throws RemoteException {
        procesos.add(proc);
    }
    public List<Proceso> obtenerProcesos() throws RemoteException{
        return procesos;
    }
    public void request(int id, int seq) throws RemoteException{

    }
    public void waitToken() throws RemoteException{

    }
    public void takeToken(Token token) throws RemoteException{

    }
    public void kill() throws RemoteException{

    }
}
