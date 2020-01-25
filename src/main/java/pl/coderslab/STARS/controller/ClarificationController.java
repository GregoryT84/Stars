package pl.coderslab.STARS.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.STARS.model.Clarification;
import pl.coderslab.STARS.repositories.ClarificationRepository;
import pl.coderslab.STARS.service.ClarificationService;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Controller
@RequestMapping("/clarification")
public class ClarificationController {

    @Autowired
    private ClarificationService clarificationService;

    @RequestMapping("/all")
    public String getAllClarificationList(Model model) {
        List<Clarification> list = clarificationService.getAllClarifications();
        model.addAttribute("clarifications", list);
        return "list-clarification";
    }

    @GetMapping("/add")
    private String createClarification(Model model) {
        model.addAttribute("clarification", new Clarification());
        return "add-clarification";
    }

    @PostMapping("/add")
    private String addClarification(@Valid Clarification clarification, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-clarification";
        }
        clarificationService.createClarification(clarification);
        return "redirect:/clarification/all";
    }

    @GetMapping("/edit/{id}")
    public String editClarificationById(Model model, @PathVariable("id") Optional<Long> id) throws NotFoundException {
        Clarification updatedClarification = clarificationService.getClarificationById(id.get());
        model.addAttribute("clarification", updatedClarification);
        return "edit-clarification";
    }

    @PostMapping("/editClarification")
    public String updateClarification(Clarification clarification) {
        clarificationService.updateClarification(clarification);
        return "redirect:/clarification/all";
    }


    @GetMapping("/delete/{id}")
    public String deleteClarification(Model model, @PathVariable("id") long id) throws NotFoundException {
        clarificationService.deleteClarificationById(id);
        return "redirect:/clarification/all";
    }

    @RequestMapping("/preparefile")
    public String prepareToUpload() {
        return "upload";
    }

    @RequestMapping("/file")
    public String addClarificationFromFile() throws FileNotFoundException {
        Scanner input = new Scanner(new File("/home/grzegorz/Pulpit/STARS/upload/clarificationUploader"));
        input.useDelimiter("-|\n");



        while (input.hasNext()) {
            int id = input.nextInt();
            String clarAccount = input.next();
            long longClarAccount = Long.parseLong(clarAccount);
            String description = input.next();
            String hitName = input.next();
            String relatedCaseNumber = input.next();

            Clarification clar = new Clarification();
            clar.setId(id);
            clar.setClarificationAccount(longClarAccount);
            clar.setHitName(hitName);
            clar.setDescription(description);
            clar.setRelatedCaseNumber(relatedCaseNumber);
            clarificationService.createClarification(clar);
        }
        return "redirect:/clarification/all";
    }
}
