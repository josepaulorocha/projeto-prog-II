/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgmagico.personagens;

/**
 *
 * @author Gustavo
 */
public class Inimigo extends Personagem {

    public Inimigo() {
        super(500,100,15,30,5,10, "Inimigo");
    }
    
    

    @Override
    public int ataqueSimples(Personagem p) {
        int dano = Math.max(this.getForca() - p.getDefesa(),0);
        p.recebeDano(dano);
        return dano;
    }
    
}
