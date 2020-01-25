package pl.coderslab.STARS.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.STARS.model.Payment;
import pl.coderslab.STARS.repositories.ClarificationRepository;
import pl.coderslab.STARS.repositories.PaymentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> getAllPayments() {
        List<Payment> result = (List<Payment>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Payment>();
        }
    }

    public List<Payment> getAllPendingPayments() {
        List<Payment> result = (List<Payment>) repository.findAllByStatus("pending");
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Payment>();
        }
    }

    public List<Payment> getAllRaisedPayments() {
        List<Payment> result = (List<Payment>) repository.findAllByStatus("raised");
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Payment>();
        }
    }

    public List<Payment> getAllClosedPayments() {
        List<Payment> result = (List<Payment>) repository.findAllByStatus("closed");
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Payment>();
        }
    }
    public List<Payment> getAllLevel3Payments() {
        List<Payment> result = (List<Payment>) repository.findAllByStatus("level3");
        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<Payment>();
        }
    }

    public Payment getPaymentById(Long id) throws NotFoundException {
        Optional<Payment> payment = repository.findById(id);

        if (payment.isPresent()) {
            return payment.get();
        } else {
            throw new NotFoundException("No payment record exist for given id");
        }
    }

    public Payment createPayment(Payment details) {
        details.setCreated(LocalDateTime.now());
        details.setCitNumber("CIT");
        repository.save(details);
        return details;
    }

    public Payment updatePayment(Payment details) {

        Optional<Payment> payment = repository.findById(details.getId());

        if (payment.isPresent()) {
            Payment newDetails = payment.get();
            newDetails.setAlertId(details.getAlertId());
            newDetails.setAccount(details.getAccount());
            newDetails.setApplication(details.getApplication());
            newDetails.setStatus(details.getStatus());
            newDetails.setAmount(details.getAmount());
            newDetails.setCurrency(details.getCurrency());
            newDetails.setCitNumber(details.getCitNumber());
            newDetails.setAssignedPerson(details.getAssignedPerson());
            newDetails = repository.save(newDetails);

            return newDetails;
        } else {
            details = repository.save(details);

            return details;
        }
    }

    public void deletePaymentById(long id) throws NotFoundException {
        Optional<Payment> payment = repository.findById(id);

        if (payment.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException("No details record exist for given id");
        }
    }
}
