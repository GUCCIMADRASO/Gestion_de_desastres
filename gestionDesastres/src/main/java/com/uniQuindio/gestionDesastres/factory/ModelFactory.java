package com.uniQuindio.gestionDesastres.factory;

import com.uniQuindio.gestionDesastres.mapping.dto.RecursoDto;
import com.uniQuindio.gestionDesastres.mapping.dto.UsuarioDto;
import com.uniQuindio.gestionDesastres.mapping.dto.EquipoDto;
import com.uniQuindio.gestionDesastres.mapping.dto.DesastreDto;
import com.uniQuindio.gestionDesastres.mapping.mappers.GestionDeDesastresMapping;
import com.uniQuindio.gestionDesastres.model.GestionDeDesastres;
import com.uniQuindio.gestionDesastres.model.Recurso;
import com.uniQuindio.gestionDesastres.model.Usuario;
import com.uniQuindio.gestionDesastres.model.Equipo;
import com.uniQuindio.gestionDesastres.model.Desastre;

import java.util.ArrayList;
import java.util.List;

public class ModelFactory {
    private static ModelFactory instance;
    GestionDeDesastres gestionDeDesastres;

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    private ModelFactory() {
        gestionDeDesastres = inicializarDatos();
    }

    private static GestionDeDesastres inicializarDatos(){
        GestionDeDesastres gestionDeDesastres1 = new GestionDeDesastres("UniQuindio");

        // ================== Datos quemados ==================

        return gestionDeDesastres1;
    }

    // ==================== RECURSO ====================
    public boolean agregarRecurso(RecursoDto recursoDto){
        Recurso recurso = GestionDeDesastresMapping.recursoDtoToRecurso(recursoDto);
        return gestionDeDesastres.agregarRecurso(recurso);
    }

    public boolean actualizarRecurso(RecursoDto recursoDto){
        Recurso recurso = GestionDeDesastresMapping.recursoDtoToRecurso(recursoDto);
        return gestionDeDesastres.actualizarRecurso(recurso);
    }

    public boolean eliminarRecurso(String id){
        return gestionDeDesastres.eliminarRecurso(id);
    }

    public List<RecursoDto> listarRecursos(){
        List<RecursoDto> lista = new ArrayList<>();
        for (Recurso r : gestionDeDesastres.listarRecursos()){
            lista.add(GestionDeDesastresMapping.recursoToRecursoDto(r));
        }
        return lista;
    }

    // ==================== USUARIO ====================
    public boolean agregarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = GestionDeDesastresMapping.usuarioDtoToUsuario(usuarioDto);
        return gestionDeDesastres.agregarUsuario(usuario);
    }

    public boolean actualizarUsuario(UsuarioDto usuarioDto){
        Usuario usuario = GestionDeDesastresMapping.usuarioDtoToUsuario(usuarioDto);
        return gestionDeDesastres.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(String id){
        return gestionDeDesastres.eliminarUsuario(id);
    }

    public List<UsuarioDto> listarUsuarios(){
        List<UsuarioDto> lista = new ArrayList<>();
        for (Usuario u : gestionDeDesastres.listarUsuarios()){
            lista.add(GestionDeDesastresMapping.usuarioToUsuarioDto(u));
        }
        return lista;
    }

    // ==================== EQUIPO ====================
    public boolean agregarEquipo(EquipoDto equipoDto){
        Equipo equipo = GestionDeDesastresMapping.equipoDtoToEquipo(equipoDto);
        return gestionDeDesastres.agregarEquipo(equipo);
    }

    public boolean actualizarEquipo(EquipoDto equipoDto){
        Equipo equipo = GestionDeDesastresMapping.equipoDtoToEquipo(equipoDto);
        return gestionDeDesastres.actualizarEquipo(equipo);
    }

    public boolean eliminarEquipo(String idEquipo){
        return gestionDeDesastres.eliminarEquipo(idEquipo);
    }

    public List<EquipoDto> listarEquipos(){
        List<EquipoDto> lista = new ArrayList<>();
        for (Equipo e : gestionDeDesastres.listarEquipos()){
            lista.add(GestionDeDesastresMapping.equipoToEquipoDto(e));
        }
        return lista;
    }

    // ==================== DESASTRE ====================
    public boolean agregarDesastre(DesastreDto desastreDto){
        Desastre d = GestionDeDesastresMapping.desastreDtoToDesastre(desastreDto);
        return gestionDeDesastres.agregarDesastre(d);
    }

    public boolean actualizarDesastre(DesastreDto desastreDto){
        Desastre d = GestionDeDesastresMapping.desastreDtoToDesastre(desastreDto);
        return gestionDeDesastres.actualizarDesastre(d);
    }

    public boolean eliminarDesastre(String idDesastre){
        return gestionDeDesastres.eliminarDesastre(idDesastre);
    }

    public List<DesastreDto> listarDesastres(){
        List<DesastreDto> lista = new ArrayList<>();
        for (Desastre d : gestionDeDesastres.listarDesastres()){
            lista.add(GestionDeDesastresMapping.desastreToDesastreDto(d));
        }
        return lista;
    }

}
