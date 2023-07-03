
import java.util.*;
import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
public class Hypermarket
{
    public static void main(String [] arg)
    {
        DecimalFormat df= new DecimalFormat("##0.00");
        try
        {//opening of try block

            //create Queue for counter 1,2 and 3
            Queue qCounter1 =new Queue();
            Queue qCounter2 =new Queue();
            Queue qCounter3 =new Queue();
            //instantiate stack for paid completed
            Stack completeStack =new Stack();

            //instantiate object of bufferedreader to store input file temporarily and read from input file by wrapping filereader 
            BufferedReader br= new BufferedReader(new FileReader("Customer.txt"));
            LinkedList<CustomerInformation> customerList = new LinkedList<>();

            String inData=null;

            //instantiate object of CustomerInformation and ItemInformation
            CustomerInformation cust;
            ItemInformation item;

            while((inData=br.readLine())!=null)
            {
                StringTokenizer st= new StringTokenizer(inData,";");
                String custId = st.nextToken();
                long custIc =Long.parseLong(st.nextToken());
                double counterPaid=Double.parseDouble(st.nextToken());

                StringTokenizer s2= new StringTokenizer(st.nextToken(), ":");
                ArrayList<ItemInformation> purchasedItems=new ArrayList<>();
                while(s2.hasMoreTokens())
                {
                    StringTokenizer s3= new StringTokenizer(s2.nextToken(),",");
                    String itemId=s3.nextToken();
                    String itemName=s3.nextToken();
                    double itemPrice=Double.parseDouble(s3.nextToken());
                    String datePurchase=s3.nextToken();

                    DecimalFormat decimalFormat = new DecimalFormat("#.00"); // instantiate Decimal format object to two decimal places
                    String formattedNumber = decimalFormat.format(itemPrice); 
                    double itemPriceAfterFormat = Double.parseDouble(formattedNumber); 

                    item=new ItemInformation(itemId,itemName,itemPriceAfterFormat,datePurchase);
                    purchasedItems.add(item);

                    counterPaid+=itemPrice;
                }
                //invoke constructor with parameter of CustomerInformation
                cust=new CustomerInformation(custId,custIc,counterPaid);

                cust.addItem(purchasedItems);
                //add cust into LinkedList(customerList)
                customerList.add(cust);
            }
            br.close();//close bufferedReader

            int counter1Count = 0;
            int counter2Count = 0;
            int counterToEnqueue = 1; // Variable to track which counter to enqueue the customer (1 for qCounter1, 2 for qCounter2)
            String counter=null;
            for (int i = 0; i < customerList.size(); i++) {
                cust = customerList.get(i);

                // Add customer who bought less than or equal to 5 items alternately between counter 1 and counter 2 until both counters have 5 customers
                if (cust.getItemQty() <= 5) 
                {
                    if (counterToEnqueue == 1) 
                    {
                        if (counter1Count < 5) 
                        {
                            cust.setCounter(1);
                            qCounter1.enqueue(cust);
                            counter1Count++;
                        } 
                        else 
                        {
                            cust.setCounter(2);
                            qCounter2.enqueue(cust);
                            counter2Count++;
                            counterToEnqueue = 2; // Switch to enqueueing to qCounter2
                        }
                    } 
                    else if (counterToEnqueue == 2)
                    {
                        if (counter2Count < 5) {
                            cust.setCounter(2);
                            qCounter2.enqueue(cust);
                            counter2Count++;
                            
                        } 
                        else 
                        {
                            cust.setCounter(1);
                            qCounter1.enqueue(cust);
                            counter1Count++;
                            counterToEnqueue = 1; // Switch to enqueueing to qCounter1
                            
                        }
                    }
                } 
                else 
                {
                    cust.setCounter(3);
                    counter="3";
                    qCounter3.enqueue(cust);
                    
                }
            }
            
            //to display customer in counter 1, 2 and 3
            System.out.println("\t\t\t *CUSTOMERS IN COUNTER 1*");
            System.out.println("\t\t\t=========================");
            System.out.println("*******************************************************************************************************************************************************");
            //using loop it will iterate customer in counter 1,2 and 3 and display it accordingly 
            Queue qCounter11= new Queue();
            Queue qCounter22= new Queue();
            Queue qCounter33= new Queue();
            while (!qCounter1.empty()) 
            {
                counter=("1");
                CustomerInformation custe = (CustomerInformation) qCounter1.dequeue();
                custe.display();
                qCounter11.enqueue(custe);
            }
            System.out.println("*******************************************************************************************************************************************************\n");
            System.out.println("\t\t\t*CUSTOMERS IN COUNTER 2*");
            System.out.println("\t\t\t=========================");
            System.out.println("*******************************************************************************************************************************************************");
            while (!qCounter2.empty()) 
            {
                CustomerInformation custe = (CustomerInformation) qCounter2.dequeue();
                custe.display();
                qCounter22.enqueue(custe);
            }
            System.out.println("*******************************************************************************************************************************************************\n");
            System.out.println("\t\t\t*CUSTOMERS IN COUNTER 3*");
            System.out.println("\t\t\t=========================");
            System.out.println("*******************************************************************************************************************************************************");
            while (!qCounter3.empty()) 
            {
                
                CustomerInformation custe = (CustomerInformation) qCounter3.dequeue();
                custe.display();
                qCounter33.enqueue(custe);
            }
            System.out.println("*******************************************************************************************************************************************************");

            int menu=0;
            do
            {
                String inp;
                inp= JOptionPane.showInputDialog("\tHYPERMARKET SELF-CHECKOUT AUTOMATED SYSTEM"
                    +"\n*************************************************************************"
                    +"\nMenu 1- remove 5 and add 5 new customer at counter 1*"
                    +"\nMenu 2- remove 5 and add 5 new customer at counter 2*"
                    +"\nMenu 3- remove and add new customer at counter 3*"
                    +"\nMenu 4- exit and display completed customer ");                                    
                menu=Integer.parseInt(inp);
                int customerCount = 0;
                if(menu==1)
                {
                    while(!qCounter11.empty())
                    {
                        //calculate 5 customer payment and remove them to completedStack, then add 5 new customer
                        for(int i=0;i<5;i++)
                        {
                            cust=(CustomerInformation) qCounter11.dequeue();

                            //add new customer to counter 5
                            qCounter11.enqueue(cust);
                            //calculate totpayment customer that has been dequeued
                            cust.setCounterPaid(cust.getTotalAmountPaid());

                            //push customer who has completed payment into stack
                            completeStack.push(cust);
                            
                        }
                        String answer=JOptionPane.showInputDialog("do you want to continue remove 5 customer<Yes/No>");
                        if(answer.equalsIgnoreCase("yes"))
                        {
                            continue;
                        }
                        else
                            break;
                    }
                }
                else if(menu==2)
                {
                    while(!qCounter22.empty()&&customerCount<5)
                    {
                        for(int i=0;i<5;i++)
                        {
                            cust=(CustomerInformation) qCounter22.dequeue();

                            //add new customer to counter 2
                            qCounter22.enqueue(qCounter22.dequeue());
                            //calculate totpayment customer that has been dequeued
                            cust.setCounterPaid(cust.getTotalAmountPaid());
                            //push customer who has completed payment into stack
                            completeStack.push(cust);
                            
                        }
                        String answer=JOptionPane.showInputDialog("do you want to continue remove 5 customer<Yes/No>");
                        if(answer.equalsIgnoreCase("yes"))
                        {
                            continue;
                        }
                        else
                            break;
                    }
                }
                else if(menu==3)
                {
                    while(!qCounter33.empty())
                    {
                        cust=(CustomerInformation) qCounter33.dequeue();
                        //add new customer to counter 3
                        qCounter33.enqueue(qCounter33.dequeue());
                        //calculate totpayment customer that has been dequeued
                        cust.setCounterPaid(cust.getTotalAmountPaid());
                        //push customer who has completed payment into stack
                        completeStack.push(cust);
                    

                        String answer=JOptionPane.showInputDialog("do you want to continue remove customer<Yes/No>");
                        if(answer.equalsIgnoreCase("yes"))
                        {
                            continue;
                        }
                        else
                            break;
                    }
                }
            }while(menu !=4);
            System.out.println("\t\t\t*********************************************");
            System.out.println("\t\t\t|         PAYMENT COMPLETED                 |");
            System.out.println("\t\t\t*********************************************");
            while(!completeStack.empty())
            {

                cust = (CustomerInformation) completeStack.pop();

                //main head resit
                String text = ("*******RESIT*******");
                String centeredText = String.format("%54s", text);
                System.out.println(centeredText);
                System.out.println("\t==============================================================================");
                System.out.println("\tCust Ic:" + cust.getCustIC() + "\n\tCust Id:" + cust.getCustID()+"\n\tCounter:"+cust.getCounter());//display ic and id 
                //head information item
                System.out.println("\t==============================================================================");
                System.out.println("\tDate           Item Id               Item Name               Item price(RM)");
                System.out.println("\t==============================================================================");

                int itemIndex = 0;
                // Iterate over the purchased items of the customer
                for (ItemInformation item2 : cust.getPurchasedItems()) 
                { 
                    System.out.print(String.format("\t|%-5s", item2.getDatePurchase()));
                    System.out.print(String.format("|\t%-5s", item2.getItemId()));
                    System.out.print(String.format("\t|%21s", item2.getItemName()));
                    System.out.print(String.format("\t|%21.2f",item2.getItemPrice()));
                    itemIndex++;//increase itemIndex value
                    //check if itemindex value equals to  cust.purchasedItems.size() or not , if yes then loop stops
                    if (itemIndex == cust.purchasedItems.size()) {
                        System.out.println("\n\t==============================================================================");
                        System.out.println("\tTOTAL:" + String.format("%64s","("+( df.format(cust.getTotalAmountPaid() ))+  ")"));// Print the total amount at the end

                    }
                    System.out.print("\n");
                }
            }
        }
        //catch block
        catch(FileNotFoundException ex) {
            System.out.println("File not found");
        }
        catch(EOFException ex) {
            System.out.println("Unexpected end of file");
        }
        catch(NumberFormatException ex) {
            System.out.println("Number format incorrect");
        }
        catch(IOException ex) {
            System.out.println("Input/Output Error");
        }
    }
}
