package com.uniQuindio.gestionDesastres.model.estructuras.listaSimpleCircular;

import java.util.Iterator;

public class ListaSimpleCircular<E extends Comparable<E>> implements Iterable<E> {
    private NodoSimpleCircular<E> primero;
    private NodoSimpleCircular<E> ultimo;
    private int tam;

    public ListaSimpleCircular() {
        this.primero = null;
        this.ultimo = null;
        this.tam = 0;
    }

    public void agregarPrimero(E dato) {
        NodoSimpleCircular<E> newNodo = new NodoSimpleCircular<>(dato);

        if (primero == null) {
            primero = newNodo;
            ultimo = newNodo;
            ultimo.setSiguiente(primero);
        } else {
            newNodo.setSiguiente(primero);
            primero = newNodo;
            ultimo.setSiguiente(primero);
        }
        tam++;
    }

    public void agregarUltimo(E dato) {
        NodoSimpleCircular<E> newNodo = new NodoSimpleCircular<>(dato);

        if (primero == null) {
            primero = newNodo;
            ultimo = newNodo;
            ultimo.setSiguiente(primero);
        } else {
            ultimo.setSiguiente(newNodo);
            ultimo = newNodo;
            ultimo.setSiguiente(primero);
        }
        tam++;
    }

    public void agregarOrdenado(E dato) {
        NodoSimpleCircular<E> newNodo = new NodoSimpleCircular<>(dato);
        if (primero == null) {
            primero = newNodo;
            primero.setSiguiente(primero);
            tam++;
            return;
        }
        NodoSimpleCircular<E> actual = primero;
        NodoSimpleCircular<E> ultimo = primero;

        while (ultimo.getSiguiente() != primero) {
            ultimo = ultimo.getSiguiente();
        }
        if (dato.compareTo(primero.getDato()) < 0) {
            newNodo.setSiguiente(primero);
            ultimo.setSiguiente(newNodo);
            primero = newNodo;
            tam++;
            return;
        }

        while (actual.getSiguiente() != primero &&
                dato.compareTo(actual.getSiguiente().getDato()) > 0) {
            actual = actual.getSiguiente();
        }

        newNodo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(newNodo);
        tam++;

    }

    public void mostrar() {
        if (primero == null) {
            System.out.println("Lista vacía");
            return;
        }

        NodoSimpleCircular<E> actual = primero;
        String mensaje = "{ ";

        do {
            mensaje += actual.getDato() + " ";
            actual = actual.getSiguiente();
        } while (actual != primero);

        System.out.println(mensaje + "}");
    }

    public boolean esVacia() {
        return primero == null;
    }

    public int getTam() {
        return tam;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private NodoSimpleCircular<E> actual = primero;
            private boolean inicio = true;

            @Override
            public boolean hasNext() {
                return actual != null && (inicio || actual != primero);
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
