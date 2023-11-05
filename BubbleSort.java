package tde3ordenacao.grupoA;
import java.util.Random;

public class BubbleSort {
    
    // Método para calcular o tamanho de um array
    public static int length(int[] array) {
        int count = 0;
        if (array != null) {
            for (int elemento : array) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // Número de rodadas para calcular a média
        int numRodadas = 5;

        Random random = new Random(42); // Use uma semente fixa para reprodutibilidade

        for (int i = 0; i < 5; i++) {
            int tamanhoDados = 0;
            // Defina o tamanho dos dados com base no valor de i
            switch (i) {
                case 0:
                    tamanhoDados = 50;
                    break;
                case 1:
                    tamanhoDados = 500;
                    break;
                case 2:
                    tamanhoDados = 1000;
                    break;
                case 3:
                    tamanhoDados = 5000;
                    break;
                case 4:
                    tamanhoDados = 10000;
                    break;
            }

            long tempoTotal = 0;
            long totalTrocas = 0;
            long totalIteracoes = 0;

            // Repita o experimento várias vezes para calcular a média
            for (int rodada = 0; rodada < numRodadas; rodada++) {
                // Cria um novo array de inteiros com o tamanho especificado (tamanhoDados)
                int[] array = new int[tamanhoDados];
                
                // Preenche o array com números inteiros aleatórios entre 0 e 9999
                for (int j = 0; j < tamanhoDados; j++) {
                    array[j] = random.nextInt(10000); // Números aleatórios no intervalo de 0 a 9999
                }

                // Obtém o tamanho do array recém-criado
                int tamanhoArray = length(array);

                // Registra o tempo de início da ordenação
                long tempoInicio = System.nanoTime();
                long trocas = 0;
                long iteracoes = 0;

                // Implementa o algoritmo Bubble Sort para ordenar o array
                for (int j = 0; j < tamanhoArray - 1; j++) {
                    // Loop interno para percorrer os elementos não ordenados do array
                    for (int k = 0; k < tamanhoArray - j - 1; k++) {
                        // A condição 'k < tamanhoArray - j - 1' garante que o loop interno percorra apenas os elementos não ordenados
                        // à medida que o maior elemento já é colocado em sua posição final no final do array.
                        // 'j' representa o número de elementos já ordenados no final do array.
                        // Portanto, 'tamanhoArray - j' é o número de elementos que ainda não estão em suas posições finais.
                        // Subtraindo 1 ('- 1') garante que o último elemento não seja verificado, pois ele já estará na posição correta.

                        iteracoes++; // Incrementa o contador de iterações

                        // Compara o elemento atual com o próximo elemento no array
                        if (array[k] > array[k + 1]) {
                            // Se o elemento atual for maior que o próximo, troca os elementos de posição
                            int temp = array[k];
                            array[k] = array[k + 1];
                            array[k + 1] = temp;
                            trocas++; // Incrementa o contador de trocas
                        }
                    }
                }

                // Registra o tempo de término da ordenação
                long tempoFim = System.nanoTime();

                // Calcula a duração da ordenação subtraindo o tempo de início do tempo de término
                long duracao = (tempoFim - tempoInicio);

                // Adiciona o tempo de execução da ordenação atual ao tempo total
                tempoTotal += duracao;

                // Adiciona o número de trocas e iterações da ordenação atual ao total
                totalTrocas += trocas;
                totalIteracoes += iteracoes;
            }

            // Calcule a média dos resultados
            long tempoMedio = tempoTotal / numRodadas;
            long trocasMedias = totalTrocas / numRodadas;
            long iteracoesMedias = totalIteracoes / numRodadas;

            // Exibe os resultados
            System.out.println("Tamanho dos Dados: " + tamanhoDados);
            System.out.println("Tempo médio de execução (nanos): " + tempoMedio);
            System.out.println("Número médio de trocas: " + trocasMedias);
            System.out.println("Número médio de iterações: " + iteracoesMedias);
            System.out.println("-------------");
        }
    }
}
