import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ClienteProceso {
  static int id;
  static int n;
  static int initialDelay;
  static Boolean bearer;
  static Token token;
  static Proceso proc;
    static public void main (String args[]) {
    
       if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());

        try {
            id = Integer.parseInt(args[0]);
            n = Integer.parseInt(args[1]);
            initialDelay = Integer.parseInt(args[2]);
            bearer = Boolean.valueOf(args[3]);
            Servicios srv = (Servicios) Naming.lookup("//localhost:54321/Process");
            if(bearer){
              token = new Token(n);
              proc = srv.crearProceso(id, n, initialDelay, bearer, token);
              System.out.println("Se ha creado el procesos " + args[0] + " exitosamente");
            }else{
              proc = srv.crearProceso(id, n, initialDelay, bearer, null);
              System.out.println("Se ha creado el procesos " + args[0] + " exitosamente");
            }
            srv.agregarProceso(proc);
            List<Proceso> l = srv.obtenerProcesos();
            for(Proceso proceso: l){
              System.out.println("Soy el proceso " + String.valueOf(proceso.getId()));
            }

        }
        catch (RemoteException e) {
            System.err.println("Error de comunicacion: " + e.toString());
        }
        catch (Exception e) {
            System.err.println("Excepcion en ClienteBanco:");
            e.printStackTrace();
        }
    }
}
