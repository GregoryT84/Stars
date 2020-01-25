package pl.coderslab.STARS.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.STARS.model.Clarification;
import pl.coderslab.STARS.repositories.ClarificationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClarificationService {

    @Autowired
    private ClarificationRepository clarificationRepository;

    public List<Clarification> getAllClarifications() {
        List<Clarification> clarificationList = (List<Clarification>) clarificationRepository.findAll();

        if (clarificationList.size() > 0) {
            return clarificationList;
        } else {
            return new ArrayList<Clarification>();
        }
    }

    public Clarification getClarificationById(long id) throws NotFoundException {
        Optional<Clarification> clarification = clarificationRepository.findById(id);
        if (clarification.isPresent()) {
            return clarification.get();
        } else {
            throw new NotFoundException("No clarification record exist for given id");
        }
    }

    public Clarification createClarification(Clarification createdClarification) {
        clarificationRepository.save(createdClarification);
        return createdClarification;
    }

    public Clarification updateClarification(Clarification updatedClarification) {

        Optional<Clarification> clarification = clarificationRepository.findById(updatedClarification.getId());

        if (clarification.isPresent()) {
            Clarification newUpdatedClarification = clarification.get();
            newUpdatedClarification.setHitName(updatedClarification.getHitName());
            newUpdatedClarification.setClarificationAccount(updatedClarification.getClarificationAccount());
            newUpdatedClarification.setDescription(updatedClarification.getDescription());
            newUpdatedClarification.setRelatedCaseNumber(updatedClarification.getRelatedCaseNumber());
            newUpdatedClarification = clarificationRepository.save(newUpdatedClarification);

            return newUpdatedClarification;
        } else {
            updatedClarification = clarificationRepository.save(updatedClarification);

            return updatedClarification;
        }
    }

    public void deleteClarificationById(long id) throws NotFoundException {
        Optional<Clarification> clarification = clarificationRepository.findById(id);

        if (clarification.isPresent()) {
            clarificationRepository.deleteById(id);
        } else {
            throw new NotFoundException("No details record exist for given id");
        }
    }
}
