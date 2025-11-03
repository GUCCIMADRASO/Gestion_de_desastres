package com.uniQuindio.gestionDesastres.mapping.mappers;

import com.uniQuindio.gestionDesastres.mapping.dto.DesastreDto;
import com.uniQuindio.gestionDesastres.mapping.dto.EquipoDto;
import com.uniQuindio.gestionDesastres.mapping.dto.RecursoDto;
import com.uniQuindio.gestionDesastres.mapping.dto.UsuarioDto;
import com.uniQuindio.gestionDesastres.model.Desastre;
import com.uniQuindio.gestionDesastres.model.Equipo;
import com.uniQuindio.gestionDesastres.model.Recurso;
import com.uniQuindio.gestionDesastres.model.Usuario;

public class GestionDeDesastresMapping {

    // ==================== RECURSO ====================
    public static Recurso recursoDtoToRecurso(RecursoDto dto) {
        if (dto == null) return null;
        return new Recurso(dto.id(), dto.nombre(), dto.tipo(), dto.cantidad());
    }

    public static RecursoDto recursoToRecursoDto(Recurso recurso) {
        if (recurso == null) return null;
        return new RecursoDto(recurso.getId(), recurso.getNombre(), recurso.getTipo(), recurso.getCantidad());
    }

    // ==================== USUARIO ====================
    public static Usuario usuarioDtoToUsuario(UsuarioDto dto) {
        if (dto == null) return null;
        return new Usuario(dto.id(), dto.nombre(), dto.email(), dto.contrasena());
    }

    public static UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
        if (usuario == null) return null;
        return new UsuarioDto(usuario.getId(), usuario.getNombre(), usuario.GetEmail(), usuario.getContrasena());
    }

    // ==================== EQUIPO ====================
    public static Equipo equipoDtoToEquipo(EquipoDto dto) {
        if (dto == null) return null;
        return new Equipo(dto.idEquipo(), dto.integrantesDisponibles(), dto.tipoEquipo());
    }

    public static EquipoDto equipoToEquipoDto(Equipo equipo) {
        if (equipo == null) return null;
        return new EquipoDto(equipo.getIdEquipo(), equipo.getIntegrantesDisponibles(), equipo.getTipoEquipo());
    }

    // ==================== DESASTRE ====================
    public static Desastre desastreDtoToDesastre(DesastreDto dto) {
        if (dto == null) return null;
        return new Desastre(dto.magnitud(), dto.nombre(), dto.idDesastre(), dto.tipoDesastre(), dto.personasAfectadas(), dto.fecha(), dto.ubicacion());
    }

    public static DesastreDto desastreToDesastreDto(Desastre desastre) {
        if (desastre == null) return null;
        return new DesastreDto(desastre.getMagnitud(), desastre.getNombre(), desastre.getIdDesastre(), desastre.getTipoDesastre(),
                desastre.getPersonasAfectadas(), desastre.getFecha(), desastre.getUbicacion());
    }

}
