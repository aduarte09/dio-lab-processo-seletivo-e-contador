import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Sistema {
    public static void main(String[] args) {
        String [] candidatos = {"FELIPE","MÁRCIA","JULIA","PAULO","AUGUSTO","MÔNICA","FABRÍCIO","MIRELA","DANIELA","JORGE"};
        String [] selecionados = new String[5];

        double salarioBase = 2000.0;
        double salarioPretendido = valorPretendido();
        
        int candidatosSelecionados = 0;
        int tentativasContato = 1;
        boolean continuarTentando;
        boolean atendeu;

        int i = 0;

        while ((candidatosSelecionados < 5) && (candidatos.length > i)) {
            salarioPretendido = valorPretendido();
            if (salarioPretendido <= salarioBase) {
                System.out.println("\nO candidato " + candidatos[i] + " foi selecionado");
                if (salarioBase > salarioPretendido) {
                    System.out.println("Ligar para o candidato.");
                } else {
                    System.out.println("Ligar para o candidato, com contra proposta.");
                }
                selecionados[candidatosSelecionados] = candidatos[i];
                candidatosSelecionados++;
            } else {
                System.out.println("\nAguardando resultado dos demais candidatos...");
            }
            i++;
        }

        System.out.println("\n\nLista de candidatos selecionados: ");
        for (i = 0; i < candidatosSelecionados; i++) {
            System.out.println(selecionados[i]);
        }

        System.out.println("\n\nEntrando em contato com os candidatos selecionados.");
        for (i = 0; i < candidatosSelecionados; i++) {
            do {
                atendeu = atenderLigacao();
                continuarTentando = !atendeu;
                if (continuarTentando) {
                    tentativasContato++;
                } else {
                    System.out.println("\nContato com " + selecionados[i] + " realizado.");
                }
            } while (continuarTentando && (tentativasContato < 3));
            if (atendeu) {
                System.out.println("Conseguimos contato com " + selecionados[i] + " na " + tentativasContato + "º tentativa.");
            } else {
                System.out.println("\nNão conseguimos contato com " + selecionados[i] + ", número máximo de tentativas (" + tentativasContato + ") alcançado.");
            }
            tentativasContato = 1;
        }
    }

	static boolean atenderLigacao() {
		return new Random().nextInt(3)==1;	
	}

    static double valorPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    }
}