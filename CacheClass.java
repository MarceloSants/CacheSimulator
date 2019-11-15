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
        this.bsize = bsize/4;
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

    public void putCache(int endereco){

        int pos = endereco % (nsets); //"pos" é o conjunto para qual será mapeada a informação. (Linha da matriz "cache")
        int i;
        //System.out.print(endereco + " "); 

        if(busca(pos, endereco)){
            hit++;
        }else{
            //"i" é a via do conjunto. Se for assoc = 1 (Mapeamento direto) recebe 0, senão recebe um valor aleatório. 
            if(assoc == 1){
                i = 0;
            }else{
                i = gerador.nextInt(assoc);
            }

            if(!cache[pos][i].validade){
                //Miss compulsório
                miss++;
                compulsorio++;
                cacheCount++;
                cache[pos][i].validate();
                cache[pos][i].espaco = calc(endereco);
            }else{
    
                //miss (conflito ou capacidade)
                if(cacheCount == cache.length){// Se a cache está cheia é capacidade, senão conflito
                    capacidade++;
                }else{
                    conflito++;
                }
                miss++;
                cache[pos][i].espaco = calc(endereco);
            
            }
        }
        //Teste commit vsCode
        
    }

    private boolean busca(int pos, int end){

        for(int i = 0; i < assoc; i++) {
            for(int j = 0; j < bsize; j++){
                if(cache[pos][i].espaco[j] == end){
                    if(cache[pos][i].validade){
                        return true;
                    }
                }
            }
        }

        return false;
    }
    private int[] calc(int end){

        int ends[] = new int[bsize];
        int inicio = end - (end%bsize);

        for(int i = 0; i < bsize; i++){
            ends[i] = inicio + i;
        }

        return ends;
    }
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

