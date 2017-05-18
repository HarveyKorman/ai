package com.accenture.adep.demo.ai.env;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMongoDBJunit {

	@Autowired
	private PersonService personService;
	
	private Logger log = Logger.getLogger(TestMongoDBJunit.class);
	
	@Test
	public void testMongo(){
		// Save one person example
		PersonExample personExample = new PersonExample();
		personExample.setFirstname("Demo");
		personExample.setLastname("ADEP");
		this.personService.saveOnePeople(personExample);
		
		//Get one person
		PersonExample other = this.personService.findOnePersion(personExample.getId());
		assertThat(other, is(personExample));
	}

}
