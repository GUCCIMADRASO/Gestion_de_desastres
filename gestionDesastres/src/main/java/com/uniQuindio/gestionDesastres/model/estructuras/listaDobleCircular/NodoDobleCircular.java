package com.uniQuindio.gestionDesastres.model.estructuras.listaDobleCircular;

public class NodoDobleCircular<E> implements Comparable<NodoDobleCircular<E>> {
    private E dato;
    private NodoDobleCircular<E> siguiente;
    private NodoDobleCircular<E> anterior;

    public NodoDobleCircular(E dato) {
        this.dato = dato;
        this.siguiente = this;
        this.anterior = this;
    }

    public E getDato() {
        return dato;
    }

    public void setDato(E dato) {
        this.dato = dato;
    }

    public NodoDobleCircular<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDobleCircular<E> siguiente) {
        this.siguiente = siguiente;
    }

    public NodoDobleCircular<E> getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoDobleCircular<E> anterior) {
        this.anterior = anterior;
    }

    @Override
    public int compareTo(NodoDobleCircular<E> otro) {
        return ((Comparable<E>) this.dato).compareTo(otro.dato);
    }

    @Override
    public String toString() {
        return "Nodo{" +
                "dato=" + dato +
                '}';
    }
}
