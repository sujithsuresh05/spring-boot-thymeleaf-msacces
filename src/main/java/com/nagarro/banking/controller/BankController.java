package com.nagarro.banking.controller;

import com.nagarro.banking.model.SearchCriteriaRequestModel;
import com.nagarro.banking.model.SearchResult;
import com.nagarro.banking.service.BankService;
import com.nagarro.banking.utils.LogTextConstants;
import com.nagarro.banking.utils.ModelAtributeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.nagarro.banking.utils.Constants.SEARCH_CRITERIA_VALIDATOR_QUALIFIER_NAME;

@Controller
@RequestMapping("/bank")
public class BankController {

    private static final Logger logger = LoggerFactory
            .getLogger(BankController.class);

    @Autowired
    private BankService bankService;

    @Autowired
    @Qualifier(SEARCH_CRITERIA_VALIDATOR_QUALIFIER_NAME)
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping(ModelAtributeConstant.DASHBOARD_URL)
    public String home(Model model) {
        logger.info(LogTextConstants.BANK_CONTROLLER_GET_HOME_INFO);
        model.addAttribute(ModelAtributeConstant.SEARCH_CRITERIA_ATRIBUTE, new SearchCriteriaRequestModel());
        return ModelAtributeConstant.DASHBOARD_URL;
    }

    @PostMapping(value = ModelAtributeConstant.SEARCH_PATH)
    public String search(HttpServletRequest request, HttpServletResponse response,
                         @ModelAttribute(ModelAtributeConstant.SEARCH_CRITERIA_ATRIBUTE) @Validated SearchCriteriaRequestModel searchCriteriaRequestModel,
                         BindingResult bindingResult, Model model) {
        logger.info(LogTextConstants.BANK_CONTROLLER_POST_METHOD_ENTRY_INFO);
        List<SearchResult> res = bankService.searchStatement(searchCriteriaRequestModel);
        if (bindingResult.hasErrors()) {
            logger.info(LogTextConstants.BANK_CONTROLLER_POST_METHOD_VALIDATION_FAIL_INFO);
        } else {
            model.addAttribute(ModelAtributeConstant.SEARCH_RESULTS, res);
            logger.info(LogTextConstants.BANK_CONTROLLER_POST_SEARCH_INFO);
        }
        return ModelAtributeConstant.DASHBOARD_URL;
    }
}
