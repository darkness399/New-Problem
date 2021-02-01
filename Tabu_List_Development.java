public class Tabu_List_Development {
    public static void tabuListDevelopment (int[] tabuList, int vertexNumber, int[][] visitorlink, int selectedLinkNumber) {
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
    }
}
