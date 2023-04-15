import java.util.Scanner;

/**
 *    Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение. 
 * Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных.
 */



public class HomeWork2 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
       
        // System.out.printf("Введенное число: %f\n", variant1()); // вариант без обработки исключений
        
        variant2(); // вариант с обработкой исключений (некорректный формат и пустая строка)
   
    }
    
    static float variant1(){


        while (true){
            System.out.print("Введите дробное число: ");
            if (scanner.hasNextFloat()){
                return scanner.nextFloat();
            }
            else{
                System.out.println("Число для поиска указано некорректно.\nПовторите попытку ввода.");
                scanner.nextLine();
            }
        }
    }
    
    static void variant2(){
      
        float number = 0;
        while (number % 1 == 0) {
            System.out.print("Введите дробное число: ");
            try { 
                number = fromStringIntoFloat(scanner.nextLine());
                System.out.printf("Введенное число: " + number + "\n");
            } 
            catch (MyNumberFormatException e) {
                System.out.printf("%s Введено не дробное число: %s\n", e.getMessage(), e.getX());
            } 
            catch (MyNullElementException e) {
                System.out.printf("%s Введена пустая строка!\n", e.getMessage());
            }
        } 
    }
        
    public static float fromStringIntoFloat(String element) throws MyNumberFormatException, MyNullElementException{  

        if (element.isEmpty()) 
            throw new MyNullElementException("Некорректный формат данных.", element);
        try {
            return Float.parseFloat(element);
        }
        catch (NumberFormatException e){
            throw new MyNumberFormatException("Некорректный формат данных.", element);
        }
    }
}




abstract class MyException extends Exception{

    private final String x;
    
    public MyException(String message, String x){
        super(message);
        this.x = x;

    }

    public String getX() {
        return x;
    }
}

class MyNumberFormatException extends MyException{

    public MyNumberFormatException(String message, String x){
        super(message, x);
    }
}

class MyNullElementException extends MyException{

    public MyNullElementException(String message, String x){
        super(message, x);
    }
}
    





 