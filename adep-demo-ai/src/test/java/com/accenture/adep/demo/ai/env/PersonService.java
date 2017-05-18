package com.accenture.adep.demo.ai.env;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	public void saveOnePeople(PersonExample personExample){
		this.personRepository.save(personExample);
	}
	
	public PersonExample findOnePersion(BigInteger id){
		return this.personRepository.findOne(id);
	}
	
	
}
