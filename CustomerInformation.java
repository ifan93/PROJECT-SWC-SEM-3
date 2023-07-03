/**
 * Class definiton of CustomerInformation
 * muhammad irfan daniel
 * 26/6/23
 */
import java.util.*;
import java.text.DecimalFormat;
public class CustomerInformation
{
    //declare variables with private modifiers so it only can be accesed in this class
    private String custId;
    private long custIC;
    private double counterPaid;
    private int counter;
    LinkedList<ItemInformation> purchasedItems;
    DecimalFormat df= new DecimalFormat("###0.00");
    //constructorr with parameter to put initial value for each attributes
    public CustomerInformation(String custId,long custIC,double counterPaid)
    {
        this.custId=custId;
        this.custIC=custIC;
        this.counterPaid=counterPaid;
        this.purchasedItems = new LinkedList<>();
    }
    //accessor for each attributes to retrive attributes when method name is invoked
    public String getCustID()
    {
        return custId;
    }
    
    public int getCounter()
    {
        return counter;
    }

    public long getCustIC()
    {
        return custIC;
    }

    public double getCounterPaid()
    {
        return counterPaid;
    }

    public int getItemQty()
    {
        return purchasedItems.size();
    }

    //mutator(to change the value of each attributes after given initial value in the constructor) for each attributes
    public void setAll(String custId,long custIC,double counterPaid,LinkedList<ItemInformation> purchasedItems)
    {
        this.custId=custId;
        this.custIC=custIC;
        this.counterPaid=counterPaid;
        this.purchasedItems=purchasedItems;
    }

    public void purchaseItem(ItemInformation item) 
    {
        purchasedItems.add(item);
    }

    public void removePurchasedItem(ItemInformation item)
    {
        purchasedItems.remove(item);
    }

    public void addItem(List<ItemInformation> items) 
    {
        purchasedItems.addAll(items);
    }

    public LinkedList<ItemInformation> getPurchasedItems()
    {
        return purchasedItems;
    }

    public void setCounterPaid(double counterPaid)
    {
        this.counterPaid=counterPaid;
    }
    
    public void setCounter(int counter)
    {
        this.counter=counter;
    }

    public double getTotalAmountPaid() 
    {
        double totalAmount = 0.0;
        for (ItemInformation item : purchasedItems) 
        {
            totalAmount += item.getItemPrice();
        }
        return totalAmount;
    }
    
    //method to display
    public void display() 
    {
        double totalAmount = 0.0;

        System.out.printf("%-10s %-12s %-25s %-15s\n", "\tCust Id", "Item Id", "Item Name", "Item price(RM)");
        System.out.println("\t==================================================================");

        System.out.printf("\t%-10s %-12s %-25s %-15.2f\n", getCustID(), purchasedItems.get(0).getItemId(),
            purchasedItems.get(0).getItemName(), purchasedItems.get(0).getItemPrice());
        totalAmount += purchasedItems.get(0).getItemPrice();

        for (int i = 1; i < purchasedItems.size(); i++) 
        {
            System.out.printf("\t%-10s %-12s %-25s %-15.2f\n", "", purchasedItems.get(i).getItemId(),
                purchasedItems.get(i).getItemName(), purchasedItems.get(i).getItemPrice());
            totalAmount += purchasedItems.get(i).getItemPrice();
        }

        System.out.println("\t==================================================================");
        System.out.printf("\t\t\t\t\t\t\t   (total: RM%.2f)\n", totalAmount);
        System.out.println("\t==================================================================\n");
    }

}
