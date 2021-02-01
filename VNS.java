public class VNS {
    public static long[] vns(int[][] visitorlink, int[][] resourceLink, double goodLinkProportion, int visitorNumber, int vertexNumber, int[] profit, int resourceNumberInEachVertex, int[][] unvisitedPoints, int[] visitorTimeUpperBound, int[][] distance, int[] processTime) {
        long startMili=System.currentTimeMillis();
        long endMili= 0;
        double criticalValue = 0;
        int flag = 0;
        int[] result;
        int lastSelectedLinkNumber = 0;
        int unImprovementNumber = 0;
        int totalDistance = 0;
        int developdeNumbers = 0;
        int[] collectedProfits = new int[visitorNumber];
        for (int i = 0; i <= vertexNumber - 1; i++){
            for (int j = 0; j <= vertexNumber - 1; j++) {
                totalDistance = totalDistance + distance[i][j];
            }
        }

        double neiborDistance = totalDistance / Math.pow((vertexNumber + 2), 2);

        result = Profit_Density_Caculation_And_Link_Selection.profit_density_calculation_and_link_selection(visitorlink, resourceLink, visitorNumber, vertexNumber, profit, resourceNumberInEachVertex, goodLinkProportion, visitorTimeUpperBound, criticalValue);
        int[] searchLink = DevelopSearchLink.developSearchLink(vertexNumber, profit);
        int selectedLinkNumber = result[0];
        int profitFlag = result[2];
        developdeNumbers = result[3];
        for (int i = visitorNumber + 4; i <= 2 * visitorNumber + 3; i++) {
            collectedProfits[i - visitorNumber - 4] = result[i];
        }
        criticalValue = ((double) profitFlag) / ((double) visitorTimeUpperBound[result[1] - 1]);
        int[] goodLinkNumbers = new int[visitorNumber];
        for (int i = 4; i <= visitorNumber + 3; i++){
            goodLinkNumbers[i - 4] = result[i];
        }
        selectedLinkNumber = Link_Selection.linkSelection2(goodLinkNumbers, visitorNumber, lastSelectedLinkNumber, developdeNumbers, visitorlink, profit, vertexNumber, resourceNumberInEachVertex, visitorTimeUpperBound);
        searchLink = DevelopSearchLink.developSearchLink(vertexNumber, profit);
        lastSelectedLinkNumber = selectedLinkNumber;

        int[] tabuList = new int[2 * vertexNumber];
        for (int i = 0; i <= 2 * vertexNumber - 1; i++){
            tabuList[i] = 0;
        }
        for (int i = 0; i <= vertexNumber - 1; i++){
            if (visitorlink[selectedLinkNumber - 1][i] != 0){
                for (int j = 0; j <= 2 * vertexNumber - 1; j++){
                    if (tabuList[j] == 0){
                        tabuList[j] = visitorlink[selectedLinkNumber - 1][i];
                        break;
                    }
                }
            }
            else{
                break;
            }
        }

        int n = 0;
        while (endMili - startMili <= 600000 && selectedLinkNumber != 0) {
            int[] result1 = Local_Search.local_search(visitorlink, resourceLink, selectedLinkNumber, resourceNumberInEachVertex, unvisitedPoints, vertexNumber, visitorNumber, profit, tabuList, visitorTimeUpperBound, goodLinkNumbers, distance, processTime, neiborDistance, unImprovementNumber, collectedProfits, criticalValue, developdeNumbers, searchLink);
            unImprovementNumber = result1[0];
            int operationFlag = result1[1];
            developdeNumbers = result1[2];
            n = n + 1;

            int[] upperT = TimeCaculation.timeCaculation(visitorlink, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
            int flagT = max1.max(upperT);

            if (n % (10 * vertexNumber) == 0) {
                Shake.shake(visitorlink, visitorNumber, vertexNumber, resourceLink, visitorTimeUpperBound, processTime, resourceNumberInEachVertex, distance, selectedLinkNumber, unvisitedPoints, unImprovementNumber, profit, goodLinkNumbers, criticalValue, collectedProfits, neiborDistance);
                unImprovementNumber = 0;

                result = Profit_Density_Caculation_And_Link_Selection.profit_density_calculation_and_link_selection(visitorlink, resourceLink, visitorNumber, vertexNumber, profit, resourceNumberInEachVertex, goodLinkProportion, visitorTimeUpperBound, criticalValue);
                searchLink = DevelopSearchLink.developSearchLink(vertexNumber, profit);
                profitFlag = result[2];
                developdeNumbers = result[3];
                for (int i = visitorNumber + 4; i <= 2 * visitorNumber + 3; i++) {
                    collectedProfits[i - visitorNumber - 4] = result[i];
                }
                goodLinkNumbers = new int[visitorNumber];
                for (int i = 4; i <= visitorNumber + 3; i++) {
                    goodLinkNumbers[i - 4] = result[i];
                }

                result1 = Local_Search2.localSearch2(visitorlink, resourceLink, selectedLinkNumber, resourceNumberInEachVertex, unvisitedPoints, vertexNumber, visitorNumber, profit, tabuList, visitorTimeUpperBound, goodLinkNumbers, distance, processTime, neiborDistance, unImprovementNumber, collectedProfits, criticalValue, developdeNumbers);
                developdeNumbers = result1[2];


                selectedLinkNumber = Link_Selection.linkSelection2(goodLinkNumbers, visitorNumber, lastSelectedLinkNumber, developdeNumbers, visitorlink, profit, vertexNumber, resourceNumberInEachVertex, visitorTimeUpperBound);
                searchLink = DevelopSearchLink.developSearchLink(vertexNumber, profit);
                lastSelectedLinkNumber = selectedLinkNumber;

                tabuList = new int[2 * vertexNumber];
                if (selectedLinkNumber == 0) {
                    break;
                }
                Tabu_List_Development.tabuListDevelopment(tabuList, vertexNumber, visitorlink, selectedLinkNumber);

            }
            else if (operationFlag == 1){
                endMili = System.currentTimeMillis();
                selectedLinkNumber = Link_Selection.linkSelection2(goodLinkNumbers, visitorNumber, lastSelectedLinkNumber, developdeNumbers, visitorlink, profit, vertexNumber, resourceNumberInEachVertex, visitorTimeUpperBound);
                searchLink = DevelopSearchLink.developSearchLink(vertexNumber, profit);
                if (selectedLinkNumber == 0){
                    break;
                }
                unImprovementNumber = 0;
                tabuList = new int[2 * vertexNumber];
                for (int i = 0; i <= 2 * vertexNumber - 1; i++) {
                    tabuList[i] = 0;
                }
                for (int i = 0; i <= vertexNumber - 1; i++) {
                    if (visitorlink[selectedLinkNumber - 1][i] != 0) {
                        for (int j = 0; j <= 2 * vertexNumber - 1; j++) {
                            if (tabuList[j] == 0) {
                                tabuList[j] = visitorlink[selectedLinkNumber - 1][i];
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
            else if (unImprovementNumber == 2 * vertexNumber) {
                developdeNumbers = Shake2.shake2(visitorlink, visitorNumber, vertexNumber, resourceLink, visitorTimeUpperBound, processTime, resourceNumberInEachVertex, distance, selectedLinkNumber, profit, goodLinkNumbers, criticalValue, unvisitedPoints, developdeNumbers, collectedProfits);
                endMili = System.currentTimeMillis();
                selectedLinkNumber = Link_Selection.linkSelection2(goodLinkNumbers, visitorNumber, lastSelectedLinkNumber, developdeNumbers, visitorlink, profit, vertexNumber, resourceNumberInEachVertex, visitorTimeUpperBound);
                searchLink = DevelopSearchLink.developSearchLink(vertexNumber, profit);
                if (selectedLinkNumber == 0){
                    break;
                }
                unImprovementNumber = 0;
                tabuList = new int[2 * vertexNumber];
                for (int i = 0; i <= 2 * vertexNumber - 1; i++) {
                    tabuList[i] = 0;
                }
                for (int i = 0; i <= vertexNumber - 1; i++) {
                    if (visitorlink[selectedLinkNumber - 1][i] != 0) {
                        for (int j = 0; j <= 2 * vertexNumber - 1; j++) {
                            if (tabuList[j] == 0) {
                                tabuList[j] = visitorlink[selectedLinkNumber - 1][i];
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
            lastSelectedLinkNumber = selectedLinkNumber;

        }


        int collectedProfit = 0;
        int profitFlag1 = 0;
        for (int i = 0; i <= visitorNumber - 1; i++){
            profitFlag1 = 0;
            for (int j = 0; j <= vertexNumber - 1; j++) {
                if (visitorlink[i][j] != 0) {
                    profitFlag1 = profit[(int) ((visitorlink[i][j] - 1) / resourceNumberInEachVertex + 1)] + profitFlag1;
                }
                else {
                    break;
                }
            }
            collectedProfit = profitFlag1 + collectedProfit;
        }
        long[] finalResult = new long[2];
        finalResult[0] = collectedProfit;
        finalResult[1] = endMili - startMili;
        return finalResult;
    }

}
