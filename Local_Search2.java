public class Local_Search2 {
    public static int[] localSearch2 (int[][] visitorLink, int[][] resourceLink, int selectedLinkNumber, int resourceNumberInEachVertex, int[][] unvisitedPoints, int vertexNumber, int visitorNumber, int[] profit, int[] tabuList, int[] visitorTimeUpperBound, int[] goodLinkNumbers, int[][] distance, int[] processTime, double neiborDistance, int unImprovementNumber, int[] collectedProfits, double criticalValue, int developdeNumbers) {
        int[] result = new int[3];
        int[] temVisitorTimeUpperBound = new int[visitorNumber];
        for (int n = 0; n <= visitorNumber - 1; n ++) {
            temVisitorTimeUpperBound[n] = visitorTimeUpperBound[n];
        }
        for (int m = 0; m <= visitorNumber - 1; m++) {
            int bestUpperT = max1.max(temVisitorTimeUpperBound);
            selectedLinkNumber = find1.LinearSearch(temVisitorTimeUpperBound, bestUpperT) + 1;
            temVisitorTimeUpperBound[selectedLinkNumber - 1] = 0;
            tabuList = new int[2 * vertexNumber];
            Tabu_List_Development.tabuListDevelopment(tabuList, vertexNumber, visitorLink, selectedLinkNumber);
            int[] searchLink = DevelopSearchLink.developSearchLink(vertexNumber, profit);
            unImprovementNumber = 0;
            while (unImprovementNumber < 2 * vertexNumber) {
                int bestTimeUpperBound = 0;
                int[][] visitorLinkFlag1 = new int[visitorNumber][vertexNumber];
                int[][] unVisitedPointsFlag1 = new int[visitorNumber][vertexNumber];
                for (int i = 0; i <= visitorNumber - 1; i++) {
                    for (int j = 0; j <= vertexNumber - 1; j++) {
                        visitorLinkFlag1[i][j] = visitorLink[i][j];
                        unVisitedPointsFlag1[i][j] = unvisitedPoints[i][j];
                    }
                }
                int[][] resourceLinkFlag1 = new int[2 * vertexNumber][visitorNumber];
                for (int i = 0; i <= vertexNumber * 2 - 1; i++) {
                    for (int j = 0; j <= visitorNumber - 1; j++) {
                        resourceLinkFlag1[i][j] = resourceLink[i][j];
                    }
                }
                int collectedPoints1 = collectedProfits[selectedLinkNumber - 1];
                if (visitorLink[selectedLinkNumber - 1][vertexNumber - 1] != 0) {
                    break;
                }
                Insert_Point.insertPoint(visitorLink, resourceLink, selectedLinkNumber, unvisitedPoints, vertexNumber, visitorNumber, profit, resourceNumberInEachVertex, tabuList, collectedProfits, searchLink);
                Change_Position.changePosition(vertexNumber, profit, selectedLinkNumber, distance, visitorLink, processTime, resourceNumberInEachVertex, visitorNumber, resourceLink, goodLinkNumbers, visitorTimeUpperBound, neiborDistance);
                //bestTimeUpperBound = Delate_Point.delatePoint(bestTimeUpperBound, visitorNumber, visitorLink, selectedLinkNumber, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, profit, visitorTimeUpperBound, unvisitedPoints, neiborDistance, goodLinkNumbers);
                bestTimeUpperBound = Delete_Point2.deletePoint2(visitorTimeUpperBound, selectedLinkNumber, vertexNumber, visitorLink, visitorNumber, unvisitedPoints, resourceLink, resourceNumberInEachVertex, profit, processTime, distance, goodLinkNumbers, neiborDistance, collectedProfits);

                int collectedPoints = collectedProfits[selectedLinkNumber - 1];
                int[] lastUpperT = TimeCaculation.timeCaculation(visitorLink, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
                int lastTimeUpperBound = lastUpperT[selectedLinkNumber - 1];
                double proportion1 = (double) collectedPoints / (double) lastTimeUpperBound;
                double proportion2 = (double) collectedPoints1 / (double) bestTimeUpperBound;
                if (proportion1 <= proportion2 && collectedPoints <= collectedPoints1) {
                    for (int i = 0; i <= visitorNumber - 1; i++) {
                        for (int j = 0; j <= vertexNumber - 1; j++) {
                            visitorLink[i][j] = visitorLinkFlag1[i][j];
                            unvisitedPoints[i][j] = unVisitedPointsFlag1[i][j];
                        }
                    }
                    for (int i = 0; i <= vertexNumber * 2 - 1; i++) {
                        for (int j = 0; j <= visitorNumber - 1; j++) {
                            resourceLink[i][j] = resourceLinkFlag1[i][j];
                        }
                    }
                    collectedProfits[selectedLinkNumber - 1] = collectedPoints1;
                    unImprovementNumber = unImprovementNumber + 1;
                } else {
                    unImprovementNumber = 0;
                }
                int operationFlag = 0;
                if ((double) collectedProfits[selectedLinkNumber - 1] / (double) visitorTimeUpperBound[selectedLinkNumber - 1] >= criticalValue && find1.LinearSearch(goodLinkNumbers, selectedLinkNumber) == -1) {
                    for (int i = 0; i <= visitorNumber - 1; i++) {
                        if (goodLinkNumbers[i] == 0) {
                            goodLinkNumbers[i] = selectedLinkNumber;
                            developdeNumbers = developdeNumbers + 1;
                            operationFlag = 1;
                            break;
                        }
                    }
                }
                result[0] = unImprovementNumber;
                result[1] = operationFlag;
                result[2] = developdeNumbers;
            }
        }
        return result;
    }
}
