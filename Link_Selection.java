public class Link_Selection {
    public static int linkSelection2 (int[] goodLinkNumbers, int visitorNumber, int lastSelectedLinkNumber, int developedNumbers, int[][] visitorLink, int[] profit, int vertexNumber, int resourceNumberInEachVertex, int[] visitorTimeUpperBound) {
        int flag = 0;
        int selectedLinkNumber = 0;
        /*
        if (developedNumbers < visitorNumber - 1) {
            while (flag == 0) {
                selectedLinkNumber = Create_Random_Number.generateRandomByScope(1, visitorNumber);
                if (selectedLinkNumber != lastSelectedLinkNumber && find1.LinearSearch(goodLinkNumbers, selectedLinkNumber) == -1) {
                    flag = 1;
                }
            }
        }
        */
        int[] linkProfit = new int[visitorNumber];
        int profitFlag1 = 0;
        for (int i = 0; i <= visitorNumber - 1; i++){
            profitFlag1 = 0;
            for (int j = 0; j <= vertexNumber - 1; j++) {
                if (visitorLink[i][j] != 0) {
                    profitFlag1 = profit[(int) ((visitorLink[i][j] - 1) / resourceNumberInEachVertex + 1)] + profitFlag1;
                }
                else {
                    break;
                }
            }
            linkProfit[i] = profitFlag1;
        }

        if (developedNumbers < visitorNumber - 1) {
            double lowestProfitDensity = 100;
            for (int i = 0; i <= visitorNumber - 1; i++) {
                if ((double) linkProfit[i] / (double) visitorTimeUpperBound[i] < lowestProfitDensity) {
                    selectedLinkNumber = i + 1;
                    lowestProfitDensity = (double) linkProfit[i] / (double) visitorTimeUpperBound[i];
                }
            }
        }
        else if (find1.LinearSearch(goodLinkNumbers, lastSelectedLinkNumber) != -1) {
            for (int i = 0; i <= visitorNumber - 1; i ++) {
                if (find1.LinearSearch(goodLinkNumbers, i + 1) == -1) {
                    selectedLinkNumber = i + 1;
                }
            }
        }
        else {
            selectedLinkNumber = lastSelectedLinkNumber;
        }
        return selectedLinkNumber;
    }
}
