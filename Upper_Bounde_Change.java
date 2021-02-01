import java.util.Random;

public class Upper_Bounde_Change {
    public static void upperBoundChange(int[][] visitorLink, int[] visitorTimeUpperBound, int visitorNumber, int[][] resourceLink, int vertexNumber, int[] processTime, int resourceNumberInEachVertex, int[][] distance){
        int[] upperT = TimeCaculation.timeCaculation(visitorLink, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
        int[] newUpperBound = new int[visitorNumber];
        int visitorUpperT;
        int[] temVisitorUpperBound = new int[visitorNumber];
        for (int i = 0; i <= visitorNumber - 1; i++) {
            temVisitorUpperBound[i] = visitorTimeUpperBound[i];
        }
        Random random = new Random();
        for (int i = 0; i <= visitorNumber - 1; i++) {
            visitorUpperT = max1.max(upperT);
            visitorTimeUpperBound = Sort.sort(temVisitorUpperBound);
            int selectedVisitor = find1.LinearSearch(upperT, visitorUpperT) + 1;
            int lowerBoundNumber = 0;
            for (int j = 0; j <= temVisitorUpperBound.length - 1; j++) {
                if (temVisitorUpperBound[j] >= visitorUpperT) {
                    lowerBoundNumber = j + 1;
                    break;
                }
            }
            int selectedPoints = Create_Random_Number.generateRandomByScope(lowerBoundNumber, visitorNumber);
            int selectedUpperT = temVisitorUpperBound[selectedPoints - 1];
            newUpperBound[selectedVisitor - 1] = selectedUpperT;
            upperT[selectedVisitor - 1] = 0;
            temVisitorUpperBound[selectedPoints - 1] = 0;
        }
        for (int i = 0; i <= visitorNumber - 1; i++) {
            visitorTimeUpperBound[i] = newUpperBound[i];
        }
    }
}
