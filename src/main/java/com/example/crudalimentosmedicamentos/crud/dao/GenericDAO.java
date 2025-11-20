package com.example.crudalimentosmedicamentos.crud.dao;

import java.util.List;

public interface GenericDAO<T> {
    T findById(Long id) throws Exception;
    List<T> findAll() throws Exception;
    void insert(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(Long id) throws Exception;
}
