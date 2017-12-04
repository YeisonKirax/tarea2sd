import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Timer;
import java.util.TimerTask;

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
            if (bearer) {
                token = new Token(n);
                proc = srv.crearProceso(id, n, initialDelay, bearer, token);
                proc.cambiarEstado(2);
                System.out.println("Se ha creado el procesos " + args[0] + " exitosamente");
            } else {
                proc = srv.crearProceso(id, n, initialDelay, bearer, null);
                System.out.println("Se ha creado el procesos " + args[0] + " exitosamente");
            }
            srv.agregarProceso(proc);
            List<Proceso> l = srv.obtenerProcesos();
            //Ver si paso mi delay time, entonces

            System.out.println("TOKEN ANTES --"+ proc.getToken());

            Thread.sleep(initialDelay);

            if(proc.getToken() == null){
              proc.cambiarEstado(1);
              proc.setBearer(false);
            }
            //Si no tengo el token
            if (!proc.getBearer()) {
                srv.cambiarVengodeCola(-100);
                while(true){
                //Aumentar numero de sequencia antes del request
                    proc.actualizarNsequencia();

                //RN[I] = SN
                    proc.modificarValorRN(id, proc.getSN());

                //Enviar Request al servidor y este lo trabaja por procesos

                    int response = srv.request(proc.getId(), proc.getSN());
                    if (response == 200) {
                      proc.modificarToken(srv.takeToken(proc.getToken()));
                      proc.cambiarEstado(2);
                      break;
                    } else if (response == 300) {
                      if(srv.waitToken()){
                        Thread.sleep(initialDelay);
                      };
                    }
                }

                //Cambiar a estado Rojo
                System.out.println("Cambiar a Rojo");
                proc.cambiarEstado(4);

                /////////////////////////Salida de la seccion Critica
                //Modificar la lista del token LN[I] = RN[I]
                Token tokenModificado = proc.getToken();
                System.out.println(tokenModificado);
                tokenModificado.modificarvalorln(id, proc.obtenerValorRN(id));
                tokenModificado.AumentarContador();
                proc.modificarToken(tokenModificado);

                proc.cambiarEstado(2);


                //Por cada id que no esta en la cola Q del token si es que RN[J] = LN[J]+1
                for (Proceso proceso : l) {
                    int indice = proceso.getId();
                    for (int e : proc.getToken().getQ()) {
                        if (indice != id && e != indice) {
                            //No esta en la cola  RN[J] = LN[J]+1
                            if (proc.obtenerValorRN(indice) == (proc.getToken().obtenervalorln(indice)) + 1) {
                                System.out.println("No esta en la cola " + indice);
                                tokenModificado = proc.getToken();
                                tokenModificado.agregarvalor(indice);
                                proc.modificarToken(tokenModificado);
                            }
                        }
                    }
                }

                if(proc.getToken().getQ().peek() != null){
                    System.out.println("Cola no vacia, sacar de cola e enviar token");
                    int procesoApedirToken = proc.getToken().getQ().remove();
                    srv.cambiarVengodeCola(procesoApedirToken);
                    srv.takeToken(proc.getToken());
                    srv.cambiarVengodeCola(-100);
                    proc.cambiarEstado(1);

                }

            } else {
                //Cambiar a estado Rojo
                System.out.println("Cambiar a Rojo");
                proc.cambiarEstado(4);

                /////////////////////////Salida de la seccion Critica
                //Modificar la lista del token LN[I] = RN[I]

                Token tokenModificado = proc.getToken();
                System.out.println(tokenModificado);
                tokenModificado.modificarvalorln(id, proc.obtenerValorRN(id));
                tokenModificado.AumentarContador();
                proc.modificarToken(tokenModificado);
                tokenModificado = null;
                proc.cambiarEstado(2);


                //Por cada id que no esta en la cola Q del token si es que RN[J] = LN[J]+1
                for (Proceso proceso : l) {
                    int indice = proceso.getId();
                    Queue<Integer> Qtmp = proc.getToken().getQ();
                    for (int e : Qtmp ) {
                        if (indice != id && e != indice) {
                            //No esta en la cola  RN[J] = LN[J]+1
                            if (proc.obtenerValorRN(indice) == (proc.getToken().obtenervalorln(indice)) + 1) {
                                System.out.println("No esta en la cola " + indice);
                                tokenModificado = proc.getToken();
                                tokenModificado.agregarvalor(indice);
                                proc.modificarToken(tokenModificado);
                            }
                        }
                    }
                }

                if(proc.getToken().getQ().peek() != null){
                    System.out.println("Cola no vacia, sacar de cola e enviar token");
                    int procesoApedirToken = proc.getToken().getQ().remove();
                    srv.cambiarVengodeCola(procesoApedirToken);
                    srv.takeToken(proc.getToken());
                    srv.cambiarVengodeCola(-100);
                    proc.cambiarEstado(1);

                }
            }

            if(proc.getToken().getContador() == n)
            {
                srv.kill();
            }

            System.out.println("ESTADO DEL PROCESO: "+proc.getEstado());
            while (true){
                if(proc.getEstado()==5){
                    break;}

            }

            System.out.println("TOKEN DESPUES --" +proc.getToken());



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
