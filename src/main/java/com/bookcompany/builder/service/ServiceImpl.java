package com.bookcompany.builder.service;

import com.bookcompany.builder.model.Entity;
import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.repository.RepositoryImpl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ServiceImpl<T extends Entity> implements Service<T> {
    Repository<T> repository = new RepositoryImpl<T>();

    @Override
    public void create(T t) throws Exception {
        repository.create(t);
    }

    @Override
    public T read(String name) throws Exception {
        return repository.read(name);
    }

    @Override
    public List<T> read() throws Exception {
        return repository.read();
    }

    @Override
    public void delete(String name) throws Exception {
        repository.delete(name);
    }

    @Override
    public void delete() throws Exception {
        repository.delete();
    }


}
