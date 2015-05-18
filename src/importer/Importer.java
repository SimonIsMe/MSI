package importer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class Importer
{
    private final String _pathFile;
    
    public Importer(String pathFile)
    {
        this._pathFile = pathFile;
    }
    
    public int[][] importFromFile() throws IOException
    {
        File file = new File(this._pathFile);
        List<String> lines = Files.readAllLines(file.toPath());
        
        int[][] sizes = new int[lines.size()][2];
        
        for (int i = 0; i < lines.size(); i++) {
            String[] width_height = lines.get(i).split(" ");
            sizes[i][0] = Integer.parseInt(width_height[0]);
            sizes[i][1] = Integer.parseInt(width_height[1]);
        }
        
        return sizes;
    }
}
