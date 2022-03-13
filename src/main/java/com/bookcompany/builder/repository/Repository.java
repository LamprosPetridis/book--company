package com.bookcompany.builder.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public interface Repository<E> {

    //CRUD
    boolean create(E t);
    E read(String name);
    List<E> read();

    boolean delete(String name) throws Exception;
    boolean delete() throws  Exception;
}
