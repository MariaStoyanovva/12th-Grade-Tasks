import java.util.*;

public class problem1 {
    private int N; // number of students
    private double[] results; // all student results
    private List<Double> validWorks; // only positive results

    public problem1(int N) {
        this.N = N;
        this.results = new double[N];
        this.validWorks = new ArrayList<>();
    }

    // 1)reads results and stores only valid works
    public void ReadPoints(Scanner sc) {
        for (int i=0; i<N; i++) {
            double point=sc.nextDouble();
            results[i]=point;
            if (point>0) {
                validWorks.add(point);
            }
        }
    }

    // 2)minimum difference between different points for valid works
    public double MinDpoints() {
        Set<Double> distinct = new TreeSet<>(validWorks); // auto-sorted, unique
        Double[] arr = distinct.toArray(new Double[0]);
        double minDiff = Double.MAX_VALUE;

        for (int i=1; i<arr.length; i++) {
            double diff = arr[i]-arr[i-1];
            if (diff<minDiff) {
                minDiff=diff;
            }
        }

        return Math.round(minDiff*1000.0)/1000.0; //round to 3 decimals
    }

    // 3)number of laureates
    public int Laureates() {
        Set<Double> distinct = new TreeSet<>(validWorks);
        List<Double> desc = new ArrayList<>(distinct);
        Collections.sort(desc, Collections.reverseOrder());

        if (desc.size()<3) {
            return 0;
        }

        double thirdHighest = desc.get(2);

        int count = 0;
        for (double v:validWorks) {
            if (v>=thirdHighest) {
                count++;
            }
        }
        return count;
    }

    // output results
    public void printResults() {
        System.out.println("valid works - " + validWorks.size());
        System.out.println("minimal difference - " + MinDpoints());
        System.out.println("laureates - " + Laureates());
    }

    // main method to test
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        problem1 sw = new problem1(N);
        sw.ReadPoints(sc);
        sw.printResults();
    }
}

