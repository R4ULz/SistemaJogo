package br.com.fatec.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Time {
    private String nome;
    private String apelido;
    private String fundacao;
    private ArrayList<Jogador> plantel;
    private ArrayList<Jogador> titulares;
    private ArrayList<Jogador> reservas;

    public Time() {
        this.plantel = new ArrayList<>();
        this.titulares = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }


    public Time(String nome, String apelido, String fundacao) {
        this.nome = nome;
        this.apelido = apelido;
        this.fundacao = fundacao;
        this.plantel = new ArrayList<>();
        this.titulares = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public void adicionarJogador(Jogador jogador) {
        this.plantel.add(jogador);
    }


    public void relacionarJogadores() {
        Collections.sort(this.plantel, new Comparator<Jogador>() {
            public int compare(Jogador j1, Jogador j2) {
                return j2.getQualidade() - j1.getQualidade();
            }
        });
        this.titulares.clear();
        this.reservas.clear();
        for (int i = 0; i < 11 && i < this.plantel.size(); i++) {
            this.titulares.add(this.plantel.get(i));
        }
        for (int i = 11; i < 18 && i < this.plantel.size(); i++) {
            this.reservas.add(this.plantel.get(i));
        }
    }
    
    public ArrayList<Jogador> getPlantel() {
		return plantel;
	}

	public ArrayList<Jogador> getTitulares() {
		return titulares;
	}


	public ArrayList<Jogador> getReservas() {
		return reservas;
	}
     
    
}
