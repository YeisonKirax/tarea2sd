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
    private int estado; // 1 Verde sin token, 2 Verde con token, 3 Amarillo Esperando token, 4 En zona critica. 5 espera a morir

    ProcesoImpl(int id, int n, int initialDelay, Boolean bearer, Token token) throws RemoteException {
        this.id = id;
        this.n = n;
        this.initialDelay = initialDelay;
        this.bearer = bearer;
        this.token = token;
        this.RN = new ArrayList<Integer>(Collections.nCopies(n, 0));
        this.estado = 1;
        }


    //Getters
    public int getId(){
      return this.id;
    }
    public int getSN() { return this.nsecuencia;  }
    public int getInitialDelay() { return this.initialDelay;  }
    public Boolean getBearer() { return this.bearer;  }
    public Token getToken() { return this.token;  }
    public int getEstado() { return this.estado;  }

    //Lista
    public void modificarValorRN(int indicadorProc, int nuevoValor) { RN.set(indicadorProc-1, nuevoValor); }
    public int obtenerValorRN(int indice)
    {
        return RN.get(indice-1);
    }
    public void removerValorRN(int indice)
    {
        RN.remove(indice-1);
    }
    public void setBearer(Boolean val){
        this.bearer = val;
    }

    //Extra
    public void actualizarNsequencia()
    {
        this.nsecuencia++;
    }
    public void asignarIndicadorProceso(int valor){
        this.id = valor;
    }
    public void cambiarEstado(int nuevoestado){this.estado = nuevoestado; }

    //Token
    public void modificarToken(Token token) {this.token = token; }
}
