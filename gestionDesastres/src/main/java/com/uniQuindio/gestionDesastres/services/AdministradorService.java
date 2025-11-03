// java
package com.uniQuindio.gestionDesastres.services;

import com.uniQuindio.gestionDesastres.model.Administrador;
import com.uniQuindio.gestionDesastres.model.ColaPrioridad;
import com.uniQuindio.gestionDesastres.model.Desastre;
import com.uniQuindio.gestionDesastres.model.Recurso;
import com.uniQuindio.gestionDesastres.model.Ruta;
import com.uniQuindio.gestionDesastres.model.GrafoDirigido;
import com.uniQuindio.gestionDesastres.model.Ubicacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    private final Administrador administrador = new Administrador();

    public void manejoRecursos(Recurso recurso, int cantidad) {
        administrador.manejoRecursos(recurso, cantidad);
    }

    public void asignarRecursos(ColaPrioridad<Desastre> colaPrioridad, List<Recurso> recursosDisponibles) {
        administrador.asignarRecursos(colaPrioridad, recursosDisponibles);
    }

    public Ruta definirRuta(GrafoDirigido grafo, Ubicacion origen, Ubicacion destino) {
        return administrador.definirRuta(grafo, origen, destino);
    }

    public void generarReporte(List<Desastre> desastres) {
        administrador.generarReporte(desastres);
    }
}