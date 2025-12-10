/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpgmagico;

import java.util.logging.Level;
import java.util.logging.Logger;
import rpgmagico.personagens.Arqueiro;
import rpgmagico.personagens.Inimigo;
import rpgmagico.personagens.Mago;

/**
 *
 * @author Gustavo
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Mago m1 = new Mago("Roberto");
        Arqueiro arq1 = new Arqueiro("Paulo");
        Inimigo i = new Inimigo();
        
        
        while(m1.getHP()>0 && arq1.getHP()>0 && i.getHP()>0){
            System.out.println(arq1.getNome()+" atacou "+m1.getNome()+" com "+arq1.ataqueSimples(m1)+" de dano.");
            System.out.println(m1.getNome()+" tem "+m1.getHP()+" de vida.");
            System.out.println(m1.getNome()+" atacou "+i.getNome()+" com "+m1.ataqueSimples(i)+" de dano.");
            System.out.println(i.getNome()+" tem "+i.getHP()+" de vida.");
            System.out.println(i.getNome()+" atacou "+arq1.getNome()+" com "+i.ataqueSimples(arq1)+" de dano.");
            System.out.println(arq1.getNome()+" tem "+arq1.getHP()+" de vida.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("A batalha terminou");
        System.out.println(m1.getNome()+" ficou com "+m1.getHP()+" de vida.");
        System.out.println(arq1.getNome()+" ficou com "+arq1.getHP()+" de vida.");
        System.out.println(i.getNome()+" ficou com "+i.getHP()+" de vida.");
    }
    
}
