package vn.techmaster.generate;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.util.Properties;
import java.util.stream.Stream;

@Slf4j
public class StudentIdGenerate implements IdentifierGenerator {

    private String prefix;

    @Override
    public Object generate(SharedSessionContractImplementor session, Object obj) throws HibernateException {
        String query = String.format("select id from %s",obj.getClass().getSimpleName());

        log.info("Query : {}", query);

        log.info("Prefix : {}", prefix);

        Stream<String> ids = session.createQuery(query, String.class).stream();

        long max = ids.map(o -> o.replace(prefix + "-", ""))
                .mapToLong(Long::parseLong)
                .max()
                .orElse(0L);

        return prefix + "-" + (max + 1);

    }

    @Override
    public void configure(Type type, Properties properties,
                          ServiceRegistry serviceRegistry) throws MappingException {
        prefix = properties.getProperty("prefix");
    }
}
