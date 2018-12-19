import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


public class Principal {

	public static void main(String[] args) {
		ArrayList<Integer> teste = new ArrayList<>();
        Random r = new Random();
        int atrib =16;
        int tam = r.nextInt(atrib);
        int x;

        for (int i = 0; i < tam; i++) {
            do {
                x = r.nextInt(atrib - 1) + 1;
            } while (teste.contains(x));
            teste.add(x);
        }
        System.out.println("atributos removidos: " + teste.size());
        CarregaBase.carregaBase(teste);


	}

}
