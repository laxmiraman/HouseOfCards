package com.cards.Test;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.cards.bo.*;
import com.cards.dao.*;
import com.cards.db.stub.*;
import com.cards.strategy.*;

public class TestCard {
	
	  @Test
	  public void testCompareCardEquals(){
	    Card card1 = new Card(Suit.DIAMONDS,Rank.ACE);
	    Card card2 = new Card(Suit.DIAMONDS,Rank.ACE);
	    assertEquals(true, card1.equals(card2));
	  }
	  
	  @Test
	  public void testGetACard() {
			DeckDAO deckDAO = new DeckDAOStubImpl();
			List<Card> list1 = deckDAO.createDeck("FIRST");
			CardDAO cardDAO = new CardDAOStubImpl();
			assertEquals("ACE-SPADES", cardDAO.get("FIRST").toString());	
		}

	  @Test
	  public void testaddOrUpdate() {
			DeckDAO deckDAO = new DeckDAOStubImpl();
			List<Card> list1 = deckDAO.createDeck("FIRST");
			CardDAO cardDAO = new CardDAOStubImpl();
			Card c1 = new Card(Suit.CLUBS, Rank.ACE);
			cardDAO.addOrUpdate("FIRST", c1);
			assertEquals(52,deckDAO.getDecks().get("FIRST").size());
		}

	  @Test
	  public void testRemove() {
			DeckDAO deckDAO = new DeckDAOStubImpl();
			List<Card> list1 = deckDAO.createDeck("FIRST");
			CardDAO cardDAO = new CardDAOStubImpl();
			Card c1 = new Card(Suit.CLUBS, Rank.ACE);
			cardDAO.remove("FIRST", c1);
			assertEquals(false,deckDAO.getDeck("FIRST").contains(c1));
		}
	  
}
