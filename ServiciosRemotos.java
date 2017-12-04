import java.rmi.*;
import java.util.*;
interface ServiciosRemotos extends Remote {
  String eco(String s, String i) throws RemoteException;
  void agregarproceso(int id, ClienteRemoto c, ArrayList t) throws RemoteException;
  void request(int id, int seq) throws RemoteException;
  void tepasoeltoken(int iddestino, Token token) throws RemoteException;
  void esperatoken(int iddestino) throws RemoteException;
  void kill() throws RemoteException;
}
