import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ProcesoImpl extends UnicastRemoteObject implements Proceso {
    private int id;
    private int n;
    private int initialDelay;
    private Boolean bearer;
    private int nsecuencia;
    private ArrayList<Integer> RN;
    private Token token = null;
    private int estado;

    ProcesoImpl(int id, int n, int initialDelay, Boolean bearer, Token token) throws RemoteException {
        this.id = id;
        this.n = n;
        this.initialDelay = initialDelay;
        this.bearer = bearer;
        this.token = token;
        }


    //Cambiar el valor en el indice
    public int getId(){
      return this.id;
    }
    public void modificarValorRN(int indicadorProc, int nuevoValor)
    {
        RN.set(indicadorProc, nuevoValor);
    }

    public int obtenerValorRN(int indice)
    {
        return RN.get(indice);
    }

    public void removerValorRN(int indice)
    {
        RN.remove(indice);
    }

    public void actualizarNsequencia()
    {
        this.nsecuencia++;
    }

    public void asignarIndicadorProceso(int valor){
        this.id = valor;
    }
}
