package vn.techmaster.jpablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.jpablog.entity.IdentityCard;

@Repository
public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}