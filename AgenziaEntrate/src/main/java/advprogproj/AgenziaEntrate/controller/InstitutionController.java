package advprogproj.AgenziaEntrate.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import advprogproj.AgenziaEntrate.model.entities.UserBankAccount;
import advprogproj.AgenziaEntrate.services.BankAccountService;
import advprogproj.AgenziaEntrate.services.UserBankAccountService;
import advprogproj.AgenziaEntrate.services.UserService;
//import ch.qos.logback.classic.Logger;

@RequestMapping("/institution")
@Controller
public class InstitutionController {
	//private final Logger logger = (Logger) LoggerFactory.getLogger(BankAccountController.class);
	private BankAccountService bankAccountService;
	private UserBankAccountService userBankAccountService;
	private UserService userService;
	
	@GetMapping(value = "/list")
	public String list(Model institutionModel) {
		//logger.info("Listing BankAccounts");
		List<BankAccount> allBankAccounts = new ArrayList<BankAccount>();
		Set<UserBankAccount> allUserBankAccounts = new HashSet<UserBankAccount>();
		int numBankAccounts = -1;
		int numUserBankAccounts = -1;
		
		try {
			allBankAccounts = this.bankAccountService.findAllBankAccounts();
			allUserBankAccounts = this.userBankAccountService.findAllUserBankAccounts();
			numBankAccounts = allBankAccounts.size();
			numUserBankAccounts = allUserBankAccounts.size();
		}catch(Exception e) {
			//logger.error(e.getMessage());
		}
		
		institutionModel.addAttribute("allBankAccounts", allBankAccounts);
		institutionModel.addAttribute("numBankAccounts", numBankAccounts);
		institutionModel.addAttribute("allUserBankAccounts", allUserBankAccounts);
		institutionModel.addAttribute("numUserBankAccounts", numUserBankAccounts);
		return "institution/list";
	}
	
	@PostMapping(value = "/profile")
	public String getProfile(@RequestParam("email") String email, Model userModel) {
		//logger.info("Listing RealEstates");
		User profile = this.userService.findUserEmail(email);
		Set<UserBankAccount> profileBankAccounts = this.userService.getUserBankAccounts(profile);
		userModel.addAttribute("profileBankAccounts", profileBankAccounts);
		return "institution/list";
	}
	
	@PostMapping(value = "/save")
	public String saveBankAccount(@ModelAttribute("bankAccount") BankAccount newBankAccount, 
					   @RequestParam(value="userId") String userId,
					   @RequestParam(value="update") boolean update) {
		BankAccount bk;
		if(update)
			bk = this.bankAccountService.update(newBankAccount);
		else
			bk = this.bankAccountService.create(newBankAccount.getIBAN(), newBankAccount.getBankName(), newBankAccount.getBillDate(), newBankAccount.getBalance());
		if(!userId.equals("noUser")) 
			return "redirect:/institution/save/"+bk.getIBAN()+"/"+bk.getBillDate().toString()+"/"+userId;
		else 
			return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/save/{bankAccountId}/{billDate}/{userId}")
	public String saveUserBankAccount(@PathVariable("bankAccountId") String bankAccountId,
									  @PathVariable("billDate") String billDate,
									  @PathVariable("userId") String userId) {
		this.userBankAccountService.create(userId, bankAccountId, LocalDate.parse(billDate));
		return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/add")
	public String add(Model institutionModel) {
		List<User> users = this.userService.findAllUsers();
		institutionModel.addAttribute("bankAccount", new BankAccount());
		institutionModel.addAttribute("users", users);
		institutionModel.addAttribute("update", false);
		return "institution/form";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/edit")
	public String editBankAccount(@PathVariable("bankAccountId") String bankAccountId, 
					   @PathVariable("billDate") String billDate, Model institutionModel) {
		List<User> users = this.userService.findAllUsers();
		BankAccount bk = this.bankAccountService.findBankAccount(bankAccountId, LocalDate.parse(billDate));
		institutionModel.addAttribute("bankAccount", bk);
		institutionModel.addAttribute("users", users);
		institutionModel.addAttribute("update", true);
		return "institution/form";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/delete")
	public String delete(@PathVariable("bankAccountId") String bankAccountId, 
						 @PathVariable("billDate") String billDate) {
		this.bankAccountService.delete(bankAccountId, billDate);
		return "redirect:/institution/list";
	}
	
	@GetMapping("/link/choose")
	public String link(Model institutionModel) {
		institutionModel.addAttribute("bankAccounts", this.bankAccountService.findAllBankAccounts());
		institutionModel.addAttribute("users", this.userService.findAllUsers());
		
		return "institution/link_choose";
	}
	
	@PostMapping("/link")
	public String link(@RequestParam(value="bankAccount") String bankAccount,
					   @RequestParam(value="user") String userId){
		String []bankAccountId = bankAccount.split("--");
		this.userBankAccountService.create(userId, bankAccountId[0], LocalDate.parse(bankAccountId[1]));
		return "redirect:/institution/list";
	}
	
	@GetMapping(value = "/{bankAccountId}/{billDate}/user/{userId}/unlink" )
	public String unlinkBankAccount( 
			@PathVariable("bankAccountId") String bankAccountId, 
			@PathVariable("billDate") String billDate,
			@PathVariable("userId") String userId){
		this.userBankAccountService.delete(userId,bankAccountId,LocalDate.parse(billDate));
		return "redirect:/institution/list";
	}
	
	@Autowired
	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}
	
	@Autowired
	public void setUserBankAccountService(UserBankAccountService userBankAccountService) {
		this.userBankAccountService = userBankAccountService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}