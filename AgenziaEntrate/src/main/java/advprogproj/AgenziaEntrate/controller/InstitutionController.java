package advprogproj.AgenziaEntrate.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//import org.slf4j.LoggerFactory;
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
		
		inModel.addAttribute("bankAccounts", allBankAccounts);
		inModel.addAttribute("numBankAccounts", numBankAccounts);
		
		return "institution/list";
	}
	
	@PostMapping(value = "/save")
	public String save(@ModelAttribute("newBankAccount") BankAccount newBankAccount, 
					   @ModelAttribute("userId") String userId, BindingResult br) {
		this.bankAccountService.update(newBankAccount);
		if(userId != null) {
			this.bankAccountService.addOwner(userId, newBankAccount.getIBAN(), newBankAccount.getBillDate().toString());
			this.userService.update(this.userService.findUser(userId));
			this.bankAccountService.update(newBankAccount);
		}
		return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model inModel) {
		List<User> users = this.userService.findAllUsers();
		inModel.addAttribute("bankAccount", new BankAccount());
		inModel.addAttribute("users", users);
		inModel.addAttribute("userId", new String());
		return "institution/form";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/edit")
	public String edit(@PathVariable("bankAccountId") String bankAccountId, 
					   @PathVariable("billDate") String billDate, Model inModel) {
		BankAccount bk = this.bankAccountService.findBankAccount(bankAccountId, billDate);
		inModel.addAttribute("bankAccount", bk);
		return "institution/form";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/delete/")
	public String delete(@PathVariable("bankAccountId") String bankAccountId, 
						 @PathVariable("billDate") String billDate, 
						 @PathVariable("userId") String userId) {
		this.bankAccountService.delete(bankAccountId, billDate, userId);
		return "redirect:/institution/list/";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/user/{userId}/unlink/" )
	public String unlinkInstrument(
			@RequestParam(value = "next", required=false) String next, 
			@PathVariable("bankAccountId") String bankAccountId, 
			@PathVariable("billDate") String billDate,
			@PathVariable("userId") String userId)
	{
		this.bankAccountService.removeOwner(bankAccountId, billDate, userId);
		
		// NB: sotto e` molto importante la barra (/) prima di singers/list, perche` la struttura del redirect
		// dev'essere:
		// redirect:<URL>
		// e /singers/list e` appunta una URL della nostra applicazione. Nella maggior parte degli altri controller, 
		// invece, si ritornava 'singers/list' che era un *nome di vista*, non una URL (!!!)
		
		if (next == null || next.length() == 0) {
			next = "/institution/list";
		}
		
		return "redirect:" + next;
	}
	
	@GetMapping("/link/choose")
	public String link(Model uiModel) {
		uiModel.addAttribute("singers", this.bankAccountService.findAllBankAccounts());
		uiModel.addAttribute("instruments", this.userService.findAllUsers());
		
		return "institution/link_choose";
	}
	
	// handle requests like this:
	//
	// http://localhost/.../link/?singer=123&instrument=456&next=/instruments/list
	//
	@PostMapping("/link")
	public String link(
			@RequestParam(value="next", required=false) String next,
			@RequestParam(value="user") String userId,
			@RequestParam(value="bankAccount") String bankAccountId, 
			@RequestParam(value="billDate")String billDate){
		User u = this.userService.findUser(userId);
		BankAccount b = this.bankAccountService.findBankAccount(bankAccountId, billDate);
		this.bankAccountService.addOwner(userId, bankAccountId, billDate);
		this.userService.addBankAccount(userId, bankAccountId, LocalDate.parse(billDate));
		this.bankAccountService.update(b);
		this.userService.update(u);
		if (next == null || next.length() == 0) {
			next = "/institution/list";
		}
		
		return "redirect:" + next;
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