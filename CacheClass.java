import java.util.Random;
public class CacheClass{

    //int cache[][];
    BlockClass cache[][];
    int nsets, assoc;
    int hit, miss, cacheCount, compulsorio, conflito, capacidade;
    char sub;
    Random gerador;
    public CacheClass(int nsets, int bsize, int assoc, char sub){

        this.nsets = nsets;
        //this.bsize = bsize;
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
        for(int i = 0; i < cache.length; i++) {
            for(int j = 0; j < cache.length; j++) {
                cache[i][j] = new BlockClass(bsize);
            }
        }
        gerador = new Random();
    }

    public void putCache(int[] endereco){

        int pos = endereco[0] % (nsets); //"pos" é o conjunto para qual será mapeada a informação. (Linha da matriz "cache")
        int i;
        System.out.print(endereco + " "); 

        if(busca(int conj, int assoc, int bsize, int info)){
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
                cache[pos][i].espaco = endereco;
            }else{
    
                //miss (conflito ou capacidade)
                if(cacheCount == cache.length){// Se a cache está cheia é capacidade, senão conflito
                    capacidade++;
                }else{
                    conflito++;
                }
                miss++;
                cache[pos][i].espaco = endereco;
            
            }
        }
        

        
        
    }

    private boolean busca(int conj, int assoc, int bsize, int info){

        for(int i = 0; i < assoc; i++) {
            for(int j = 0; j < bsize; j++){
                if(cache[conj][i].espaco[j] == info){
                    if(cache[conj][i].validade){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /*
    public int getHits(){
        return hit;
    }

    public int getMisses(){
        return miss;
    }
    */
    public int[] getInfo(){

        int info[] = new int[5];

        info[0] = hit;
        info[1] = miss;
        info[2] = compulsorio;
        info[3] = conflito;
        info[4] = capacidade;

        return info;
    }
}

