package com.bookcompany.builder.service;

import java.util.List;

public interface Service<E> {
    void create(E t) throws Exception;
    E read(String name) throws Exception;
    List<E> read() throws Exception;
    void delete(String name) throws Exception;
    void delete() throws Exception;


}
