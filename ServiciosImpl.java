import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServiciosImpl extends UnicastRemoteObject implements Servicios {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
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
    public void PrintColors(Proceso proc) throws RemoteException{
        int Estado = proc.getEstado();
        if(Estado == 1)
        {
            System.out.println(ANSI_GREEN + "Proceso "+proc.getId()+" de color Verde sin Token" + ANSI_RESET);
        } else if(Estado == 2)
        {
            System.out.println(ANSI_GREEN + "Proceso "+proc.getId()+" de color Verde con Token" + ANSI_RESET);
        } else if(Estado == 3)
        {
            System.out.println(ANSI_YELLOW + "Proceso "+proc.getId()+" de color Amarillo esperando Token" + ANSI_RESET);
        } else if(Estado == 4)
        {
            System.out.println(ANSI_RED + "Proceso "+proc.getId()+" de color Rojo en zona critica con Token" + ANSI_RESET);
        } else if (Estado == 5) {
            System.out.println("Proceso "+proc.getId()+" Muerto");
        }

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
