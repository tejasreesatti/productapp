package com.capgemini.productapp;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.controller.ProductController;
import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.service.ProductService;
	@RunWith(MockitoJUnitRunner.class)
/*@RunWith(SpringRunner.class)*/
@SpringBootTest
public class ProductControllerTest {
	@Mock
	private ProductService productService;
	
	
	@InjectMocks
	private ProductController productController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}
	
	@Test
	public void testAddProduct() throws Exception {
		when(productService.addProduct(Mockito.isA(Product.class))).thenReturn(new Product(123, "DELL", "Laptop", 24000));
				mockMvc.perform(post("/product").
							 contentType(MediaType.APPLICATION_JSON_UTF8)
							.content("{\"productId\": 123, \"productName\": \"DELL\", \"productCategory\": \"Laptop\", \"productPrice\": 24000 }")
							.accept(MediaType.APPLICATION_JSON))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.productId").exists())
						.andDo(print());
	}
	/*@Test
	public void testUpdateProduct() throws Exception {
		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(new Product(123, "DELL", "Laptop", 25000));
//		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(new Product(123, "DELL", "Laptop", 25000));
			mockMvc.perform(put("/product").
					contentType(MediaType.APPLICATION_JSON)
					.content("{\"productId\": 123, \"productName\": \"DELL\", \"productCategory\": \"Laptop\", \"productPrice\": 24000 }")
					.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productId").exists())
			;
	}*/
	
	/*@Test
	public void verifyProductById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/productController/4").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.productId").exists())
		.andExpect(jsonPath("$.productName").exists())
		.andExpect(jsonPath("$.productCategory").exists())
		.andExpect(jsonPath("$.productPrice").exists())
		.andExpect(jsonPath("$.productId").value(300))
		.andExpect(jsonPath("$.productName").value("lenovo"))
		.andExpect(jsonPath("$.productCategory").value("laptop"))
		.andExpect(jsonPath("$.productPrice").value(50000))
		.andDo(print());
	}
	*/
	
	@Test
	public void testproductwhichUpdateProduct() throws Exception {
		Product product = new Product(1, "Laptop", "Electronics", 10000);
		when(productService.updateProduct(Mockito.isA(Product.class))).thenReturn(product);
		when(productService.findProductById(1)).thenReturn(product);
		mockMvc.perform(put("/product").contentType(MediaType.APPLICATION_JSON_UTF8).content(
				"{\"productId\":\"1\",\"productName\":\"mobile\",\"productCategory\":\"Electronics\",\"productPrice\":\"10000\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists()).andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists()).andExpect(status().isOk()).andDo(print());

		
	}
	
	@Test
	public void testproductFindProductById() throws Exception {
		Product product = new Product(1, "Laptop", "Electronics", 10000);
		when(productService.findProductById(1)).thenReturn(product);
		mockMvc.perform(get("/products/1")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.productName").exists()).andExpect(jsonPath("$.productCategory").exists())
				.andExpect(jsonPath("$.productPrice").exists()).andExpect(status().isOk()).andDo(print());

	}
	
	@Test
	public void testDelete() throws Exception {
		Product product = new Product(1, "Laptop", "Electronics", 30000);
		when(productService.findProductById(1)).thenReturn(product);
		mockMvc.perform(delete("/products/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());
	}

	
}
