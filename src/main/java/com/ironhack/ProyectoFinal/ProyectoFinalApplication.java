package com.ironhack.ProyectoFinal;

import com.ironhack.ProyectoFinal.models.Users.Role;
import com.ironhack.ProyectoFinal.Repositories.*;
import com.ironhack.ProyectoFinal.models.*;
import com.ironhack.ProyectoFinal.models.Address.Address;
import com.ironhack.ProyectoFinal.models.Users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ProyectoFinalApplication implements CommandLineRunner {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	AccountHolderRepository accountHolderRepository;

	@Autowired
	CreditCardRepository creditCardRepository;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;

	@Autowired
	PasswordEncoder passwordEncoder;



	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Address primaryAddress = new Address("Calle", "Strees", "Venezuela");

		AccountHolder primaryOwner = new AccountHolder("Juan", passwordEncoder.encode("2525"), LocalDate.of(1997, 06,06), primaryAddress);
		AccountHolder primaryOwner1 = new AccountHolder("Luis Pinto", passwordEncoder.encode("2525"), LocalDate.now(), primaryAddress);
		AccountHolder holder = new AccountHolder("Luis", passwordEncoder.encode("jssf"), LocalDate.now(), primaryAddress);
        User user1 = new Admin("Juus", "2526");
        Admin admin = new Admin("Juan Soto", passwordEncoder.encode("25725816"));
		ThirdParty thirdParty = new ThirdParty("Juan", passwordEncoder.encode("jss"), "hsss");
		StudentChecking studentChecking = new StudentChecking("2572", new BigDecimal(1000), primaryOwner);
		studentChecking.setSecondaryOwner(primaryOwner1);

		SavingAccount savingAccount = new SavingAccount("2572", new BigDecimal(50),primaryOwner,new BigDecimal("0.2"), new BigDecimal(100));
		CreditCard creditCard = new CreditCard(new BigDecimal(22), primaryOwner, new BigDecimal("1.00"), new BigDecimal("0.5"));



        userRepository.save(admin);
		userRepository.save(holder);
		userRepository.save(primaryOwner);
		userRepository.save(primaryOwner1);
		roleRepository.save(new Role("ACCOUNT_HOLDER", primaryOwner1));
		roleRepository.save(new Role("ACCOUNT_HOLDER", primaryOwner));
		roleRepository.save(new Role("ACCOUNT_HOLDER", holder));
		roleRepository.save(new Role("ADMIN", admin));
		userRepository.save(thirdParty);
		roleRepository.save(new Role("THIRD_PARTY", thirdParty));
		studentChecking.setPenaltyFee(studentChecking.getPenaltyFee());
		creditCard.setCreditLimit(creditCard.getCreditLimit());
		creditCardRepository.save(creditCard);
		creditCardRepository.save(creditCard);
		accountRepository.save(studentChecking);
		savingAccount.setPrimaryOwner(thirdParty);
		accountRepository.save(savingAccount);



	}
}
