package com.number26.number26webservice;
/**
 *
 * @author ISHVARYA
 */
import java.util.Iterator;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.json.JSONObject; 
import org.json.JSONArray;
import org.json.JSONException;
import static org.json.JSONObject.NULL;

@Path("/gettransactionservice")
public class GetTransactionService {
    @Path("/getsum/{c}")
    @GET
    @Produces("application/xml")
    public String getSum(@PathParam("c") Long c) throws JSONException {
        
        Long search_id = c;   
        JSONArray list = putTransactionData();
        
        String result1 = "\nExisting Transactions in the System are: \n"+list ;
        String result2 = "\n";
        
        long transaction_id;
        String type;
        double amount;
        long p_id;
        double sum = 0.00;
        
        for(int i = 0; i < list.length(); i++) 
        {
            JSONObject object = list.getJSONObject(i);
            transaction_id = (long) object.get("id"); 
            type = object.get("type").toString();
            amount = (double) object.get("amount");
            p_id = (long) object.get("parent_id");
            if(transaction_id == search_id || p_id == search_id){
                sum = sum + amount;    
            }
            //result3 = "tran ID:\n"+transaction_id+"\nType\n"+type+"\nAmount\n"+amount+"\nParent ID\n"+p_id;
        }
        result2 = "\nThe total amount of Transaction ID \n"+ search_id + "\n is: \n"+sum;
        return "<getTransactionService>" + "<getAllTransactions>" + result1 + "</getAllTransactions>" + "<searchCriteria>" + "Input Transaction ID is: " + search_id + "</searchCriteria>" + "<getSum>" + result2 + "</getSum>" + "</getTransactionService>";
    } 
    @Path("/gettransactionbytype/{d}")
    @GET
    @Produces("application/xml")
    public String getTransactionsbyType(@PathParam("d") String d) throws JSONException{
	String search_type = d;
        JSONArray list = putTransactionData();
        
        long transaction_id;
        String type;
        double amount;
        long p_id;
        String result = "\nThe Transactions of Type \n" + search_type + "\n in the System are: \n";
        String result1 = "\nExisting Transactions in the System are: \n"+list ;
        
        for(int i = 0; i < list.length(); i++) 
        {
            JSONObject object = list.getJSONObject(i);
            transaction_id = (long) object.get("id"); 
            type = object.get("type").toString();
            amount = (double) object.get("amount");
            p_id = (long) object.get("parent_id");
            if(search_type.equalsIgnoreCase(type)){
                result = result + "\n(Transaction ID: \n" + transaction_id + "\nAmount: \n"+ amount +"\nParent Transaction ID: \n" + p_id + "\n), \n";    
            }
        }
	return "<getTransactionService>" + "<getAllTransactions>" + result1 + "</getAllTransactions>" + "<searchCriteria>" + "The Input Transaction Type is: " + search_type + "</searchCriteria>" + "<getTransactionsbyType>" + result + "</getTransactionsbyType>" + "</getTransactionService>";
    }
    public JSONArray putTransactionData() throws JSONException{
        
        JSONObject obj1 = new JSONObject();
        obj1.put("id", new Long(1));
        obj1.put("type", "deposit");
        obj1.put("amount", new Double(1000.21));
        obj1.put("parent_id", new Long(0));
        
        JSONObject obj2 = new JSONObject();
        obj2.put("id", new Long(2));
        obj2.put("type", "credit1");
        obj2.put("amount", new Double(800.90));
        obj2.put("parent_id", new Long(1));  
        
        JSONObject obj3 = new JSONObject();
        obj3.put("id", new Long(3));
        obj3.put("type", "credit2");
        obj3.put("amount", new Double(500.80));
        obj3.put("parent_id", new Long(1)); 
        
        JSONObject obj4 = new JSONObject();
        obj4.put("id", new Long(4));
        obj4.put("type", "withdrawal");
        obj4.put("amount", new Double(700.80));
        obj4.put("parent_id", new Long(0)); 
        
        JSONObject obj5 = new JSONObject();
        obj5.put("id", new Long(5));
        obj5.put("type", "debit");
        obj5.put("amount", new Double(400.80));
        obj5.put("parent_id", new Long(4)); 
        
        JSONObject obj6 = new JSONObject();
        obj6.put("id", new Long(6));
        obj6.put("type", "deposit");
        obj6.put("amount", new Double(100.55));
        obj6.put("parent_id", new Long(0));
        
        JSONObject obj7 = new JSONObject();
        obj7.put("id", new Long(7));
        obj7.put("type", "deposit");
        obj7.put("amount", new Double(300.22));
        obj7.put("parent_id", new Long(6));
        
        JSONObject obj8 = new JSONObject();
        obj8.put("id", new Long(8));
        obj8.put("type", "withdrawal");
        obj8.put("amount", new Double(480.87));
        obj8.put("parent_id", new Long(0)); 
        
        JSONArray list1 = new JSONArray();
	list1.put(obj1);
	list1.put(obj2);
	list1.put(obj3);
        list1.put(obj4);
        list1.put(obj5);
        list1.put(obj6);
        list1.put(obj7);
        list1.put(obj8);
        
        //String result1 = "Transactions:\n\n"+ obj1 + " , " + obj2 + " , " + obj3 + " , " + obj4 + " , " + obj5;
        return list1;
    }
}
