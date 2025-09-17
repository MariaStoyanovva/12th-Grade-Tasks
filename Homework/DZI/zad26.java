import java.util.Scanner;
import java.util.Arrays;

public class zad26{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        int maxA=scanner.nextInt();
        for(int i=1;i<N;i++){
            int current=scanner.nextInt();
            if(current>maxA)maxA=current;
        }
        maxA+=1;
        System.out.println("Max Element = " + maxA);

        System.out.println("Max Digit = " + findMaxDigit(maxA));

        System.out.println("next Max Digit = " + findMaxDigit(maxA)+1);
    }

    public static int findMaxDigit(int number){
        int numberOfDigits=String.valueOf(number).length();
        int newNumber=0;
        int[] arrayNumber = new int[numberOfDigits];
        int[] newNumberArr = new int[numberOfDigits];

        for(int i=0;i<numberOfDigits;i++){   //make number into array
            arrayNumber[i]=number%10;
            number/=10;
        }

        Arrays.sort(arrayNumber); //sorts in ascending

        for(int i=0;i<numberOfDigits;i++){ //reverse arrayNumber so that it's in descending
            newNumberArr[i]=arrayNumber[numberOfDigits-1-i];
        }

        for(int i=0;i<numberOfDigits;i++){
            if(i!=0){
                newNumber*=10;
            }
            newNumber+=newNumberArr[i];
        }

        return newNumber;

    }
}
