package com.cards.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cards.bo.Card;
import com.cards.dao.DeckDAO;
import com.cards.db.stub.DeckDAOStubImpl;
import com.cards.strategy.*;

/**
 * This coarse class acts like a Business Service class
 * All the methods talks to DAO layer
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
public class CardService{	
	
	IStrategy iStrategy = null;
	
	public List<Card> createDeck(String deckName){		
		DeckDAO deckDAO = new DeckDAOStubImpl();
		return deckDAO.createDeck(deckName);		
	}	
	
	public List<Card> doShuffling(String deckName, String algorithm){
		DeckDAO deckDAO = new DeckDAOStubImpl();
		List<Card> list = createDeck(deckName);		
		System.out.println("Selected algorithm is"+algorithm);
		if(algorithm != null && algorithm.trim().equalsIgnoreCase("SplitHalfShuffling")){	
			iStrategy = new SplitHalfShuffling();
		}else{
			iStrategy = new SimpleShuffling();			
		}
		return iStrategy.doShuffling(list);
	}
	
	public void removeDeck(String deckName){
		DeckDAO deckDAO = new DeckDAOStubImpl();
		deckDAO.remove(deckName);
	}
	
	public List<List<Card>> getAllDecks(){
		ArrayList<Card> l = new ArrayList<Card>();
		DeckDAO deckDAO = new DeckDAOStubImpl();
		HashMap hmap = (HashMap) deckDAO.getDecks();
		return new ArrayList<List<Card>>(hmap.values());
	}
	
	public List<Card> getDeck(String deckName){
		ArrayList<Card> l = new ArrayList<Card>();
		DeckDAO deckDAO = new DeckDAOStubImpl();
		List<Card> list = (ArrayList<Card>) deckDAO.getDeck(deckName);
		return list;
	}
}
