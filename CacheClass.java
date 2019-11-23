import java.util.Random;
public class CacheClass{

    //int cache[][];
    BlockClass cache[][];
    int nsets, bsize, assoc;
    int hit, miss, cacheCount, compulsorio, conflito, capacidade;
    char sub;
    Random gerador;
    public CacheClass(int nsets, int bsize, int assoc, char sub){

        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.sub = sub;
        cacheCount = 0;
        compulsorio = 0;
        conflito = 0;
        capacidade = 0;
        hit = 0;
        miss = 0;

        /*Inicializando a cache como um vetor de objetos da classe BlockClass e percorrendo 
        cada bloco para inicializar passando o tamanho do bloco. 
        */
        cache = new BlockClass[nsets][assoc];
        for(int i = 0; i < nsets; i++) {
            for(int j = 0; j < assoc; j++) {
                cache[i][j] = new BlockClass(bsize);
            }
        }
        gerador = new Random();

    }

    public void putCache(int indice, int tag){

        // System.out.println("Tag: " + tag);
        // System.out.println("Indice: " + indice);
        double end = indice;
        
        double block = bsize;
        //System.out.println("end: " + end);
        double posd = (end / block); //"pos" é o conjunto para qual será mapeada a informação. (Linha da matriz "cache")
        
        //System.out.println("posd: " + posd);
        int pos = (int) (posd %nsets);
        
        int i;
        //System.out.println(pos + " pos "); 
        
        if(busca(pos, tag)){
            hit++;
            //System.out.println("Hit!");
        }else{
            //"i" é a via do conjunto. Se for assoc = 1 (Mapeamento direto) recebe 0, senão recebe um valor aleatório. 
            if(assoc == 1){
                i = 0;
            }else{
                //escolhe a via usando política random
                i = gerador.nextInt(assoc);
            }
            
            if(buscaCompulsorio(pos, tag)){
                //Miss compulsório
                compulsorio++;
                
            }else{
                if(cacheCount == cache.length){// Se a cache está cheia é capacidade, senão conflito
                    capacidade++;
                }else{
                    conflito++;
                }
                cache[pos][i].tag = tag;
            }
            miss++;
        }

        //System.out.println(cacheCount);
    }

    private boolean buscaCompulsorio(int pos, int tag){
        for(int i = 0; i < assoc; i++) {
            //System.out.print(i);
            if(!cache[pos][i].validade){
                cache[pos][i].validate();
                cache[pos][i].tag = tag;
                cacheCount++;
                return true;
            }
        }
        //System.out.println(" a");
        return false;
    }
    private boolean busca(int pos, int tag){

        for(int i = 0; i < assoc; i++) {
            
            if(cache[pos][i].tag == tag){
                if(cache[pos][i].validade){
                    return true;
                }
            }
        
        }

        return false;
    }
    /*private int[] calc(int end){

        int ends[] = new int[bsize];
        int inicio = end - (end%bsize);

        for(int i = 0; i < bsize; i++){
            ends[i] = inicio + i;
        }

        return ends;
    }*/
    /*
    public int getHits(){
        return hit;
    }
    public int getMisses(){
        return miss;
    }
    */
    public double[] getInfo(){

        double info[] = new double[5];

        info[0] = hit;
        info[1] = miss;
        info[2] = compulsorio;
        info[3] = capacidade;
        info[4] = conflito;

        return info;
    }
}