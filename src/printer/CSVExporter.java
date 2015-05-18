package printer;

import generation.Generation;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class CSVExporter
{
    private final String _fileName;
    
    private LinkedList<int[]> _results = new LinkedList<>();
    
    public CSVExporter(String fileName)
    {
        this._fileName = fileName;
    }

    public String getFileName()
    {
        return _fileName;
    }
    
    public String getFullFileName()
    {
        return _fileName + ".csv";
    }
    
    public void addGeneration(Generation generation)
    {
        int[] result = new int[generation.getSolutions().size()];
        
        for (int i = 0; i < generation.getSolutions().size(); i++)
            result[i] = generation.getSolutions().get(i).getPalettes().size();
        
        this._results.add(result);
    }
    
    public void saveFile()
    {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.getFullFileName(), "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HtmlPrinter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HtmlPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this._writeHeader(writer);
        this._writeResults(writer);
        
        writer.flush();
        writer.close();
    }
    
    private void _writeHeader(PrintWriter writer)
    {
        String header = "ID pokolenia;";
        for (int i = 0; i < this._results.get(0).length; i++) {
            header += i;
            if (i + 1 < this._results.get(0).length)
                header += ";";
        }
        
        writer.write(header + "\n");
    }
    
    private void _writeResults(PrintWriter writer)
    {
        String line;
        for (int k = 0; k < this._results.size(); k++) {
            line = k + ";";
            for (int i = 0; i < this._results.get(k).length; i++) {
                line += this._results.get(k)[i];
                if (i + 1 < this._results.get(k).length)
                    line += ";";
            }
            
            writer.write(line + "\n");
        }
    }
}
