import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

class ClienteProceso {
    static public void main (String args[]) {
        ArrayList<ServiciosRemotos> stubs = new ArrayList();
        System.out.println(args.length);
        if (args.length<4) {
            System.err.println("Uso: ClienteEco hostregistro numPuertoRegistro id numProcesos ...");
            return;
        }

        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            for (int i = 1; i <= Integer.parseInt(args[3]); i++) {
                if (i == Integer.parseInt(args[2])) {
                    continue;
                } else {
                    ServiciosRemotos srv = (ServiciosRemotos) Naming.lookup("//" + args[0] + ":" + args[1] + "/" + String.valueOf(i) + "/Eco");
                    stubs.add(srv);
                }
            }
            for (int i = 0; i < stubs.size(); i++) {
                System.out.println(stubs.size());
                System.out.println(stubs.get(i).eco(args[4], args[2]));

            }
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
