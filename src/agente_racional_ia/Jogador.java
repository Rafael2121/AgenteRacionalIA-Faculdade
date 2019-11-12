/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente_racional_ia;

import java.util.ArrayList;

/**
 *
 * @author Milanez
 */
public class Jogador {
    int x, y;
    
    public Jogador(int posicao_x, int posicao_y){
        this.x = posicao_x;
        this.y = posicao_y;
    }
    
    public Posicao proximoMovimento(ArrayList<Posicao> possibilidades){
        Posicao maiorPosicao = null;
        double peso, maiorPeso = -1000;
        for(Posicao p : possibilidades){
            peso = this.funcaoAtivacao(p);
            if (peso > maiorPeso){
                maiorPeso = peso;
                maiorPosicao = p;
            }
        }
        return maiorPosicao;
    }
    
    public double funcaoAtivacao(Posicao p){
        // 0 ........................... 1
        double peso = 0;
        if (p.objetivo){
            peso = 1.0;
        } else if (p.vala || p.jogador){
            peso = -1.0;
        } else {
            peso += p.qtdVisitado * -0.1 + p.qtdVisto* - 0.01;
            if (p.alerta){
                peso -= 0.02;
            }
        }
        return peso;
    }
    
    public void setPosicao(int x, int y){
        this.x = x;
        this.y = y;
    }
    
}
