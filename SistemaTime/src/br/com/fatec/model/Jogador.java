package br.com.fatec.model;

import static java.lang.Math.random;
import java.util.Date;
import java.util.Random;

public class Jogador {

    private int id;
    private String nome;
    private String apelido;
    private String dataNascimento;
    private int numero;
    private String posicao;
    private int qualidade;
    private int cartoes = 0;
    private boolean suspenso = false;
    private Random random;
    private boolean treinou = false;
    private boolean lesionado = false;
   

    public Jogador() {
    }

    public Jogador(int id, String nome, String apelido, String dataNascimento, int numero, String posicao) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = dataNascimento;
        this.numero = numero;
        this.posicao = posicao;
        this.random = new Random();
        this.qualidade = random.nextInt(51) + 50;

    }

    public boolean verificarCondicaoDeJogo() {
        if (suspenso) {
            return false;
        }else if(qualidade <40){
            return false;
        }else if(lesionado == true){
            return false;
        }else {
            return true;
        }
    }
    
    public void aplicarCartao(int quantidade){
        this.cartoes += quantidade;
    }
    public void cumprirSuspensao(){
        if(cartoes == 3){
            this.suspenso = true;
        cartoes = 0;
        } 
    }
    
    public void sofrerLesao(){
                  int chance = random.nextInt(100) + 1;
                  int reducao = 0;
                  
                  if(chance <=5){
                      reducao = (int) (this.qualidade *0.15);
                  }else if(chance <=10){
                      reducao = (int) (this.qualidade * 0.10);
                  }if(chance <=15){
                      reducao = (int) (this.qualidade *0.5);
                  }else if(chance <=30){
                      reducao = 2;
                  }else if(chance <=40){
                      reducao = 1;
                  }
         
         reducao = Math.max(1, reducao);
         
         this.qualidade -= reducao;
         
         this.qualidade = Math.max(0, this.qualidade);
         
         this.lesionado = true;
         
         System.out.println(this.nome + " sofreu uma lesão!\n redução na qualidade "
         + "do jogador de " + reducao + "\n Qualidade atual de: " + this.qualidade);
    }
    
    public void executarTreinamento(){
        if(treinou){
            System.out.println("Você precisa esperar a partida para poder treinar novamente!");
        }else{
            int chance = random.nextInt(100) + 1;
                  int acrescimo = 0;
                  
                  if(chance <=5){
                      acrescimo = 5;
                  }else if(chance <=10){
                      acrescimo = 4;
                  }if(chance <=15){
                      acrescimo = 3;
                  }else if(chance <=30){
                      acrescimo = 2;
                  }else if(chance <=40){
                      acrescimo = 1;
                  }
         
         acrescimo = Math.max(1, acrescimo);
         
         this.qualidade += acrescimo;
         
         this.qualidade = Math.min(100, this.qualidade);
         
         this.treinou = true;
         
         //System.out.println("O jogador " + this.nome + " realizou um treino! Foi acrescentado na qualidade "
            //+ acrescimo + " pontos.\n Qualidade atual de: " + this.qualidade);
    }
    }
    
    public void voltouDalesao(){
        this.lesionado = false;
    }

    public int getQualidade() {
        return qualidade;
    }

    public String getNome() {
        return nome;
    }
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	@Override
	public String toString() {
		return "Jogador [id=" + id + ", nome=" + nome + ", apelido=" + apelido + ", dataNascimento=" + dataNascimento
				+ ", numero=" + numero + ", posicao=" + posicao + ", qualidade=" + qualidade + ", cartoes=" + cartoes
				+ ", suspenso=" + suspenso +"]";
	}

    
}
