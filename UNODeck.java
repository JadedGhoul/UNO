/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class UNODeck {
    
    private UNOCard[] cards;
    private int cardsInDeck;
    
    public UNODeck(){
        cards = new UNOCard[108];
    }
    
    public void reset(){
        UNOCard.Color[] colors=UNOCard.Color.values();
        cardsInDeck =0;
        for(int i =0;i<colors.length-1;i++){
            UNOCard.Color color = colors[i];
            cards[cardsInDeck++] = new UNOCard(color, UNOCard.Value.getValue(0));
            
            for(int k = 1; k<10;k++){
                cards[cardsInDeck++] = new UNOCard(color, UNOCard.Value.getValue(k));
                cards[cardsInDeck++] = new UNOCard(color, UNOCard.Value.getValue(k));
            }
            UNOCard.Value[] values = new UNOCard.Value[]{UNOCard.Value.DrawTwo,UNOCard.Value.Skip,UNOCard.Value.Reverse};
            for(UNOCard.Value value: values){
                cards[cardsInDeck++] = new UNOCard(color,value);
                cards[cardsInDeck++] = new UNOCard(color,value);
            }
            
        }
         UNOCard.Value[] values = new UNOCard.Value[]{UNOCard.Value.Wild,UNOCard.Value.WildFour};
            for(UNOCard.Value value: values){
               for(int j =0; j<4;j++){
                   cards[cardsInDeck++] = new UNOCard(UNOCard.Color.Wild,value);
               }
            }
    }
    
    //    public void replaceDeckWith(ArrayList<UnoCard> cards){
//        this.cards= cards.toArray new UnoCard(cards.si)
//    
//    }
    public void replaceDeckWith (ArrayList<UNOCard>cards){
    this.cards= cards.toArray(new UNOCard[cards.size()]);
    this.cardsInDeck= this.cards.length;
        
    }
    public boolean isEmpty(){
    return cardsInDeck == 0;
    }
    
    public void shuffle(){
    int n = cards.length;
    Random random= new Random();
    
    for (int i = 0; i< cards.length; i++){
    int randomValue= i+ random.nextInt(n-i);
    UNO randomCard= cards[randomValue];
    cards[randomValue]= cards[i];
    cards[i]= randomCard;
    }
    
    }
     public UNOCard drawCard() throws IllegalArgumentException{
     if(isEmpty()){
     throw new IllegalArgumentException("Cannot draw a card since there are no cards in the deck");
     
     }
     return cards[--cardsInDeck];
     } 
    
     public ImageIcon drawCardImage()throws IllegalArgumentException{
     if(isEmpty()){
     throw new IllegalArgumentException("Cannot draw a card since the deck is empty");
     
     }
     return new ImageIcon(cards[--cardsInDeck].toString()+ ".png");
     
     }
     
     public UNOCard [] drawCard (int n){
     if(n<0){
     throw new IllegalArgumentException("Must draw positive cards but tried to draw" + n + "cards");
     }
     if (n > cardsInDeck){
     throw new IllegalArgumentException("Cannot draw " + n + " cards since there are only " + cardsInDeck + " cards.");
    
     }
     UNOCard [] ret= UNOCard [n];
     
     for(int i= 0; i< n; i++){
     ret[i] = cards[--cardsInDeck];
     }
     return ret;
     }
     
}
    
