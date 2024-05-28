package br.com.fatec.model;

import java.util.ArrayList;
import java.util.Random;

public class Jogo {
    private Time mandante;
    private Time visitante;
    private String dataDoJogo;
    private String estadio;
    private String cidade;
    private Integer placarMandante;
    private Integer placarVisitante;
    private Random random;

    public Jogo() {
        this.random = new Random();
    }

    public Jogo(Time mandante, Time visitante, String dataDoJogo, String estadio, String cidade) {
        this.mandante = mandante;
        this.visitante = visitante;
        this.dataDoJogo = dataDoJogo;
        this.estadio = estadio;
        this.cidade = cidade;
        this.random = new Random();
        this.placarMandante = 0;
        this.placarVisitante = 0;
    }

    public void gerarResultado() {
        int qualidadeTotalMandante = mandante.getTitulares().stream().mapToInt(Jogador::getQualidade).sum();
        int qualidadeTotalVisitante = visitante.getTitulares().stream().mapToInt(Jogador::getQualidade).sum();


        double chanceMandante = qualidadeTotalMandante / (double) (qualidadeTotalMandante + qualidadeTotalVisitante);
        double chanceVisitante = qualidadeTotalVisitante / (double) (qualidadeTotalMandante + qualidadeTotalVisitante);

        double resultado = random.nextDouble();
        if (resultado < chanceMandante * 0.5) {
            placarMandante++;
        } else if (resultado < (chanceMandante * 0.5 + chanceVisitante * 0.3)) {
            placarVisitante++;
        }
    }

    public void gerarLesoes() {
        int lesaoCount = random.nextInt(3);
        for (int i = 0; i < lesaoCount; i++) {
            Time timeEscolhido = random.nextBoolean() ? mandante : visitante;
            ArrayList<Jogador> jogadores = timeEscolhido.getTitulares();
            if (!jogadores.isEmpty()) {
                int index = random.nextInt(jogadores.size());
                jogadores.get(index).sofrerLesao();
            }
        }
    }

    public void gerarCartoes() {
        int cartaoCount = random.nextInt(11);
        for (int i = 0; i < cartaoCount; i++) {
            Time timeEscolhido = random.nextBoolean() ? mandante : visitante;
            ArrayList<Jogador> jogadores = timeEscolhido.getTitulares();
            if (!jogadores.isEmpty()) {
                int index = random.nextInt(jogadores.size());
                jogadores.get(index).aplicarCartao(1);
            }
        }
    }

    public void permitirTreinamento() {
        mandante.getTitulares().forEach(j -> j.executarTreinamento());
        visitante.getTitulares().forEach(j -> j.executarTreinamento());
    }

	public Integer getPlacarMandante() {
		return placarMandante;
	}

	public void setPlacarMandante(Integer placarMandante) {
		this.placarMandante = placarMandante;
	}

	public Integer getPlacarVisitante() {
		return placarVisitante;
	}

	public void setPlacarVisitante(Integer placarVisitante) {
		this.placarVisitante = placarVisitante;
	}
    
    
}

