public class Upper_Bound_Change2 {
    public static int upperBoundChange2 (int selectedLinkNumber, int[] visitorTimeUpperBound, int visitorNumber, int vertexNumber, int resourceNumberInEachVertex, int[][] visitorLink, int[] goodLinkNumbers, int[][] resourceLink, int[] processTime, int[][] distance, int[] profit, double criticalValue, int flag) {
        int[] upperT = new int[visitorNumber];
        int bestPosition = 0;
        int bestTimeUpperBound = visitorTimeUpperBound[selectedLinkNumber - 1];
        upperT = TimeCaculation.timeCaculation(visitorLink, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
        for (int i = 0; i <= visitorNumber - 1; i++) {
            if (i != selectedLinkNumber - 1 && find1.LinearSearch(goodLinkNumbers, i + 1) == -1 && visitorTimeUpperBound[i] >= upperT[selectedLinkNumber - 1] && visitorTimeUpperBound[i] < bestTimeUpperBound && visitorTimeUpperBound[selectedLinkNumber - 1] >= upperT[i]) {
                bestTimeUpperBound = visitorTimeUpperBound[i];
                bestPosition = i + 1;
            }
        }
        if (bestPosition != 0) {
            int collectedPoint = 0;
            for (int i = 0; i <= vertexNumber - 1; i++) {
                if (visitorLink[selectedLinkNumber - 1][i] != 0) {
                    collectedPoint = profit[(int) ((visitorLink[selectedLinkNumber - 1][i] - 1) / resourceNumberInEachVertex + 1)] + collectedPoint;
                } else {
                    break;
                }
            }
            if ((double) collectedPoint / (double) visitorTimeUpperBound[bestPosition - 1] >= criticalValue) {
                int temTimeUpperBound = visitorTimeUpperBound[bestPosition - 1];
                visitorTimeUpperBound[bestPosition - 1] = visitorTimeUpperBound[selectedLinkNumber - 1];
                visitorTimeUpperBound[selectedLinkNumber - 1] = temTimeUpperBound;
                flag = 1;
            }
        }
        return flag;
    }
}
