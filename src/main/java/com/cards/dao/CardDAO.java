package com.cards.dao;

import com.cards.bo.*;

/**
 * Card DAO Interface
 * @author Bakkiyalakshmi Ramanjulu
 */
public interface CardDAO {

	public Card get(String deckName);	
	public void addOrUpdate(String deckName, Card card);	
	public void remove(String deckName, Card card);

}
