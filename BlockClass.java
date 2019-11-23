public class BlockClass{

    boolean validade;
    int tag;
    //int espaco[];

    public BlockClass(int tam){
        validade= false;
        tag = 0;
        //espaco = new int[tam];
    }

    public void validate(){
        validade = true;
    }
}