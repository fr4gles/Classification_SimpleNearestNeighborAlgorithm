/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Michal
 */
public class Main
{

    private static int LicabaElementow = 0;
    private static Boolean TEST = Boolean.FALSE;

    public static void main(String[] args)
    {
        readNSize(args);

        Classification c = new Classification();

        readFile(args, c);

        List<Integer> result = c.resolveProblem();

        System.out.print("Wyniki klasyfikacji : ");

        for (int i = 0; i < result.size(); ++i)
        {
            System.out.print(result.get(i));
        }

    }

    /**
     * @return the LicabaElementow
     */
    public static int getLicabaElementow()
    {
        return LicabaElementow;
    }

    /**
     * @param aLicabaElementow the LicabaElementow to set
     */
    public static void setLicabaElementow(int aLicabaElementow)
    {
        LicabaElementow = aLicabaElementow;
    }

    /**
     * @return the TEST
     */
    public static Boolean getTEST()
    {
        return TEST;
    }

    private static void readFile(String[] args, Classification c)
    {
        int tmpId;
        int[] tmpPointList;
        File file = new File(args[0]); // użycie pliku wejsciowego do odczytu danych
        try
        {
            Scanner sc = new Scanner(file).useDelimiter("\\D+"); // pobieranie tylko liczb ...
            while (sc.hasNext())
            {
                tmpId = sc.nextInt();
                tmpPointList = new int[Main.LicabaElementow - 1];
                for (int i = 0; i < Main.LicabaElementow - 1; ++i)
                {
                    tmpPointList[i] = sc.nextInt();
                }
                c.addAndLearn(tmpId, new Vector(tmpPointList));
            }
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("BLAD Z PARSOWANIEM PLIKU WEJSCIOWEGO czy PLIK istnieje??? -> " + ex.getMessage());
        }
        catch (NoSuchElementException ex)
        {
            if (Main.getTEST())
            {
                System.err.println("BLAD ZLY FORMAT KRAWEDZI -> " + ex.getMessage());
            }
        }
    }

    private static void readNSize(String[] args)
    {
        if (args.length < 2) // jesli zla ilosc argumentow wejsciowych to poinforuj o tym uzytkownika
        {
            System.err.println("BLAD, zla ilosc argumentow wejsciowych. Podaj <nazwa_pliku> <n_size>");
            System.exit(1);
        }

        try
        {
            Main.setLicabaElementow(Integer.parseInt(args[1])); // czytanie z wejscia wielkosc tafli
        }
        catch (NumberFormatException e)
        {
            System.err.println("BLAD ZLY ROZMIAR" + e.getMessage());
        }
    }
}
