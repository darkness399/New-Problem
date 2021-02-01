public class linkShowing1 {
    public static void linkShowing(int vertexNumber, int visitorNumber, int[][] visitorLink, int[][] resourceLink){
        System.out.println("游客链：");
        for (int i = 0; i <= visitorNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){

                System.out.print(visitorLink[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("资源链:");
        for (int i = 0; i <= 2 * vertexNumber - 1; i++){
            for (int j = 0; j <= visitorNumber - 1; j++){
                System.out.print(resourceLink[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
