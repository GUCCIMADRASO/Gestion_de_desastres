package com.uniQuindio.gestionDesastres.services;


import com.uniQuindio.gestionDesastres.model.ColaPrioridad;
import com.uniQuindio.gestionDesastres.model.Desastre;
import org.springframework.stereotype.Service;

@Service
public class ColaPrioridadService {

    private final ColaPrioridad<Desastre> cola = new ColaPrioridad<>();

    public void agregarElemento(Desastre desastre) {
        cola.agregarElemento(desastre);
    }

    public Desastre atenderSiguiente() {
        return cola.atenderSiguiente();
    }

    public String mostrarCola() {
        return cola.mostrarCola();
    }

    public boolean estaVacia() {
        return cola.estaVacia();
    }

    public int tam() {
        return cola.tam();
    }

    public ColaPrioridad<Desastre> obtenerColaInterna() {
        return cola;
    }
}