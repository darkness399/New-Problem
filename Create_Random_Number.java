import java.util.Random;

public class Create_Random_Number {
    public static int generateRandomByScope(int small,int bignum){
        int num=-1;
        Random random = new Random();
        num = random.nextInt(bignum) % (bignum - small + 1) + small;  //产生幸运数
        return num;
    }
}
