package vn.techmaster.jpatutorial.id.id_table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Long>, JpaSpecificationExecutor<UserTable> {
}