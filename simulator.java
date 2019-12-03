import java.io.*;
import java.util.Scanner;

public class simulator{
    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);
        /*fields of vector "input" : 0 - Program name; 1 - nsets; 2 - bsize; 3 - assoc;
        4 - politics; 5 - flag of output; 6 - file.
        */
        String input[] = new String[7];
        int tag, index, offset;
        System.out.print("input cache info:");
        input = read.nextLine().split(" ");

        offset = calcFat(Integer.parseInt(input[2]));
        index = calcFat(Integer.parseInt(input[1]));
        tag = 32 - (offset + indice);

        CacheClass cache = new CacheClass(Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]), input[4].charAt(0));
        
        // int k = 7;
        // int x = k >> 1;
        // int y = k << 2;
        // System.out.println(String.format("k=%d, x=%d, y=%d", k, x, y)); // i=7, x=3, y=28

        try{
            double info[];
            File file = new File(input[6]);

            FileInputStream in = new FileInputStream(file); 
            DataInputStream readIn = new DataInputStream(in);
            int i = (int) (file.length() / 4);
            
            for(int j = 0; j < i; j++){

                int end = readIn.readInt();
                int endTag;

                endTag = end >> (index + offset);
                
                //endIndex = (end << tag) >> (tag + offset);
                //dendIndex = endIndex ;

                cache.putCache(end, endTag);//, tag, index, offset
            }
            
            info = cache.getInfo();
            //-----------------------Processing of info
            //System.out.println("Hit tax:" + info[0]);
            //System.out.println("i value:" + i);
            info[0] = info[0]/i; //Hit tax
            //System.out.println("Hit tax:" + info[0]);
            info[2] = info[2]/info[1]; //Compulsory miss tax
            info[3] = info[3]/info[1]; //Capacity miss tax
            info[4] = info[4]/info[1]; //Conflict miss tax
            info[1] = info[1]/i; //Miss tax

            System.out.println();

            if(Integer.parseInt(input[5]) == 1){
                
                System.out.print(i + ", ");
                for(int j = 0; j < 5; j++){
                    System.out.print(info[j]);
                    if(j != 4){
                        System.out.print(", ");
                    }
                }
            }else{
                System.out.print("Entry numbers: ");
                System.out.println(i);
                System.out.print("Hit tax: ");
                System.out.println(info[0]);
                System.out.print("Miss tax: ");
                System.out.println(info[1]);
                System.out.print("Compulsory miss tax: ");
                System.out.println(info[2]);
                System.out.print("Capacity miss tax: ");
                System.out.println(info[3]);
                System.out.print("Conflict miss tax: ");
                System.out.println(info[4]);
            }

            readIn.close();
            read.close();
        }catch(IOException e){
            System.out.println("Error!");
        }
        
    }

    private static int calcFat(int blocksize){

        int block = blocksize;
        int counter = 0;

        if(blocksize%2 == 0){ //If pair
            //divide by 2 until result 1, until counter save the factorization exponent
            while(block != 1){ 
                block = block/2;
                counter++;
            }
        }

        return counter;
    }
}
