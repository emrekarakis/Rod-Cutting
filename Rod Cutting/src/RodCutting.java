/**
 * Created by emrekarakis on 15/04/16.
 */
public class RodCutting {

public static void main(String[] args ){
    int[] length = new int[]{0,1,2,3,4,5,6,7,8,9,10};
    int[] price = new int[]{0,1,5,8,9,10,17,17,20,24,30};
    int result;

    //result = rodCutting(price,length.length-1); // calıştı
    //result = memoizedCutRod(price,length.length-1); // calıstı
    //result = bottomUpCutRod(price,10);//calıstı
    result = extendedButtomUpCutRod(price,10);//calıstı
    System.out.println("The maximum revenue "+result);

}

 public static int extendedButtomUpCutRod(int[] price, int length){
     int q;
     int[] revenue = new int[length+1];
     int[] solutions = new int[length+1];
     revenue[0]=0;
     for(int i=1; i <= length ; i++){
         q=Integer.MIN_VALUE;
         for(int j=0; j <= i;j++){
             if(q<price[i]+revenue[i-j]){
                 q=Math.max(q,price[j]+revenue[i-j]);
                 solutions[i]=j;
             }
             revenue[i]=q;
         }
     }
     return revenue[length];
 }


    public static int bottomUpCutRod(int price[],int length){
        int q;
        int[] revenue  = new int[length+1];
        revenue[0]=0;
        for(int i=1; i <= length ; i++){
            q=Integer.MIN_VALUE;
            for(int j=0; j <= i;j++){
                q=Math.max(q,price[j]+revenue[i-j]);
                revenue[i]=q;
            }

        }
        return revenue[length];
    }

    public static int memoizedCutRod(int price[],int length)
    {
        int[] revenue = new  int[length+1];
        for(int i = 0 ; i <= length ;i++){

            revenue[i]=Integer.MIN_VALUE;

        }
        return  memoizedCutRodAux(price,length,revenue);


    }

    public static int memoizedCutRodAux(int price[],int length,int[] revenue){
        int q;
        if(revenue[length]>=0){
            return revenue[length];
        }
        if (length==0) {
            q = 0;
        }else{
            q = Integer.MIN_VALUE;
            for(int i = 1; i <= length; i++){
                q = Math.max(q,price[i]+memoizedCutRodAux(price,length-i,revenue));
                System.out.println("Revenue => " + revenue[i]);
            }
        }
        revenue[length]=q;

        return q;
    }

    ////Recursive Top-Down Implementation
    public static int rodCutting(int[] price, int length) {

        if (length == 0) {
            return 0;
        }

        int q = Integer.MIN_VALUE;

        for (int i = 1; i <=length; i++){
            q = Math.max(q, price[i] + rodCutting(price, length - i));
        }
        return q;
    }

}



