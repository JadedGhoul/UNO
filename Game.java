/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

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
    private String[] playerIds;
    
    private UNODeck deck;
    private ArrayList<ArrayList<UNOCard>> playerHand;
    private ArrayList<UNOCard> stockpile;
    
    private UNOCard.Color validColor;
    private UNOCard.Value validValue;

    boolean gameDirection;
    
    public Game(String[]pids){
        deck = new UNODeck();
        deck.shuffle();
        stockpile = new ArrayList<UNOCard>();
        
        
        playerIds =pids;
        currentPlayer = 0;
        gameDirection =false;
        
        playerHand = new ArrayList<ArrayList<UNOCard>>();
        
        for(int i =0; i < pids.length; i++){
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
         JLabel message = new JLabel(playerIds[currentPlayer] + "was skipped");
         message.setFont(new Font("Arial",Font.BOLD,48));
         JOptionPane.showMessageDialog(null, message);
          
         if(gameDirection == false){
         currentPlayer = (currentPlayer +1)% playerIds.length;
         
     }
         else if(gameDirection == true){
             currentPlayer = (currentPlayer -1)% playerIds.length;
             if(currentPlayer == -1){
                currentPlayer = playerIds.length -1;
            }
        }
     }
     if (card.getValue()== UNOCard.Value.Reverse){
         JLabel message = new JLabel(playerIds[currentPlayer] + " The game direction changed!");
         message.setFont(new Font("Arial",Font.BOLD,48));
         JOptionPane.showMessageDialog(null,message);
         gameDirection ^=true;
         currentPlayer = playerIds.length -1;
     }
     stockpile.add(card);
 }
 
 
 public UNOCard getTopCard(){
     return new UNOCard(validColor, validValue);
 }
 
 public ImageIcon getTopCardImage(){
     return new ImageIcon(validColor + "-" + validValue + ".png");
 }
 
 public boolean isGameOver(){
     for (String player: this.playerIds){
         if (hasEmptyHand(player)){
             return true;
         }
     }
     return false;
 }
 
 public String getCurrentPlayer() {
     return this.playerIds[this.currentPlayer];
 }
 
 public String getPreviousPlayer(int i){
     int index = this.currentPlayer - i;
     if(index == -1){
         index = playerIds.length -1;
     }
     return this.playerIds[index];
 }
 
 public String[] getPlayer(){
     return playerIds;
 }
 
 public ArrayList<UNOCard> getPlayerHand(String pid){
     int index = Arrays.asList(playerIds).indexOf(pid);
     return playerHand.get(index);
 }
 
 public int getPlayerHandSize(String pid){
     return getPlayerHand(pid).size();
 }
 
 public UNOCard getPlayerCard(String pid, int choice){
     ArrayList<UNOCard> hand = getPlayerHand(pid);
     return hand.get(choice); 
 }
    
 public boolean hasEmptyHand(String pid){
     return getPlayerHand(pid).isEmpty();
 }
 
 public boolean validCardPlay(UNOCard card){
     return card.getColor()== validColor || card.getValue()== validValue;
 }
 
 public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException{
     if (this.playerIds[this.currentPlayer] != pid){
         throw new InvalidPlayerTurnException("it is not " + pid + " 's turn", pid);
     }
 }
 
 public void submitDraw(String pid) throws InvalidPlayerTurnException{
     checkPlayerTurn(pid);
     
     if(deck.isEmpty()){
         deck.replaceDeckWith(stockpile);
         deck.shuffle();
     }
     getPlayerHand(pid).add(deck.drawCard());
     if (gameDirection == false){
         currentPlayer = (currentPlayer + 1) % playerIds.length;
     }
     else if(gameDirection == true){
         currentPlayer = (currentPlayer - 1) % playerIds.length;
         if(currentPlayer == -1){
             currentPlayer = playerIds.length -1;
         }
     }
 }
 
 public void setCardColor(UNOCard.Color color){
     validColor = color;
 }
 
    public void  submitPlayerCard(String pid, UNOCard card, UNOCard.Color declaredColor)
    throws InvalidColorSubmissionException, InvalidValueSubmissionException, InvalidPlayerTurnException{
        checkPlayerTurn(pid);
        
        ArrayList<UNOCard> pHand = getPlayerHand(pid);
        
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
        
        if (hasEmptyHand(this.playerIds[currentPlayer])){
            JLabel message2 = new JLabel(this.playerIds[currentPlayer] + " won the game! Thank you for Playing!"); 
               message2.setFont(new Font("Arial", Font.BOLD,48));
               JOptionPane.showMessageDialog(null, message2);
               System.exit(0);
        }
        validColor = card.getColor();
        validValue = card.getValue();
        stockpile.add(card);
        
        if( gameDirection == false) {
            currentPlayer = (currentPlayer + 1) % playerIds.length;
        }
        else if(gameDirection == true){
            currentPlayer = (currentPlayer - 1) % playerIds.length;
            if (currentPlayer == -1){
                currentPlayer = playerIds.length - 1;
            }
        }
        if(card.getColor() == UNOCard.Color.Wild){
            validColor = declaredColor;
        }
        if(card.getValue() == UNOCard.Value.DrawTwo){
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " draw 2 cards!");
        }
        if(card.getValue() == UNOCard.Value.WildFour){
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " draw 4 cards!");
        }
        if(card.getValue() == UNOCard.Value.Skip){
            JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped!");
            message.setFont(new Font("Arial", Font.BOLD,48));
            JOptionPane.showMessageDialog(null,message);
            if (gameDirection == false){
                currentPlayer = (currentPlayer +1)% playerIds.length;
            }
            else if(gameDirection == true){
                currentPlayer = (currentPlayer - 1) % playerIds.length;
                if (currentPlayer ==-1){
                    currentPlayer = playerIds.length -1;
                }
                    
                }
            }
        if(card.getValue()==UNOCard.Value.Reverse){
            JLabel message = new JLabel(pid + " game direction has been reveresed!");
            message.setFont(new Font("Arial", Font.BOLD,48));
            JOptionPane.showMessageDialog(null,message);
           
            gameDirection ^= true;
            if(gameDirection == true){
                currentPlayer = (currentPlayer -2)%playerIds.length;
                if(currentPlayer == -1){
                    currentPlayer = playerIds.length -1;
                }
                if(currentPlayer == -2){
                    currentPlayer = playerIds.length -2;
                }
            }
            else if (gameDirection == false){
                currentPlayer = (currentPlayer + 2)% playerIds.length;
            }
        }
    }
}



class InvalidPlayerTurnException extends Exception{
    String playerId;
    
    public InvalidPlayerTurnException(String message, String pid) {
        super(message);
        playerId = pid;
    }
    public String getPid() {
        return playerId;
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