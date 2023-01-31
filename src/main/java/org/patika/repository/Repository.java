package org.patika.repository;

import java.util.List;

public interface Repository {
    List<?> getAll();

    <T> T getById(Long id);
}
