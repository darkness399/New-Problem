

public class Insert_Point {
    public static void insertPoint(int[][] visitorLink, int[][] resourceLink, int selectedLinkNumber, int[][] unvisitedPoints, int vertexNumber, int visitorNumber, int[] profit, int resourceNumberInEachVertex, int[] tabuList, int[] collectedProfits, int[] searchLink){
        int bestResource = 0;
        int bestVertex = 0;


        for (int i = 0; i <= vertexNumber - 1; i++){
            for (int k = 0; k <= resourceNumberInEachVertex - 1; k++){
                if (find1.LinearSearch(tabuList, (searchLink[i] - 1) * resourceNumberInEachVertex + k + 1) == -1 && unvisitedPoints[selectedLinkNumber - 1][searchLink[i] - 1] != 0){
                bestResource = (searchLink[i] - 1) * resourceNumberInEachVertex + k + 1;
                bestVertex = (searchLink[i]);
                }
            }
        }

        if (bestResource != 0) {
            for (int i = 0; i <= vertexNumber - 1; i++) {
                if (visitorLink[selectedLinkNumber - 1][i] == 0) {
                    visitorLink[selectedLinkNumber - 1][i] = bestResource;
                    collectedProfits[selectedLinkNumber - 1] = collectedProfits[selectedLinkNumber - 1] + profit[bestVertex];
                    break;
                }
            }
            for (int i = 0; i <= visitorNumber - 1; i++) {
                if (resourceLink[bestResource - 1][i] == 0) {
                    resourceLink[bestResource - 1][i] = selectedLinkNumber;
                    break;
                }
            }
            for (int i = 0; i <= 2 * vertexNumber - 1; i++) {
                if (tabuList[i] == 0) {
                    tabuList[i] = bestResource;
                    break;
                }
            }
            unvisitedPoints[selectedLinkNumber - 1][bestVertex - 1] = 0;
        }
    }
}
