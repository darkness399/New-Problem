public class min1 {
    public static int min(int [] array){
        int min = array[0];
        int i = 0;
        for(i = 0;i <= array.length - 1; i++){
            if(array[i] < min){
                min=array[i];
            }
        }
        return min;
    }
}
