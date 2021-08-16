
//package com.rvy.store.controller;
//
//import static org.mockito.Mockito.when;
//import java.io.IOException;
//import java.time.LocalDate;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rvy.store.app.StoreDemoApplication;
//import com.rvy.store.entity.Role;
//import com.rvy.store.entity.Store;
//import com.rvy.store.entity.User;
//import com.rvy.store.service.IUserService;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = StoreDemoApplication.class)
//@AutoConfigureMockMvc 
//@AutoConfigureTestDatabase(replace=Replace.NONE)
//public class UserControllerTest {
//	static byte[] asJsonString(Object object) throws IOException {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//		return mapper.writeValueAsBytes(object);
//	}
//	
//	@Autowired
//	private MockMvc mvc;
//	
//	@Mock
//	private IUserService userService;
//	private User user;
//	private Store store;
//	private List<Role> roleList;
//	
//	@InjectMocks
//	private UserController userController;
//	
//	@BeforeEach
//	public void setup(){
//		User user = new User(null,"BPIPC5522M","user01",LocalDate.of(2019,8,9),"user01@rvy.com",
//				7114411223L,"sec 121","near statue luffy","Lucknow","Uttar Pradesh",
//				110110,"India",store,roleList);
//		mvc = MockMvcBuilders.standaloneSetup(userController).build();
//
//	}
//	
//	@Test
//	public void whenValidInput_thenCreateUser() throws IOException, Exception {
//		when(userService.addUser(user)).thenReturn(user);
//		mvc.perform(post("/rvy/um/v1/user").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user))).
//		andExpect(status().isCreated());
//	}
//
////	@Test
////	public void whenFindAll_ReturnAll() throws IOException, Exception{
////		when(productOperation.getAllProducts()).thenReturn(productList);
////		mvc.perform(get("/rvy/api/pms/v1/products").contentType(MediaType.APPLICATION_JSON))
////		.andDo(print())
////		.andExpect(status().isOk())
////		.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
////		.andExpect(jsonPath("$[0].category", is("Electronics")));
////	}
//}

