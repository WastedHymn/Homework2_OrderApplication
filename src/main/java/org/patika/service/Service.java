package org.patika.service;

import java.util.List;

public interface Service {
    List<?> getAll();

    <T> T getById(Long id);
}
