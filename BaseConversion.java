import java.util.Scanner;

public class BaseConversion 
{
    public static void main(String[] args) 
    {
        //convertDecimalToBase(16, 100);
        //convertBaseToDecimal(2, 100);

        System.out.println("Assignment 1 Number converter:\n");
        // String convertFrom;
        boolean loop = true;
        Scanner keyboard = new Scanner(System.in);
        //1 ask for type of # DEC BIN HEX FLOAT
        while(loop)
        {
            try 
            {
                System.out.println("Pick what number and base you wish to convert!");
                
                // Since hexadecimal can include string i decided to accept all the number inputs as string then make them ints if i need to

                System.out.print("Type your starting number: ");
                String number = keyboard.next();

                System.out.println("1. Decimal\n2. Binary\n3. Hexadecimal\n4. Float");

                System.out.print("Pick the base of your number (1-4): ");
                int choice = keyboard.nextInt();
                

                int baseTo = 0;
                String baseToDecAnswer = "";
                String decToBaseAnswer = "";
                int chosenBase = 0;
                switch(choice)
                {
                    case 1:
                    {
                        System.out.println("You picked DECIMAL");
                        System.out.println("What base would you like to convert " + number + " to?");
                        System.out.println("1. Binary \n2. Hexadecimal");
                        
                        System.out.println("Enter 1 or 2: ");
                        chosenBase = keyboard.nextInt();
                        if(chosenBase == 1)
                        {
                            baseTo = 2;
                        }
                        else if(chosenBase==2)
                        {
                            baseTo = 16;
                        }

                        decToBaseAnswer = convertDecimalToBase(baseTo, number);
                        System.out.println("You successfully converted " + number + " to " + decToBaseAnswer);

                        break;
                    }
                    case 2:
                    {
                        System.out.println("You picked BINARY");
                        int baseFrom = 2;

                        System.out.println("What base would you like to convert " + number + " to?");
                        System.out.println("1. Decimal \n2. Hexadecimal\n3.float");
                        chosenBase = keyboard.nextInt();

                        if(chosenBase == 1)
                        {
                            baseToDecAnswer = convertBaseToDecimal(baseFrom, number);
                            System.out.println("You successfully converted " + number + " to " + baseToDecAnswer);
                        }
                        else if(chosenBase == 2)
                        {
                            baseTo = 16;
                            baseToDecAnswer = convertBaseToDecimal(baseFrom, number);
                            decToBaseAnswer = convertDecimalToBase(baseTo, baseToDecAnswer);
                            System.out.println("You successfully converted " + number + " to " + decToBaseAnswer);
                        }
                        else if(chosenBase == 3)
                        {
                            baseTo = 10;
                            convertFloat(baseTo, number);
                        }
                        
                        break;
                    }
                    case 3:
                    {
                        System.out.println("You picked HEXADECIMAL");

                        int baseFrom = 16;

                        System.out.println("What base would you like to convert " + number + " to?");
                        System.out.println("1. Decimal \n2. Binary");
                        chosenBase = keyboard.nextInt();

                        if(chosenBase == 1) 
                        {
                            baseToDecAnswer = convertBaseToDecimal(baseFrom, number);
                            System.out.println("You successfully converted " + number + " to " + baseToDecAnswer);
                        }
                        else if(chosenBase == 2)
                        {
                            baseTo = 2;
                            baseToDecAnswer = convertBaseToDecimal(baseFrom, number);
                            decToBaseAnswer = convertDecimalToBase(baseTo, baseToDecAnswer);
                            System.out.println("You successfully converted " + number + " to " + decToBaseAnswer);
                        }

                        break;
                    }
                    case 4:
                    {
                        System.out.println("You picked float! \nConverting float to binary!");
                        baseTo = 2; // float is only ocnverted to binary, no choice needed and baseTo is known
                        convertFloat(baseTo, number);

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
        keyboard.close();
    }

    public static String convertDecimalToBase(int baseTo, String num) 
    {
        //int baseTo = 16;
        //int number = 22;
        //convert base to other then use params
        int number = Integer.valueOf(num);
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
        //System.out.println(answer);
        return answer;
    }

    // need the number and base the number is 
    public static String convertBaseToDecimal(int baseFrom, String number) 
    {

        int answer = 0;
        int j = 0;
        for(int i = number.length()-1; i >= 0; i--)
        {
            int temp;
            if(baseFrom == 16)
            {
                char value = number.charAt(i);
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
                int value = number.charAt(i) - '0';
                answer += value * Math.pow(baseFrom, j);
                System.out.println(answer);
            }


            // int value = num.charAt(i) - '0';
            // answer += value * Math.pow(baseFrom, j);
            // System.out.println(answer);
            j++;
        }
        //System.out.println("Your new number is " + answer);
        return String.valueOf(answer);
    }

    public static void convertFloat(int baseTo, String number)
    {
        
    }

}