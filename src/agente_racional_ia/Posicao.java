/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente_racional_ia;

/**
 *
 * @author Cedeplar
 */
public class Posicao {
    int identificador, x, y, qtdVisitado, qtdVisto;
    boolean alerta, vala, objetivo, jogador;

    public Posicao(int identificador, int x, int y, boolean temAlerta, 
            boolean temVala, boolean eObjetivo, boolean temBoneco) {
        this.identificador = identificador;
        this.x = x;
        this.y = y;
        this.objetivo = eObjetivo;
        this.alerta = temAlerta;
        this.vala = temVala;
        this.jogador = temBoneco;
        this.qtdVisitado = 0;
    }
    
    public void incrementaVisto(){
        this.qtdVisto ++;
    }
    
    public void incrementaVisitado(){
        this.qtdVisitado++;
    }
    
    public String getConteudoPosicaoTudo(){
        String aux = " ";
        if (this.objetivo){
            aux = " >< ";
        } else if(this.vala){
            aux = " [] ";
        } else if(this.jogador){
            aux = " BB ";
        } else if(this.alerta){
            aux = " aa ";
        } else {
            aux = " " + this.qtdVisitado + "  ";
        }
        return aux;
    }
    
    public String getConteudoPosicaoLimitado(){
        String aux = " ";
        if (this.objetivo && this.qtdVisto > 0){
            aux = " >< ";
        } else if(this.vala && this.qtdVisto > 0){
            aux = " [] ";
        } else if(this.jogador){
            aux = " BB ";
        } else if(this.alerta && this.qtdVisto > 0){
            aux = " aa ";
        } else if (this.qtdVisto > 0){
            aux = " " + this.qtdVisitado + "  ";
        } else {
            aux = " __ ";
        }
        return aux;
    }
    
    
    
    
    
    
    
}
