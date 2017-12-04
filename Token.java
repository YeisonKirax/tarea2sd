import java.util.*;
import java.io.*;

class Token implements Serializable{

    Queue<Integer> Q; //cOLA DE PROCESOS SOLICITANTES
    ArrayList<Integer> LN; //aRREGLO QUE CONTIENE ns DE LA ULTIMA SOLICITUD POR PROCESO.

    public Token(int cantProc)
    {
        this.Q = new LinkedList<>(); // Se va llenando con procesos cuya ID no este en la cola de solicitantes si RNi[j] = LN[j] +1
        this.LN = new ArrayList<Integer>(Collections.nCopies(cantProc, 0));
    }

    //Arreglo LN
    public void modificarvalorln(int Indice, int NuevoValor)
    {
        LN.set(Indice,NuevoValor);
    }
    public int obtenervalorln(int indice)
    {
        return LN.get(indice);
    }
    public void removervalorln(int indice)
    {
        LN.remove(indice);
    }

    //Cola Q

    public Queue<Integer> getQ() {
        return Q;
    }

    public int sacarvalor()
    {
        return Q.remove();
    }
    public void agregarvalor(int valor)
    {
        Q.add(valor);
    }
}
