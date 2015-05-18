package printer;

import generation.Generation;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public abstract class AbstractPrinter
{
    private String _fileName = "";
    private Generation _generation;
    
    public abstract void print();
    public abstract String getFullFileName();
    
    public void setFileName(String fileName)
    {
        this._fileName = fileName;
    }

    public String getFileName()
    {
        return _fileName;
    }
    
    public void setGeneration(Generation _generation)
    {
        this._generation = _generation;
    }

    public Generation getGeneration()
    {
        return _generation;
    }
    
}
