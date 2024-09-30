package org.example.interfaces;

import org.example.entities.Persona;

public interface Usuario {
    Persona getIdPersona();
    void setIdPersona(Persona isPersona);

    String getRol();
    void setRol(String rol);
}
