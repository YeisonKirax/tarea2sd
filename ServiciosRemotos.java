import java.rmi.*;

interface ServiciosRemotos extends Remote {
  String eco(String s) throws RemoteException;
  //void request(int id, int seq) throws RemoteException;
  //void waitToken() throws RemoteException;
  //void takeToken(Token token) throws RemoteException;
  //void kill() throws RemoteException;
}
