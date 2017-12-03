import java.rmi.*;
import java.rmi.server.*;

class ServiciosRemotosImpl extends UnicastRemoteObject implements ServiciosRemotos {
  ServiciosRemotosImpl() throws RemoteException {}
  public String eco(String s, String i) throws RemoteException {
    return "Recibido desde proceso "+ i +", "+s.toUpperCase();
  }
  //public void request(int id, int seq){}
  //public void waitToken(){}
  //public void takeToken(Token token){}
  //public void kill(){}
}
