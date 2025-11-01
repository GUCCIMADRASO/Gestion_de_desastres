package com.uniQuindio.gestionDesastres.model.estructuras.listaSimpleEnlazada;

public class NodoSimple<E> implements Comparable<NodoSimple<E>> {
    private E dato;
    private NodoSimple<E> siguiente;

    public NodoSimple(E dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoSimple<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple<E> siguiente) {
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
    public int compareTo(NodoSimple<E> otro) {
        return ((Comparable<E>) this.dato).compareTo(otro.dato);
    }

}
