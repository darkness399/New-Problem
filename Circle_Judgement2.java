public class Circle_Judgement2 {
    public static void circleJudgement2 (int visitorNumber, int vertexNumber, int[] successFlagMatrix, int[][] visitorLink, int[][] resourceLink, int turnNumber) {
        int flag = 1;
        while (flag == 1) {
            int maxNumber;
            if (turnNumber != 0) {
                maxNumber = 2 << (turnNumber - 1);
            }
            else {
                maxNumber = 1;
            }
            int pointNumber = 0;
            flag = 0;
            for (int i = 0; i <= 2 * vertexNumber * visitorNumber - 1; i++){
                if (successFlagMatrix[i] == turnNumber){
                    int thisVisitor = (int) (i + 1) / (2 * vertexNumber) + 1;
                    int thisResource = (i + 1) % (2 * vertexNumber);
                    if (thisResource == 0){
                        thisResource = 2 * vertexNumber;
                        thisVisitor = thisVisitor - 1;
                    }
                    int nextResourcePosition = find1.LinearSearch(visitorLink[thisVisitor - 1], thisResource);
                    int nextPointInVisitorLink = 0;
                    if (nextResourcePosition < vertexNumber - 1) {
                        nextPointInVisitorLink = (thisVisitor - 1) * 2 * vertexNumber + visitorLink[thisVisitor - 1][nextResourcePosition + 1];
                    }
                    int nextPointInResourceLink = 0;
                    int nextVisitorPosition = find1.LinearSearch(resourceLink[thisResource - 1], thisVisitor);
                    if (nextVisitorPosition < visitorNumber - 1) {
                        nextPointInResourceLink = (resourceLink[thisResource - 1][nextVisitorPosition + 1] - 1) * 2 * vertexNumber + thisResource;
                    }

                    if (nextPointInResourceLink != 0 && resourceLink[thisResource - 1][find1.LinearSearch(resourceLink[thisResource - 1], thisVisitor) + 1] != 0 && successFlagMatrix[nextPointInResourceLink - 1] == 9999) {
                        successFlagMatrix[nextPointInResourceLink - 1] = turnNumber + 1;
                    }
                    if (nextPointInVisitorLink != 0 && visitorLink[thisVisitor - 1][find1.LinearSearch(visitorLink[thisVisitor - 1], thisResource) + 1] != 0 && successFlagMatrix[nextPointInVisitorLink - 1] == 9999) {
                        successFlagMatrix[nextPointInVisitorLink - 1] = turnNumber + 1;
                    }
                    flag = 1;
                    pointNumber = pointNumber + 1;
                }
                if (pointNumber == maxNumber){
                    break;
                }
            }
            turnNumber = turnNumber + 1;
        }
    }
}
