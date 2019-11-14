import java.util.Random;
public class CacheClass{

    //int cache[][];
    BlockClass cache[][];
    int nsets, bsize, assoc, hit, miss;
    char sub;
    Random gerador;
    public CacheClass(int nsets, int bsize, int assoc, char sub){
        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.sub = sub;
        hit = 0;
        miss = 0;
        cache = new BlockClass[nsets][assoc];
        //cache = new int[this.nsets][assoc];
        gerador = new Random();
    }

    public void putCache(int endereco){
        int pos = endereco % (nsets);
        int i;
        System.out.print(endereco + " "); 
        if(assoc == 1){
            i = 0;
        }else{
            i = gerador.nextInt(assoc);
        }
        if(cache[pos][i].espaco[0] == endereco){
            hit++;
        }else{
            cache[pos][i].espaco[0] = endereco;
            miss++;
        }
        
    }

    public int getHits(){
        return hit;
    }

    public int getMisses(){
        return miss;
    }
}

