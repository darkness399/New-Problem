public class Circle_Judgement {
    public static void circleJudgement(int visitorNumber, int vertexNumber, int[] successFlagMatrix, int[][] visitorLink, int[][] resourceLink, int turnNumber){
        int maxNumber;
        if (turnNumber != 0) {
            maxNumber = 2 << (turnNumber - 1);
        }
        else {
            maxNumber = 1;
        }
        int flag = 0;
        int pointNumber = 0;
        for (int i = 0; i <= 2 * vertexNumber * visitorNumber - 1; i++){
            if (successFlagMatrix[i] == turnNumber){
                int thisVisitor = (int) (i + 1) / (2 * vertexNumber) + 1;
                int thisResource = (i + 1) % (2 * vertexNumber);
                if (thisResource == 0){
                    thisResource = 2 * vertexNumber;
                    thisVisitor = thisVisitor - 1;
                }
                int nextPointInVisitorLink = (thisVisitor - 1) * 2 * vertexNumber + visitorLink[thisVisitor - 1][find1.LinearSearch(visitorLink[thisVisitor - 1], thisResource) + 1];
                int nextPointInResourceLink = (resourceLink[thisResource - 1][find1.LinearSearch(resourceLink[thisResource - 1], thisVisitor) + 1] - 1) * 2 *vertexNumber + thisResource;
                if (resourceLink[thisResource - 1][find1.LinearSearch(resourceLink[thisResource - 1], thisVisitor) + 1] != 0) {
                    successFlagMatrix[nextPointInResourceLink - 1] = turnNumber + 1;
                }
                if (visitorLink[thisVisitor - 1][find1.LinearSearch(visitorLink[thisVisitor - 1], thisResource) + 1] != 0) {
                    successFlagMatrix[nextPointInVisitorLink - 1] = turnNumber + 1;
                }
                flag = 1;
                pointNumber = pointNumber + 1;
            }
            if (pointNumber == maxNumber){
                break;
            }
        }
        if (flag == 1){
            turnNumber = turnNumber + 1;
            circleJudgement(visitorNumber, vertexNumber, successFlagMatrix, visitorLink, resourceLink, turnNumber);
        }
    }
}
