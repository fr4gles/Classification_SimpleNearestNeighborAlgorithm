
import java.util.ArrayList;
import java.util.List;

/**
 * Posiada tez funkcje rozwiazywania problemu.
 *
 * @author Michal Klasa realizujaca uczenie sie na podstawie zadanych danych.
 * @date 13.04.2013
 */
public class Classification
{

    /**
     * lista klasyfikacji potrzebna do realizacji algorytmu. Przechowuje
     * wszystkie klasy wraz z ich wektorami. Przechowuje rowniez wektory do
     * klasyfikacji.
     */
    private List<ClassOfVector> classList;

    /**
     * Konstruktor inicjalizujacy
     */
    public Classification()
    {
        classList = new ArrayList<>();
    }

    /**
     * Funkcja uczaca na podstawie dodanych danych. Uczenie - dodawanie do
     * wektora danych.
     *
     * @param id - id klasy
     * @param list - lista punktow do nauczenia
     */
    public void addAndLearn(int id, Vector list)
    {
        int index = searchIn(id);

        if (index != -1)
        {
            classList.get(index).add(list);
        }
        else
        {
            classList.add(new ClassOfVector(id, list));
        }
    }

    /**
     * Funkcja szukająca czy podana klasa już istnieje. Przydaje się gdy punkty
     * wraz z klasami są podaje w losowej kolejnosci.
     *
     * @param id - id do szukania
     * @return zwraca -1 gdy nie znalazło, != -1 gdy znalazło
     */
    public Integer searchIn(int id)
    {
        for (int i = 0; i < classList.size(); ++i)
        {
            if (classList.get(i).getId() == id)
            {
                return i;
            }
        }

        return -1;
    }

    /**
     * Funkcja rozwiazujaca problem Wykorzystuje algorytm: najbliższych
     * sąsiadów. Odleglosci pomiędzy punktami są sorawdzane liniowo.
     *
     * @return lista id klas bedaca wynikiem klasyfikacji
     */
    public List<Integer> resolveProblem()
    {
        List<Integer> result = new ArrayList<>();

        for (Vector k : classList.get(classList.size() - 1).getPoints())
        {
            countDistanceToAll(k, result);
        }

        return result;
    }

    /**
     * Funkcja liczy dystans od wektora wejściwego do wszystkich pozostalych.
     * Funkcja zajmuje sie klasyfikacja danego wektora k do odpowiedniej klasy.
     *
     * @param k wektor wejsciowy
     * @param result lista wynikowa - zapisywana zakwalifikowana klasa.
     */
    private void countDistanceToAll(Vector k, List<Integer> result)
    {
        float tmpLocalBest;
        float localBest = Float.POSITIVE_INFINITY;
        int localID = 0;
        for (int i = 0; i < classList.size() - 1; ++i)
        {
            for (Vector j : classList.get(i).getPoints())
            {
                tmpLocalBest = localBest;
                localBest = Math.min(localBest, k.countDistanceTo(j));
                localID = updateBestClassID(localBest, tmpLocalBest, localID, i);
            }
        }
        result.add(localID);
    }

    /**
     * Funkcja sprawdza czy najmniejsza odleglosc ulegla zmianie. Jesli tak to
     * zaktualizuj ID klasy do której nalezy ten wektor.
     *
     * @param localBest najmniejsza odleglosc
     * @param tmpLocalBest kopia najmniejszej odleglosci - do sprawdzania zmian
     * @param localID ID klasy do ktorej nalezy wektor
     * @param i numer potrzebny do odczytani poprawnych danych z listy klas
     * @return ID zakwalifikowanej klasy
     */
    private int updateBestClassID(float localBest, float tmpLocalBest, int localID, int i)
    {
        if (Math.abs(localBest - tmpLocalBest) > 0.0001f)
        {
            localID = classList.get(i).getId();
        }
        return localID;
    }
}

/**
 *
 * @author Michal
 */
final class ClassOfVector
{

    private List<Vector> points;
    private Integer Id;

    public ClassOfVector(Integer id, Vector p)
    {
        points = new ArrayList<>();

        this.Id = id;
        add(p);
    }

    public void add(Vector p)
    {
        getPoints().add(p);
    }

    /**
     * @return the points
     */
    public List<Vector> getPoints()
    {
        return points;
    }

    /**
     * @return the Id
     */
    public Integer getId()
    {
        return Id;
    }
}

final class Vector
{

    private int[] point;

    public Vector(int[] p)
    {
        point = new int[p.length];
        point = p.clone();
    }

    public float countDistanceTo(Vector p)
    {
        float result = 0.0f;
        for (int i = 0; i < this.point.length; ++i)
        {
            result += (p.point[i] - this.point[i]) * (p.point[i] - this.point[i]);//Math.pow(second.get(i)-first.get(i),2);
        }
        return /*Math.abs*/ (float) Math.sqrt(result);
    }
}