import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

public class testcode {
    public static void main(String[] args){
        Workbook book;
        Sheet sheet;
        int resourceNumberInEachVertex = 2;

        try {
            double goodLinkProportion = 0.9;
            book = Workbook.getWorkbook(new File("G:\\Matlab_R2020b\\Matlab_R2020b\\instance4.xls"));
            sheet = book.getSheet(0);
            Cell VertexNumber = sheet.getCell(0, 1);
            int vertexNumber = Integer.parseInt(VertexNumber.getContents());
            Cell VisitorNumber = sheet.getCell(0, 3);
            int visitorNumber = Integer.parseInt(VisitorNumber.getContents());
            int[] visitorTimeUpperBound = new int[visitorNumber];
            for (int i = 1; i<= visitorNumber; i++){
                Cell TimeUpperBound = sheet.getCell(i-1, 5);
                visitorTimeUpperBound[i-1] = Integer.parseInt(TimeUpperBound.getContents());
            }
            int[] profit = new int[vertexNumber+2];
            for (int i = 1; i <= vertexNumber+2; i++) {
                Cell Profit = sheet.getCell(i-1, 7);
                profit[i-1] = Integer.parseInt(Profit.getContents());
            }
            int[] processTime = new int[vertexNumber+2];
            for (int i = 1; i <= vertexNumber+2; i++) {
                Cell ProcessTime = sheet.getCell(i-1, 9);
                processTime[i-1] = Integer.parseInt(ProcessTime.getContents());
            }

            int[][] distance = new int[vertexNumber+2][vertexNumber+2];
            for (int i = 1; i < vertexNumber+2; i++) {
                for (int j = 1; j < vertexNumber + 2; j++) {
                    Cell Distance = sheet.getCell(i-1, j+10);
                    distance[i-1][j-1] = Integer.parseInt(Distance.getContents());
                }
            }
            int[] upperT = new int[visitorNumber];

            //long startMili=System.currentTimeMillis();
            //System.out.println("开始 "+startMili);
            int[][] result = new int[visitorNumber + 2 * vertexNumber][Math.max(vertexNumber, visitorNumber)];
            result = inicialization1.inicialization(visitorNumber, vertexNumber, distance, profit, resourceNumberInEachVertex, processTime, visitorTimeUpperBound);
            long endMili=System.currentTimeMillis();
            //System.out.println("结束 s"+endMili);
            //System.out.println("总耗时为："+(endMili-startMili)+"毫秒");

            int[][] visitorLink = new int[visitorNumber][vertexNumber];
            int[][] resourceLink = new int[2 * vertexNumber][visitorNumber];
            int[][] unvisitPoint = new int[visitorNumber][vertexNumber];
            for (int i = 0; i <= visitorNumber - 1; i++) {
                for (int j = 0; j <= vertexNumber - 1; j++) {
                    visitorLink[i][j] = result[i][j];
                }
            }
            for (int i = visitorNumber; i <= visitorNumber + 2 * vertexNumber - 1; i++){
                for (int j = 0; j <= vertexNumber - 1; j++) {
                    resourceLink[i - visitorNumber][j] = result[i][j];
                }
            }
            for (int i = visitorNumber + 2 * vertexNumber; i <= visitorNumber + 2 * vertexNumber + visitorNumber - 1; i++){
                for (int j = 0; j <= vertexNumber - 1; j++){
                    unvisitPoint[i - visitorNumber - 2 * vertexNumber][j] = result[i][j];
                }
            }

            //linkShowing1.linkShowing(vertexNumber, visitorNumber, visitorLink, resourceLink);
            //upperT = TimeCaculation.timeCaculation(visitorLink, resourceLink, vertexNumber, processTime, resourceNumberInEachVertex, distance, visitorNumber);
            //for (int i = 0; i <= visitorNumber - 1; i++){
                //System.out.println(upperT[i]);
            //}
            long[] finalResult = VNS.vns(visitorLink, resourceLink, goodLinkProportion, visitorNumber, vertexNumber, profit, resourceNumberInEachVertex, unvisitPoint, visitorTimeUpperBound, distance, processTime);
            System.out.println(finalResult[0]);
            System.out.println(finalResult[1]);
            /*
            int collectedProfit = 0;
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
                collectedProfit = profitFlag1 + collectedProfit;
            }
            System.out.println(collectedProfit);
            */
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }
    }
}
