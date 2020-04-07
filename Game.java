/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import javax.swing.JLabel;

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
        stockPile = new ArrayList<UNOCard>();
        
        
        playerIds =pids;
        currentPlayer =0;
        gameDirection =false;
        
        playerHand = new ArrayList<ArrayList<UNOCard>>();
        
        for(int i =0; i <pids.length; i++){
         ArrayList<UNOCard> hand = new ArrayList(UNOCard>(Arrats.asList(deck.drawCard(7)));   
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
     if (card.getValue()== UNOCard.Value.Wild_Four || card.getValue() == UNOCard.Value.DrawTwo){
         
     }
     if(card.getValue() == UNOCard.Value.Skip){
         JLabel message = new Jlabel(playerIds[currentPlayer] + "was skipped");
         message.setFont(new Font("Arial",Font.BOLD,48));
          
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
         JLabel message = new Jlabel(playerIds[currentPlayer] + " The game direction changed!");
         message.setFont(new Font("Arial",Font.BOLD,48));
         JOptionPane.showMessageDialog(null,message);
         gameDirection ^=true;
         currentPlayer = playerIds.length -1;
     }
     stockPile.add(card);
 }
    
    public void  submitPlayerCard(String pid, UNOCard card, UNOCard.Color declaredColor)
            throws InvalidColorSubmissionException, InvalidValueSubmissionException, InvalidPlayerTurnException{
        checkPlayerTurn(pid);
        
        ArrayList(UNOCard) pHand = getPlayerHand(pid);
        
        if(!validCardPlay(card)){
            if(card.getColor() == UNOCard.Color.Wild){
                validColor = card.getColor();
                validValue = card.getValue();
            }
            if (card.getColor() != validColor){
               JLabel message = new JLabel("Invalid player move, expected color: " + validColor + " but got color " + card.getColor()); 
               message.setFont(new Font("Arial", Font.BOLD,48));
               JOptionPane.showMessageDialog(null, message);
               throw new InvalidColorSubmissionException(message, actual, expected);
            }
            else if (card.getValue != validValue){
                 JLabel message2 = new JLabel("Invalid player move, expected value: " + validValue + " but got color " + card.getValue()); 
               message2.setFont(new Font("Arial", Font.BOLD,48));
               JOptionPane.showMessageDialog(null, message2);
            throw new InvalidValueSubmissionException(message2, actual,expected);
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
        if(card.getValue() == UnoCard.Value.DrawTwo){
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " draw 2 cards!")
        }
        if(card.getValue() == UnoCard.Value.Wild_Four){
            pid = playerIds[currentPlayer];
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            getPlayerHand(pid).add(deck.drawCard());
            JLabel message = new JLabel(pid + " draw 4 cards!")
        }
        if(card.getValue() == UNOCard.Value.Skip){
            JLabel message = new JLabel(playerIds[currentPlayer] + " was skipped!");
            message.setFont(new Font("Arial", Font.BOLD,48));
            JOptionPane.showMessageDailog(null,message);
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
            JOptionPane.showMessageDailog(null,message);
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
    private UNOCard.Color expected;
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
        this.expected=expected;
    }
}