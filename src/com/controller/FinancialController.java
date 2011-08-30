package com.controller;

import com.domain.commerce.FinancialService;
import com.domain.customer.User;
import com.domain.commerce.TransactionRepository;
import com.domain.customer.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

/**
 * @author Stanislav Kurilin
 */

@Controller
@RequestMapping("/financial")
public class FinancialController {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    private final FinancialService service;

    @Inject
    public FinancialController(TransactionRepository transactionRepository, FinancialService service, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.service = service;
        this.userRepository = userRepository;
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    public String inputPage(Model model){
        return "transfer";
    }
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    public String transfer(@RequestParam Long from, @RequestParam Long to, @RequestParam int amount){
        final User userFrom = userRepository.findOne(from);
        final User userTo = userRepository.findOne(to);
        service.transfer(userFrom, userTo, amount);
        return "transfer";
    }

}
