package pl.coderslab.STARS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.STARS.model.Clarification;

import java.util.List;

public interface ClarificationRepository extends JpaRepository<Clarification, Long> {
    List<Clarification> findAllByClarificationAccount(long cla);
}
