package Estructuras.Grafo;

import Estructuras.ListasEnlaceDoble.LinkedList;

public class Vertice  {
    String nombre;
    int numVertice;

    public Vertice(String x) {
        nombre = x;
        numVertice = -1;
    }

    public Vertice(int etiqueta) {
        this.numVertice = etiqueta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumVertice() {
        return numVertice;
    }

    public void setNumVertice(int numVertice) {
        this.numVertice = numVertice;
    }

    // devuelve identificador del vértice
    public String getNombre() {
        return nombre;
    }
    // true, si dos vértices son iguales
    public boolean equals(Vertice n) {
        return nombre.equals(n.nombre);
    }
    // establece el número de vértices
    public void asigVert(int n) {
            numVertice = n;
    }
    // características del vértice
    public String toString() {
            return nombre + " (" + numVertice + ")";
    }

}
