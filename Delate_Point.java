public class Delate_Point {
    public static int delatePoint(int bestTimeUpperBound, int visitorNumber, int[][] visitorLink, int selectedLinkNumber, int[][] resourceLink, int vertexNumber, int[] processTime, int resourceNumberInEachVertex, int[][] distance, int[] profit, int[] visitorTimeUpperBound, int[][] unvisitPoint, double neiborDistance, int[] goodLinkNumbers){
        double worstGoodness = 9999;
        int bestPointNumber = 0;
        double goodness = 0;
        int prePoint = 0;
        int successPoint = 0;
        int nowPoint = 0;
        int bestNowPoint = 0;
        int[] timeUpperBound = new int[visitorNumber];
        while (bestTimeUpperBound > visitorTimeUpperBound[selectedLinkNumber - 1]) {
            bestNowPoint = 0;
            worstGoodness = 9999;
            bestPointNumber = 0;
            for (int i = 0; i <= vertexNumber - 1; i++) {
                if (visitorLink[selectedLinkNumber - 1][i] != 0) {
                    nowPoint = (int) ((visitorLink[selectedLinkNumber - 1][i] - 1) / resourceNumberInEachVertex) + 1;
                    int neiborWerad = profit[nowPoint];
                    for (int j = 0; j <= vertexNumber - 1; j++){
                        if (j != i && visitorLink[selectedLinkNumber - 1][j] != 0 && distance[nowPoint][((visitorLink[selectedLinkNumber - 1][j] - 1) / resourceNumberInEachVertex) + 1] < neiborDistance){
                            neiborWerad = neiborWerad + profit[((visitorLink[selectedLinkNumber - 1][j] - 1) / resourceNumberInEachVertex) + 1];
                        }
                    }

                    if (i == 0) {
                        successPoint = (int) ((visitorLink[selectedLinkNumber - 1][i + 1] - 1) / resourceNumberInEachVertex) + 1;
                        goodness = (double) neiborWerad / (double) (distance[nowPoint][successPoint] + processTime[nowPoint] + distance[0][nowPoint]);
                    } else if (visitorLink[selectedLinkNumber - 1][i + 1] == 0) {
                        prePoint = (int) ((visitorLink[selectedLinkNumber - 1][i - 1] - 1) / resourceNumberInEachVertex) + 1;
                        goodness = (double) neiborWerad / (double) (distance[prePoint][nowPoint] + processTime[nowPoint] + distance[nowPoint][0]);
                    } else {
                        successPoint = (int) ((visitorLink[selectedLinkNumber - 1][i + 1] - 1) / resourceNumberInEachVertex) + 1;
                        prePoint = (int) ((visitorLink[selectedLinkNumber - 1][i - 1] - 1) / resourceNumberInEachVertex) + 1;
                        goodness = (double) neiborWerad / (double) (distance[prePoint][nowPoint] + distance[nowPoint][successPoint] + processTime[nowPoint]);
                    }
                } else {
                    break;
                }
                if (goodness <= worstGoodness) {
                    worstGoodness = goodness;
                    bestPointNumber = i;
                    bestNowPoint = nowPoint;
                }
            }
            int[] temVisitorLink = new int[vertexNumber];
            for (int i = 0; i <= vertexNumber - 1; i++){
                if (i < bestPointNumber){
                    temVisitorLink[i] = visitorLink[selectedLinkNumber - 1][i];
                }
                else if (i == vertexNumber - 1){
                    temVisitorLink[i] = 0;
                }
                else if (i > bestPointNumber){
                    temVisitorLink[i - 1] = visitorLink[selectedLinkNumber - 1][i];
                }
            }
            int[] temResourceLink = new int[visitorNumber];
            int resourceLinkPosition = find1.LinearSearch(resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1], selectedLinkNumber);
            for (int i = 0; i <= visitorNumber - 1; i++){
                if (i < resourceLinkPosition){
                    temResourceLink[i] = resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][i];
                }
                else if (i == visitorNumber - 1){
                    temResourceLink[i] = 0;
                }
                else if (i > resourceLinkPosition){
                    temResourceLink[i - 1] = resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][i];
                }
            }
            for (int i = 0; i <= visitorNumber - 1; i++) {
                resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][i] = temResourceLink[i];
            }

            for (int i = 0; i <= vertexNumber - 1; i++) {
                visitorLink[selectedLinkNumber - 1][i] = temVisitorLink[i];
            }
            bestTimeUpperBound = Change_Position.changePosition(vertexNumber, profit, selectedLinkNumber, distance, visitorLink, processTime, resourceNumberInEachVertex, visitorNumber, resourceLink, goodLinkNumbers, visitorTimeUpperBound, neiborDistance);
            unvisitPoint[selectedLinkNumber - 1][bestNowPoint - 1] = bestNowPoint;
        }
        return bestTimeUpperBound;
    }
}
