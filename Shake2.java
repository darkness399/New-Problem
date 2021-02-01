public class Shake2 {
    public static int shake2 (int[][] visitorLink, int visitorNumber, int vertexNumber, int[][] resourceLink, int[] visitorTimeUpperBound, int[] processTime, int resourceNumberInEachVertex, int[][] distance, int selectedLinkNumber, int[] profit, int[] goodLinkNumbers, double criticalValue, int[][] unvisitedPoints, int developedNumbers, int[] collectedProfits) {
        int flag = 0;
        flag = Upper_Bound_Change2.upperBoundChange2(selectedLinkNumber, visitorTimeUpperBound, visitorNumber, vertexNumber, resourceNumberInEachVertex, visitorLink, goodLinkNumbers, resourceLink, processTime, distance, profit, criticalValue, flag);
        if (flag == 1) {
            for (int i = 0; i <= visitorNumber - 1; i ++){
                if (goodLinkNumbers[i] == 0) {
                    goodLinkNumbers[i] = selectedLinkNumber;
                    developedNumbers = developedNumbers + 1;
                    break;
                }
            }
        }
        else {
            Random_Delete2.randomDelete2(visitorLink, vertexNumber, selectedLinkNumber, resourceNumberInEachVertex, unvisitedPoints, visitorNumber, resourceLink, collectedProfits, profit);
        }
        return developedNumbers;
    }
}
