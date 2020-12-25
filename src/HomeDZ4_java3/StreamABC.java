package HomeDZ4_java3;

public class StreamABC extends Thread {


    private static  String A = "";
    private static  String B = "";
    private static  String C = "";

    public  void  printA()  {
            System.out.println("Поток А");
    }

    public void printB() {
        System.out.println("Поток В");
    }

    public void printC() {
        System.out.println("Поток С");
    }

/*******************Методы записи потоков в файл*****************************/

    public String writeA()  {

        for (int i = 0; i < 10; i++) {
            A = A + "Слово  ";

        }
         return A;
    }

    public String writeB()  {

        for (int i = 0; i < 10; i++) {
            B = B + "Цитата ";

        }

        return B;
    }

    public String writeC()  {

        for (int i = 0; i < 10; i++) {
            C = C + "Ремарка ";

        }
        return C;
    }

}

