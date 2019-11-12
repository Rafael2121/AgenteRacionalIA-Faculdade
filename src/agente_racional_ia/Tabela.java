/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agente_racional_ia;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cedeplar
 */
public class Tabela {
    
    int largura, altura, comprimento, totalMovimentos;
    int qtdValas;
    Posicao[][] tabela;
    Jogador jogador;
    
    public Tabela(int largura, int altura, int qtdValas){
        this.largura = largura;
        this.altura = altura;
        this.qtdValas = qtdValas;
        this.tabela = new Posicao[largura][altura];
        this.totalMovimentos = 0;
        this.preparaAmbiente();
        this.run();
    }
    
    public ArrayList<Posicao> olharEnvolta(){
        ArrayList<Posicao> posicoesObservadas = new ArrayList<Posicao>();
        for (int i = 0; i < this.largura; i++){
            for (int j = 0; j < this.altura; j++){
                if ((Math.abs(i - this.jogador.x) <= 1) && (Math.abs(j - this.jogador.y) <= 1)){
                    tabela[i][j].incrementaVisto();
                    posicoesObservadas.add(tabela[i][j]);
                }
            }
        }
        return posicoesObservadas;
    }
    
    public void run(){
        boolean stop = false;
        Posicao proximaPosicao;
        do {
            proximaPosicao = this.jogador.proximoMovimento(this.olharEnvolta());
            this.imprimeCampo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tabela.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.movimentaJogador(proximaPosicao);
            stop = proximaPosicao.objetivo;
        } while (!stop);
        System.out.println("O jogador chegou ao destino depois de " + this.totalMovimentos + " movimentos");
        
    }
    
    public void movimentaJogador(Posicao proximaPosicao){
        this.tabela[this.jogador.x][this.jogador.y].incrementaVisitado();
        this.tabela[this.jogador.x][this.jogador.y].jogador = false;
        
        this.totalMovimentos++;
        
        this.jogador.setPosicao(proximaPosicao.x,  proximaPosicao.y);
        this.tabela[proximaPosicao.x][proximaPosicao.y].jogador = true;
    }
    
    public void imprimeCampo(){
        for (int i = 0; i < this.altura; i++){
            System.out.print("|");
            for (int j = 0; j < this.largura; j++){
                System.out.print(" " + this.tabela[i][j].getConteudoPosicaoLimitado() + " ");
            }
            System.out.println("|");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    
     public void preparaAmbiente(){
        Random aleatorio = new Random();
        ArrayList<Integer> posBuracos = new ArrayList<Integer>();
        int posObjetivo, posJogador,aux = 0;
        this.comprimento = this.altura * this.largura;
        
        // Obtem os valores das posições sorteadas
        posObjetivo = aleatorio.nextInt(this.comprimento);
        do{
            posJogador = aleatorio.nextInt(this.comprimento);
        } while (posObjetivo == posJogador);
        for (int i = 0; i < this.qtdValas; i++){
            do{
                aux = aleatorio.nextInt(this.comprimento);
            } while (aux == posJogador || aux == posObjetivo || posBuracos.contains(aux));
            posBuracos.add(aux);
        }
        
        // Cria todas as posições da tabela
        int count = 0;
        Posicao p;
        boolean temVala, temObjetivo, temJogador;
        for (int i = 0; i < this.altura; i++){
            for (int j = 0; j < this.largura; j++){
                temVala = temObjetivo = temJogador = false;
                if (count == posObjetivo){
                    temObjetivo = true;
                } else if (posBuracos.contains(count)){
                    temVala = true;
                } else if (count == posJogador){
                    temJogador = true;
                    this.jogador = new Jogador(i, j);
                }
                p = new Posicao(count, i,j, false, temVala, temObjetivo, temJogador);
                this.tabela[i][j] = p;
                count ++;
            }
        }
        for (int i = 0; i < this.altura; i++){
            for (int j = 0; j < this.largura; j++){
                if (this.tabela[i][j].vala){
                    this.insereAlertas(this.tabela[i][j]);
                }
            }
        }
    }
     
     public void insereAlertas(Posicao posicaoComVala){
        for (int i = 0; i < this.largura; i++){
            for (int j = 0; j < this.altura; j++){
                if ((Math.abs(i - posicaoComVala.x) <= 1) && (Math.abs(j - posicaoComVala.y) <= 1)){
                    tabela[i][j].alerta = true;
                }
            }
        }
    }
    
}





