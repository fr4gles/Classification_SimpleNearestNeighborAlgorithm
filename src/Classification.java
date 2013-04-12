
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Michal
 */
public class Classification
{

    private List<ClassOfVector> classList;

    public Classification()
    {
        classList = new ArrayList<>();
    }

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

    public List<Integer> resolveProblem()
    {
        List<Integer> result = new ArrayList<>();

        float localBest;
        float tmpLocalBest;
        int localID = 0;

        for (Vector k : classList.get(classList.size() - 1).getPoints())
        {
            localBest = Float.POSITIVE_INFINITY;
            for (int i = 0; i < classList.size() - 1; ++i)
            {
                for (Vector j : classList.get(i).getPoints())
                {
                    tmpLocalBest = localBest;
                    localBest = Math.min(localBest, k.countDistanceTo(j));

                    if (Math.abs(localBest - tmpLocalBest) > 0.0001f)
                    {
                        localID = classList.get(i).getId();
                    }
                }
            }
            result.add(localID);
        }

        return result;
    }
}

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