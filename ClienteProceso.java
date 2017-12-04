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
            //Ver si paso mi delay time, entonces

            //Si no tengo el token
            if(!proc.getBearer()) {

                //Aumentar numero de sequencia antes del request
                proc.actualizarNsequencia();

                //RN[I] = SN
                proc.modificarValorRN(id, proc.getSN());

                //Enviar Request al servidor y este lo trabaja por procesos
                srv.request(proc.getId(), proc.getSN());


            } else {
                //Cambiar a estado Rojo
                System.out.println("Cambiar a Rojo");
                proc.cambiarEstado(4);

                /////////////////////////Salida de la seccion Critica
                //Modificar la lista del token LN[I] = RN[I]
                Token tokenModificado = proc.getToken();
                System.out.println(tokenModificado);
                tokenModificado.modificarvalorln(id, proc.obtenerValorRN(id));
                proc.modificarToken(tokenModificado);

                proc.cambiarEstado(2);


                //Por cada id que no esta en la cola Q del token si es que RN[J] = LN[J]+1
                for (Proceso proceso : l) {
                    int indice = proceso.getId();
                    for (int e : proc.getToken().getQ()) {
                        if(indice != id && e != indice){
                            //No esta en la cola  RN[J] = LN[J]+1
                            if(proc.obtenerValorRN(indice) == (proc.getToken().obtenervalorln(indice)) +1){
                                System.out.println("No esta en la cola");
                            }
                        }
                    }

                }
            }






            //Sacar elemento de la cola y enviarlo a el, cambiar estado
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
