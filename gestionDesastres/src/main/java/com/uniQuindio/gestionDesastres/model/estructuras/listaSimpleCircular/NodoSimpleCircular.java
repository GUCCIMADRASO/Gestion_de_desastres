package com.uniQuindio.gestionDesastres.model.estructuras.listaSimpleCircular;

public class NodoSimpleCircular<E> implements Comparable<NodoSimpleCircular<E>> {
    private E dato;
    private NodoSimpleCircular<E> siguiente;

    public NodoSimpleCircular(E dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoSimpleCircular<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimpleCircular<E> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "dato=" + dato +
                ", siguiente=" + siguiente +
                '}';
    }

    @Override
    public int compareTo(NodoSimpleCircular<E> otro) {
        return ((Comparable<E>) this.dato).compareTo(otro.dato);
    }

}
