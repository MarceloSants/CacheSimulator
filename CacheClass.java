import java.util.Random;
public class CacheClass{

    //int cache[][];
    BlockClass cache[][];
    int nsets, bsize, assoc;
    int hit, miss, cacheCount, compulsory, conflict, capacity;
    char sub;
    Random generator;
    public CacheClass(int nsets, int bsize, int assoc, char sub){

        this.nsets = nsets;
        this.bsize = bsize;
        this.assoc = assoc;
        this.sub = sub;
        cacheCount = 0;
        compulsory = 0;
        conflict = 0;
        capacity = 0;
        hit = 0;
        miss = 0;

        /*Initializing the cache as a vector of BlockClass objects and walking through
        each block to initialize by passing the block size. 
        */
        cache = new BlockClass[nsets][assoc];
        for(int i = 0; i < nsets; i++) {
            for(int j = 0; j < assoc; j++) {
                cache[i][j] = new BlockClass(bsize);
            }
        }
        generator = new Random();

    }

    public void putCache(int index, int tag){

        // System.out.println("Tag: " + tag);
        // System.out.println("Index: " + index);
        double end = index;
        
        double block = bsize;
        //System.out.println("end: " + end);
        double posd = (end / block); //"pos" is the set to which the information will be mapped. (Cache matrix line)
        
        //System.out.println("posd: " + posd);
        int pos = (int) (posd %nsets);
        
        int i;
        //System.out.println(pos + " pos "); 
        
        if(search(pos, tag)){
            hit++;
            //System.out.println("Hit!");
        }else{
            //"i" is the path of the group. If assoc = 1 (Direct Mapping) gets 0, otherwise it gets a random value. 
            if(assoc == 1){
                i = 0;
            }else{
                //choose the route using random policy
                i = generator.nextInt(assoc);
            }
            
            if(searchCompulsory(pos, tag)){
                //Miss compulsory
                compulsory++;
                
            }else{
                if(cacheCount == cache.length){// If the cache is full is capacity, otherwise conflict
                    capacity++;
                }else{
                    conflict++;
                }
                cache[pos][i].tag = tag;
            }
            miss++;
        }

        //System.out.println(cacheCount);
    }

    private boolean searchCompulsory(int pos, int tag){
        for(int i = 0; i < assoc; i++) {
            //System.out.print(i);
            if(!cache[pos][i].validity){
                cache[pos][i].validate();
                cache[pos][i].tag = tag;
                cacheCount++;
                return true;
            }
        }
        //System.out.println(" a");
        return false;
    }
    private boolean search(int pos, int tag){

        for(int i = 0; i < assoc; i++) {
            
            if(cache[pos][i].tag == tag){
                if(cache[pos][i].validity){
                    return true;
                }
            }
        
        }

        return false;
    }
    /*private int[] calc(int end){

        int ends[] = new int[bsize];
        int start = end - (end%bsize);

        for(int i = 0; i < bsize; i++){
            ends[i] = start + i;
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
        info[2] = compulsory;
        info[3] = capacity;
        info[4] = conflict;

        return info;
    }
}
