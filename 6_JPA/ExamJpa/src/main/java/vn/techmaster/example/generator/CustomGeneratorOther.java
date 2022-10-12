package vn.techmaster.example.generator;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class CustomGeneratorOther implements IdentifierGenerator {

    private String prefix;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object o) throws HibernateException {
        String query = "select count(p.id) from Post p";

        long count = session.createQuery(query).stream().count();
        System.out.println(count);

        return prefix + "-" + (count + 1);
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        prefix = params.getProperty("prefix");
    }
}
