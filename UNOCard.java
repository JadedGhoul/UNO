/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unodemo;

public class UNOCard {
    
    //This determins the card's color
    enum Color{
        Red, Blue, Green, Yellow, Wild;
        
        private static final Color[] colors = Color.values();
        public static Color getColor(int i){
            return Color.colors[i];
        }
    }
    //This determins the card's number
     enum Value{
        Zero, One,Two,Three,Four,Five,Six,Seven,Eight,Nine,DrawTwo,Skip,Reverse,Wild,WildFour;
        
        private static final Value[] values = Value.values();
        public static Value getValue(int i){
            return Value.values[i];
        }
    }
     
     private final Color color;
     private final Value value;
     
     public UNOCard(final Color color, final Value value){
         this.color = color;
         this.value = value;
     }
     
     public Color getColor(){
         return this.color;
     }
     
     public Value getValue(){
         return this.value;
     }
     
     public String toString(){
         return color + "_" + value;
     }
}