import java.util.Scanner;

public class BaseConversion 
{
    public static void main(String[] args) 
    {
        System.out.println("Assignment 1 Number converter:\n");

        boolean loop = true;
        Scanner keyboard = new Scanner(System.in);

        while(loop)
        {
            try 
            {
                System.out.println("Pick what number and base you wish to convert! Example Input:\n32\n101010\n2F\n8.125");
                
                // Since hexadecimal can include string i decided to accept all the number inputs as string then make them ints if i need to as i go
                // general process, ask for starting number, what base that number is, then use a switch and ask what conversion to be doine
                // try catch to catch bad input in the loop
                // each method operates similarly, takes the number and base converitng to or from depending if converting to decimal, or another base, 
                // and then returns a string built with the final answer and print statement afterwards

                System.out.print("Type your starting number: ");
                String number = keyboard.next();

                System.out.println("1. Decimal\n2. Binary\n3. Hexadecimal\n4. Float (Only convert to binary)");

                System.out.print("Pick the base of your number (1-4): ");
                int choice = keyboard.nextInt();
                
                int baseTo = 0;
                String baseToDecAnswer = "";
                String decToBaseAnswer = "";
                int chosenBase = 0;
                switch(choice)
                {
                    case 1: //decimal
                    {
                        System.out.println("You picked DECIMAL");
                        System.out.println("What base would you like to convert " + number + " to?");
                        System.out.println("1. Binary \n2. Hexadecimal\n3. Float");
                        
                        System.out.println("Enter 1, 2, or 3: ");
                        chosenBase = keyboard.nextInt();
                        if(chosenBase == 1)
                        {
                            baseTo = 2;
                        }
                        else if(chosenBase==2)
                        {
                            baseTo = 16;
                        }
                        else if(chosenBase == 3)
                        {
                            String answer = convertDecimalToFloat(number);
                            System.out.println(answer);
                            break;
                        }

                        decToBaseAnswer = convertDecimalToBase(baseTo, number);
                        System.out.println("You successfully converted " + number + " to " + decToBaseAnswer);

                        break;
                    }
                    case 2: //binary
                    {
                        System.out.println("You picked BINARY");
                        int baseFrom = 2;

                        System.out.println("What base would you like to convert " + number + " to?");
                        System.out.println("1. Decimal \n2. Hexadecimal");
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
                        
                        break;
                    }
                    case 3: //hex
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
                    case 4: //float
                    {
                        System.out.println("You picked float! \nConverting float to binary!");
                        //baseTo = 2; // float is only ocnverted to binary, no choice needed and baseTo is known
                        System.out.println("You succesfully converted " + number + " to " + convertFloatToBinary(number));
                        break;
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
        /*
         * To convert decimal to base i pass in the base im converitn to and the original  into this method.
         * works by using a while loop and repeatedly dividing the number by the baseTo.
         * I store how many times the new number after dividing, and store the remainder left over in my answer. 
         * repeat until my number is zero
         * this process works from decimal to any base
         * 
         * ex
         * 
         * 25 decimal to binary
         * 
         * 2 goes into 25 12 times with remaiunder 1. ans = 1
         * 2 into 12 6 times with rem 0. ans = 01
         * 2 into 6 3 times rem 0. ans = 001
         * 2 into 3 1 time rem 1. ans = 1001
         * 2 into 1 zero times, remainder 1. ans = 11001
         * 
         * 25 in decimal = 11001 in binary
         * 
         * 
         * Hex
         * hex is slightly differernt since there is apotential letter in the answer. 
         * first check if baseTo is 16, so i know im working w hex.
         * follow same process as above, except
         * check if the remainder >= 10. if so i need to insert a letter into the answer. 
         * I take the remainder - 10, and get anything from 0-5. Add that to a char of 'A'. This will increment it how ever many its over ten, covering A-F. 
         * Use reult as my answer for that position. repeat till zero
         * 
         * ex 
         * 
         * 249 decimal to hex
         * 
         * 249 / 16 = 15 remainder 9. answer = 9
         * 15/16 = 0 remainder 15. 
         * 15 - 10 = 5. 'A' + 5 = 'F'. answer = F9
         * 
         * 249 decimal = F9 hexadecimal
         * 
        */
        
        boolean negative = false; 

        if(num.charAt(0) == '-')
        {
            num = num.substring(1);
            negative = true;
        }

        int number = Integer.valueOf(num);
        int remainder;
        String answer = "";
        int temp;
        
        while(number > 0)
        { 
            temp = number / baseTo;
            
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
        }

        if(negative && baseTo == 16)
        {
            answer = "-" + answer;
        }

        return answer;
    }
 
    public static String convertBaseToDecimal(int baseFrom, String number) 
    {
        /*
         * Convert base to decimal
         * 
         * Here i do the opposite, i check what base im coming from and i know im going to decimal
         * arguments are the base im converting from and the number
         * 
         * String is used because hex can include letters and numbers
         * 
         * to convert, take a number in each place value multiplyied by the base to the power of the position of the place value. such as X * Base^ (position of place value)
         * Do that for each position and add them all together as i go.
         * 
         * ex
         * 
         * binary 1010 to decimal
         * 
         * base 2
         * 
         * number       1 0 1 0
         * place values 3 2 1 0
         * 
         * = 1*2^3 + 0*2^2 + 1*2^1 + 0*2^0
         * 
         * =   8   +   0   +   2   +   0   = 10
         * 
         * To accomplish this i use a for loop and start at the end of the number (as a string)
         * as i loop from the back i keep up with an exponent. the exponent starts at zero, each loop i increment the exponent and perform the same operations above
         * once my loop completes i should have the correct answer.
         * 
         * Hex is slightly different since it can include letters. first i check if im converting from base 16, then i check if the value of the current 
         * char im looking at is a number or a letter, if its a letter i need to do math to get the number of that letter. I did a hard subtraction to get the number
         * back to 10-15. since 'A' is eq to 65. I convert the char to a number then subtract 55. In the case of A I would get 10, B is 11 etc
         * 
         * 
         */

        int answer = 0;
        int exponent = 0;
        for(int i = number.length()-1; i >= 0; i--)
        {
            int temp;
            if(baseFrom == 16)
            {
                char value = number.charAt(i);
                if(value >= '0' && value <= '9')
                {
                    //solve for numbers
                    temp = value - '0';
                    answer += temp * Math.pow(baseFrom, exponent);
                }
                else
                {
                    //solve for letters
                    // A = 65, subtract 55 for 10
                    value = Character.toUpperCase(value);
                    temp = value - 55;
                    answer += temp * Math.pow(baseFrom, exponent);
                }
            }
            else //binary
            {
                int value = number.charAt(i) - '0';
                answer += value * Math.pow(baseFrom, exponent);
                //System.out.println(answer);
            }
            exponent++;
        }
        //System.out.println("Your new number is " + answer);
        return String.valueOf(answer);
    }

    public static String convertFloatToBinary(String number)
    {
        /*
         * Float to Binary
         * 
         * here I'm converting float to binary. To do so I split the number into two parts and convert each accordingly
         * The first part is in front of the decimal,a nd the second part is behind the deciimal. 
         * 
         * Front (in front of decimal) 
         * For loop to scan string till running into a decimal, building the front as it loops
         * Then simply converted calling the convertDecimalToBase method
         * 
         * Back (behind decimal)
         * Built with for loop as well, scan fromt he end of hte string and building as it loops
         * 
         * converted using a while loop checking if the decimal is > 0, and if my count is > 10. I have a count variable to check if the whilke loop has cyucled more then 10 times, if so
         * I exit the loop and limit my answer to 10 checks. just to keep it simple
         * 
         * Logic start with my decimal, and a fraction that starts at 0.5
         * I check if fraction > then decimal, if so then i half the fraction, put a zero in my answer and check again.
         * if fraction smaller than the decimal, then my answer gets a 1 added to that position and the fraction is halfed again and the decimal is updated to = decimal - fraction. 
         * this repeats till decimal == 0 or count is > 10
         * 
         * I then combine the two parts for a final answer
         * 
         * 
         * 
        */
        String front = "";
        String back = "";
        int exponent = number.length()-1;
        for(int i = 0; i < number.length(); i++)
        {
            if(number.charAt(i) != '.')
            {
                front = front + number.charAt(i);
            }
            else
            {
                break;
            }
        }
        //System.out.println("front " + front);
        if(!front.isEmpty())
        {
            front = convertDecimalToBase(2, front);
        }
        

        for(int i = number.length()-1; i > 0; i--)
        {
            if(number.charAt(i) != '.')
            {
                back = number.charAt(i) + back;
            }
            else
            {
                break;
            }
        }
        //System.out.println("back " + back);
        back = "." + back;

        //convert places past decimal point
        int count = 0;
        float decimal = Float.valueOf(back);
        //System.out.println("Decimal: " + decimal);
        float fraction = 0.5f;
        //System.out.println("fraction: " + fraction);
        String result = "";
        while(decimal > 0 && count < 10)
        {
            if(fraction > decimal)
            {
                result = result + "0";
                fraction /= 2;
            }
            else
            {
                decimal -= fraction;
                result = result + "1";
                fraction /= 2;
            }

            if(decimal == 0)
            {
                break;
            }

            count++;
        }
        String answer = front + "." + result;
        return answer;
    }
    
    public static String convertDecimalToFloat(String number)
    {
        //Checking if negative. if negative, remove sign and keep up with bool to track negative.
        // if im given a decimal base number, i know its whole unless it'd be a float
        // so i simply take the length of the of the number and subtract 1, and that will be my exponent.
        // i know where my decimal place will always go and its after the first number. this is assuming numbers will not be input with leading zeros for some reason. since im using strings this could be an issue
        // example code flow
        // -1234
        // is negative, negative = true
        // strip - so that code works for both neg and positive.
        // calulate length -1. that is exponent.
        //  length is 4, so my exponent needs to be 3 because a flaot in normalized form will have the form of (1-9).XXXXeX
        //  exponent = 3
        //  add decimal to string, always in position after first number
        // 1.234
        // check negative bool, if negative add - to beginning of string
        // -1.234
        // print with exponent
        // -1.234e3
        
        String answer;
        boolean negative = false;

        if(number.charAt(0)=='-')
        {
            number = number.substring(1);
            negative = true;
        }

        int exponent = number.length()-1;
        
        number = number.substring(0, 1) + "." + number.substring(1);
        
        if(negative)
        {
            number = "-" + number;
        }

        if(number.charAt(number.length()-1) == '.')
        {
            number = number + "0";
        }

        answer = "Decimal converted to float is: " + number + "e" + exponent;
        return answer;
    }

}