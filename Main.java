
import java.util.Random;
import java.util.Scanner;



/**
Реализуйте 3 метода, чтобы в каждом из них получить разные исключения
Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?
Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив, каждый элемент которого 
равен разности элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя.
* Реализуйте метод, принимающий в качестве аргументов два целочисленных массива, и возвращающий новый массив, каждый элемент которого равен 
частному элементов двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо как-то оповестить пользователя. 
Важно: При выполнении метода единственное исключение, которое пользователь может увидеть - RuntimeException, т.е. ваше.
    */

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        excep1(); // вариант с throw с try/catch
        // excep2(); // вариант с throw без try/catch
        // excep3(); // вариант с кодами ошибок
        // task2(); // вторая часть задачи со *
    } 
         
    static void excep1() {  // вариант с throw с try/catch
        try {
            int[] res = getDiffArray(generateArray(), generateArray());
            System.out.println("Разница элементов двух массивов:");
            for (int e: res) {
                System.out.printf("%d ", e);
            }
            System.out.println();
        }
        catch (CustomArraySizeException e){
            System.out.println(e.getMessage());
            System.out.printf("Длина первого массива: %d\nДлина второго массива: %d\n",
                    e.getLength1(), e.getLength2());
        }
    }

    static void excep2(){ // вариант с throw без try/catch
        int[] res = getDiffArray(generateArray(), generateArray());
        System.out.println("Разница элементов двух массивов:");
        for (int e: res) {
            System.out.printf("%d ", e);
        }
        System.out.println();
    }

    static int[] getDiffArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null)
            throw new NullPointerException("Оба массива должны существовать.");
        if (arr1.length == 0 || arr2.length == 0)
            throw new CustomArraySizeException("Длина массивов должна быть больше 0.", 
                            arr1.length, arr2.length); // в варианте excep2 будет работать как простой выброс без длины масива
        if (arr1.length != arr2.length)
            throw new CustomArraySizeException("Кол-во элементов массива должно быть одинаковым.", 
                            arr1.length, arr2.length); // в варианте excep2 будет работать как простой выброс без длины масива

        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++)
            res[i] = arr1[i] - arr2[i];
        return res;
    }

    static void excep3(){ // вариант с кодами ошибок
        
        int[]array1 = generateArray();
        int[]array2 = generateArray();
        int result = searchErrors(array1, array2);
        
        
        switch (result){
            case -1 -> {
                System.out.println("Оба массива должны существовать");
                break;
            }
            case -2 -> {
                System.out.println("Длина массивов должна быть больше 0.");
                break;
            }
            case -3 -> {
                System.out.println("Кол-во элементов массива должно быть одинаковым.");
                break;
            }
            default -> {
                System.out.println("Разница элементов двух массивов:");
                for (int e: diffArray(array1, array2)) {
                    System.out.printf("%d ", e);
                }
            }
        }
    }

    static int searchErrors(int[] arr1, int[] arr2){
        if (arr1 == null || arr2 == null)
            return -1; // Оба массива должны существовать
        if (arr1.length == 0 || arr2.length == 0)
            return -2; // Длина массивов должна быть больше 0
        if (arr1.length != arr2.length)
            return -3; // Кол-во элементов массива должно быть одинаковым
        return 0;
    }

    static int[] diffArray(int[] arr1, int[] arr2){
        int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++)
            res[i] = arr1[i] - arr2[i];
        return res;
    }



    static void task2(){
        int[] res = getMultArray(generateArray(), generateArray());
        System.out.println("Частное элементов двух массивов:");
        for (int e: res) {
            System.out.printf("%d ", e);
        }
        System.out.println();
    }

    static int[] getMultArray(int[] arr1, int[] arr2){
        if (arr1 == null || arr2 == null)
            throw new NullPointerException("Оба массива должны существовать.");
        if (arr1.length == 0 || arr2.length == 0)
            throw new RuntimeException("Длина массивов должна быть больше 0.");
        if (arr1.length != arr2.length)
            throw new RuntimeException("Кол-во элементов массива должно быть одинаковым.");
        
            int[] res = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++)
            res[i] = arr1[i] * arr2[i];
        return res;
    }

     /**
     * Метод генерации  массива чисел
     */
    static int[] generateArray(){
        int[] arr = new int[random.nextInt(0, 3)]; // 0 - для проверки ошибки
        for (int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(10);
            System.out.printf("%d ", arr[i]);
            }
        System.out.println();
        return arr;
    }

}


class CustomArraySizeException extends RuntimeException{

    private int length1;
    private int length2;

    public int getLength1() {
        return length1;
    }

    public int getLength2() {
        return length2;
    }

    public CustomArraySizeException(String message, int length1, int length2) {
        super(message);
        this.length1 = length1;
        this.length2 = length2;
    }
}

