package com.example.jpanew.compositeIdGenerate;

import com.github.javafaker.Faker;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class EmployeeGenerateId implements IdentifierGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Faker faker = new Faker();
        return new EmployeeIds(
                faker.name().firstName(),
                faker.name().lastName()
        );
    }
}
