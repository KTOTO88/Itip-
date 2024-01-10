public class Main{
    public static void main(String[] args){
        for (int current_number = 100; current_number > 1; current_number--){
            boolean our_number = IsPrime(current_number);

            if(our_number)
                System.out.println(current_number);
        }
    }

    public static boolean IsPrime(int number){
        boolean bool = true;

        for(int number_div = number - 1; number_div > 1; number_div--){
            if(number % number_div == 0){
                bool = false;
                break;
            }
        }
        return bool;
    }
}




