package advprogproj.AgenziaEntrate.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.services.BankAccountService;
import ch.qos.logback.classic.Logger;

@RequestMapping("/institution")
@Controller
public class InstitutionController {
	private final Logger logger = (Logger) LoggerFactory.getLogger(InstitutionController.class);
	private BankAccountService bankAccountService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model inModel) {
		logger.info("Listing BankAccounts");
		List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
		int numBankAccounts = -1;
		
		try {
			allBankAccounts = this.bankAccountService.findAllBankAccounts();
			numBankAccounts = allBankAccounts.size();
		}catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		inModel.addAttribute("bankAccounts", allBankAccounts);
		inModel.addAttribute("numBankAccounts", numBankAccounts);
		
		return "institution/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newSinger") BankAccount newBankAccount, BindingResult br) {
		this.bankAccountService.update(newBankAccount);
		
		return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		inModel.addAttribute("newBankAccount", new BankAccount());
		return "institution/list";
	}
	
	@GetMapping(value = "/{bankAccountId}/edit")
	public String edit(@PathVariable("bankAccountId") String bankAccountId, 
					   @PathVariable("billDate") LocalDate billDate, Model inModel) {
		BankAccount bk = this.bankAccountService.findBankAccount(bankAccountId, billDate);
		inModel.addAttribute("newBankAccount", bk);
		return "institution/form";
	}
	
	@GetMapping(value = "/{singerId}/delete/")
	public String delete(@PathVariable("bankAccountId") String bankAccountId, @PathVariable("billDate") LocalDate billDate, @PathVariable("userId") String userId) {
		this.bankAccountService.delete(bankAccountId, billDate, userId);
		return "redirect:/institution/list/";
	}
	
	@Autowired
	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
}