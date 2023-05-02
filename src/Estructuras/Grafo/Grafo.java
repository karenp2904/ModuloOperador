package Estructuras.Grafo;

import Estructuras.Colas.ColasArray;
import Estructuras.ListasEnlaceDoble.LinkedList;
import Estructuras.ListasEnlaceDoble.Node;

import java.util.Iterator;

public class Grafo {
    private int numVerts;
    private LinkedList<Vertice> verts;
    private LinkedList<Arista>[] adyacencias;

    public Grafo(int numVerts) {
        this.numVerts = numVerts;
        this.verts = new LinkedList<>();
        this.adyacencias = new LinkedList[numVerts];

        for (int i = 0; i < numVerts; i++) {
            verts.add(new Vertice(i));
            adyacencias[i] = new LinkedList<>();
        }
    }

    public void agregarArista(int origen, int destino, int peso) {
        adyacencias[origen].add(new Arista(destino, peso));
        adyacencias[destino].add(new Arista(origen, peso));
    }

    public void agregarVertice() {
        verts.add(new Vertice(numVerts));
        adyacencias[numVerts] = new LinkedList<>();
        numVerts++;
    }


}
