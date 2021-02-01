public class max1 {
    public static int max(int [] array){
        int max = 0;
        int i = 0;
        for(i = 0;i < array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        return max;
    }
}
