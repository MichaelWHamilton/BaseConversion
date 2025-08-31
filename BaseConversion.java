import java.util.Scanner;

public class BaseConversion 
{
    public static void main(String[] args) 
    {
        //convertDecimalToBase(16, 100);
        //convertBaseToDecimal(2, 100);

        System.out.println("Assignment 1 Number converter:" + "/n");
        String convertFrom;
        boolean loop = true;
        Scanner keyboard = new Scanner(System.in);
        //1 ask for type of # DEC BIN HEX FLOAT
        while(loop)
        {
            try 
            {
                System.out.println("Pick what number and base you wish to convert!");
                
                System.out.print("Type your starting number: ");
                int number = keyboard.nextInt();
                System.out.println("1. Decimal\n2. Binary\n3. Hexadecimal\n4. Float");
                System.out.print("Pick the base of your number (1-4): ");
                int choice = keyboard.nextInt();
                
                switch(choice)
                {
                    case 1:
                    {
                        System.out.println("You picked DECIMAL");
                        System.out.println("What base would you like to convert " + number + " to?");
                        System.out.println("1. Binary \n2. Hexadecimal");
                        System.out.print("New base: ");
                        int baseTo = 0;
                        int chosenBase = keyboard.nextInt();
                        if(chosenBase == 1)
                        {
                            baseTo = 2;
                        }
                        else if(chosenBase==2)
                        {
                            baseTo = 16;
                        }

                        //int baseTo = 10;
                        //baseTo = keyboard.nextInt();
                        System.out.println("Converting base to " + baseTo);
                        convertDecimalToBase(baseTo, number);
                        break;
                    }
                    case 2:
                    {
                        System.out.println("You picked BINARY");
                        int baseFrom = 2;
                        convertBaseToDecimal(baseFrom, number);
                        break;
                    }
                    case 3:
                    {
                        System.out.println("You picked HEXADECIMAL");
                        convertFrom = "hexadecimal";
                        int baseFrom = 16;
                        convertBaseToDecimal(baseFrom, number);
                        break;
                    }
                    case 4:
                    {
                        System.out.println("You picked float!");
                    }
                    default:
                    {
                        System.out.println("Something went wrong");
                        break;
                    }
                }
            } 
            catch (Exception e) 
            {
                System.out.println("Something went wrong.");
                keyboard.nextLine();
            }
            
            System.out.println("Would you like to try again?" + "\nEnter y for yes, or n for no.");
            String answer = keyboard.next();
            if(answer.equals("n"))
            {
                loop = false;
            }
        }
    }

    public static void convertDecimalToBase(int baseTo, int number) 
    {
        //int baseTo = 16;
        //int number = 22;
        //convert base to other then use params
        
        int remainder;
        String answer = "";
        int temp;
        
        while(number > 0)
        { 
            temp = number / baseTo;   //22/16 = 1
            // remainder = number - baseTo * temp; // 22 - 16 * 1
            // answer = remainder + answer;
            // number = temp; 
            
            if(baseTo == 16) // hex
            { 
                remainder = number - baseTo * temp;
                char hexadecimalOverTen = 'A';
                
                if(remainder >= 10) 
                {
                    hexadecimalOverTen += remainder - 10;
                    answer = hexadecimalOverTen + answer;
                    number = temp;
                }
                else
                {
                    //remainder = number - baseTo * temp; // 22 - 16 * 1
                    answer = remainder + answer;
                    number = temp;
                }
            }
            else // bionary
            {
                remainder = number - baseTo * temp; // 22 - 16 * 1
                answer = remainder + answer;
                number = temp;
            }

             
            
            //rem = 22 / 16 = 1
            // temp = 22 - 16 * 1 = 6
            // answer = temp + answer = 1 + "" = "1"
            //number = temp = 6 
        }
        System.out.println(answer);
    }

    // need the number and base the number is 
    public static void convertBaseToDecimal(int baseFrom, int number) 
    {
        //int baseFrom = 16;
        
        //String num = "15";
        String num = String.valueOf(number);
        //num = "1a";
        int answer = 0;
        int j = 0;
        for(int i = num.length()-1; i >= 0; i--)
        {
            int temp;
            if(baseFrom == 16)
            {
                char value = num.charAt(i);
                if(value >= '0' && value <= '9')
                {
                    //solve nfor numbers
                    temp = value - '0';
                    answer += temp * Math.pow(baseFrom, j);
                }
                else
                {
                    //solve for letters
                    // A = 65, '7' = 55
                    value = Character.toUpperCase(value);
                    temp = value - '7';
                    answer += temp * Math.pow(baseFrom, j);
                }
            }
            else
            {
                int value = num.charAt(i) - '0';
                answer += value * Math.pow(baseFrom, j);
                System.out.println(answer);
            }


            // int value = num.charAt(i) - '0';
            // answer += value * Math.pow(baseFrom, j);
            // System.out.println(answer);
            j++;
        }
        System.out.println("Your new number is " + answer);
    }

}