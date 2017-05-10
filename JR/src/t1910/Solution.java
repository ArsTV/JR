package t1910;

/* Пунктуация
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Считать содержимое первого файла, удалить все знаки пунктуации, включая символы новой строки.
Результат вывести во второй файл.
Закрыть потоки.

Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла 
   (используй BufferedReader c конструктором FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл содержимое первого файла, 
   где удалены все знаки пунктуации, включая символы новой строки 
   (Для записи в файл используй BufferedWriter с конструктором FileWriter).
6. Поток записи*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader fileNames = new BufferedReader(new InputStreamReader(System.in));
        FileReader reader = new FileReader(fileNames.readLine());
        FileWriter writer = new FileWriter(fileNames.readLine());

        BufferedReader fis = new BufferedReader(reader);
        BufferedWriter fos = new BufferedWriter(writer);

        while(fis.ready()){
            String line = fis.readLine();
            String arrline[] = line.split("[\\p{Punct}\\n]");
            for (int j = 0; j < arrline.length; j++) {
                fos.write(arrline[j]);
            }
        }

        fileNames.close();
        reader.close();
        writer.close();
        fis.close();
        fos.close();
    }
}
