package com.bookcompany.builder;

import com.bookcompany.builder.model.Ingredient;
import com.bookcompany.builder.repository.Repository;
import com.bookcompany.builder.repository.RepositoryImpl;
import com.bookcompany.builder.usecases.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class Main {
    public static <inputOutputRepository> void main(String[] args) throws Exception {

        Test test = new Test();

        while(true){
            test.doTransactions();
        }
    }
}
