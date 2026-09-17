public class ArvoreBinariaMorse {
    // Classe interna para representar um nó da árvore
    class No {
        char dado;    // Valor armazenado no nó
        No direita;  // Referência para a direita
        No esquerda; // Referência para a esquerda

        // Construtor do nó
        No(char letra) {
            this.dado = letra;
            this.direita = null;
            this.esquerda = null;
        }
    }

    No raiz = new No(' '); // Referência para o primeiro nó da árvore


    public void insereLetra(char letra, String codigo){
        No atual = raiz;


        int idx = 0;
        while(idx < codigo.length()){
            // Se move na árvore conforme o próximo caractere em morse
            if(codigo.charAt(idx) == '.'){
                if(atual.esquerda == null){
                    atual.esquerda = new No(' ');
                }

                atual = atual.esquerda;
            }
            else {
                if(atual.direita == null){
                    atual.direita = new No(' ');
                }

                atual = atual.direita;
            }

            idx++;
        }

        atual.dado = letra;
    }


    public void buscaPalavra(String codigo){
        No atual = raiz;

        int idx = 0;
        String resposta = "";
        while(idx < codigo.length() && atual != null){
            if(codigo.charAt(idx) == '.'){
                atual = atual.esquerda;
            }
            else if(codigo.charAt(idx) == '-'){
                atual = atual.direita;
            }
            else if(codigo.charAt(idx) == ' '){
                // Chegou em algo que nem existe
                if(atual == null || atual.dado == ' '){
                    System.out.println("Parte da sequência de " + codigo + " não existe em morse!");
                    return;
                }

                resposta += atual.dado;
                atual = raiz;
            }

            idx++;
        }

        if(atual == null || atual.dado == ' '){
            System.out.println("Parte da sequência de " + codigo + " não existe em morse!");
            return;
        }
        resposta += atual.dado;


        if(!resposta.isEmpty()){
            System.out.println(codigo + " representa " + resposta + " em morse.");
        }
        else if(atual != null){
            System.out.println("Elemento não existe!");
        }
    }


    public void exibirArvore() {
        if (raiz == null) {
            System.out.println("Árvore vazia!");
            return;
        }
        System.out.println("Raiz [*]");
        // O filho à esquerda representa o ponto (.) e o da direita o traço (-)
        exibirArvore(raiz.esquerda, "", true, ".");
        exibirArvore(raiz.direita, "", false, "-");
    }

    private void exibirArvore(No no, String prefixo, boolean isEsquerda, String simboloMorse) {
        if (no == null) return;

        // Se o dado for espaço (vazio), exibe um asterisco (*) para facilitar a visualização
        char caractere = (no.dado == ' ' || no.dado == '\0') ? '*' : no.dado;
        
        System.out.println(prefixo + (isEsquerda ? "├── " : "└── ") + simboloMorse + " -> " + caractere);

        // Define a formatação das linhas verticais para os próximos níveis
        String novoPrefixo = prefixo + (isEsquerda ? "│   " : "    ");

        // Chamadas recursivas
        exibirArvore(no.esquerda, novoPrefixo, true, ".");
        exibirArvore(no.direita, novoPrefixo, false, "-");
    }



    public static void main(String[] args) {
        ArvoreBinariaMorse arvore = new ArvoreBinariaMorse();

        arvore.insereLetra('A', ".-");
        arvore.insereLetra('B', "-...");
        arvore.insereLetra('C', "-.-.");
        arvore.insereLetra('D', "-..");
        arvore.insereLetra('E', ".");
        arvore.insereLetra('F', "..-.");
        arvore.insereLetra('G', "--.");
        arvore.insereLetra('H', "....");
        arvore.insereLetra('I', "..");
        arvore.insereLetra('J', ".---");
        arvore.insereLetra('K', "-.-");
        arvore.insereLetra('L', ".-..");
        arvore.insereLetra('M', "--");
        arvore.insereLetra('N', "-.");
        arvore.insereLetra('O', "---");
        arvore.insereLetra('P', ".--.");
        arvore.insereLetra('Q', "--.-");
        arvore.insereLetra('R', ".-.");
        arvore.insereLetra('S', "...");
        arvore.insereLetra('T', "-");
        arvore.insereLetra('U', "..-");
        arvore.insereLetra('V', "...-");
        arvore.insereLetra('W', ".--");
        arvore.insereLetra('X', "-..-");
        arvore.insereLetra('Y', "-.--");
        arvore.insereLetra('Z', "--..");
        arvore.insereLetra('1', ".----");
        arvore.insereLetra('2', "..---");
        arvore.insereLetra('3', "...--");
        arvore.insereLetra('4', "....-");
        arvore.insereLetra('5', ".....");
        arvore.insereLetra('6', "-....");
        arvore.insereLetra('7', "--...");
        arvore.insereLetra('8', "---..");
        arvore.insereLetra('9', "----.");
        arvore.insereLetra('0', "-----");

    
        arvore.buscaPalavra(".- .-.-.- .-");
        arvore.buscaPalavra("-----");
        arvore.buscaPalavra("... --- ...");
        arvore.buscaPalavra(".--.");
        arvore.buscaPalavra(".-.");

        arvore.exibirArvore();
    }
}