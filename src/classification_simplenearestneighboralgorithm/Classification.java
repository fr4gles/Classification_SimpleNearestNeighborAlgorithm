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
    
    public void resolveProblem()
    {
        for(int k=0; k<classList.get(classList.size()-1).points.size(); ++k)
        {
            for(int i=0; i<classList.size()-2; ++i)
            {
                for(int j=0; j<classList.get(i).points.size(); ++j)
                {
                    
                }
            }
        }
    }
    
    public Double liczOdleglosc()
    {
        
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