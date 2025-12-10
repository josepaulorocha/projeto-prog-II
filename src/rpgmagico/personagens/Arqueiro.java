/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgmagico.personagens;

/**
 *
 * @author Gustavo
 */
public class Arqueiro extends Personagem {

    public Arqueiro(String nome) {
        super(500, 100, 15, 5, 10, 30, nome);
    }

    @Override
    public int ataqueSimples(Personagem p) {
        int dano = Math.max(this.getDestreza()- p.getDefesa(),0);
        p.recebeDano(dano);
        return dano;
    }
    
}
