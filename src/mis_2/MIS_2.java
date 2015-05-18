package mis_2;

import generation.Generation;
import importer.Importer;
import java.io.IOException;
import java.util.Random;
import printer.AbstractPrinter;
import printer.CSVExporter;
import printer.HtmlPrinter;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class MIS_2
{

    public static void main(String[] args)
    {   
        
//        Random random = new Random();
//        for (int i = 0; i < 90; i++)
//            System.out.println((random.nextInt(40) + 1) + " " + (random.nextInt(40) + 1));
//        
//        return;
        
        if (args.length == 0) {
            return;
        }
        
        CSVExporter exporter = new CSVExporter("./" + args[0] + "/plik");
        
        Importer importer = new Importer("./" + args[0] + "/test.txt");
        SettingsImporter settingsImporter = new SettingsImporter();
        try {
            settingsImporter.importFromFile("./" + args[0] + "/");
            Settings.boxes = importer.importFromFile();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        
        AbstractPrinter printer = new HtmlPrinter();
        printer.setFileName("./" + args[0] + "/file");
        
        Generation generation = new Generation();
        printer.setGeneration(generation);
        printer.print();
        exporter.addGeneration(generation);
        
        for (int i = 0; i < Settings.NUMBER_OF_GENERATIONS; i++) {
            System.out.println(i);
            generation = new Generation(generation);
            printer.setGeneration(generation);
            printer.print();    
            exporter.addGeneration(generation);
        }
        
        exporter.saveFile();
    }
}
