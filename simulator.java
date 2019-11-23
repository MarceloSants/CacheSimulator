import java.io.*;
import java.util.Scanner;

public class simulator{
    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        /*Campos do vetor "entrada" : 0 - Nome do programa; 1 - nsets; 2 - bsize; 3 - assoc;
        4 - politica; 5 - flag de saida; 6 - arquivo.
        */
        String entrada[] = new String[7];
        int tag, indice, offset;
        System.out.print("Entre com as informacoes da cache:");
        entrada = ler.nextLine().split(" ");

        offset = calcFat(Integer.parseInt(entrada[2]));
        indice = calcFat(Integer.parseInt(entrada[1]));
        tag = 32 - (offset + indice);

        CacheClass cache = new CacheClass(Integer.parseInt(entrada[1]), Integer.parseInt(entrada[2]), Integer.parseInt(entrada[3]), entrada[4].charAt(0));
        
        // int k = 7;
        // int x = k >> 1;
        // int y = k << 2;
        // System.out.println(String.format("k=%d, x=%d, y=%d", k, x, y)); // i=7, x=3, y=28

        try{
            double info[];
            File arquivo = new File(entrada[6]);

            FileInputStream in = new FileInputStream(arquivo); 
            DataInputStream lerIn = new DataInputStream(in);
            int i = (int) (arquivo.length() / 4);
            
            for(int j = 0; j < i; j++){

                int end = lerIn.readInt();
                int endTag;

                endTag = end >> (indice + offset);
                
                //endIndice = (end << tag) >> (tag + offset);
                //dendIndice = endIndice ;

                cache.putCache(end, endTag);//, tag, indice, offset
            }
            
            info = cache.getInfo();
            //-----------------------Tratamento da informação
            //System.out.println("Taxa de hit:" + info[0]);
            //System.out.println("Valor de i:" + i);
            info[0] = info[0]/i; //Taxa de hit
            //System.out.println("Taxa de hit:" + info[0]);
            info[2] = info[2]/info[1]; //Taxa de miss compulsório
            info[3] = info[3]/info[1]; //Taxa de miss de capacidade
            info[4] = info[4]/info[1]; //Taxa de miss de conflito
            info[1] = info[1]/i; //Taxa de miss

            System.out.println();

            if(Integer.parseInt(entrada[5]) == 1){
                
                System.out.print(i + ", ");
                for(int j = 0; j < 5; j++){
                    System.out.print(info[j]);
                    if(j != 4){
                        System.out.print(", ");
                    }
                }
            }else{
                System.out.print("Numero de acessos: ");
                System.out.println(i);
                System.out.print("Taxa de hit: ");
                System.out.println(info[0]);
                System.out.print("Taxa de miss: ");
                System.out.println(info[1]);
                System.out.print("Taxa de miss compulsorio: ");
                System.out.println(info[2]);
                System.out.print("Taxa de miss de capacidade: ");
                System.out.println(info[3]);
                System.out.print("Taxa de miss de conflito: ");
                System.out.println(info[4]);
            }

            lerIn.close();
            ler.close();
        }catch(IOException e){
            System.out.println("Erro!");
        }
        
    }

    private static int calcFat(int blocksize){

        int block = blocksize;
        int contador = 0;

        if(blocksize%2 == 0){ //Se for par
            //divide por 2 até resultar em 1, enquanto contador guarda o expoente da fatoração
            while(block != 1){ 
                block = block/2;
                contador++;
            }
        }

        return contador;
    }
}
