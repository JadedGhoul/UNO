/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uno;

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
}
