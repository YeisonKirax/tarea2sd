import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class ServidorProceso  {
    static public void main (String args[]) {
        ArrayList<ServiciosRemotosImpl> skel= new ArrayList();
        if (args.length<2) {
            System.err.println("Uso: ServidorEco numPuertoRegistro id numProcesos");
            return;
        }
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            for (int i = 1;i <= Integer.parseInt(args[1]); i++) {
                if (i == Integer.parseInt(args[1])) {
                    ServiciosRemotosImpl srv = new ServiciosRemotosImpl();
                    skel.add(srv);
                    Naming.rebind("rmi://localhost:" + args[0] + "/" + String.valueOf(i) + "/Eco", srv);

                } else {
                    continue;
                }
            }
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorProceso:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
