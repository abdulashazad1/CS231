/**
 * Author: Abdullah Shahzad
 * Project 6
 * Section B Lab C
 * Date: 3/31/2022
 */

 import java.awt.*;

public class CheckoutAgent {
    // Fields for the x and y positions of the customer
    int xPos; 
    int yPos;

    // Queue for customers
    MyQueue<Customer> que;

    // Constructor for the checkout agent
    public CheckoutAgent(int xPos, int yPos){
        // Initializing the position fields and an empty queue
        this.xPos = xPos;
        this.yPos = yPos;
        que = new MyQueue<>();
    }

    // Adds a customer to the queue
    public void addCustomerToQueue(Customer c){
        que.offer(c);
    }

    // Returns the number of customers in the queue
    public int getNumInQueue(){
        return que.size();
    }

    // Update method 
    public void updateState( Landscape scape ){
        if (que.peek() == null){
            return;} else {
                for (Customer c : que){
                    c.incrementTime();
                }
                que.peek().giveUpItem();
                if (que.peek().getNumItems() == 0){
                    Customer removed = que.poll();
                    scape.addFinishedCustomer(removed);
                }
            }
        }

    // Draws the checkout agent
    public void draw(Graphics g){
        Graphics2D gr=(Graphics2D) g;
        int height = que.size()*10;
        gr.setColor(Color.RED);
        gr.setPaint(Color.RED);
        gr.fillRect(this.xPos, this.yPos-height, 10, height);
        gr.drawRect(this.xPos, this.yPos-height, 10, height);
        
    }
}
