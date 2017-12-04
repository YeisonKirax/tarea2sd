import java.rmi.*;
import java.rmi.server.*;

class Servidor  {
    static public void main (String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            ServiciosImpl srv = new ServiciosImpl();
            Naming.rebind("rmi://localhost:54321/Process", srv);
        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Excepcion en ServidorBanco:");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
