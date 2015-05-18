package mis_2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class SettingsImporter
{
    private final String _pathName = "settings.txt";

    public void importFromFile() throws IOException
    {
        this.importFromFile("");
    }

    public void importFromFile(String path) throws IOException
    {
        File file = new File(path + this._pathName);
        List<String> lines = Files.readAllLines(file.toPath());

        Settings.PALETTE_WIDTH = Integer.parseInt(lines.get(0));
        Settings.PALETTE_HEIGHT = Integer.parseInt(lines.get(1));

        Settings.SOLUTIONS_PER_GENERATION = Integer.parseInt(lines.get(2));
        Settings.SOLUTIONS_FOR_MUTATION = Integer.parseInt(lines.get(3));
        Settings.BOXES_FOR_MUTATION = Integer.parseInt(lines.get(4));

        Settings.PAIR_OF_SOLUTIONS_FOR_CROSSING = Integer.parseInt(lines.get(5));

        Settings.NUMBER_OF_GENERATIONS = Integer.parseInt(lines.get(6));
    }
}
