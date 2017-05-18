package com.accenture.adep.demo.ai.common;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudBaseRepository<T, ID extends Serializable> extends MongoRepository<T, ID>{

}
