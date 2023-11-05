package tde3ordenacao.grupoA;
import java.util.Random;

public class InsertSort {
    
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
            long tOcorrencias = 0;
            long totalIteracoes = 0;

            // Repita o experimento várias vezes para calcular a média
            for (int rodada = 0; rodada < numRodadas; rodada++) {
                
                // Cria um novo array de inteiros com o tamanho especificado (tamanhoDados)
                int[] array = new int[tamanhoDados];
                // Preenche o array com números inteiros aleatórios entre 0 e 9999
                for (int j = 0; j < tamanhoDados; j++) {
                    array[j] = random.nextInt(10000);
                }

                // Registra o tempo de início da ordenação
                long tInicio = System.nanoTime();
                int tamanhoArray = length(array);
                int ocorrencias = 0; // Contador de trocas
                
                for (int j = 1; j < tamanhoArray; j++) {
                    int chave = array[j];
                    int indiceAnterior = j - 1;
                    
                    // Início do processo de inserção da chave na posição correta
                    while (indiceAnterior >= 0 && array[indiceAnterior] > chave) {
                        array[indiceAnterior + 1] = array[indiceAnterior]; // Deslocamento de elementos maiores para a direita
                        indiceAnterior = indiceAnterior - 1;
                        ocorrencias++; // Incrementa o contador de trocas
                    }
                    array[indiceAnterior + 1] = chave; // Inserção da chave na posição correta
                    // Fim do processo de inserção
                }

                // Registra o tempo de término da ordenação
                long tFim = System.nanoTime();
                tempoTotal += (tFim - tInicio);
                tOcorrencias += ocorrencias;
            }

            // Calcule a média dos resultados para o Insertion Sort
            long tMedio = tempoTotal / numRodadas;
            
            // Exibe os resultados do Insertion Sort
            System.out.println("Resultados do Insertion Sort:");
            System.out.println("Tamanho dos Dados: " + tamanhoDados);
            System.out.println("Tempo médio de execução (nanos): " + tMedio);
            System.out.println("Número médio de trocas e interações: " + tOcorrencias / numRodadas);
            System.out.println("-------------");
        }
    }
}
