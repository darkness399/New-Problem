

public class TimeCaculation {
    public static int[] timeCaculation(int[][] visitorLink, int[][] resourceLink, int vertexNumber, int[] processTime, int resourceNumberInEachVertex, int[][] distance, int visitorNumber){
        int[] upperT = new int[visitorNumber];
        int[][] newVisitorLink = new int[visitorNumber][vertexNumber];
        for (int i = 0; i <= visitorNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){
                newVisitorLink[i][j] = visitorLink[i][j];
            }
        }
        int[][] newResourceLink = new int[2 * vertexNumber][visitorNumber];
        for (int i = 0; i <= 2 * vertexNumber - 1; i++){
            for (int j = 0; j <= visitorNumber - 1; j++){
                newResourceLink[i][j] = resourceLink[i][j];
            }
        }
        int[][] visitorMatrix = new int[3][visitorNumber];
        int[][] resourceMatrix = new int[3][2 * vertexNumber];
        int T = 0;
        int visitorToMove = find1.LinearSearch(visitorMatrix[2],0) +1;
        int nextResourceToMove = 0;
        int unlockFlag;
        int timeLip;
        for (int i = 0; i <= visitorNumber - 1; i++){
            visitorMatrix[0][i] = i + 1;
            visitorMatrix[1][i] = 0;
            visitorMatrix[2][i] = 0;
        }
        for (int i = 0; i <= 2 * vertexNumber - 1; i++){
            resourceMatrix[0][i] = i + 1;
            resourceMatrix[1][i] = 0;
            resourceMatrix[2][i] = 0;
        }
        for (int i = 0; i <= 2 * vertexNumber - 1; i++){
            for (int j = 0; j <= visitorNumber - 1; j++){
                if (resourceLink[i][j] != 0){
                    resourceMatrix[1][i] = resourceLink[i][j];
                    break;
                }
            }
        }
        for (int i = 0; i <= visitorNumber - 1; i++){
            upperT[i] = 0;
        }
        while (visitorToMove != 0){
            if (visitorMatrix[2][visitorToMove - 1] ==0) {
                for (int j = 0; j <= vertexNumber - 1; j++) {
                    nextResourceToMove = 0;
                    if (visitorLink[visitorToMove - 1][j] != 0) {
                        nextResourceToMove = visitorLink[visitorToMove - 1][j];
                        break;
                    }
                }
                if (nextResourceToMove == 0) {
                    upperT[visitorToMove - 1] = T + distance[(int) ((visitorMatrix[1][visitorToMove - 1] - 1) / resourceNumberInEachVertex + 1)][0];
                    visitorMatrix[2][visitorToMove - 1] = 10000;
                    unlockFlag = find1.LinearSearch(resourceMatrix[1], visitorToMove) + 1;
                    if (unlockFlag != 0) {
                        resourceMatrix[1][unlockFlag - 1] = resourceLink[unlockFlag - 1][find1.LinearSearch(resourceLink[unlockFlag - 1], visitorToMove) + 1];
                        resourceLink[unlockFlag - 1][find1.LinearSearch(resourceLink[unlockFlag - 1], visitorToMove)] = 0;
                    }

                    continue;
                }
                if (visitorMatrix[1][visitorToMove - 1] != nextResourceToMove) {
                    if (visitorMatrix[1][visitorToMove - 1] != 0) {
                        visitorMatrix[2][visitorToMove - 1] = distance[(int) ((visitorMatrix[1][visitorToMove - 1] - 1) / resourceNumberInEachVertex) + 1][(int) ((nextResourceToMove - 1) / resourceNumberInEachVertex) + 1];

                    } else {
                        visitorMatrix[2][visitorToMove - 1] = distance[0][(int) ((nextResourceToMove - 1) / resourceNumberInEachVertex) + 1];

                    }
                    if (visitorMatrix[1][visitorToMove - 1] != 0) {
                        for (int j = 0; j <= visitorNumber - 1; j++) {
                            if (resourceLink[visitorMatrix[1][visitorToMove - 1] - 1][j] != 0) {
                                resourceLink[visitorMatrix[1][visitorToMove - 1] - 1][j] = 0;
                                if (j + 1 != visitorNumber) {
                                    resourceMatrix[1][visitorMatrix[1][visitorToMove - 1] - 1] = resourceLink[visitorMatrix[1][visitorToMove - 1] - 1][j + 1];
                                    break;
                                }
                            }
                        }
                    }
                    visitorMatrix[1][visitorToMove - 1] = nextResourceToMove;


                } else if (resourceMatrix[1][nextResourceToMove - 1] != visitorToMove && resourceMatrix[1][nextResourceToMove - 1] != 0) {
                    if (visitorMatrix[2][resourceMatrix[1][nextResourceToMove - 1] - 1] != 0) {
                        visitorMatrix[2][visitorToMove - 1] = visitorMatrix[2][resourceMatrix[1][nextResourceToMove - 1] - 1];
                    } else {
                        visitorToMove = resourceMatrix[1][nextResourceToMove - 1];
                        continue;
                    }
                } else {
                    visitorMatrix[2][visitorToMove - 1] = processTime[(int) ((nextResourceToMove - 1) / resourceNumberInEachVertex) + 1];
                    visitorLink[visitorToMove - 1][find1.LinearSearch(visitorLink[visitorToMove - 1], nextResourceToMove)] = 0;
                }
            }
            if (min1.min(visitorMatrix[2]) < 5000){
                timeLip = min1.min(visitorMatrix[2]);
                for (int i = 0; i <= visitorNumber - 1; i++){
                    visitorMatrix[2][i] = visitorMatrix[2][i] - timeLip;
                }
                T = T + timeLip;
            }
            visitorToMove = find1.LinearSearch(visitorMatrix[2],0) +1;
        }
        for (int i = 0; i <= visitorNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++){
                visitorLink[i][j] = newVisitorLink[i][j];
            }
        }
        for (int i = 0; i <= 2 * vertexNumber - 1; i++){
            for (int j = 0; j <= visitorNumber - 1; j++){
                resourceLink[i][j] = newResourceLink[i][j];
            }
        }
        return upperT;
    }
}
