package com.Jaxs.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


@Configuration 
public class Config implements RepositoryRestConfigurer {

	 private EntityManager entityManager;
	 
	 @Autowired
	 public Config(EntityManager theEntityManager) {
		 entityManager = theEntityManager;
	 }
	 
	 @Override
	    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
	        exposeIds(config);
	    }
	 
	 
	 private void exposeIds(RepositoryRestConfiguration config) {

		       

		        // Getting entity from entity manager
		        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();

		        // Create array of entities
		        List<Class<?>> entityClasses = new ArrayList<>();

		        // Getting entity type
		        for (EntityType<?> tempEntityType : entities) {
		            entityClasses.add(tempEntityType.getJavaType());
		        }

		        // Find entity ids for the array of entity/SQL domain types
		        Class<?>[] domainTypes = entityClasses.toArray(new Class[0]);
		        config.exposeIdsFor(domainTypes);
		    }
		}
