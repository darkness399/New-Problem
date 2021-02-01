public class Profit_Density_Caculation_And_Link_Selection {
    public static int[] profit_density_calculation_and_link_selection(int[][] visitorLink, int[][] resourceLink, int visitorNumber, int vertexNumber, int[] profit, int resourceNumberInEachVertex, double goodLinkProportion, int[] timeUpperBound, double criticalValue){
        int[] collectedProfit = new int[visitorNumber];
        int[] goodLinkNumbers = new int[visitorNumber];
        int criticalPoint = 0;
        int developedNumbers = 0;
        for (int i = 0; i <= visitorNumber - 1; i++){
            goodLinkNumbers[i] = 0;
        }
        int profitFlag = 0;
        for (int i = 0; i <= visitorNumber - 1; i++){
            profitFlag = 0;
            for (int j = 0; j <= vertexNumber - 1; j++) {
                if (visitorLink[i][j] != 0) {
                    profitFlag = profit[(int) ((visitorLink[i][j] - 1) / resourceNumberInEachVertex + 1)] + profitFlag;
                }
                else {
                    break;
                }
            }
            collectedProfit[i] = profitFlag;
        }
        double[] profitDensity = new double[visitorNumber];
        for (int i = 0; i <= visitorNumber - 1; i++){
            profitDensity[i] = ((double) collectedProfit[i]) / ((double) timeUpperBound[i]);
        }
        int selectedLinkNumber = 0;
        double[] sortedProfitDensity = new double[visitorNumber];
        for (int i = 0; i <= visitorNumber - 1; i++){
            sortedProfitDensity[i] = profitDensity[i];
        }
        Sort.sort(sortedProfitDensity);
        int goodLinkNumber = (int) (goodLinkProportion * visitorNumber);
        int badLinkNumber = visitorNumber - goodLinkNumber;
        if (criticalValue == 0) {
            criticalValue = sortedProfitDensity[badLinkNumber - 1];
            for (int i = 0; i <= visitorNumber - 1; i++) {
                if (sortedProfitDensity[i] >= criticalValue && i != 0){
                    criticalPoint = find1.LinearSearch(profitDensity, sortedProfitDensity[i - 1]) + 1;
                    break;
                }
            }
        }
        for (int i = 0; i <= visitorNumber - 1; i++) {
            if (profitDensity[i] > criticalValue) {
                for (int j = 0; j <= visitorNumber - 1; j++) {
                    if (goodLinkNumbers[j] == 0) {
                        goodLinkNumbers[j] = i + 1;
                        developedNumbers = developedNumbers + 1;
                        break;
                    }
                }
            }
        }
        /*
        for (int i = 0; i <= visitorNumber - 1; i++){
            if (sortedProfitDensity[i] >= criticalValue && i != 0){
                selectedLinkNumber = find1.LinearSearch(profitDensity, sortedProfitDensity[i - 1]) + 1;
                break;
            }
            else if (i == 0 && sortedProfitDensity[i] >= criticalValue){
                selectedLinkNumber = 0;
                break;
            }
            else if (i == visitorNumber - 1 && sortedProfitDensity[i] < criticalValue) {
                selectedLinkNumber = find1.LinearSearch(profitDensity, sortedProfitDensity[visitorNumber - 1]) + 1;
                break;
            }
        }
        */
        if (sortedProfitDensity[0] >= criticalValue) {
            selectedLinkNumber = 0;
        }
        else {
            selectedLinkNumber = find1.LinearSearch(profitDensity, sortedProfitDensity[0]) + 1;
        }
            int[] result = new int[2 * visitorNumber + 4];
            result[0] = selectedLinkNumber;
            result[1] = criticalPoint;
            if (criticalPoint != 0) {
                result[2] = collectedProfit[criticalPoint - 1];
            }
            else {
                result[2] = 0;
            }
            result[3] = developedNumbers;
            for (int i = 4; i <= visitorNumber + 3; i++) {
                result[i] = goodLinkNumbers[i - 4];
            }
            for (int i = visitorNumber + 4; i <= 2 * visitorNumber + 3; i++) {
                result[i] = collectedProfit[i - visitorNumber - 4];
            }
        return result;
    }
}
