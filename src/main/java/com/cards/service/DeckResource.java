package com.cards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cards.bo.Card;
import com.cards.exception.DeckException;

/**
 * Spring REST class where all the resources and URLs are mapped
 * All the below REST services returns object in JSON format by default
 * 
 * @author Bakkiyalakshmi Ramanjulu
 *
 */

@RestController
@RequestMapping("/deck")
public class DeckResource {

	// REST class uses this business service class to retrieve card and deck details
	private CardService cardService = new CardService();

	/*
	 * Algorithm is configurable, pulled by Spring boot dynamically, the algorithm
	 * is configurable, application team can change the algorithm during deployment
	 * Allow switching the algorithms at deploy-time only via configuration.
	 * 
	 */
	@Value("${algorithm}")
	private String algorithm;	

	/**
	 * This is dummy test method to check whether the service is available or not
	 * @return
	 */
	@RequestMapping("/")
	public String helpPage() {
		StringBuffer sb = new StringBuffer();
		sb.append("Welcome to Card Game !!\n")
		.append("\nYour card shuffling Algorithm is -"+algorithm)
		.append("\n\n\n\n-------------------------------------------------------------------------------------")
		.append("\n http://localhost:8080/deck/new/<deck_name>     - PUT (Idempotent) - Create a new deck")
		.append("\n http://localhost:8080/deck/shuffle/<deck_name> - POST - shuffles a deck")
		.append("\n http://localhost:8080/deck/delete/<deck_name>  - DELETE - deletes a given deck")
		.append("\n http://localhost:8080/deck/getAll/             - GET - retrieves all the decks")
		.append("\n http://localhost:8080/deck/get/<deck_name>     - GET - retrieves a single deck")
		.append("\n\n --------------------------------------------------------------------------------------");
		return sb.toString();
	}

	
	/**
	 * An idempotent REST service for the creation of a new named deck.  
	 * New decks are created in some initial sorted order.
	 * 
	 * @param deckName
	 * @return
	 */
	@RequestMapping(value = "/new/{deckName}", method = RequestMethod.PUT)
	public ResponseEntity<?> createDeck(@PathVariable("deckName") String deckName) {
		if (deckName != null) {
			return new ResponseEntity(cardService.createDeck(deckName), HttpStatus.OK);
		} else {
			return new ResponseEntity(new DeckException("Invalid DeckName"), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * REST POST request to shuffle an existing named deck.
	 * 
	 * @param deckName
	 * @return
	 */
	@RequestMapping(value = "/shuffle/{deckName}", method = RequestMethod.POST)
	public ResponseEntity<?> doShuffling(@PathVariable("deckName") String deckName) {
		if (deckName != null) {
			return new ResponseEntity(cardService.doShuffling(deckName,algorithm), HttpStatus.OK);
		} else {
			return new ResponseEntity(new DeckException("Invalid Request"), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * DELETEs a named deck.
	 * 
	 * @param deckName
	 * @return
	 */
	@RequestMapping(value = "/delete/{deckName}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeck(@PathVariable("deckName") String deckName) {
		if (deckName != null) {
			cardService.removeDeck(deckName);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity(new DeckException("Invalid Request"), HttpStatus.BAD_REQUEST);
		}
        
    }
	
	/**
	 * GETs a list of the current decks persisted in the service
	 * 
	 * @return all the deck list available in memory
	 */
	  @RequestMapping(value = "/getAll/", method = RequestMethod.GET)
	    public ResponseEntity<List<Card>> getAllDecks() {
	        List<List<Card>> cards = cardService.getAllDecks();
	        if (cards != null) {
	            return new ResponseEntity(cards,HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }else {
				return new ResponseEntity(new DeckException("Invalid Request"), HttpStatus.NOT_FOUND);
			}
	    }
	  	  
	  /**
	   * GETs a named deck in its current sorted/shuffled order
	   * 
	   * @param deckName
	   * @return
	   */
	  @RequestMapping(value = "/get/{deckName}", method = RequestMethod.GET)
	    public ResponseEntity<List<Card>> getDeck(@PathVariable("deckName") String deckName) {
	        List<Card> deck = cardService.getDeck(deckName);
	        if (deck != null) {
	            return new ResponseEntity(deck, HttpStatus.OK);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }else {
				return new ResponseEntity(new DeckException("Invalid Request"), HttpStatus.NOT_FOUND);
			}
	    } 

}