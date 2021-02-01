import java.util.Random;

public class Random_Delate {
    public static void randomDelate(int[][] visitorLink, int visitorNumber, int vertexNumber, int[][] resourceLink, int selectedLinkNumber, int[][] unvisitedPoints, int resourceNumberInEachVertex){
        int pointNumber = 0;
        int bestDeleteNumber = 0;
        for (int i = 0; i <= visitorNumber - 1; i++){
            pointNumber = 0;
            for (int j = 0; j <= vertexNumber - 1; j++){
                if (visitorLink[i][j] != 0){
                    pointNumber = pointNumber + 1;
                }
                else{
                    break;
                }
            }
            Random random = new Random();
            double randomNumber = random.nextDouble();
            double randomNumber1 = random.nextDouble();
            bestDeleteNumber = (int) (Math.pow(randomNumber, randomNumber1) * pointNumber / 2);
            int[] deletePoints = new int[pointNumber];
            for (int j = 0; j <= pointNumber - 1; j++){
                deletePoints[j] = 0;
            }
            for (int j = 0; j <= bestDeleteNumber - 1; j++){
                int selectedPoints =  random.nextInt(pointNumber + 1);
                while (find1.LinearSearch(deletePoints, selectedPoints) != -1){
                     selectedPoints =  random.nextInt(pointNumber + 1);
                }
                for (int k = 0; k <= pointNumber - 1; k++){
                    if (deletePoints[k] == 0) {
                        deletePoints[k] = selectedPoints;
                        break;
                    }
                }
            }
            for (int j = 0; j <= bestDeleteNumber - 1; j++){
                int pointFlag = ((visitorLink[i][deletePoints[j] - 1] - 1) / resourceNumberInEachVertex) + 1;
                unvisitedPoints[i][pointFlag - 1] = pointFlag;
                int[] temResourceLink = new int[visitorNumber];
                for (int l = 0; l <= visitorNumber - 1; l++) {
                    temResourceLink[l] = resourceLink[visitorLink[i][deletePoints[j] - 1] - 1][l];
                }
                int[] developResourceLink = new int[visitorNumber];
                for (int m = 0; m <= visitorNumber - 1; m++){
                    developResourceLink[m] = 0;
                }
                int k = 0;
                int l = 0;
                while (k <= visitorNumber - 1){
                    while (l <= visitorNumber - 1){
                        if (temResourceLink[l] != i + 1){
                            developResourceLink[k] = temResourceLink[l];
                            l = l + 1;
                            break;
                        }
                        l = l + 1;
                    }
                    k = k + 1;
                }
                for (int m = 0; m <= visitorNumber - 1; m++) {
                    resourceLink[visitorLink[i][deletePoints[j] - 1] - 1][m] = developResourceLink[m];
                }
            }
             int[] temVisitorLink = new int[vertexNumber];
            for (int l = 0; l <= vertexNumber - 1; l++) {
                temVisitorLink[l] = visitorLink[i][l];
            }
            int[] developVisitorLink = new int[vertexNumber];
            for (int m = 0; m <= vertexNumber - 1; m++){
                developVisitorLink[m] = 0;
            }
            int k =0;
            int l = 0;
            while (k <= vertexNumber - 1){
                while (l <= vertexNumber - 1){
                    if (find1.LinearSearch(deletePoints, l + 1) == -1){
                        developVisitorLink[k] = temVisitorLink[l];
                        l = l + 1;
                        break;
                    }
                    l = l + 1;
                }
                k = k + 1;
            }
            for (int m = 0; m <= vertexNumber - 1; m++) {
                visitorLink[i][m] = developVisitorLink[m];
            }

        }
    }
}
