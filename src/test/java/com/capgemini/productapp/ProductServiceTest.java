package com.capgemini.productapp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.productapp.entity.Product;
import com.capgemini.productapp.repository.ProductRepository;
import com.capgemini.productapp.service.ProductService;
import com.capgemini.productapp.service.impl.ProductServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	
	private ProductServiceImpl productServiceImpl;
	
	private MockMvc mockMvc;
	
	Product product = new Product(1, "DEll", "Aspiron", 50000);
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productServiceImpl).build();
	}
	
	
	
	@Test
	public void testServiceAddProduct() {
		when(productRepository.save(Mockito.isA(Product.class))).thenReturn(product);
		System.out.println(product);
		Product result = productServiceImpl.addProduct(product);
		System.out.println(result);
		assertEquals(product, result);
	
	}
	
	@Test
	public void testServiceUpdateProduct() {
		System.out.println(product);
		product.setProductName("acer");
		when(productRepository.save(Mockito.isA(Product.class))).thenReturn(product);
		System.out.println(product);
		assertEquals(product, productServiceImpl.updateProduct(product));
		
	}
	
	
	
	@Test
	public void testFindProductById() throws Exception {
		System.out.println(product.getProductId());
		
		Optional<Product> optionalProduct = Optional.of(product);
		when(productRepository.findById(Mockito.isA(Integer.class))).thenReturn(optionalProduct);
		
		System.out.println(product.getProductId() + "for" + product);
		assertEquals(optionalProduct.get(), productServiceImpl.findProductById(1));
	}
	
	
	@Test
	public void TestForDeleteProduct() throws Exception {
		System.out.println(product);
		productServiceImpl.deleteProduct(product);
		verify(productRepository).delete(product);
		System.out.println(product);
	}
	
	
	
	
	
	
	
	

	
}
