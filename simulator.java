import java.io.*;
import java.util.Scanner;

public class simulator{
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        String entrada[] = new String[7];
        entrada = ler.nextLine().split(" ");
        
        CacheClass cache = new CacheClass(Integer.parseInt(entrada[1]), Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3]), entrada[4].charAt(0));
        
        try{
            File arquivo = new File(entrada[6]);

            FileInputStream in = new FileInputStream(arquivo); 
            DataInputStream lerIn = new DataInputStream(in);
            int i = (int) (arquivo.length() / 4);
            
            for(int j = 0; j < i; j++){
                cache.putCache(lerIn.readInt());
            }
            System.out.println();
            System.out.println("Hits: " + cache.getHits());
            System.out.println("Misses: " + cache.getMisses());

            lerIn.close();
        }catch(IOException e){
            System.out.println("Erro!");
        }
        
    }
}
