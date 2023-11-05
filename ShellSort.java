package tde3ordenacao.grupoB;

import java.util.Random;

public class ShellSort {

    public static int length(int[] array) {
        int soma = 0;
        if (array != null) {
            for (int elemento : array) {
                soma++;
            }
        }
        return soma;
    }

    public static void main(String[] args) {
        Random random = new Random(42); // Use uma semente fixa para reprodutibilidade

        for (int i = 0; i < 5; i++) {
            int tamanhoArray = 0;
            // Defina o tamanho dos dados com base no valor de i
            switch (i) {
                case 0:
                    tamanhoArray = 50;
                    break;
                case 1:
                    tamanhoArray = 500;
                    break;
                case 2:
                    tamanhoArray = 1000;
                    break;
                case 3:
                    tamanhoArray = 5000;
                    break;
                case 4:
                    tamanhoArray = 10000;
                    break;
            }

            long tempoTotal = 0; // Variável para armazenar o tempo total
            long nTrocas = 0; // Variável para armazenar o total de trocas
            long nIteracoes = 0; // Variável para armazenar o total de iterações

            // Repita o experimento várias vezes para calcular a média
            for (int rodada = 0; rodada < 5; rodada++) {
                int[] dados = new int[tamanhoArray];

                // Preencha o array com números aleatórios
                for (int indice = 0; indice < tamanhoArray; indice++) {
                    dados[indice] = random.nextInt();
                }

                long tInicio = System.nanoTime(); // Registra o tempo de início da ordenação

                // Início do algoritmo Shell Sort
                int tArray = length(dados); // Obtém o tamanho do array
                int intervalo = tArray / 2;

                int numTrocas = 0;
                int numIteracoes = 0;

                while (intervalo > 0) {
                    for (int a = intervalo; a < tArray; a++) {
                        int chave = dados[a]; // A chave atual a ser inserida na posição correta
                        int index = a;

                        while (index >= intervalo && dados[index - intervalo] > chave) {
                            dados[index] = dados[index - intervalo]; // Movendo elementos maiores que a chave para a frente
                            index -= intervalo;
                            numTrocas++; // Incrementa o contador de trocas sempre que ocorre uma troca
                        }

                        dados[index] = chave; // Inserir a chave na posição correta
                        numIteracoes++; // Incrementa o contador de iterações a cada iteração
                    }

                    intervalo /= 2; // Reduz o intervalo pela metade a cada iteração, diminuindo o espaçamento entre elementos
                }


                long tFim = System.nanoTime(); // Registra o tempo de término da ordenação
                tempoTotal += (tFim - tInicio); // Calcula o tempo total gasto
                nTrocas += numTrocas; // Acumula o número de trocas
                nIteracoes += numIteracoes; // Acumula o número de iterações
            }

            // Calcule a média do tempo para execução
            long tMedio = tempoTotal / 5;

            // Exibe os resultados do Shell Sort
            System.out.println("Resultados do Shell Sort:");
            System.out.println("Tamanho dos Dados: " + tamanhoArray);
            System.out.println("Tempo médio de execução (nanos): " + tMedio);
            System.out.println("Número médio de trocas: " + nTrocas);
            System.out.println("Número médio de iterações: " + nIteracoes);
            System.out.println("-------------");
        }
    }
}
