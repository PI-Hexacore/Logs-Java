package school.sptech; // Declara o pacote ao qual esta classe pertence (organiza as classes no projeto)

import java.time.LocalDateTime;              // Importa a classe para trabalhar com data e hora atuais
import java.time.format.DateTimeFormatter;   // Importa a classe para formatar data/hora em texto
import java.util.ArrayList;                  // Importa a implementação de lista dinâmica
import java.util.List;                       // Importa a List
import java.util.concurrent.ThreadLocalRandom; // Importa gerador de números aleatórios por thread (performático e seguro)

public class Main { // Declaração da classe pública Main (nome do arquivo deve ser Main.java)
    public static void main(String[] args) throws InterruptedException { // Método principal; "throws" porque usamos Thread.sleep
        int erroFalso; // Variável que armazenará um número aleatório para simular sucesso/erro
        List<String> registrosSucesso = new ArrayList<>(); // Lista para registrar mensagens de sucesso
        List<String> registrosErros = new ArrayList<>();   // Lista para registrar mensagens de erro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); // Define o formato de data/hora

        int contador = 0; // Contador de iterações do loop (usado para emitir relatórios parciais)

        while (true) { // Loop infinito (só para quando o programa for interrompido externamente)
            erroFalso = ThreadLocalRandom.current().nextInt(1, 10); // Gera número aleatório de 1 a 9 (10 é exclusivo)
            LocalDateTime dataHora = LocalDateTime.now();            // Captura a data e hora do momento atual
            String dataHoraFormatada = dataHora.format(formatter);   // Formata a data/hora para string legível
            String registro = dataHoraFormatada + " " + erroFalso;   // Monta uma linha simples com data/hora e valor gerado

            if (erroFalso == 3) { // Se o número aleatório for 3, vamos considerar que ocorreu um erro
                System.err.println( // Imprime no fluxo de erro (stderr) para diferenciar de mensagens normais
                        dataHoraFormatada
                                + " O registro não foi puxado com sucesso, houve um erro! --- { VALOR REGISTRO: "
                                + erroFalso + " } ---"
                );
                registrosErros.add(registro); // Guarda este registro na lista de erros

                Thread.sleep(3000); // Pausa a execução por 3 segundos quando acontece erro (simulação de impacto/espera)
            } else { // Caso contrário, consideramos um sucesso
                System.out.println( // Imprime no fluxo padrão (stdout) uma mensagem de sucesso
                        dataHoraFormatada
                                + " O registro foi puxado com sucesso! --- { VALOR REGISTRO: "
                                + erroFalso + " } ---"
                );
                registrosSucesso.add(registro); // Guarda este registro na lista de sucessos
            }

            contador++; // Incrementa o número total de iterações realizadas

            if (contador % 50 == 0) { // A cada 50 iterações, mostra um relatório parcial
                System.out.println("\n===== RELATÓRIO PARCIAL ====="); // Cabeçalho do relatório
                System.out.println("Total de registros com sucesso: " + registrosSucesso.size()); // Quantidade de sucessos
                System.out.println("Total de erros encontrados: " + registrosErros.size());       // Quantidade de erros
                System.out.println("================================\n"); // Rodapé do relatório
            }

            Thread.sleep(500); // Aguarda 0,5 segundo entre cada iteração (controle de frequência do loop)
        }
    }
}
