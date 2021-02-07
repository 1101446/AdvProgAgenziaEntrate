package advprogproj.AgenziaEntrate.controller;

import java.util.ArrayList;
import java.util.List;

//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import advprogproj.AgenziaEntrate.model.entities.BankAccount;
import advprogproj.AgenziaEntrate.model.entities.User;
import advprogproj.AgenziaEntrate.services.BankAccountService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/institution")
@Controller
public class InstitutionController {
	//private final Logger logger = (Logger) LoggerFactory.getLogger(BankAccountController.class);
	private BankAccountService bankAccountService;
	private UserService userService;
	
	@GetMapping(value = "/list")
	public String list(Model inModel) {
		//logger.info("Listing BankAccounts");
		List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
		int numBankAccounts = -1;
		
		try {
			allBankAccounts = this.bankAccountService.findAllBankAccounts();
			numBankAccounts = allBankAccounts.size();
		}catch(Exception e) {
			//logger.error(e.getMessage());
		}
		
		inModel.addAttribute("allBankAccounts", allBankAccounts);
		inModel.addAttribute("numBankAccounts", numBankAccounts);
		
		return "institution/list";
	}
	
	@PostMapping(value = "/save")
	public String saveBankAccount(@ModelAttribute("bankAccount") BankAccount newBankAccount, 
					   @RequestParam("userId") String userId) {
		BankAccount bk = this.bankAccountService.update(newBankAccount);
		if(userId != null) {
			return "redirect:/institution/save/"+bk.getIBAN()+"/"+bk.getBillDate().toString()+"/"+userId;
		}
		return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/save/{bankAccountId}/{billDate}/{userId}")
	public String saveBankAccountUser(@PathVariable("bankAccountId") String bankAccountId,
									  @PathVariable("billDate") String billDate,
									  @PathVariable("userId") String userId) {
		this.bankAccountService.addOwner(userId, bankAccountId, billDate);
		return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		List<User> users = this.userService.findAllUsers();
		inModel.addAttribute("bankAccount", new BankAccount());
		inModel.addAttribute("users", users);
		return "institution/form";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/edit")
	public String edit(@PathVariable("bankAccountId") String bankAccountId, 
					   @PathVariable("billDate") String billDate, Model inModel) {
		List<User> users = this.userService.findAllUsers();
		BankAccount bk = this.bankAccountService.findBankAccount(bankAccountId, billDate);
		inModel.addAttribute("bankAccount", bk);
		inModel.addAttribute("users", users);
		return "institution/form";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/delete/")
	public String delete(@PathVariable("bankAccountId") String bankAccountId, 
						 @PathVariable("billDate") String billDate, 
						 @PathVariable("userId") String userId) {
		this.bankAccountService.delete(bankAccountId, billDate, userId);
		return "redirect:/institution/list/";
	}
	
	@GetMapping("/link/choose")
	public String link(Model uiModel) {
		uiModel.addAttribute("bankAccounts", this.bankAccountService.findAllBankAccounts());
		uiModel.addAttribute("users", this.userService.findAllUsers());
		
		return "institution/link_choose";
	}
	
	@PostMapping("/link")
	public String link(@RequestParam(value="bankAccount") String bankAccount,
					   @RequestParam(value="user") String userId){
		String []bankAccountId = bankAccount.split("--");
		System.out.println(bankAccountId);
		System.out.println(bankAccountId[1]);
		this.bankAccountService.addOwner(userId, bankAccountId[0], bankAccountId[1]);
		return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/user/{userId}/unlink/" )
	public String unlinkBankAccount( 
			@PathVariable("bankAccountId") String bankAccountId, 
			@PathVariable("billDate") String billDate,
			@PathVariable("userId") String userId){
		this.bankAccountService.removeOwner(userId,bankAccountId,billDate);
		return "redirect:/institution/list";
	}
	
	@Autowired
	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}