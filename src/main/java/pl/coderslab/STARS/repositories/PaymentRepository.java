package pl.coderslab.STARS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.STARS.model.Payment;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findAllByStatus(String status);
}
