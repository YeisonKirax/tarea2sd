import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class ServiciosRemotosImpl extends UnicastRemoteObject implements ServiciosRemotos {
  Map<Integer, ClienteRemoto> l;
  Map<Integer, ArrayList> K;
  ServiciosRemotosImpl() throws RemoteException {
    l = new HashMap<Integer, ClienteRemoto>();
  }
  public void agregarproceso(int id, ClienteRemoto c, ArrayList t) throws RemoteException {
    l.put(id, c);
    K.put(id, t);
    for (Map.Entry<Integer, ClienteRemoto> e:l.entrySet()) {
      int key = e.getKey();
      ClienteRemoto metodo = e.getValue();
      System.out.println(key);
    }
    System.out.println("se agrego proceoso");

  }

  public String eco(String s, String i) throws RemoteException {
    return "Recibido desde proceso "+ i +", "+s.toUpperCase();
  }
  public void request(int id, int seq) throws RemoteException {
    for (Map.Entry<Integer, ClienteRemoto> e:l.entrySet()) {
      int key = e.getKey();
      ClienteRemoto metodo = e.getValue();
      if (key != id){
        for (Map.Entry<Integer, ClienteRemoto> e:l.entrySet()) {
          int key = e.getKey();
          metodo.yotengotoken(id, seq, K);
      }
    }

  }
  public void tepasoeltoken(int iddestino, Token token) throws RemoteException {
    for (Map.Entry<Integer, ClienteRemoto> e:l.entrySet()) {
      int key = e.getKey();
      ClienteRemoto metodo = e.getValue();
      if (key == iddestino){
        metodo.takeToken(token);
      }
    }
  }
  public void esperatoken(int iddestino) throws RemoteException{
    for (Map.Entry<Integer,ClienteRemoto> e:l.entrySet()) {
      int key = e.getKey();
      ClienteRemoto metodo = e.getValue();
      if (key == iddestino){
        metodo.waitToken();
      }
    }
  }
  //public void takeToken(Token token){}
  public void kill() throws RemoteException{
    for (Map.Entry<Integer,ClienteRemoto> e:l.entrySet()) {
      ClienteRemoto metodo = e.getValue();
      metodo.killprocess();
      }
    }


}
