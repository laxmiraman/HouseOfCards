package com.cards.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
/**
 * The Spring Boot project aims to make building web application with Spring much faster and easier. 
 * The guiding principle of Boot is convention over configuration.
 * 
 * Main Spring boot application which is the starting point for the Card Deck application
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
@SpringBootApplication
public class HouseOfCards {
	
   public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(HouseOfCards.class, args);
    }
}