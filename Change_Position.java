

public class Change_Position {
    public static int changePosition(int vertexNumber, int[] profit, int selectedLinkNumber, int[][] distance, int[][] visitorLink, int[] processTime, int resourceNumberInEachVertex, int visitorNumber, int[][] resourceLink, int[] goodLinkNumbers, int[] visitorTimeUpperBound, double neiborDistance){
        double worstGoodness = 9999;
        int bestPointNumber = 0;
        double goodness = 0;
        int prePoint = 0;
        int successPoint = 0;
        int nowPoint = 0;
        int bestResource = 0;
        int flag1 = 0;
        int flag = 0;
        int[] bestTimeUpperBound = new int[visitorNumber];
        int[] timeUpperBound = new int[visitorNumber];
        int[] bestVisitorLink = new int[vertexNumber];
        while (flag1 == 0) {
            worstGoodness = 9999;
            bestPointNumber = 0;
            bestResource = 0;
            flag1 = 1;
            int[][] visitorLinkFlag = new int[visitorNumber][vertexNumber];
            for (int i = 0; i <= visitorNumber - 1; i++) {
                for (int j = 0; j <= vertexNumber - 1; j++) {
                    visitorLinkFlag[i][j] = visitorLink[i][j];
                }
            }
            int[][] resourceLinkFlag = new int[2 * vertexNumber][visitorNumber];
            for (int i = 0; i <= vertexNumber * 2 - 1; i++) {
                for (int j = 0; j <= visitorNumber - 1; j++) {
                    resourceLinkFlag[i][j] = resourceLink[i][j];
                }
            }
            for (int i = 0; i <= vertexNumber - 1; i++) {
                bestVisitorLink[i] = visitorLink[selectedLinkNumber - 1][i];
            }
            bestTimeUpperBound = TimeCaculation.timeCaculation(visitorLink, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
            for (int i = 0; i <= vertexNumber - 1; i++) {
                if (visitorLink[selectedLinkNumber - 1][i] != 0) {
                    nowPoint = (int) ((visitorLink[selectedLinkNumber - 1][i] - 1) / resourceNumberInEachVertex) + 1;
                    int neiborWerad = profit[nowPoint];
                    for (int j = 0; j <= vertexNumber - 1; j++) {
                        if (j != i && visitorLink[selectedLinkNumber - 1][j] != 0 && distance[nowPoint][((visitorLink[selectedLinkNumber - 1][j] - 1) / resourceNumberInEachVertex) + 1] < neiborDistance) {
                            neiborWerad = neiborWerad + profit[((visitorLink[selectedLinkNumber - 1][j] - 1) / resourceNumberInEachVertex) + 1];
                        }
                    }
                    if (i == 0) {
                        successPoint = (int) ((visitorLink[selectedLinkNumber - 1][i + 1] - 1) / resourceNumberInEachVertex) + 1;
                        goodness = (double) neiborWerad / (double) (distance[nowPoint][successPoint] + processTime[nowPoint]);
                    } else if (i == vertexNumber - 1) {
                        prePoint = (int) ((visitorLink[selectedLinkNumber - 1][i - 1] - 1) / resourceNumberInEachVertex) + 1;
                        goodness = (double) neiborWerad / (double) (distance[prePoint][nowPoint] + processTime[nowPoint]);
                    } else {
                        successPoint = (int) ((visitorLink[selectedLinkNumber - 1][i + 1] - 1) / resourceNumberInEachVertex) + 1;
                        prePoint = (int) ((visitorLink[selectedLinkNumber - 1][i - 1] - 1) / resourceNumberInEachVertex) + 1;
                        goodness = (double) neiborWerad / (double) (distance[prePoint][nowPoint] + distance[nowPoint][successPoint] + processTime[nowPoint]);
                    }
                } else {
                    break;
                }
                if (goodness <= worstGoodness) {
                    worstGoodness = goodness;
                    bestPointNumber = i;
                    bestResource = visitorLink[selectedLinkNumber - 1][i];
                }
            }
            int[] bestResourceLink = new int[visitorNumber];
            for (int i = 0; i <= visitorNumber - 1; i++) {
                if (visitorLink[selectedLinkNumber - 1][bestPointNumber] != 0) {
                    bestResourceLink[i] = resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][i];
                }
            }
            int[] temVisitorLink = new int[vertexNumber];
            for (int i = 0; i <= vertexNumber - 1; i++) {
                if (i < bestPointNumber) {
                    temVisitorLink[i] = visitorLink[selectedLinkNumber - 1][i];
                } else if (i > bestPointNumber) {
                    temVisitorLink[i - 1] = visitorLink[selectedLinkNumber - 1][i];
                }
            }
            int[] temResourceLink = new int[visitorNumber];
            int resourceLinkPosition = 0;
            if (visitorLink[selectedLinkNumber - 1][bestPointNumber] != 0) {
                resourceLinkPosition = find1.LinearSearch(resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1], selectedLinkNumber);
                for (int i = 0; i <= visitorNumber - 1; i++) {
                    if (i < resourceLinkPosition) {
                        temResourceLink[i] = resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][i];
                    } else if (i > resourceLinkPosition) {
                        temResourceLink[i - 1] = resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][i];
                    }
                }
            }

            int[][] temVisitorMatrix = new int[visitorNumber][vertexNumber];

            for (int i = 0; i <= visitorNumber - 1; i++) {
                for (int j = 0; j <= vertexNumber - 1; j++) {
                    temVisitorMatrix[i][j] = visitorLink[i][j];
                }
            }
            int[][] temResourceMatrix = new int[2 * vertexNumber][visitorNumber];
            for (int i = 0; i <= 2 * vertexNumber - 1; i++) {
                for (int j = 0; j <= visitorNumber - 1; j++) {
                    temResourceMatrix[i][j] = resourceLink[i][j];
                }
            }
            for (int i = 0; i <= vertexNumber - 1; i++) {
                temVisitorMatrix[selectedLinkNumber - 1][i] = temVisitorLink[i];
            }
            if (visitorLink[selectedLinkNumber - 1][bestPointNumber] != 0) {
                for (int i = 0; i <= visitorNumber - 1; i++) {
                    temResourceMatrix[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][i] = temResourceLink[i];
                }
            }
            int[] temporaryVisitorLink = new int[vertexNumber];
            int[] temporaryResourceLink = new int[visitorNumber];
            for (int i = 0; i <= vertexNumber - 1; i++) {
                if (i == 0 || temVisitorLink[i - 1] != 0) {
                    temporaryVisitorLink[i] = bestResource;
                    for (int m = 0; m <= vertexNumber - 1; m++) {
                        if (m < i) {
                            temporaryVisitorLink[m] = temVisitorLink[m];
                        } else if (m > i) {
                            temporaryVisitorLink[m] = temVisitorLink[m - 1];
                        }
                    }
                    for (int j = 0; j <= vertexNumber - 1; j++) {
                        temVisitorMatrix[selectedLinkNumber - 1][j] = temporaryVisitorLink[j];
                    }
                    for (int j = 0; j <= visitorNumber - 1; j++) {

                        if (j == 0 || temResourceLink[j - 1] != 0) {
                            flag = 0;
                            temporaryResourceLink[j] = selectedLinkNumber;
                            for (int m = 0; m <= visitorNumber - 1; m++) {
                                if (m < j) {
                                    temporaryResourceLink[m] = temResourceLink[m];
                                } else if (m > j) {
                                    temporaryResourceLink[m] = temResourceLink[m - 1];
                                }
                            }
                            if (visitorLink[selectedLinkNumber - 1][bestPointNumber] != 0) {
                                for (int l = 0; l <= visitorNumber - 1; l++) {
                                    temResourceMatrix[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][l] = temporaryResourceLink[l];
                                }
                            }

                        if (j != 0 && temVisitorLink[i] != 0 && temResourceLink[j - 1] != 0) {
                            int[] successFlagMatrix1 = new int[2 * visitorNumber * vertexNumber];
                            for (int k = 0; k <= 2 * visitorNumber * vertexNumber - 1; k++) {
                                successFlagMatrix1[k] = 9999;
                            }
                            if (temVisitorLink[i] != 0) {
                                successFlagMatrix1[(selectedLinkNumber - 1) * 2 * vertexNumber + temVisitorLink[i] - 1] = 0;
                            }
                            int turnNumber = 0;
                            Circle_Judgement2.circleJudgement2(visitorNumber, vertexNumber, successFlagMatrix1, visitorLink, resourceLink, turnNumber);
                            if (visitorLink[selectedLinkNumber - 1][bestPointNumber] != 0 && successFlagMatrix1[(temResourceLink[j - 1] - 1) * 2 * vertexNumber + visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1] != 9999) {
                                continue;
                            }
                        }

                            if (i != 0 && temResourceLink[j] != 0 && temVisitorLink[i - 1] != 0) {
                                int[] successFlagMatrix2 = new int[2 * visitorNumber * vertexNumber];
                                for (int k = 0; k <= 2 * visitorNumber * vertexNumber - 1; k++) {
                                    successFlagMatrix2[k] = 9999;
                                }
                                if (visitorLink[selectedLinkNumber - 1][bestPointNumber] != 0) {
                                    successFlagMatrix2[(temResourceLink[j] - 1) * 2 * vertexNumber + visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1] = 0;
                                }
                                int turnNumber1 = 0;
                                Circle_Judgement2.circleJudgement2(visitorNumber, vertexNumber, successFlagMatrix2, visitorLink, resourceLink, turnNumber1);
                                if (successFlagMatrix2[(selectedLinkNumber - 1) * 2 * vertexNumber + temVisitorLink[i - 1] - 1] != 9999) {
                                    continue;
                                }
                            }

                                    timeUpperBound = TimeCaculation.timeCaculation(temVisitorMatrix, temResourceMatrix, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
                                    for (int n = 0; n <= visitorNumber - 1; n++) {
                                        if (goodLinkNumbers[n] != 0) {
                                            if (timeUpperBound[goodLinkNumbers[n] - 1] > visitorTimeUpperBound[goodLinkNumbers[n] - 1]) {
                                                flag = 1;
                                                break;
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                    if (flag == 1) {
                                        continue;
                                    }
                                    if (timeUpperBound[selectedLinkNumber - 1] < bestTimeUpperBound[selectedLinkNumber - 1]) {
                                        bestTimeUpperBound = timeUpperBound;

                                        for (int n = 0; n <= vertexNumber - 1; n++) {
                                            bestVisitorLink[n] = temporaryVisitorLink[n];
                                        }
                                        for (int n = 0; n <= visitorNumber - 1; n++) {
                                            bestResourceLink[n] = temporaryResourceLink[n];
                                        }
                                    }
                                }


                    }
                }
            }
            if (visitorLink[selectedLinkNumber - 1][bestPointNumber] != 0) {
                for (int n = 0; n <= visitorNumber - 1; n++) {
                    resourceLink[visitorLink[selectedLinkNumber - 1][bestPointNumber] - 1][n] = bestResourceLink[n];
                }
                for (int n = 0; n <= vertexNumber - 1; n++) {
                    visitorLink[selectedLinkNumber - 1][n] = bestVisitorLink[n];
                }
                for (int i = 0; i <= vertexNumber - 1; i++) {
                    if (visitorLinkFlag[selectedLinkNumber - 1][i] != visitorLink[selectedLinkNumber - 1][i]) {
                        flag1 = 0;
                        break;
                    }
                }
            }
        }

        return bestTimeUpperBound[selectedLinkNumber - 1];
    }
}
