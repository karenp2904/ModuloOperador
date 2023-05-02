package Estructuras.Grafo;

public class Arco {

    int destino;
    double peso;

    public Arco(int d) {
        destino = d;
    }

    public Arco(int destino, double peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public int getDestino()
    {
        return destino;
    }

    public boolean equals(Object n)
    {
        Arco a = (Arco)n;
        return destino == a.destino;
    }
}
