import java.util.Random;

public class DevelopSearchLink {
    public static int[] developSearchLink (int vertexNumber, int[] profit) {
        int[] searchLink = new int[vertexNumber];
        for (int i = 0; i <= vertexNumber - 1; i++) {
            searchLink[i] = 0;
        }
        for (int i = 0; i <= vertexNumber - 1; i++) {
            int movingPoint = i + 1;
            int[] temLink = new int[vertexNumber];
            for (int j = 0; j <= vertexNumber - 1; j++) {
                temLink[j] = searchLink[j];
            }
            for (int j = 0; j <= vertexNumber - 1; j++) {
                if (searchLink[j] == 0) {
                    searchLink[j] = movingPoint;
                    break;
                }
                else {
                    int comparePoint = searchLink[j];
                    Random random = new Random();
                    double randomNumber = random.nextDouble();
                    if (randomNumber < ((double) profit[movingPoint] / (double) (profit[movingPoint] + profit[comparePoint]))) {
                        searchLink[j] = movingPoint;
                        for (int k = j; k <= vertexNumber - 1; k++) {
                            if (temLink[k] != 0) {
                                searchLink[k + 1] = temLink[k];
                            }
                            else {
                                break;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return searchLink;
    }
}
