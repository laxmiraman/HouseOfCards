package com.cards.strategy;

import java.util.*;

import com.cards.bo.Card;
import com.cards.bo.Rank;
import com.cards.bo.Suit;

/**
 * Implement a more complex algorithm that simulates hand-shuffling, 
 * i.e. splitting the deck in half and interleaving the two halves, repeating the process multiple times.
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
public class SplitHalfShuffling implements IStrategy {

	@Override
	public List<Card> doShuffling(List<Card> cards) {

		int suitLength = Suit.values().length;
		int rankLength = Rank.values().length;
		int deckCount = suitLength + rankLength;
		
		
		List<Card> left = cards.subList(0, cards.size() / 2);
		List<Card> right = cards.subList(cards.size() / 2 + 1, cards.size());

		// do a right hand shuffling
		for (int i = 0; i < right.size(); i++) {
			int r = i + (int) (Math.random() * (deckCount - i));
			Card temp = cards.get(r);
			cards.set(r, cards.get(i));
			cards.set(i, temp);
		}

		// do a left hand shuffling
		for (int i = 0; i < left.size(); i++) {
			int r = i + (int) (Math.random() * (deckCount - i));
			Card temp = cards.get(r);
			cards.set(r, cards.get(i));
			cards.set(i, temp);
		}

		LinkedList cardsList = new LinkedList(cards);
		for (int i = 1; i < deckCount; i++) {
			
			if ((i % 2) == 0) {
				cardsList.addFirst((Card) cards.get(i - 1)); // add to top
			} else {
				cardsList.addLast((Card) cards.get(i - 1)); // add to bottom
			}
		}

		return new ArrayList<Card>(cardsList);
	}
	
}