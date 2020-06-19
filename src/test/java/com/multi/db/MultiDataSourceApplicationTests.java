package com.multi.db;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.multi.db.primary.Users;
import com.multi.db.primary.repo.UserRepository;
import com.multi.db.secondary.Product;
import com.multi.db.secondary.repo.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MultiDataSourceApplicationTests {

 @Autowired
 private UserRepository userRepo;
 
 @Autowired
 private ProductRepository proRepo;
 
 private Product product;
 
 private Users users;
 
	@Before
	public void init() {
	  users= new Users();
	  users.setAge(34);
	  users.setEmail("gsk1@hcl.com");
	  users.setName("Gsk");
	  product= new Product();
	  product.setName("mulitSource1");
	  product.setPrice(new Float("128833.02"));
	}

	
	@Test
	@Rollback(value=false)
	public void testSaveData() {
	  users=userRepo.save(users);
	  System.out.println(users);
	  System.out.println(userRepo.findAll());
	  assertNotNull(users);
	}
	
	@Test
	@Rollback(value=false)
	public void testProductRepo() {
	  product=proRepo.save(product);
	  System.out.println(product);
	  System.out.println(userRepo.findAll());
	  assertNotNull(product);
	}
}
