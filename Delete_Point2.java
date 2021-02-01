public class Delete_Point2 {
    public static int deletePoint2(int[] visitorTimeUpperBound, int selectedLinkNumber, int vertexNumber, int[][] visitorLink, int visitorNumber, int[][] unvisitPoint, int[][] resourceLink, int resourceNumberInEachVertex, int[] profit, int[] processTime, int[][] distance, int[] goodLinkNumbers, double neiborDistance, int[] collectedProfits) {
        int[] bestTimeUpperBoundFlag = TimeCaculation.timeCaculation(visitorLink, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
        int bestTimeUpperBound = bestTimeUpperBoundFlag[selectedLinkNumber - 1];
        while (bestTimeUpperBound > visitorTimeUpperBound[selectedLinkNumber - 1]) {
            int pointNumber = 0;
            for (int i = 0; i <= vertexNumber - 1; i++){
                if (visitorLink[selectedLinkNumber - 1][i] != 0){
                    pointNumber = pointNumber + 1;
                }
                else {
                    break;
                }
            }
            int bestPointNumber = pointNumber - 1;
            int bestNowPoint = ((visitorLink[selectedLinkNumber - 1][pointNumber - 1] - 1) / resourceNumberInEachVertex) + 1;
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
            collectedProfits[selectedLinkNumber - 1] = collectedProfits[selectedLinkNumber - 1] - profit[bestNowPoint];
        }
        return bestTimeUpperBound;
    }
}
