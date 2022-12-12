package com.ironhack.ProyectoFinal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.ProyectoFinal.Repositories.*;
import com.ironhack.ProyectoFinal.models.Account;
import com.ironhack.ProyectoFinal.models.Address.Address;
import com.ironhack.ProyectoFinal.models.CheckingAccount;
import com.ironhack.ProyectoFinal.models.DTO.AccountDTO;
import com.ironhack.ProyectoFinal.models.SavingAccount;
import com.ironhack.ProyectoFinal.models.StudentChecking;
import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    private SavingAccountRepository savingAccountRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp(){

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.findAndRegisterModules();
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void shouldCreatedNewSavingAccount_WhenIsPerformed() throws Exception {
        Address address = new Address("El coral", "Valls", "43800");
        AccountHolder accountHolder = new AccountHolder("Juan Soto", "Js2572", LocalDate.of(1997, 06,06), address);
        accountHolderRepository.save(accountHolder);
        SavingAccount savingAccount = new SavingAccount("2572", new BigDecimal(1000), accountHolder, new BigDecimal("0.1"), new BigDecimal(250));
        savingAccountRepository.save(savingAccount);

        String body = objectMapper.writeValueAsString(savingAccount);

        MvcResult result = mockMvc.perform(post("/accounts/admin/saving-accounts/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2572"));
    }

    @Test
    void shouldCreatedNewCheckingAccount_WhenIsPerformed() throws Exception {
        AccountDTO accountDTO = new AccountDTO(2L,new BigDecimal(1000), "2572", new BigDecimal(12),new BigDecimal(250));

        String body = objectMapper.writeValueAsString(accountDTO);
        MvcResult result = mockMvc.perform(post("/accounts/admin/checking-accounts/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("1000"));
    }

    @Test
    void shouldCreatedNewStudentChecking_WhenIsPerformed() throws Exception {
        Address address = new Address("El coral", "Valls", "43800");
        AccountHolder accountHolder = new AccountHolder("Juan Soto", "Js2572", LocalDate.of(1997, 06,06), address);
        accountHolderRepository.save(accountHolder);

        StudentChecking studentChecking = new StudentChecking("2572",new BigDecimal(2500),accountHolder);
        String body = objectMapper.writeValueAsString(studentChecking);

        MvcResult result = mockMvc.perform(post("/accounts/admin/student-accounts/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2500"));

    }

    @Test
    void shouldReturnAnAccountById_WhenIsPerformed() throws Exception {
        Long id = 1L;
        MvcResult result = mockMvc.perform(get("/accounts/admin/id/{id}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("2022-12-10"));
    }

    @Test
    void shouldDeleteAccountById_WhenIsPerformed() throws Exception {
        Long id = 2L;
        mockMvc.perform(delete("/accounts/admin/delete-accounts/id/{id}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

        assertEquals(1, accountRepository.findAll().size());
    }


}
