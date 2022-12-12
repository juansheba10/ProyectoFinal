package com.ironhack.ProyectoFinal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.ProyectoFinal.Repositories.AccountHolderRepository;
import com.ironhack.ProyectoFinal.Repositories.AccountRepository;
import com.ironhack.ProyectoFinal.Repositories.RoleRepository;
import com.ironhack.ProyectoFinal.Repositories.UserRepository;
import com.ironhack.ProyectoFinal.Services.AccountServices;
import com.ironhack.ProyectoFinal.Services.UserService;
import com.ironhack.ProyectoFinal.models.Address.Address;
import com.ironhack.ProyectoFinal.models.DTO.TransferDTO;
import com.ironhack.ProyectoFinal.models.Users.AccountHolder;
import com.ironhack.ProyectoFinal.models.Users.Admin;
import com.ironhack.ProyectoFinal.models.Users.ThirdParty;
import com.ironhack.ProyectoFinal.models.Users.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    UserService userService;

    @Autowired
    AccountServices accountServices;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    AccountRepository accountRepository;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @BeforeEach
    void setUp(){

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper.findAndRegisterModules();
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    void shouldAddNewAccountHolder_WhenIsPerformed() throws Exception {
        Address address = new Address("El coral", "Valls", "43800");
        AccountHolder accountHolder = new AccountHolder("Juan Soto", "Js2572", LocalDate.of(1997, 06,06), address);
        String body = objectMapper.writeValueAsString(accountHolder);

        MvcResult result = mockMvc.perform(post("/admin/account-holder/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Juan Soto"));
    }

    @Test
    void shouldAddNewAdmin_WhenIsPerformed() throws Exception {
        Admin admin = new Admin("Pedro", "2572");
        String body = objectMapper.writeValueAsString(admin);

        MvcResult result = mockMvc.perform(post("/admin/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();


        assertTrue(result.getResponse().getContentAsString().contains("Pedro"));

    }

    @Test
    void shouldAddNewThirdParty_WhenIsPerformed() throws Exception {
        ThirdParty thirdParty = new ThirdParty("Ronald", "2572", "5276");
        String body = objectMapper.writeValueAsString(thirdParty);

        MvcResult result = mockMvc.perform(post("/admin/user-thirdparty/add").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

        assertTrue(result.getResponse().getContentAsString().contains("Ronald"));
    }

    @Test
    void shouldTransferMoney_WhenIsPerformed() throws Exception {
        TransferDTO transferDTO = new TransferDTO(2L,1L, new BigDecimal(50), "Juan");
        String body = objectMapper.writeValueAsString(transferDTO);

        MvcResult result = mockMvc.perform(post("/user/holder/transfer").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();

    }

    @Test
    void shouldDeleteUserById_WhenIsPerformed() throws Exception {
        Long id = 2L;

        mockMvc.perform(delete("/admin/delete-user/{id}", id).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted());

        assertEquals(4, userRepository.findAll().size());
    }

}
