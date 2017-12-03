import java.util.*;

class Proceso {
    int IndicadorProceso; //Proceso p_j
    int Nsequencia; //Numero de sequencia actual, se aumenta con cada peticion
    ArrayList<int> RN; // Arreglo de largo N (N PROCESOS) dpnde se mantienen las REQUEST

    int CantidadProcesos; // Cantidad fija de procesos totales
    long initialDelay; //Delay para intentar entrar en la zona critica
    bool PoseedorToken; // Dice si es el poseedor inicial del token

    Token tok = null; //Referencia al token

    string Estado; //Estado ocioso con o sin token, en zona critica,esperando por recibir token

    public Proceso(int indiProceso,int cantProcesos, long delay, bool TokenInitial, Token touken)
    {
        this.IndicadorProceso = indiProceso; //Modificar por orden de llegada al token o con request.
        this.Nsequencia = 0 //todos empiezan con 0
        this.RN = new arrayList<int>(cantProcesos); //ir agregando a medida que llegen valores
        this.CantidadProcesos =cantProcesos;
        this.initialDelay =  delay;
        this.PoseedorToken = TokenInitial;
        this.Estado = "Ocioso";
        if(TokenInitial)
        {
            tok = touken;
        } else
        {
            tok = null;
        }
    }

    //Cambiar el valor en el indice
    public void ModificarValorRN(int IndicadorProc, int NuevoValor)
    {
        RN.set(IndicadorProc,NuevoValor);
    }

    public int ObtenerValorRN(int indice)
    {
        return RN.get(indice);
    }

    public void RemoverValorRN(int indice)
    {
        RN.remove(indice);
    }

    public void ActualizarNsequencia()
    {
        Nsequencia++;
    }

    public void AsignarIndicadorProceso(int valor)
    {
        IndicadorProceso = valor;
    }

    public void PrintearColorSemaforo()
    {
        if (Estado.equals("Ocioso"))
        {
            System.out.println("Luz Verde \n");
        } else if ("ZC")
        {
            System.out.println("Luz roja \n");
        } else if ("Espera")
        {
            System.out.println("Luz Amarilla \n");
        }
    }

}
