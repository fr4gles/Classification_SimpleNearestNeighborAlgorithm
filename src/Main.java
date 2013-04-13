
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Glowna klasa programu.
 *
 * @author Michal
 * @date 13.04.2013
 */
public class Main
{

    /**
     * Jest to liczba podana na wejsciu - size
     */
    private static int LicabaElementow = 0;

    /**
     * Glowna funkcja programu - rozpoczyna dzialanie.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        readNSize(args); // wczytanie rozmiaru

        Classification c = new Classification();

        readFile(args, c); // wczytanie danych z pliku

        List<Integer> result = c.resolveProblem();  // rozwiazanie problemu

        /*
         * Wypis wyniku
         */
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
     * Wczytywanie danych z pliku. Podczas wczytywania funkcja rozpoczyna etap
     * nauki
     *
     * @param args argumenty wejscowe
     * @param c obiekt klasy, klasyfikujacy
     */
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
                c.addAndLearn(tmpId, new Vector(tmpPointList)); // rozpoczecie etapu nauki
            }
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("BLAD Z PARSOWANIEM PLIKU WEJSCIOWEGO czy PLIK istnieje??? -> " + ex.getMessage());
        }
        catch (NoSuchElementException ex)
        {
            System.err.println("BLAD ZLY FORMAT PLIKU WEJSCIOWEGO, CZY ABY NA PEWNO N_SIZE JEST OK? -> " + ex.getMessage());
        }
    }

    /**
     * Wczytanie z wejscia rozmiaru - size
     *
     * @param args argumenty wejsciowe
     */
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
