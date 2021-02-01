import java.util.Random;

public class Random_Delete2 {
    public static void randomDelete2 (int[][] visitorLink, int vertexNumber, int selectedLinkNumber, int resourceNumberInEachVertex, int[][] unvisitedPoints, int visitorNumber, int[][] resourceLink, int[] collectedProfits, int[] profit) {
        int pointNumber = 0;
        int bestDeleteNumber = 0;
        for (int j = 0; j <= vertexNumber - 1; j++){
            if (visitorLink[selectedLinkNumber - 1][j] != 0){
                pointNumber = pointNumber + 1;
            }
            else{
                break;
            }
        }
        Random random = new Random();
        double randomNumber = random.nextDouble();
        double randomNumber1 = random.nextDouble();
        bestDeleteNumber = (int) Math.round(Math.pow(randomNumber, randomNumber1) * pointNumber);
        if (bestDeleteNumber < 1) {
            bestDeleteNumber = 1;
        }
        int[] deletePoints = new int[pointNumber];
        if (pointNumber != 0) {
            for (int j = 0; j <= pointNumber - 1; j++) {
                deletePoints[j] = 0;
            }
            for (int j = 0; j <= bestDeleteNumber - 1; j++) {
                int selectedPoints = random.nextInt(pointNumber + 1);
                while (find1.LinearSearch(deletePoints, selectedPoints) != -1) {
                    selectedPoints = random.nextInt(pointNumber + 1);
                }
                for (int k = 0; k <= pointNumber - 1; k++) {
                    if (deletePoints[k] == 0) {
                        deletePoints[k] = selectedPoints;
                        break;
                    }
                }
            }
            for (int j = 0; j <= bestDeleteNumber - 1; j++) {
                int pointFlag = ((visitorLink[selectedLinkNumber - 1][deletePoints[j] - 1] - 1) / resourceNumberInEachVertex) + 1;
                unvisitedPoints[selectedLinkNumber - 1][pointFlag - 1] = pointFlag;
                int[] temResourceLink = new int[visitorNumber];
                for (int l = 0; l <= visitorNumber - 1; l++) {
                    temResourceLink[l] = resourceLink[visitorLink[selectedLinkNumber - 1][deletePoints[j] - 1] - 1][l];
                }
                int[] developResourceLink = new int[visitorNumber];
                for (int m = 0; m <= visitorNumber - 1; m++) {
                    developResourceLink[m] = 0;
                }
                int k = 0;
                int l = 0;
                while (k <= visitorNumber - 1) {
                    while (l <= visitorNumber - 1) {
                        if (temResourceLink[l] != selectedLinkNumber - 1 + 1) {
                            developResourceLink[k] = temResourceLink[l];
                            l = l + 1;
                            break;
                        }
                        l = l + 1;
                    }
                    k = k + 1;
                }
                for (int m = 0; m <= visitorNumber - 1; m++) {
                    resourceLink[visitorLink[selectedLinkNumber - 1][deletePoints[j] - 1] - 1][m] = developResourceLink[m];
                }
            }
            int[] temVisitorLink = new int[vertexNumber];
            for (int l = 0; l <= vertexNumber - 1; l++) {
                temVisitorLink[l] = visitorLink[selectedLinkNumber - 1][l];
            }
            int[] developVisitorLink = new int[vertexNumber];
            for (int m = 0; m <= vertexNumber - 1; m++) {
                developVisitorLink[m] = 0;
            }
            int k = 0;
            int l = 0;
            while (k <= vertexNumber - 1) {
                while (l <= vertexNumber - 1) {
                    if (find1.LinearSearch(deletePoints, l + 1) == -1) {
                        developVisitorLink[k] = temVisitorLink[l];
                        l = l + 1;
                        break;
                    }
                    l = l + 1;
                }
                k = k + 1;
            }
            for (int m = 0; m <= vertexNumber - 1; m++) {
                visitorLink[selectedLinkNumber - 1][m] = developVisitorLink[m];
            }
        }
        int profitFlag = 0;
        for (int i = 0; i <= vertexNumber - 1; i++) {
            if (visitorLink[selectedLinkNumber - 1][i] != 0) {
                profitFlag = profitFlag + profit[(int) (visitorLink[selectedLinkNumber - 1][i]  - 1) / resourceNumberInEachVertex + 1];
            }
            else {
                break;
            }
        }
        collectedProfits[selectedLinkNumber - 1] = profitFlag;
    }
}
