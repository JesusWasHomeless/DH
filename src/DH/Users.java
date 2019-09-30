package DH;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Users {
    private Random random = new Random();
    private int p; //prime number
    private int g; //primitive root of p
   //  private int OpenKey;
    private BigInteger P;
    private BigInteger G;
    private BigInteger Sec;
    private BigInteger OpenKey;


    public void initPG(){
        p = getP();
        g = getG(p);
        P = BigInteger.valueOf(p);
        G = BigInteger.valueOf(g);
    }

    public int [] getPG(){
        return new int [] {p,g};
    }

    public void setParameters(int[] parameters) {
        p = parameters[0];
        P = BigInteger.valueOf(p);
        g = parameters[1];
        G = BigInteger.valueOf(g);
    }

    public void inSec(){
        Sec = BigInteger.valueOf(random.nextInt(p));
    }

    public int getSec(){
        return Sec.intValue();
    }

    public int openKeyCalc(){
        return G.modPow(Sec, P).intValue();
    }
    public void openKeySet(int key){
        OpenKey = BigInteger.valueOf(key);
    }

    public int getOK(){
        return OpenKey.intValue();
    }
    public int getSharedSecretKey(){
        return OpenKey.modPow(Sec,P).intValue();
    }



    //prime numbers randomizer
    private int getP(){
        List<Integer>PrimeList = new ArrayList<>();
        int n = 1000;
        boolean [] isPrime = new boolean[n];
        isPrime[0]=isPrime[1]=false; //0 i 1 ne prime

        for(int i = 2; i < n; i++){
            isPrime[i] = true; // Пусть все изначально простые
        }
//Если число простое, то любое произведение с ним даёт не простое число, такие отсеиваются
        for(int i = 2; i < isPrime.length; i++){
            if (isPrime[i]){
                for (int j = 2; j*i<isPrime.length; j++){
                    isPrime[i*j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                PrimeList.add(i);
            }
        }
        //System.out.println(PrimeList);
        return PrimeList.get(random.nextInt(PrimeList.size()));
    }

    private int getG(int p){
        List<Integer> ROOT = new ArrayList<>();
        for(int gInit = 2; gInit < 1000; gInit++)
            if (isRoot(gInit, p))
                ROOT.add(gInit);

            return ROOT.get(random.nextInt(ROOT.size()));
    }

    private boolean isRoot(int gInit, int p1){
        int  phi = p1-1;
        BigInteger PHI = BigInteger.valueOf(phi);
        //Факторизация числа
        List<Integer>F = new ArrayList<>(); //Лист простых множителей
        for(int i = 2; i< phi; i++){
            while (phi%i==0){
                F.add(i);
                phi/=i;
            }
        }
        if(phi>2)
            F.add(phi);

        BigInteger G = BigInteger.valueOf(gInit);
        BigInteger P1 = BigInteger.valueOf(p1);
        for (Integer a : F) {
            BigInteger Factor = BigInteger.valueOf(a);
            BigInteger Div = PHI.divide(Factor);
            // Если g^(phi/factor(i)) mod p = 1, то это не первообразный корень
            if (G.modPow(Div, P1).equals(BigInteger.ONE)) return false;
        }

        return true;
    }

}
