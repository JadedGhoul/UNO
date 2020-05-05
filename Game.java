/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unodemo;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import javax.swing.ImageIcon;
/**
 *
 * @author Leo
 */
public class Game {
 
    private int currentPlayer;
    private String[] playerNames;
    
    private UNODeck deck;
    private ArrayList<ArrayList<UNOCard>> playerHand;
    private ArrayList<UNOCard> stockpile;
    
    private UNOCard.Color validColor;
    private UNOCard.Value validValue;

    boolean gameDirection;
    
    public Game(String[]pns){
        deck = new UNODeck();
        deck.shuffle();
        stockpile = new ArrayList<UNOCard>();
        
        
        playerNames =pns;
        currentPlayer = 0;
        gameDirection =false;
        
        playerHand = new ArrayList<ArrayList<UNOCard>>();
        
        for(int i =0; i < pns.length; i++){
         ArrayList<UNOCard> hand = new ArrayList<UNOCard>(Arrays.asList(deck.drawCard(7)));   
        playerHand.add(hand);
        }
        
    }
 public void start(Game game){
     UNOCard card = deck.drawCard();
     validColor = card.getColor();
     validValue = card.getValue();
     
     if(card.getValue() == UNOCard.Value.Wild){
         start(game);
     }
     if (card.getValue()== UNOCard.Value.WildFour || card.getValue() == UNOCard.Value.DrawTwo){
         start(game);
     }
     if(card.getValue() == UNOCard.Value.Skip){
         JLabel message = new JLabel(playerNames[currentPlayer] + "was skipped");
         message.setFont(new Font("Arial",Font.BOLD,48));
         JOptionPane.showMessageDialog(null, message);
          
         if(gameDirection == false){
         currentPlayer = (currentPlayer +1)% playerNames.length;
         
     }
         else if(gameDirection == true){
             currentPlayer = (currentPlayer -1)% playerNames.length;
             if(currentPlayer == -1){
                currentPlayer = playerNames.length -1;
            }
        }
     }
     if (card.getValue()== UNOCard.Value.Reverse){
         JLabel message = new JLabel(playerNames[currentPlayer] + " The game direction changed!");
         message.setFont(new Font("Arial",Font.BOLD,48));
         JOptionPane.showMessageDialog(null,message);
         gameDirection ^=true;
         currentPlayer = playerNames.length -1;
     }
     stockpile.add(card);
 }
 
 
 public UNOCard getTopCard(){
     return new UNOCard(validColor, validValue);
 }
 
 public ImageIcon getTopCardImage(){
     return new ImageIcon(validColor + "_" + validValue + ".png");
 }
 
 public boolean isGameOver(){
     for (String player: this.playerNames){
         if (hasEmptyHand(player)){
             return true;
         }
     }
     return false;
 }
 
 public String getCurrentPlayer() {
     return this.playerNames[this.currentPlayer];
 }
 
 public String getPreviousPlayer(int i){
     int index = this.currentPlayer - i;
     if(index == -1){
         index = playerNames.length -1;
     }
     return this.playerNames[index];
 }
 
 public String[] getPlayer(){
     return playerNames;
 }
 
 public ArrayList<UNOCard> getPlayerHand(String pn){
     int index = Arrays.asList(playerNames).indexOf(pn);
     return playerHand.get(index);
 }
 
 public int getPlayerHandSize(String pn){
     return getPlayerHand(pn).size();
 }
 
 public UNOCard getPlayerCard(String pn, int choice){
     ArrayList<UNOCard> hand = getPlayerHand(pn);
     return hand.get(choice); 
 }
    
 public boolean hasEmptyHand(String pn){
     return getPlayerHand(pn).isEmpty();
 }
 
 public boolean validCardPlay(UNOCard card){
     return card.getColor()== validColor || card.getValue()== validValue;
 }
 
 public void checkPlayerTurn(String pn) throws InvalidPlayerTurnException{
     if (this.playerNames[this.currentPlayer] != pn){
         throw new InvalidPlayerTurnException("it is not " + pn + " 's turn", pn);
     }
 }
 
 public void submitDraw(String pn) throws InvalidPlayerTurnException{
     checkPlayerTurn(pn);
     
     if(deck.isEmpty()){
         deck.replaceDeckWith(stockpile);
         deck.shuffle();
     }
     getPlayerHand(pn).add(deck.drawCard());
     if (gameDirection == false){
         currentPlayer = (currentPlayer + 1) % playerNames.length;
     }
     else if(gameDirection == true){
         currentPlayer = (currentPlayer - 1) % playerNames.length;
         if(currentPlayer == -1){
             currentPlayer = playerNames.length -1;
         }
     }
 }
 
 public void setCardColor(UNOCard.Color color){
     validColor = color;
 }
 
    public void  submitPlayerCard(String pn, UNOCard card, UNOCard.Color declaredColor)
    throws InvalidColorSubmissionException, InvalidValueSubmissionException, InvalidPlayerTurnException{
        checkPlayerTurn(pn);
        
        ArrayList<UNOCard> pHand = getPlayerHand(pn);
        
        if(!validCardPlay(card)){
            if(card.getColor() == UNOCard.Color.Wild){
                validColor = card.getColor();
                validValue = card.getValue();
            }
            if (card.getColor() != validColor){
               JLabel message = new JLabel("Invalid player move, expected color: " + validColor + " but got color " + card.getColor()); 
               message.setFont(new Font("Arial", Font.BOLD,48));
               JOptionPane.showMessageDialog(null, message);
               throw new InvalidColorSubmissionException("Invalid player move, expected color: " + validColor + " but got color " + card.getColor(),card.getColor(),validColor);
            }
            else if (card.getValue() != validValue){
                 JLabel message2 = new JLabel("Invalid player move, expected value: " + validValue + " but got color " + card.getValue()); 
               message2.setFont(new Font("Arial", Font.BOLD,48));
               JOptionPane.showMessageDialog(null, message2);
            throw new InvalidValueSubmissionException("Invalid player move, expected value: " + validValue + " but got color " + card.getValue(),card.getValue() ,validValue);
            }
        }
        pHand.remove(card);
        
        if (hasEmptyHand(this.playerNames[currentPlayer])){
            JLabel message2 = new JLabel(this.playerNames[currentPlayer] + " won the game! Thank you for Playing!"); 
               message2.setFont(new Font("Arial", Font.BOLD,48));
               JOptionPane.showMessageDialog(null, message2);
               System.exit(0);
        }
        validColor = card.getColor();
        validValue = card.getValue();
        stockpile.add(card);
        
        if( gameDirection == false) {
            currentPlayer = (currentPlayer + 1) % playerNames.length;
        }
        else if(gameDirection == true){
            currentPlayer = (currentPlayer - 1) % playerNames.length;
            if (currentPlayer == -1){
                currentPlayer = playerNames.length - 1;
            }
        }
        if(card.getColor() == UNOCard.Color.Wild){
            validColor = declaredColor;
        }
        if(card.getValue() == UNOCard.Value.DrawTwo){
            pn = playerNames[currentPlayer];
            getPlayerHand(pn).add(deck.drawCard());
            getPlayerHand(pn).add(deck.drawCard());
            JLabel message = new JLabel(pn + " draw 2 cards!");
        }
        if(card.getValue() == UNOCard.Value.WildFour){
            pn = playerNames[currentPlayer];
            getPlayerHand(pn).add(deck.drawCard());
            getPlayerHand(pn).add(deck.drawCard());
            getPlayerHand(pn).add(deck.drawCard());
            getPlayerHand(pn).add(deck.drawCard());
            JLabel message = new JLabel(pn + " draw 4 cards!");
        }
        if(card.getValue() == UNOCard.Value.Skip){
            JLabel message = new JLabel(playerNames[currentPlayer] + " was skipped!");
            message.setFont(new Font("Arial", Font.BOLD,48));
            JOptionPane.showMessageDialog(null,message);
            if (gameDirection == false){
                currentPlayer = (currentPlayer +1)% playerNames.length;
            }
            else if(gameDirection == true){
                currentPlayer = (currentPlayer - 1) % playerNames.length;
                if (currentPlayer ==-1){
                    currentPlayer = playerNames.length -1;
                }
                    
                }
            }
        if(card.getValue()==UNOCard.Value.Reverse){
            JLabel message = new JLabel(pn + " game direction has been reveresed!");
            message.setFont(new Font("Arial", Font.BOLD,48));
            JOptionPane.showMessageDialog(null,message);
           
            gameDirection ^= true;
            if(gameDirection == true){
                currentPlayer = (currentPlayer -2)%playerNames.length;
                if(currentPlayer == -1){
                    currentPlayer = playerNames.length -1;
                }
                if(currentPlayer == -2){
                    currentPlayer = playerNames.length -2;
                }
            }
            else if (gameDirection == false){
                currentPlayer = (currentPlayer + 2)% playerNames.length;
            }
        }
    }
}



class InvalidPlayerTurnException extends Exception{
    String playerName;
    
    public InvalidPlayerTurnException(String message, String pn) {
        super(message);
        playerName = pn;
    }
    public String getPn() {
        return playerName;
    }
}

class InvalidColorSubmissionException extends Exception {
    private final UNOCard.Color expected;
    private UNOCard.Color actual;
    
    public InvalidColorSubmissionException(String message, UNOCard.Color actual, UNOCard.Color expected) {
        this.actual = actual;
        this.expected = expected;
        
    }
} 

class InvalidValueSubmissionException extends Exception{
    private UNOCard.Value expected;
    private UNOCard.Value actual;
    
    public InvalidValueSubmissionException(String message, UNOCard.Value actual, UNOCard.Value expected){
        this.actual = actual;
        this.expected = expected;
    }
}