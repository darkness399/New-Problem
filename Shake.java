public class Shake {
    public static void shake(int[][] visitorLink, int visitorNumber, int vertexNumber, int[][] resourceLink, int[] visitorTimeUpperBound, int[] processTime, int resourceNumberInEachVertex, int[][] distance, int selectedLinkNumber, int[][] unvisitedPoints, int unImprovementNumber, int[] profit, int[] goodLinkNumbers, double criticalValue, int[] collectedProfits, double neiborDistance){
        Random_Delate.randomDelate(visitorLink, visitorNumber, vertexNumber, resourceLink, selectedLinkNumber, unvisitedPoints, resourceNumberInEachVertex);
        for (int i = 0; i <= visitorNumber - 1; i++) {
            int selectedLinkNumberFlag = i + 1;
            Delete_Point2.deletePoint2(visitorTimeUpperBound, selectedLinkNumberFlag, vertexNumber, visitorLink, visitorNumber, unvisitedPoints, resourceLink, resourceNumberInEachVertex, profit, processTime, distance, goodLinkNumbers, neiborDistance, collectedProfits);
        }
        Upper_Bounde_Change.upperBoundChange(visitorLink, visitorTimeUpperBound, visitorNumber, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance);

    }
}
