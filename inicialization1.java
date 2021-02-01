import static java.lang.Math.min;
import static sun.swing.MenuItemLayoutHelper.max;

public class inicialization1 {
    public static int[][] inicialization(int visitorNumber, int vertexNumber, int[][] distance, int[] profit, int resourceNumberInEachVertex, int[] processTime, int[] visitorTimeUpperBound){
        int[][] visitorMatrix = new int[3][visitorNumber];
        int[][] resourceMatrix = new int[2][2*vertexNumber];
        int[][] visitorLink = new int[visitorNumber][vertexNumber];
        int[][] resourceLink = new int[2 * vertexNumber][visitorNumber];
        int[][] unvisitPoint = new int[visitorNumber][vertexNumber];
        int[][] unvisitPoints = new int[visitorNumber][vertexNumber];
        int totalDistance = 0;
        int visitorID = find1.LinearSearch(visitorMatrix[2], 0) + 1;
        int bestResource = 0;
        int bestVertex = 0;
        double bestFitness = 0;
        int Tbest = 0;
        int presentPoint;
        int neiborReward;
        int[] Tflag = new int[resourceNumberInEachVertex];
        int vertexToVisit;
        int T = 0;
        int lipTime = 0;


        for (int i = 0; i <= vertexNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++) {
                totalDistance = totalDistance + distance[i][j];
            }
        }

        float neiborDistance = totalDistance / ((vertexNumber + 2) << 1);

        for (int i = 0; i <= visitorNumber-1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){
                unvisitPoint[i][j] = j + 1;
            }
        }
        for (int i = 0; i <= visitorNumber-1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){
                unvisitPoints[i][j] = j + 1;
            }
        }

        for (int i = 0; i <= visitorNumber-1; i++){
            visitorMatrix[0][i] = i + 1;
            visitorMatrix[1][i] = 0;
            visitorMatrix[2][i] = 0;
        }
        for (int i = 0; i <= 2 * vertexNumber - 1; i++){
            resourceMatrix[0][i] = i + 1;
            resourceMatrix[1][i] = 0;
        }

        for (int i = 0; i <= visitorNumber-1; i++){
            for (int j = 0; j <= vertexNumber-1; j++){
                visitorLink[i][j] = 0;
            }
        }

        for (int i = 0; i <= 2 * vertexNumber-1; i++){
            for (int j = 0; j <= visitorNumber-1; j++){
                resourceLink[i][j] = 0;
            }
        }
        







        while (visitorID != 0){
            presentPoint = visitorMatrix[1][visitorID - 1];
            bestResource = 0;
            bestFitness = 0;
            Tbest = 0;
            lipTime = 0;
            for (int j = 0; j <= vertexNumber - 1; j++){
                vertexToVisit = unvisitPoint[visitorID - 1][j];
                if (vertexToVisit != 0){
                    neiborReward = 0;
                    for (int m = 0; m <= vertexNumber - 1; m++){
                        if (unvisitPoint[visitorID - 1][m] != 0 && unvisitPoint[visitorID - 1][m] != vertexToVisit){
                            if (distance[vertexToVisit - 1][unvisitPoint[visitorID - 1][m]] <= neiborDistance){
                                neiborReward = neiborReward + profit[unvisitPoint[visitorID - 1][m]];
                            }
                        }
                    }
                    for (int i = 0; i <= resourceNumberInEachVertex-1; i++){
                        Tflag[i] = 0;
                    }
                    for (int k = 1; k <= resourceNumberInEachVertex; k++){
                        if (resourceMatrix[1][resourceNumberInEachVertex * (vertexToVisit - 1) + k - 1] == 0){
                            Tflag[k - 1] = distance[presentPoint][vertexToVisit] + processTime[vertexToVisit];
                        }
                        else{
                            Tflag[k - 1] = max(distance[presentPoint][vertexToVisit], visitorMatrix[2][resourceMatrix[1][resourceNumberInEachVertex * (vertexToVisit - 1) + k - 1] - 1]) + processTime[vertexToVisit];

                        }
                    }
                    if (min(Tflag[0], Tflag[1]) + T + distance[vertexToVisit][0] <= visitorTimeUpperBound[visitorID - 1]){
                        if ((profit[vertexToVisit] + neiborReward) / ((min(Tflag[0], Tflag[1]) - processTime[vertexToVisit]) * 4.1 + processTime[vertexToVisit]) > bestFitness){
                            bestFitness = (profit[vertexToVisit] + neiborReward) / ((min(Tflag[0], Tflag[1]) - processTime[vertexToVisit]) * 4.1 + processTime[vertexToVisit]);
                            bestResource = resourceNumberInEachVertex * (vertexToVisit - 1) + find1.LinearSearch(Tflag, min(Tflag[0], Tflag[1])) + 1;
                            bestVertex = vertexToVisit;
                            Tbest = min(Tflag[0], Tflag[1]);
                        }
                    }
                    else{
                        unvisitPoint[visitorID - 1][vertexToVisit - 1] = 0;
                    }
                }
            }
            if (bestFitness != 0){
                for (int l = 0; l <= vertexNumber - 1; l++){
                    if (visitorLink[visitorID - 1][l] == 0){
                        visitorLink[visitorID - 1][l] = bestResource;
                        break;
                    }
                }
                for (int l = 0; l <= visitorNumber - 1; l++){
                    if (resourceLink[bestResource - 1][l] == 0){
                        resourceLink[bestResource - 1][l] = visitorID;
                        break;
                    }
                }
                visitorMatrix[1][visitorID - 1] = bestVertex;
                visitorMatrix[2][visitorID - 1] = Tbest;
                unvisitPoint[visitorID - 1][bestVertex - 1] = 0;
                unvisitPoints[visitorID - 1][bestVertex - 1] = 0;
                resourceMatrix[1][bestResource - 1] = visitorID;
            }
            else{
                visitorMatrix[2][visitorID - 1] = 100000;
            }
            lipTime = min1.min(visitorMatrix[2]);
            if (lipTime < 5000 && lipTime != 0){
                T = T + lipTime;
                for (int i = 0; i <= visitorNumber - 1; i++){
                    visitorMatrix[2][i] = visitorMatrix[2][i] - lipTime;
                }
            }
            visitorID = find1.LinearSearch(visitorMatrix[2], 0) + 1;
        }
        int[][] result = new int[visitorNumber + 2 * vertexNumber + visitorNumber][Math.max(vertexNumber, visitorNumber)];
        for (int i = 0; i <= visitorNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){
                result[i][j] = visitorLink[i][j];
            }
        }
        for (int i = visitorNumber; i <= visitorNumber + 2 * vertexNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){
                result[i][j] = resourceLink[i - visitorNumber][j];
            }
        }
        for (int i = visitorNumber + 2 * vertexNumber; i <= visitorNumber + 2 * vertexNumber + visitorNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){
                result[i][j] = unvisitPoints[i - visitorNumber - 2 * vertexNumber][j];
            }
        }
        return result;
    }
}
