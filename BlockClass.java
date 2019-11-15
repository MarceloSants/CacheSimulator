public class BlockClass{

    boolean validade;
    int espaco[];

    public BlockClass(int tam){
        validade= false;
        espaco = new int[tam];
    }

    public void validate(){
        validade = true;
    }
}