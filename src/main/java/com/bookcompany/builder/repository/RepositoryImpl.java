package com.bookcompany.builder.repository;

import com.bookcompany.builder.model.Entity;
import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.model.Recipe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public  class RepositoryImpl<T extends Entity> implements Repository<T>{
    private final List<T> tList = new ArrayList<>();

    @Override
    public boolean create(T t) {
        if (t == null )
            return false;
        tList.add(t);
        return true;
    }

    @Override
    public T read(String name) {

        for(T t : tList){
            if (t.getName().equals(name))
                return t;
        }
        return null;
    }

    @Override
    public List<T> read() {
        /* deep copy of the list */
        List<T> returntList = new ArrayList<>();
        for(T t : tList) {
            returntList.add(t);
        }
        return returntList;


        //  return customers;
    }


    @Override
    public boolean delete(String name) throws Exception {
        T t = read (name);
        if(t == null)
            throw new Exception("Delete failed");
        tList.remove(t);
        return true;
    }

    @Override
    public boolean delete() throws Exception {
        if(tList == null)
            throw new Exception("Nothing to delete");
        tList.clear();
        return true;
    }

}
