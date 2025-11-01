package com.uniQuindio.gestionDesastres.model.estructuras.listaDobleCircular;

import java.util.Iterator;

public class ListaDobleCircular<E> implements Iterable<E> {
    private NodoDobleCircular<E> primero;
    private int tam;

    public ListaDobleCircular() {
        this.primero = null;
        this.tam = 0;
    }

    public boolean esVacia() {
        return primero == null;
    }

    public int getTam() {
        return tam;
    }

    public void agregarPrimero(E dato) {
        NodoDobleCircular<E> nuevo = new NodoDobleCircular<>(dato);

        if (esVacia()) {
            primero = nuevo;
        } else {
            NodoDobleCircular<E> ultimo = primero.getAnterior();

            nuevo.setSiguiente(primero);
            nuevo.setAnterior(ultimo);

            primero.setAnterior(nuevo);
            ultimo.setSiguiente(nuevo);

            primero = nuevo;
        }
        tam++;
    }

    public void agregarUltimo(E dato) {
        if (esVacia()) {
            agregarPrimero(dato);
            return;
        }
        NodoDobleCircular<E> nuevo = new NodoDobleCircular<>(dato);
        NodoDobleCircular<E> ultimo = primero.getAnterior();

        nuevo.setSiguiente(primero);
        nuevo.setAnterior(ultimo);

        ultimo.setSiguiente(nuevo);
        primero.setAnterior(nuevo);

        tam++;
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("{ }");
            return;
        }

        NodoDobleCircular<E> actual = primero;
        StringBuilder sb = new StringBuilder("{ ");

        do {
            sb.append(actual.getDato()).append(" ");
            actual = actual.getSiguiente();
        } while (actual != primero);

        sb.append("}");
        System.out.println(sb.toString());
    }

    public void agregarOrdenado(E dato) {

        if (esVacia()) {
            agregarPrimero(dato);
            return;
        }

        NodoDobleCircular<E> nuevo = new NodoDobleCircular<>(dato);
        NodoDobleCircular<E> actual = primero;

        do {
            if (((Comparable<E>) dato).compareTo(actual.getDato()) < 0) {

                NodoDobleCircular<E> anterior = actual.getAnterior();

                nuevo.setSiguiente(actual);
                nuevo.setAnterior(anterior);

                anterior.setSiguiente(nuevo);
                actual.setAnterior(nuevo);

                if (actual == primero) {
                    primero = nuevo;
                }
                tam++;
                return;
            }
            actual = actual.getSiguiente();
        } while (actual != primero);

        agregarUltimo(dato);
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private NodoDobleCircular<E> actual = primero;
            private boolean inicio = true;

            @Override
            public boolean hasNext() {
                return !esVacia() && (inicio || actual != primero);
            }

            @Override
            public E next() {
                E dato = actual.getDato();
                actual = actual.getSiguiente();
                inicio = false;
                return dato;
            }
        };
    }
}
