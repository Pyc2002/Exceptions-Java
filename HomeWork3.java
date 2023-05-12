import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;



public class HomeWork3 {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Task();
    }
        
        
    static void Task(){    
        System.out.print("Введите данные: ");
        ArrayList <String> in_data = new ArrayList<String>(List.of(scanner.nextLine().split(" "))); 
      
        try {
           Data person = parse(in_data);
            try (FileWriter writer = new FileWriter(person.lastname, true)) { // проверка по фамилии, если true, то записывает в файл с такой же фамилией, если false - создает новый файл
                writer.append(person.toString());
                writer.append('\n');
                writer.flush();
                writer.close();
                
                System.out.println(" ");
                System.out.printf("Данные - %s - записаны в файл!", person);
                System.out.println(" ");
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (MyDataException e) {
            System.out.printf(" Ошибка! %s\n", e.getMessage());
              
        } catch (MyArrayException e) {
            System.out.printf(" Ошибка! %s Введено: %s\n", e.getMessage(), e.getArr());
        }
    }

    public static Data parse(ArrayList<String> data) throws MyDataException, MyArrayException{
        if (data.size() < 6) 
            throw new MyLessDataException("Введены не все данные.", data);
        if (data.size() > 6) 
            throw new MyMoreDataException("Введено больше данных.", data);
        Data personData = new Data(null, null, null, null, null, null);
       
        for (String item: data) {
            if (item.equals("f") || item.equals("m")) { // проверка на наличие символов m или f в списке для определения пола
                data.remove(item); // если true, то удаляем символ из списка, чтобы в конце остались только эдементы списка для ФИО
                personData.sex = item; // добавляем символ в созданную строку типа Data
                break;
            }
        }
        if (personData.sex.isEmpty()) throw new MyDataException("Пол введен не верно!"); // если эдемент пуст, значит условие было false (символы f или m не были найдены)
        

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).matches("[\\d]+")){ // поиск элемента списка, состоящего только из цифр (условие для телефона)  
                personData.phone = data.get(i);
                data.remove(i);
            }
        }
        if (personData.phone.isEmpty()) throw new MyDataException("В номере телефона должны быть только цифры!");

   
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat df=new SimpleDateFormat("dd.MM.yyyy"); 
        df.setLenient(false);
        
        for (String item: data) {
            try {
                calendar.setTime(df.parse(item)); // поиск элемента списка с форматом dd.MM.yyyy
                personData.dateBirth = item;
                data.remove(item);
                break;
            } catch (Exception e){
                continue;
            }
        }
        if (personData.dateBirth.isEmpty()) throw new MyDataException("Дата рождения введена не верно!");

        // проверка оставшихся элементов на буквы латиницы
        if (data.get(0).matches("[a-zA-Z]+") && data.get(1).matches("[a-zA-Z]+") && data.get(2).matches("[a-zA-Z]+")) {
            personData.lastname = data.get(0);
            personData.name = data.get(1);
            personData.surname = data.get(2);
        }
        if (personData.lastname.isEmpty() || personData.name.isEmpty() || personData.surname.isEmpty()) {
            throw new MyDataException("ФИО должны быть введены только латинскими буквами!");
        }
        return personData;
    }


    public static class Data {
        String lastname;
        String name;
        String surname;
        String dateBirth;
        String phone;
        String sex;

        public Data(String lastname, String name, String surname, String dateBirth, String phone, String sex) {
            this.lastname = "";
            this.name = "";
            this.surname = "";
            this.dateBirth = "";
            this.phone = "";
            this.sex = "";
        }

        @Override
        public String toString() {
            return String.format("%s %s %s %s %s %s", lastname, name, surname, dateBirth, phone, sex); // перезаписывает данные в нужном порядке
        }
    }
    
}


class MyDataException extends Exception{
    public MyDataException(String message) {
        super(message);
    }
}

abstract class MyArrayException extends Exception{

    private final  ArrayList<String> arr;
   
    public MyArrayException(String message, ArrayList<String> arr) {
        super(message);
        this.arr = arr;
    }
    
    public ArrayList<String> getArr() {
        return arr;
    }
}

class MyLessDataException extends MyArrayException{

    public MyLessDataException(String message,ArrayList<String> arr){ 
        super(message, arr);
    }
}
class MyMoreDataException extends MyArrayException{

    public MyMoreDataException(String message,ArrayList<String> arr){ 
        super(message, arr);
    }
}

