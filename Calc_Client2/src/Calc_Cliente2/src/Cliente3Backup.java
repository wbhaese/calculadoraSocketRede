import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Cliente3 {

    public static void main(String[] args) throws UnknownHostException, IOException {
        double num1;
        double num2;
        int operacao = 0;
        char opr;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Aguardando conexao com o Servidor...");
        System.out.println();
        Socket cliente = new Socket("192.168.0.2", 12342);
        System.out.println("Voce esta conectado ao Servidor!");
        System.out.println();


        ObjectInputStream resultado = new ObjectInputStream(cliente.getInputStream());
        ObjectOutputStream dados = new ObjectOutputStream(cliente.getOutputStream());

    
        System.out.println("Digite o primeiro valor: ");
        num1 = entrada.nextDouble();
        System.out.println();
        System.out.println("Digite o segundo valor: ");
        num2 = entrada.nextDouble();
        System.out.println();
        while (!((operacao >= 1) && (operacao <= 4))) {
            System.out.println("Escolha um numero de acordo com a operacao: ");
            System.out.println();
            System.out.println(" 1 - Soma (+)");
            System.out.println(" 2 - Subtracao (-)");
            System.out.println(" 3 - Multiplicacao (*)");
            System.out.println(" 4 - Divisao (/)");
            operacao = entrada.nextInt();
            if (!((operacao >= 1) && (operacao <= 4))) {
                System.out.println("Voce digitou uma operação invelida.");
            }
        }
        dados.writeInt(operacao);
        dados.writeDouble(num1);
        dados.writeDouble(num2);
        dados.flush();

        double total = resultado.readDouble();
        opr = resultado.readChar();
        System.out.println();
        System.out.println("O resultado de " + num1 +" "+ opr +" "+ num2 + " e igual a " + total);

        resultado.close();
        dados.close();
        cliente.close();
    }
}