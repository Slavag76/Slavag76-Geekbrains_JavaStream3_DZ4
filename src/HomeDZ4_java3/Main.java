package HomeDZ4_java3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        /*Класс StreamABC наследуется от класса Thread*/
        StreamABC streamA = new StreamABC();
        StreamABC streamB = new StreamABC();
        StreamABC streamC = new StreamABC();

        /*Задаем приоритеты потокам*/
        streamA.setPriority(Thread.MAX_PRIORITY);
        streamB.setPriority(Thread.NORM_PRIORITY);
        streamC.setPriority(Thread.MIN_PRIORITY);

         File file = new File("D:\\Android разработка\\JavaStream3\\Slavag76-Geekbrains_JavaStream3_DZ4_\\src\\HomeDZ4_java3\\file.txt");
        FileWriter writer1 = new FileWriter(file, true);
        FileWriter writer2 = new FileWriter(file, true);
        FileWriter writer3 = new FileWriter(file, true);


        startPrintABC(streamA, streamB, streamC); /*запускаем метод вывода АВС*/
        startWrite10linesABC(streamA, streamB, streamC, writer1, writer2, writer3); /*запускаем метод печати в файл*/

    }

    private static void startWrite10linesABC(StreamABC streamA, StreamABC streamB, StreamABC streamC, FileWriter writer1, FileWriter writer2, FileWriter writer3) throws IOException, InterruptedException {
        Object sync = new Object();
        new Thread(() -> {
            synchronized (sync) {
                try {
                    writer1.write(String.format("\n" + streamA.writeA()));
                    writer1.close();
                    sync.wait(); /*попытка усыпить поток*/

                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        streamA.sleep(1000); /*задержка*/

        new Thread(() -> {
            synchronized (sync) {
                try {
                    sync.notify(); /*попытка запустить поток после ожидания*/
                    writer2.write(String.format("\n" + streamB.writeB()));
                    writer2.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        streamB.sleep(1000);

        new Thread(() -> {
            synchronized (sync) {
                try {
                    sync.notifyAll();
                    writer3.write(String.format("\n" + streamC.writeC()));
                    writer3.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private static void startPrintABC(StreamABC streamA, StreamABC streamB, StreamABC streamC) {
        for (int i = 0; i < 3; i++) {

            new Thread(() -> streamA.printA()).start();
            new Thread(() -> streamB.printB()).start();
            new Thread(() -> streamC.printC()).start();

        }

    }
}

