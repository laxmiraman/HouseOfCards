package com.cards.bo;

/**
 * 
 * Card Model Class contains Rank and Suit
 * @author Bakkiyalakshmi Ramanjulu
 *
 */
public class Card
{
	private Rank rank;
	private Suit suit;

	public Card(Suit suit, Rank rank)
	{
		this.rank=rank;
		this.suit=suit;
	}

	@Override 
	public String toString(){
		  return rank+"-"+suit;
	}

	public Rank getRank() {
		 return rank;
	}

	public Suit getSuit() {
		return suit;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(obj != null){
			Card c1 = (Card) obj;			
			if (c1.getRank().equals(this.rank) && c1.getSuit().equals(this.suit)){
				return true;				
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.rank.hashCode() + this.suit.hashCode();
	}
}