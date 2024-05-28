package br.com.fatec.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	private static Time timeMandante, timeVisitante;
	private static Random random = new Random();

	public static void main(String[] args) {
		boolean sair = false;
		while (!sair) {
			System.out.println("Menu de Opções:");
			System.out.println("1. Cadastrar time Mandante");
			System.out.println("2. Cadastrar time Visitante");
			System.out.println("3. Imprimir lista de jogadores cadastrados");
			System.out.println("4. Testar sofrer lesão");
			System.out.println("5. Executar treinamento");
			System.out.println("6. Relacionar jogadores");
			System.out.println("7. Ler times do arquivo e gerar resultado");
			System.out.println("8. Sair");
			System.out.print("Escolha uma opção: ");

			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Cadastrando Time Mandante:");
				timeMandante = cadastrarTime();
				break;
			case 2:
				System.out.println("Cadastrando Time Visitante:");
				timeVisitante = cadastrarTime();
				break;
			case 3:
				imprimirJogadores();
				break;
			case 4:
				testarSofrerLesao();
				break;
			case 5:
				executarTreinamento();
				break;
			case 6:
				relacionarJogadores();
				break;
			case 7:
				lerTimesEGerarResultado();
				break;
			case 8:
				sair = true;
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
			}
		}
		scanner.close();
	}

	private static Time cadastrarTime() {
		Time time = new Time();
		System.out.println("Cadastrar via arquivo ou manualmente? (arquivo/manual)");
		String modo = scanner.nextLine().trim().toLowerCase();
		if ("arquivo".equals(modo)) {
			System.out.println("Digite o caminho completo do arquivo:");
			String arquivoPath = scanner.nextLine().trim();
			lerTimeDeArquivo(time, arquivoPath);
		} else {
			cadastrarTimeManualmente(time);
		}
		return time;
	}

	private static void lerTimeDeArquivo(Time time, String arquivoPath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(arquivoPath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] dados = line.split(",");
				Jogador jogador = new Jogador(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3],
						Integer.parseInt(dados[4]), dados[5]);
				time.adicionarJogador(jogador);
			}
			System.out.println("Time cadastrado do arquivo: " + arquivoPath);
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
	}

	private static void cadastrarTimeManualmente(Time time) {
		System.out.println("Quantos jogadores deseja adicionar ao time?");
		int count = scanner.nextInt();
		scanner.nextLine();

		for (int i = 0; i < count; i++) {
			System.out.println(
					"Digite os dados do jogador #" + (i + 1) + " Exemplo (1,Lucas Moura,Lulu,00/00/0000,10,atacante):");
			String dadosJogador = scanner.nextLine();
			String[] dados = dadosJogador.split(",");
			Jogador jogador = new Jogador(Integer.parseInt(dados[0]), dados[1], dados[2], dados[3],
					Integer.parseInt(dados[4]), dados[5]);
			time.adicionarJogador(jogador);
		}
	}

	private static void imprimirJogadores() {
		if (timeMandante == null && timeVisitante == null) {
			System.out.println("Nenhum time foi cadastrado ainda.");
			return;
		}

		System.out.println("De qual time deseja ver os jogadores? (1 para Time 1, 2 para Time 2, 3 para ambos)");
		int escolha = scanner.nextInt();
		scanner.nextLine();

		switch (escolha) {
		case 1:
			if (timeMandante != null) {
				System.out.println("Jogadores do Time Mandante:");
				imprimirDadosDoTime(timeMandante);
			} else {
				System.out.println("Time Mandante não está cadastrado.");
			}
			break;
		case 2:
			if (timeVisitante != null) {
				System.out.println("Jogadores do Time Visitante:");
				imprimirDadosDoTime(timeVisitante);
			} else {
				System.out.println("Time Visitante não está cadastrado.");
			}
			break;
		case 3:
			System.out.println("Jogadores do Time Mandante:");
			imprimirDadosDoTime(timeMandante);
			System.out.println("Jogadores do Time Visistante:");
			imprimirDadosDoTime(timeVisitante);
			break;
		default:
			System.out.println("Opção inválida.");
		}
	}

	private static void imprimirDadosDoTime(Time time) {
		if (time == null) {
			System.out.println("Este time ainda não foi cadastrado.");
			return;
		}
		for (Jogador jogador : time.getPlantel()) {
			System.out.println(jogador.toString()); // Supondo que Jogador tem um método toString formatado
		}
	}

	private static void testarSofrerLesao() {
		if (timeMandante == null && timeVisitante == null) {
			System.out.println("Nenhum time foi cadastrado ainda.");
			return;
		}

		Time timeEscolhido = random.nextBoolean() ? timeMandante : timeVisitante;
		if (timeEscolhido == null) {
			System.out.println("O time escolhido ainda não foi cadastrado.");
			return;
		}

		ArrayList<Jogador> plantel = timeEscolhido.getPlantel();
		if (plantel.isEmpty()) {
			System.out.println("Não há jogadores cadastrados no time escolhido.");
			return;
		}

		int index = random.nextInt(plantel.size());
		Jogador jogadorEscolhido = plantel.get(index);

		jogadorEscolhido.sofrerLesao();
		System.out.println("O jogador " + jogadorEscolhido.getNome() + " do time "
				+ (timeEscolhido == timeMandante ? "Mandante" : "Visitante") + " sofreu uma lesão.");
	}

	private static void executarTreinamento() {
		if (timeMandante == null && timeVisitante == null) {
			System.out.println("Nenhum time foi cadastrado ainda.");
			return;
		}

		if (timeMandante != null) {
			System.out.println("Executando treinamento para o Time 1.");
			treinarJogadoresDoTime(timeMandante);
		}

		if (timeVisitante != null) {
			System.out.println("Executando treinamento para o Time 2.");
			treinarJogadoresDoTime(timeVisitante);
		}
	}

	private static void treinarJogadoresDoTime(Time time) {
		for (Jogador jogador : time.getPlantel()) {
			jogador.executarTreinamento();
			System.out.println(
					"Jogador " + jogador.getNome() + " treinou e agora tem qualidade " + jogador.getQualidade());
		}
	}

	private static void relacionarJogadores() {
		// Implementar lógica para relacionar jogadores
	}

	private static void lerTimesEGerarResultado() {
		// Implementar lógica para ler times do arquivo e gerar resultado de um jogo
	}
}
