package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Ноут
 */
public class Main {
    private int[] massiv;
    File file = new File("longPoker.txt");
    public Main() throws IOException
    {
        file.createNewFile();
        massiv = new int[1000000];
        CreateArray();
    }
    private void CreateArray()
    {
            for (int j=0;j<=1000000;++j)
                massiv[j] = j;
    }
    public void WriteFile() throws IOException
    {
        CreateArray();
        FileWriter filewriter = new FileWriter(new File("Test.txt"));

        for (int j=0;j<=1000000;++j)
            filewriter.write(massiv[j] + " ");
        filewriter.flush();
    }
//    public void ReadFile() throws FileNotFoundException, IOException
//    {
//
//        Scanner scannerfile = new Scanner(file);
//        for(int i=0;i<=100000;++i)
//        {
//            for (int j=0;j<=100000;++j)
//            {
//                if(scannerfile.hasNextInt())
//                    massiv[j]=scannerfile.nextInt();
//            }
//
//        }
//        System.out.print("Введенный массив\n");
//        for(int i=0;i<100000;++i)
//        {
//            for (int j=0;j<100000;++j)
//            {
//                System.out.print(massiv[j] + " ");
//            }
//            System.out.print("\n");
//        }
//
//    }
    public static void main(String[] args) throws IOException
    {
        Main test = new Main();


        test.WriteFile();
    }

}