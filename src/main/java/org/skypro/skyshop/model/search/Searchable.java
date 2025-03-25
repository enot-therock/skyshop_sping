package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String searchTerm();

    String typeContent();

    String searchableName();

    default UUID getId() {
        return UUID.randomUUID();
    }

    default String getStringRepresentation() {
        return "Имя " + searchTerm() + " объекта - тип " + typeContent() + " - объекта";
    }
}
