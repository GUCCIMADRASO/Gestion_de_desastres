package com.uniQuindio.gestionDesastres.factory;

import com.uniQuindio.gestionDesastres.model.GestionDeDesastres;

public class ModelFactory {
    private static ModelFactory instance;
    GestionDeDesastres gestionDeDesastres;

    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }
}
