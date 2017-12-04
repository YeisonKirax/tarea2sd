import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServiciosImpl extends UnicastRemoteObject implements Servicios {
    List<Proceso> procesos;
    int VengoDeLaCola; //-100 si no vengo, un id si vengo

    ServiciosImpl() throws RemoteException {
        procesos = new LinkedList<Proceso>();
    }
    public Proceso crearProceso(int id, int n, int initialDelay, Boolean bearer, Token token) throws RemoteException{
      Proceso pro = new ProcesoImpl(id, n, initialDelay, bearer, token);
      return pro;
    }
    public void cambiarVengodeCola(int i) throws RemoteException {
        VengoDeLaCola = i;
    }
    public void agregarProceso(Proceso proc) throws RemoteException {
        procesos.add(proc);
    }
    public List<Proceso> obtenerProcesos() throws RemoteException{
        return procesos;
    }
    public int request(int id, int seq) throws RemoteException {
        for (Proceso proceso : procesos) {
            int indice = proceso.getId();

            if (id != indice) {
                //Hacer que RN[J]  = MAX(RN[J], seq)
                proceso.modificarValorRN(indice, Math.max(proceso.obtenerValorRN(indice), seq));

                //Si el proceso tiene el token ocioso, se lo envia al otro proceso si RN[I] = LN[I]+1
                if (proceso.getEstado() == 2) {

                    if (proceso.obtenerValorRN(indice) == proceso.getToken().obtenervalorln(indice) + 1) {
                        //Pasarle el token
                        return 200;
                    } else {
                        //Wait
                        return 300;

                    }
                }
            }

        }
        return 0;
    }
    public Boolean waitToken() throws RemoteException{
        return true;

    }
    public Token takeToken(Token token) throws RemoteException{
        System.out.println("Take Token");
        if(VengoDeLaCola ==-100){
            //El token de entrada es null, pasarle el token
            for (Proceso proceso : procesos) {
                if (proceso.getToken() != null) {
                    Token temp = proceso.getToken();
                    proceso.modificarToken(token);
                    proceso.cambiarEstado(1);
                    return temp;
                }
            }
        } else {
            //El token de entrada no es null, quitarle el token
            for (Proceso proceso : procesos) {
                if (proceso.getId() == VengoDeLaCola) {
                        Token temp = proceso.getToken();
                        proceso.modificarToken(token);
                        proceso.cambiarEstado(2);
                        return null;
                }
            }
        }
        return token;

    }
    public void kill() throws RemoteException{
        for (Proceso proceso : procesos) {
            proceso.cambiarEstado(5);
        }
        procesos.clear();

    }
}
