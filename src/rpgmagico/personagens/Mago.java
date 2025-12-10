/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgmagico.personagens;

/**
 *
 * @author Gustavo
 */
public class Mago extends Personagem{

    public Mago(String nome) {
        super(300,300,10,3,30,15, nome);
    }
    
    

    @Override
    public int ataqueSimples(Personagem p) {
        int dano = Math.max(this.getInteligencia() - p.getDefesa(),0);
        p.recebeDano(dano);
        return dano;
    }
    
}
