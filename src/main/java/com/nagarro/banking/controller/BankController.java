package com.nagarro.banking.controller;

import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.model.SearchResult;
import com.nagarro.banking.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/bank")
public class BankController {

    @Autowired
    BankService bankService;

    @GetMapping("/dashboard")
    public String home(Model model) {
        model.addAttribute("searchcriteria", new SearchCriteriaRequestModel());
        return "/dashboard";
    }

    @RequestMapping(value = "/statementsearch", method = RequestMethod.POST)
    public String search(HttpServletRequest request, HttpServletResponse response,
                         @ModelAttribute("searchcriteria") SearchCriteriaRequestModel searchCriteriaRequestModel, Model model) {
        List<SearchResult> res = bankService.searchStatement(searchCriteriaRequestModel);
        model.addAttribute("search_results", res);

        return "/dashboard";
    }
}
