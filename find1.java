public class find1 {
    public static int LinearSearch(int[] list,int key) {
        // TODO Auto-generated method stub
        for(int i = 0;i < list.length;i++){
            if(key == list[i]){
                return i;
            }
        }
        return -1;
    }
    public static int LinearSearch(double[] list,double key) {
        // TODO Auto-generated method stub
        for(int i = 0;i < list.length;i++){
            if(key == list[i]){
                return i;
            }
        }
        return -1;
    }
}
