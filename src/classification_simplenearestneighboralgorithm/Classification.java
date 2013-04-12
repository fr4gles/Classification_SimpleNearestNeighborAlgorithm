/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classification_simplenearestneighboralgorithm;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Michal
 */
public class Classification
{
    public List<Class> classList;
    
    public Classification()
    {
        classList = new ArrayList<>();
    }
    
    public void addAndLearn(int id, List<Integer> list)
    {
        Integer index = searchIn(id);
        
        if(index != -1)
            classList.get(index).add(list);
        else
            classList.add(new Class(id, list));
    }
    
    public Integer searchIn(int id)
    {
        for(int i=0; i<classList.size(); ++i)
            if(classList.get(i).Id == id)
                return i;
        
        return -1;
    }
    
    public List<Integer> resolveProblem()
    {
        List<Integer> result = new ArrayList<>();
        
        Double localBest;
        Double tmpLocalBest;
        Integer localID = 0;
        
        for(int k=0; k<classList.get(classList.size()-1).points.size(); ++k)
        {
            localBest = Double.MAX_VALUE;
            tmpLocalBest = 0.0;
            for(int i=0; i<classList.size()-1; ++i)
            {
                for(int j=0; j<classList.get(i).points.size(); ++j)
                {
                    tmpLocalBest = localBest;
                    localBest = Math.min(localBest, countDistance(classList.get(classList.size()-1).points.get(k), classList.get(i).points.get(j)));
                    
                    if(localBest.equals(tmpLocalBest))
                    {
                        localID = classList.get(i).Id;
                        break;
                    }
                }
            }
            result.add(localID);
        }
        
        return result;
    }
    
    public Double countDistance(List<Integer> one, List<Integer> second)
    {
        Double result = 0.0;
        for(int i=0; i<one.size(); ++i)
        {
            result += Math.pow(second.get(i)-one.get(i),2);
        }
        return /*Math.abs*/Math.sqrt(result);
    }
}

class Class
{
    public List<List<Integer>> points;
    public Integer Id;
    
    public Class(Integer id, List<Integer> p)
    {
        points = new ArrayList<>();
        
        this.Id = id;
        add(p);
    }
    
    public void add(List<Integer> p)
    {
        points.add(p);
    }
}