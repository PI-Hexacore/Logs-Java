package school.sptech;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int erroFalso;
        List<String> registrosSucesso = new ArrayList<>();
        List<String> registrosErros = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        int contador = 0;

        while (true) {
            erroFalso = ThreadLocalRandom.current().nextInt(1, 10);
            LocalDateTime dataHora = LocalDateTime.now();
            String dataHoraFormatada = dataHora.format(formatter);
            String registro = dataHoraFormatada + " " + erroFalso;

            if (erroFalso == 3) {
                System.err.println(dataHoraFormatada + " O registro não foi puxado com sucesso, houve um erro! --- { VALOR REGISTRO: "+ erroFalso + " } ---");
                registrosErros.add(registro);

                Thread.sleep(3000);
            } else {
                System.out.println(dataHoraFormatada + " O registro foi puxado com sucesso! --- { VALOR REGISTRO: "+ erroFalso + " } ---");
                registrosSucesso.add(registro);
            }

            contador++;

            if (contador % 50 == 0) {
                System.out.println("\n===== RELATÓRIO PARCIAL =====");
                System.out.println("Total de registros com sucesso: " + registrosSucesso.size());
                System.out.println("Total de erros encontrados: " + registrosErros.size());
                System.out.println("================================\n");
            }

            Thread.sleep(500);
        }
    }
}