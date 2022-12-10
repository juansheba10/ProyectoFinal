package com.ironhack.ProyectoFinal.Services;

import com.ironhack.ProyectoFinal.Repositories.AccountRepository;
import com.ironhack.ProyectoFinal.Repositories.ThirdPartyRepository;
import com.ironhack.ProyectoFinal.Repositories.TransferMoneyRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.DTO.ThirdPartyDTO;
import com.ironhack.ProyectoFinal.models.DTO.TransferDTO;
import com.ironhack.ProyectoFinal.models.TransferMoney;
import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.Admin;
import com.ironhack.ProyectoFinal.models.Users.ThirdParty;
import com.ironhack.ProyectoFinal.models.Users.User;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransferMoneyRepository transferMoneyRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));
    }

    public AccountHolder addUser(AccountHolder accountHolder) {

        return userRepository.save(accountHolder);
    }

    public ThirdParty addThirdParty(ThirdParty thirdParty) {
        return userRepository.save(thirdParty);
    }

    public Admin addAdmin(Admin admin) {
        return userRepository.save(admin);
    }




    public TransferMoney transferMoney(TransferDTO transferDTO){
        Account sendingAccount = accountRepository.findById(transferDTO.getSendAccountID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));
        Account receiveAccount = accountRepository.findById(transferDTO.getReceiveAccountID()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));



        if (receiveAccount.getPrimaryOwner().getUsername().equals(transferDTO.getAccountOwnerName()) || receiveAccount.getSecondaryOwner().get().getUsername().equals(transferDTO.getAccountOwnerName())){
            if (sendingAccount.getBalance().compareTo(transferDTO.getAmmount()) > 0){
                sendingAccount.setBalance(sendingAccount.getBalance().subtract(transferDTO.getAmmount()));
                receiveAccount.setBalance(receiveAccount.getBalance().add(transferDTO.getAmmount()));
                accountRepository.save(sendingAccount);
                accountRepository.save(receiveAccount);
            } else {
                throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "No hay dinero suficiente para hacer la transferencia");
            }
        }
        TransferMoney transferMoney = new TransferMoney(sendingAccount, receiveAccount, transferDTO.getAmmount());
        return transferMoneyRepository.save(transferMoney);
    }

    public ThirdParty findByHashedKey(String hashedKey) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(hashedKey.getBytes(StandardCharsets.UTF_8));
        String hashedKey2 = DatatypeConverter.printHexBinary(hash);
        return userRepository.findByHashedKey(hashedKey2).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));
    }


    public TransferMoney thirdPartyTransferMoney(String hashedKey, ThirdPartyDTO thirdPartyDTO) throws NoSuchAlgorithmException {


        Account sendingAccount = accountRepository.findById(thirdPartyDTO.getSendingAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));
        Account receivingAccount = accountRepository.findById(thirdPartyDTO.getReceivingAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] hash = messageDigest.digest(hashedKey.getBytes(StandardCharsets.UTF_8));
        String hashedKey2 = DatatypeConverter.printHexBinary(hash);
        ThirdParty thirdParty = userRepository.findByHashedKey(hashedKey2).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));

        if (hashedKey2.equals(thirdParty.getHashedKey())){

        if (sendingAccount.getBalance().compareTo(thirdPartyDTO.getAmmount()) > 0){
            if (receivingAccount.getSecretKey().equals(thirdPartyDTO.getSecretKey())){
                if (receivingAccount.getAccountId().equals(thirdPartyDTO.getReceivingAccountId())){
                    sendingAccount.setBalance(sendingAccount.getBalance().subtract(thirdPartyDTO.getAmmount()));
                    receivingAccount.setBalance(receivingAccount.getBalance().add(thirdPartyDTO.getAmmount()));
                    accountRepository.save(sendingAccount);
                    accountRepository.save(receivingAccount);

                }
                else {
                    throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "No hay dinero suficiente para hacer la transferencia");
                }
            }
            else {
                throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "No hay dinero suficiente para hacer la transferencia");
            }
        }  else {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "No hay dinero suficiente para hacer la transferencia");
        }

        TransferMoney transferMoney = new TransferMoney(sendingAccount, receivingAccount, thirdPartyDTO.getAmmount());
        return transferMoneyRepository.save(transferMoney);
    } else {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "La hashed key no coincide con la proporcionada");
        }
    }


    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe en la base de datos"));
        userRepository.deleteById(id);
    }
}
