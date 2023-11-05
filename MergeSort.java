package tde3ordenacao.grupoB;

import java.util.Random;

public class MergeSort {
    private long trocas; // Variável para rastrear o número de trocas
    private long iteracoes; // Variável para rastrear o número de iterações

    public MergeSort() {
        this.trocas = 0;
        this.iteracoes = 0;
    }
    
    public static int length(int[] array) {
        int soma = 0;
        if (array != null) {
            for (int elemento : array) {
                soma++;
            }
        }
        return soma;
    }

    // Função principal para realizar a ordenação Merge Sort
    public void mergeSort(int[] array) {
        int n = length(array); // Obtém o tamanho do array
        int[] aux = new int[n]; // Cria um array auxiliar do mesmo tamanho para mesclar partes

        mergeSort(array, aux, 0, n - 1);
    }

    // Função de ordenação Merge Sort recursiva
    private void mergeSort(int[] array, int[] aux, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            mergeSort(array, aux, esquerda, meio); // Ordena a metade esquerda
            mergeSort(array, aux, meio + 1, direita); // Ordena a metade direita
            merge(array, aux, esquerda, meio, direita); // Combina as partes ordenadas
        }
    }

    // Função para mesclar duas subarrays ordenadas em uma única array ordenada
    private void merge(int[] array, int[] aux, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;

        int[] arrayEsquerda = new int[n1];
        int[] arrayDireita = new int[n2];

        // Copia os elementos das duas partes para arrays temporários
        for (int i = 0; i < n1; i++) {
            arrayEsquerda[i] = array[esquerda + i];
        }
        for (int j = 0; j < n2; j++) {
            arrayDireita[j] = array[meio + 1 + j];
        }

        int indiceEsquerda = 0, indiceDireita = 0;
        int indiceArray = esquerda;

        // Combina os elementos das duas partes de volta na array original
        while (indiceEsquerda < n1 && indiceDireita < n2) {
            iteracoes++; // Incrementa o número de iterações

            if (arrayEsquerda[indiceEsquerda] <= arrayDireita[indiceDireita]) {
                // Se o elemento na subarray esquerda é menor ou igual, copie para a array original
                array[indiceArray] = arrayEsquerda[indiceEsquerda];
                indiceEsquerda++;
            } else {
                // Se o elemento na subarray direita é menor, copie para a array original e registre a troca
                array[indiceArray] = arrayDireita[indiceDireita];
                trocas++;
                indiceDireita++;
            }
            indiceArray++;
        }

        // Copia os elementos restantes, se houver, dos arrays temporários de esquerda
        while (indiceEsquerda < n1) {
            trocas++;
            array[indiceArray] = arrayEsquerda[indiceEsquerda];
            indiceEsquerda++;
            indiceArray++;
        }

        // Copia os elementos restantes, se houver, dos arrays temporários de direita
        while (indiceDireita < n2) {
            array[indiceArray] = arrayDireita[indiceDireita];
            indiceDireita++;
            indiceArray++;
        }
    }

    public long getTrocas() {
        return trocas;
    }

    public long getIteracoes() {
        return iteracoes;
    }

    public static void main(String[] args) {
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

            long tempoTotal = 0; // Variável para armazenar o tempo total
            long nTrocas = 0; // Variável para armazenar o total de trocas
            long nIteracoes = 0; // Variável para armazenar o total de iterações

            // Repita o experimento várias vezes para calcular a média
            for (int rodada = 0; rodada < 5; rodada++) {
                int[] dados = new int[tamanhoDados];

                // Preencha o array com números aleatórios
                for (int indice = 0; indice < tamanhoDados; indice++) {
                    dados[indice] = random.nextInt();
                }
                long tInicio = System.nanoTime(); // Registra o tempo de início da ordenação

                // Cria uma instância da classe MergeSortRefactored para realizar a ordenação
                MergeSort merge = new MergeSort();
                // Chama o método mergeSort para ordenar o array de dados
                merge.mergeSort(dados);

                long tFim = System.nanoTime(); // Registra o tempo de término da ordenação
                tempoTotal += (tFim - tInicio); // Calcula o tempo total gasto
                nTrocas += merge.getTrocas(); // Atualiza o total de trocas
                nIteracoes += merge.getIteracoes(); // Atualiza o total de iterações
            }
            // Calcule o tempo médio de resultados para o Merge Sort
            long tMedio = tempoTotal / 5;

            // Exiba os resultados do Merge Sort
            System.out.println("Resultados do Merge Sort:");
            System.out.println("Tamanho dos Dados: " + tamanhoDados);
            System.out.println("Tempo médio de execução (nanos): " + tMedio);
            System.out.println("Número de trocas: " + nTrocas);
            System.out.println("Número de iterações: " + nIteracoes);
            System.out.println("-------------");
        }
    }
}
