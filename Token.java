import java.util.*;

class Token {

    Queue<int> Q; //cOLA DE PROCESOS SOLICITANTES
    ArrayList<int> LN; //aRREGLO QUE CONTIENE ns DE LA ULTIMA SOLICITUD POR PROCESO.

    public Token(int cantProc)
    {
        this.Q = new LinkedList<>(); // Se va llenando con procesos cuya ID no este en la cola de solicitantes si RNi[j] = LN[j] +1
        this.LN = new ArrayList<int>(cantProc);
    }

    public void ModificarValorLN(int Indice, int NuevoValor)
    {
        LN.set(Indice,NuevoValor);
    }

    public int ObtenerValorLN(int indice)
    {
        return LN.get(indice);
    }

    public void RemoverValorLN(int indice)
    {
        LN.remove(indice);
    }

    public int SacarValor()
    {
        return Q.remove();
    }

    public void AgregarValor(int valor)
    {
        Q.add(valor);
    }
}
