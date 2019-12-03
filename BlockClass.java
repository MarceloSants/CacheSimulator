public class BlockClass{

    boolean validity;
    int tag;
    //int space[];

    public BlockClass(int size){
        validity= false;
        tag = 0;
        //space = new int[size];
    }

    public void validate(){
        validity = true;
    }
}
