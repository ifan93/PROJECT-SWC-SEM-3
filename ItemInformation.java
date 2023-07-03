
/**
 * Write a description of class ItemInformation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.text.DecimalFormat;
public class ItemInformation
{
    //variables declaration with private modifier only can be accesed in same eclass
    private String itemId,itemName,datePurchase;
    private double itemPrice;
    
    DecimalFormat df= new DecimalFormat("##00.00");
    //constructor with parameter to give initial value to each of attributes
    public ItemInformation(String itemId,String itemName,double itemPrice,String datePurchase)
    {
        this.itemId=itemId;
        this.itemName=itemName;
        this.itemPrice=itemPrice;
        this.datePurchase=datePurchase;
    }

    //mutator for all attributes
    public void setAll(String itemId,String itemName,double itemPrice,String datePurchase)
    {
        this.itemId=itemId;
        this.itemName=itemName;
        this.itemPrice=itemPrice;
        this.datePurchase=datePurchase;
    }

    //mutator for each attributes
    
    public void setItemId()
    {
        this.itemId=itemId;
    }

    public void setItemName()
    {
        this.itemName=itemName;
    }

    public void setDatePurch()
    {
        this.datePurchase=datePurchase;
    }

    public void setItemPrice()
    {
        this.itemPrice=itemPrice;
    }

    //accesor for each atrributes
    public String getItemId()
    {
        return itemId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getDatePurchase()
    {
        return datePurchase;
    }

    public double getItemPrice()
    {
        return itemPrice;
    }
}
