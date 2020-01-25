package pl.coderslab.STARS.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.STARS.model.Payment;
import pl.coderslab.STARS.repositories.ClarificationRepository;
import pl.coderslab.STARS.service.PaymentService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ClarificationRepository clarificationRepository;

    @RequestMapping("/all")
    public String getAllPaymentsList(Model model) {
        List<Payment> list = paymentService.getAllPayments();
        model.addAttribute("payments", list);
        return "list-payments";
    }
    @RequestMapping("/pending")
    public String getAllPendingPaymentsList(Model model) {
        List<Payment> list = paymentService.getAllPendingPayments();
        model.addAttribute("payments", list);
        return "list-payments";
    }

    @RequestMapping("/level3")
    public String getAllLevel3PaymentsList(Model model) {
        List<Payment> list = paymentService.getAllLevel3Payments();
        model.addAttribute("payments", list);
        return "list-payments";
    }

    @RequestMapping("/raised")
    public String getAllRaisedPaymentsList(Model model) {
        List<Payment> list = paymentService.getAllRaisedPayments();
        model.addAttribute("payments", list);
        return "list-payments";
    }

    @RequestMapping("/closed")
    public String getAllClosedPaymentsList(Model model) {
        List<Payment> list = paymentService.getAllClosedPayments();
        model.addAttribute("payments", list);
        return "list-payments";
    }

    @GetMapping("/add")
    private String createPayment(Model model) {
        model.addAttribute("payment", new Payment());
        return "add-payment";
    }

    @PostMapping("/add")
    private String addClarification(@Valid Payment payment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-payment";
        }
        long testAccount = payment.getAccount();
        String testStringAccount = Long.toString(testAccount);
        if (clarificationRepository.findAllByClarificationAccount(testAccount).toString().contains(testStringAccount)) {
            return "information";
        }
        paymentService.createPayment(payment);
        return "redirect:/payment/pending";
    }

    @GetMapping("/edit/{id}")
    public String editPaymentById(Model model, @PathVariable("id") Optional<Long> id) throws NotFoundException {
        Payment details = paymentService.getPaymentById(id.get());
        model.addAttribute("payment", details);
        return "edit-payment";
    }

    @PostMapping("/editPayment")
    public String updatePayment(Payment payment) {
        long testAccount = payment.getAccount();
        String testStringAccount = Long.toString(testAccount);
        if (clarificationRepository.findAllByClarificationAccount(testAccount).toString().contains(testStringAccount)) {
            return "information";
        }
        paymentService.updatePayment(payment);
        return "redirect:/payment/all";
    }

    @GetMapping("/delete/{id}")
    public String deletePayment(Model model, @PathVariable("id") long id) throws NotFoundException {
        paymentService.deletePaymentById(id);
        return "redirect:/payment/all";
    }
}
