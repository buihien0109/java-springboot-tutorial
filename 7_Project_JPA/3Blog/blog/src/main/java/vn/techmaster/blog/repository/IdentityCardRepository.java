package vn.techmaster.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.blog.entity.IdentityCard;

@Repository
public interface IdentityCardRepository extends JpaRepository<IdentityCard, Integer> {
}