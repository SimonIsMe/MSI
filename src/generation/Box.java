package generation;

import mis_2.Settings;

/**
 *
 * @author Szymon Skrzyński<skrzynski.szymon@gmail.com>
 */
public class Box
{
    private int _id;
    private int _x;
    private int _y;
    private int _width;
    private int _height;

    public Box(int x, int y, int width, int height)
    {
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;
    }

    public int getId()
    {
        return _id;
    }

    public void setId(int _id)
    {
        this._id = _id;
    }
    
    public int getX()
    {
        return _x;
    }

    public void setX(int _x)
    {
        this._x = _x;
    }

    public int getY()
    {
        return _y;
    }

    public void setY(int _y)
    {
        this._y = _y;
    }

    public int getWidth()
    {
        return _width;
    }

    public void setWidth(int _width)
    {
        this._width = _width;
    }

    public int getHeight()
    {
        return _height;
    }

    public void setHeight(int _height)
    {
        this._height = _height;
    }
    
    public void rotate()
    {
        int buffor = this._width;
        this._width = this._height;
        this._height = buffor;
    }
    
    public boolean canRotate()
    {
        return  Math.max(this._width, this._height) < Math.min(Settings.PALETTE_WIDTH, Settings.PALETTE_HEIGHT);
    }
    
    public boolean isCrossing(Box box) 
    {
        int a1X = this.getX();
        int a1Y = this.getY();
        int a2X = this.getX() + this.getWidth();
        int a2Y = this.getY();
        int a3X = this.getX() + this.getWidth();
        int a3Y = this.getY() + this.getHeight();
        int a4X = this.getX();
        int a4Y = this.getY() + this.getHeight();
        
        int b1X = box.getX();
        int b1Y = box.getY();
        int b2X = box.getX() + box.getWidth();
        int b2Y = box.getY();
        int b3X = box.getX() + box.getWidth();
        int b3Y = box.getY() + box.getHeight();
        int b4X = box.getX();
        int b4Y = box.getY() + box.getHeight();
        
//        System.out.println("("+a1X+","+a1Y+") ("+a2X+","+a2Y+")");
//        System.out.println("("+a4X+","+a4Y+") ("+a3X+","+a3Y+")");
//        
//        System.out.println("------------");
//        
//        System.out.println("("+b1X+","+b1Y+") ("+b2X+","+b2Y+")");
//        System.out.println("("+b4X+","+b4Y+") ("+b3X+","+b3Y+")");
        
        //  czy są wspólne obszary?
        if ((b1X < a1X && a1X < b2X && a1Y < b1Y && b1Y < a4Y) || (a1X < b1X && b1X < a2X && b1Y < a1Y && a1Y < b4Y))
            return true;
        if ((b1X < a2X && a2X < b2X && a2Y < b1Y && b1Y < a3Y) || (a1X < b2X && b2X < a2X && b2Y < a1Y && a1Y < b3Y))
            return true;
        
        //  czy są wspólne narożniki?
        if ((a1X == b1X && a1Y == b1Y) || (a2X == b2X && a2Y == b2Y) || (a3X == b3X && a3Y == b3Y) || (a4X == b4X && a4Y == b4Y))
            return true;
        
        //  czy boki się nakładają?
        if ((a1Y == b1Y && a4Y == b4Y && b1X < a2X && a2X < b2X) || (b1Y == a1Y && b4Y == a4Y && a1X < b2X && b2X < a2X))
            return true;
        if ((a1X == b1X && a2X == b2X && a1Y < b4Y && b4Y < a4Y) || (b1X == a1X && b2X == a2X && b1Y < a4Y && a4Y < b4Y))
            return true;
        
        //  czy 1 bok się nakłada?
        if (((a1X < b1X && b1X < a2X) || (a1X < b2X && b2X < a2X)) && a1Y == b1Y && b1Y < a4Y && a4Y < b4Y)
            return true;
        if (((b1X < a1X && a1X < b2X) || (b1X < a2X && a2X < b2X)) && b1Y == a1Y && a1Y < b4Y && b4Y < a4Y)
            return true;
        if (((a1X < b1X && b1X < a2X) || (a1X < b2X && b2X < a2X)) && a4Y == b4Y && b1Y < a4Y && a4Y < b4Y)
            return true;
        if (((b1X < a1X && a1X < b2X) || (b1X < a2X && a2X < b2X)) && b4Y == a4Y && a1Y < b4Y && b4Y < a4Y)
            return true;
        
        //  czy jakiś narożnik jest wewnątrz prostokąta
        if ((this._pointIsInsideRectangle(a1X, a1Y, a3X, a3Y, b1X, b1Y)) || (this._pointIsInsideRectangle(b1X, b1Y, b3X, b3Y, a1X, a1Y)))
            return true;
        if ((this._pointIsInsideRectangle(a1X, a1Y, a3X, a3Y, b2X, b2Y)) || (this._pointIsInsideRectangle(b1X, b1Y, b3X, b3Y, a2X, a2Y)))
            return true;
        if ((this._pointIsInsideRectangle(a1X, a1Y, a3X, a3Y, b3X, b3Y)) || (this._pointIsInsideRectangle(b1X, b1Y, b3X, b3Y, a3X, a3Y)))
            return true;
        if ((this._pointIsInsideRectangle(a1X, a1Y, a3X, a3Y, b4X, b4Y)) || (this._pointIsInsideRectangle(b1X, b1Y, b3X, b3Y, a4X, a4Y)))
            return true;
        
        return false;
    }
    
    private boolean _pointIsInsideRectangle(int x1, int y1, int x2, int y2, int x, int y)
    {
        
        return x1 < x && x < x2 && y1 < y && y < y2;
    }
    
    public String toString()
    {
        return this._x + "x" + this._y + " " + this.getWidth() + " " + this.getHeight();
    }
}
