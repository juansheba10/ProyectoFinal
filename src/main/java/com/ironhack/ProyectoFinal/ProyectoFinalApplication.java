package com.ironhack.ProyectoFinal;

import com.ironhack.ProyectoFinal.Enums.Status;
import com.ironhack.ProyectoFinal.Repositories.*;
import com.ironhack.ProyectoFinal.models.Address.Address;
import com.ironhack.ProyectoFinal.models.CreditCard;
import com.ironhack.ProyectoFinal.models.Owners.Owner;
import com.ironhack.ProyectoFinal.models.Owners.SecondaryOwner;
import com.ironhack.ProyectoFinal.models.SavingAccount;
import com.ironhack.ProyectoFinal.models.Users.*;
import jakarta.persistence.AttributeOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class ProyectoFinalApplication implements CommandLineRunner {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PrimaryOwnerRepository primaryOwnerRepository;

	@Autowired
	SecondaryOwnerRepository secondaryOwnerRepository;

	@Autowired
	CreditCardRepository creditCardRepository;

	@Autowired
	ThirdPartyRepository thirdPartyRepository;



	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Address primaryAddress = new Address("Calle", "Strees", "Venezuela");
		Owner owner = new Owner("Juan Soto", LocalDate.of(2022, 05, 01));
		SecondaryOwner secondaryOwner = new SecondaryOwner("Monica", LocalDate.of(2022, 05, 01));
		CreditCard creditCard = new CreditCard(new BigDecimal(22), owner, secondaryOwner,new BigDecimal(21), new BigDecimal("1.00"), new BigDecimal("0.5"));
		User user = new AccountHolder("Juan SOto", "25252g", LocalDate.now(), primaryAddress);
        Role role = new Role("ADMIN", user);
		SavingAccount savingAccount = new SavingAccount("Juan Soto", new BigDecimal(21), owner, secondaryOwner, new BigDecimal(22), LocalDate.now(), Status.FROZEN, new BigDecimal("1.2"), new BigDecimal(122));
        User admin = new Admin("Juan Soto", "25725816");
		ThirdParty thirdParty = new ThirdParty("Juan", "Sgsgsg");

        userRepository.save(admin);
		userRepository.save(user);
		userRepository.save(thirdParty);
		roleRepository.save(role);
		primaryOwnerRepository.save(owner);
		secondaryOwnerRepository.save(secondaryOwner);
		creditCardRepository.save(creditCard);
		accountRepository.save(savingAccount);

	}
}
