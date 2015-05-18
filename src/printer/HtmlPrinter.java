package printer;

import generation.Box;
import generation.Palette;
import generation.Solution;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mis_2.Settings;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class HtmlPrinter extends AbstractPrinter
{
    
    public void print()
    {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(this.getFullFileName(), "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HtmlPrinter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HtmlPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }
        this._printHeadToHtmlFile(writer);
        
        for (Solution solution : this.getGeneration().getSolutions()) {
            String solutionHtml = "<div class=\"solution-container\">";
            for (Palette palette : solution.getPalettes()) {
                solutionHtml += "<div class=\"palette\" style=\"width:" + Settings.PALETTE_WIDTH + "px; height:" + Settings.PALETTE_HEIGHT + "px;\">";
                for (Box box : palette.getBoxes()) {
                    solutionHtml += "<div class=\"box\" data-id=\"" + box.getId() + "\" style=\"top:" + box.getY() + "px; left:" + box.getX() + "px; width:" + box.getWidth() + "px; height:" + box.getHeight() + "px;\">" + box.getId() + "</div>";
                }
                solutionHtml += "</div>";
            }
            solutionHtml += "</div>";
            writer.write(solutionHtml);
        }
        
        this._printFooterToHtmlFile(writer);
        writer.flush();
        writer.close();
    }
    
    private void _printHeadToHtmlFile(PrintWriter writer)
    {
        writer.write("<html><head><link rel=\"Stylesheet\" type=\"text/css\" href=\"../style.css\" /></head><body>\n");
    }
    
    private void _printFooterToHtmlFile(PrintWriter writer)
    {
        writer.write("</body></html>");
    }

    @Override
    public String getFullFileName()
    {
        return this.getFileName() + "-" + this.getGeneration().getId() + ".html";
    }
    
}
