import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import javax.swing.Timer;
import java.io.*;
import java.awt.event.*;

public class ClienteProceso {
    static int IndicadorProceso; //Proceso p_j
    static int Nsequencia; //Numero de sequencia actual, se aumenta con cada peticion
    static ArrayList<Integer> RN; // Arreglo de largo N (N PROCESOS) dpnde se mantienen las REQUEST

    static int CantidadProcesos; // Cantidad fija de procesos totales
    static long initialDelay; //Delay para intentar entrar en la zona critica
    static boolean PoseedorToken; // Dice si es el poseedor inicial del token

    static Token tk = null; //Referencia al token


    // 1 = ocioso con token
    // 2 = ocioso sin token
    // 3 = esperando a recibir token
    // 4 = posee token y esta en zona critica


    static int estado; //Estado ocioso con o sin token, en zona critica,esperando por recibir token
    public static void main (String args[]) {
        ArrayList<ServiciosRemotos> stubs = new ArrayList();
        System.out.println(args.length);
        if (args.length<4) {
            System.err.println("Uso: ClienteEco id numProcesos initialDelay bearer...");
            return;
        }

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            System.out.println(IndicadorProceso);
            if (Boolean.valueOf(args[3]) == true) {
                tk = new Token(Integer.parseInt(args[1]));
                IndicadorProceso = Integer.parseInt(args[0]);
                CantidadProcesos = Integer.parseInt(args[1]);
                initialDelay = Integer.parseInt(args[2]);
                PoseedorToken = Boolean.valueOf(args[3]);
                Nsequencia = 0;
                RN = new ArrayList<Integer>(Collections.nCopies(CantidadProcesos, 0));
                estado = 1;
            } else {
                IndicadorProceso = Integer.parseInt(args[0]);
                CantidadProcesos = Integer.parseInt(args[1]);
                initialDelay = Integer.parseInt(args[2]);
                PoseedorToken = Boolean.valueOf(args[3]);
                Nsequencia = 0;
                RN = new ArrayList<Integer>(Collections.nCopies(CantidadProcesos, 0));
                estado = 2;

            }
            ServiciosRemotos srv = (ServiciosRemotos) Naming.lookup("//localhost:54321/Proceso");

            System.out.println(IndicadorProceso);
            ClienteProcesoImpl c = new ClienteProcesoImpl();
            ArrayList datosProcesos = new ArrayList();
            datosProcesos.add(IndicadorProceso);
            datosProcesos.add(Nsequencia);
            datosProcesos.add(RN);
            datosProcesos.add(initialDelay);
            datosProcesos.add(PoseedorToken);
            datosProcesos.add(estado);
            srv.agregarproceso(IndicadorProceso, c, datosProcesos);
            srv.request(IndicadorProceso, Nsequencia);


        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteEco:");
            e.printStackTrace();
        }
    }
}
