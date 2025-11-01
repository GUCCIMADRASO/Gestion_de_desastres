package com.uniQuindio.gestionDesastres.model.estructuras.pila;

public class Pila <E>{
    private NodoPila<E> cima;
    private int tam;

    public Pila() {
        cima = null;
        tam = 0;
    }

    public void apilar(E dato) {
        NodoPila<E> nuevo = new NodoPila<>(dato);
        nuevo.setSiguiente(cima);
        cima = nuevo;
        tam++;
    }

    public E desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        E valor = cima.getDato();
        cima = cima.getSiguiente(); // la cima pasa al siguiente nodo
        tam--;
        return valor;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}
