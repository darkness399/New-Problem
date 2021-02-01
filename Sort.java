public class Sort {
    public static double[] sort(double[] array){
        double temp = 0;
        for (int i = 1; i < array.length; i++){
            for (int j = 0; j < array.length - i; j++){
                if (array[j] > array[j + 1]){
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
    public static int[] sort(int[] array){
        int temp = 0;
        for (int i = 1; i < array.length; i++){
            for (int j = 0; j < array.length - i; j++){
                if (array[j] > array[j + 1]){
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

}
