import java.rmi.*;
import java.util.*;

interface Servicios extends Remote {
  public Proceso crearProceso(int id, int n, int initialDelay, Boolean bearer, Token token) throws RemoteException;
  public void agregarProceso(Proceso proc) throws RemoteException;
  public List<Proceso> obtenerProcesos() throws RemoteException;
  public void request(int id, int seq) throws RemoteException;
  public void waitToken() throws RemoteException;
  public void takeToken(Token token) throws RemoteException;
  public void kill() throws RemoteException;

}
