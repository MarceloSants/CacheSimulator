import java.io.*;
import java.util.Scanner;

public class simulator{
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        String entrada[] = new String[7];
        System.out.print("Entre com as informacoes da cache:");
        entrada = ler.nextLine().split(" ");
        
        CacheClass cache = new CacheClass(Integer.parseInt(entrada[1]), Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3]), entrada[4].charAt(0));
        
        try{
            int info[];
            File arquivo = new File(entrada[6]);

            FileInputStream in = new FileInputStream(arquivo); 
            DataInputStream lerIn = new DataInputStream(in);
            int i = (int) (arquivo.length() / 4);
            
            for(int j = 0; j < i; j++){
                cache.putCache(lerIn.readInt());
            }
            /*
            System.out.println();
            System.out.println("Hits: " + cache.getHits());
            System.out.println("Misses: " + cache.getMisses());
            */
            info = cache.getInfo();
            //-----------------------Tratamento da informação
            info[0] = info[0]/i; //Taxa de hit
            info[2] = info[2]/info[1]; //Taxa de miss compulsório
            info[3] = info[3]/info[1]; //Taxa de miss de capacidade
            info[4] = info[4]/info[1]; //Taxa de miss de conflito
            info[1] = info[1]/i; //Taxa de miss
            System.out.println();
            System.out.print(i + ", ");
            for(int j = 0; j < 5; j++){
                System.out.print(info[j] + ", ");
            }

            lerIn.close();
            ler.close();
        }catch(IOException e){
            System.out.println("Erro!");
        }
        
    }
}
