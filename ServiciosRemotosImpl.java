import java.rmi.*;
import java.rmi.server.*;

class ServiciosRemotosImpl extends UnicastRemoteObject implements ServiciosRemotos {
  ServiciosRemotosImpl() throws RemoteException {}
  public String eco(String s, String i) throws RemoteException {
    return "Recibido desde proceso "+ i +", "+s.toUpperCase();
  }
  /*
  //Si el proceso que envia el request no tiene el token, incrementar
  //numero de sequencia, hacer RN_i[i] = sn y enviar request atodos los procesos
  public void request(int id, int seq)
  {
    //Se compara con el valor de antes, para ver cual es el actual
    int Valor1 = Proceso.ObtenerValor();
    Proceso.ModificarValor(id, Math.max(Valor1,seq));

    //Si tengo el token ocioso entonces se lo envio a P_i si RN_j[i] = LN[i] + 1
    if(Proceso.Estado.equals("Ocioso") && Proceso.ObtenerValorRN(id) = tok.ObtenerValorLN(id))
    {
        takeToken(tok);
    } else {
        waitToken();
    }
  }
  */

  //public void waitToken(){}
  //public void takeToken(Token token){}
  //public void kill(){}
}
