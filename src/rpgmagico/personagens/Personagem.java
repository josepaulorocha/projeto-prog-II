/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rpgmagico.personagens;

/**
 *
 * @author Gustavo
 */
public abstract class Personagem {
    
    private int HPmax;
    private int HP;
    private int SPmax;
    private int SP;
    
    private int defesa;
    private int forca;
    private int inteligencia;
    private int destreza;
    
    private String nome;

    public Personagem(int HPmax, int SPmax, int defesa, int forca, int inteligencia, int destreza, String nome) {
        this.HPmax = HPmax;
        HP = HPmax;
        this.SPmax = SPmax;
        SP = SPmax;
        
        this.defesa = defesa;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.destreza = destreza;
        this.nome = nome;
    }

    
    
    
    

    public int getHPmax() {
        return HPmax;
    }

    public int getHP() {
        return HP;
    }

    public int getSPmax() {
        return SPmax;
    }

    public int getSP() {
        return SP;
    }

    public int getDefesa() {
        return defesa;
    }

    public int getForca() {
        return forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public String getNome() {
        return nome;
    }
    
    public void recebeDano(int dano){
        this.HP-=dano;
        this.HP=Math.max(this.HP,0);
    }
    
    
    public abstract int ataqueSimples(Personagem p);
    
    
}
