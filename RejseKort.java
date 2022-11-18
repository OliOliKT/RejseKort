public class RejseKort {
    
    private int balance = 100;
    private boolean checkedIn = false;
    private int lastCheckIn;
    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    public RejseKort() {
    }

    public void depositMoney(int dkk) {
        if (dkk < 0) {
            System.out.println("Error: Cannot deposit negative amount");
        }
        else {
            balance += dkk;
            System.out.println(dkk + " DKK deposited. New balance: " + balance + " DKK");
        }     
    }

    public boolean isCheckedIn(int timeStamp) {
        if (checkedIn) {
            if (timeStamp - lastCheckIn <= 120) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public void checkIn(int x, int y, int timeStamp) {
        if (!checkedIn) {
            if (balance >= 100) {
                checkedIn = true;
                lastCheckIn = timeStamp;
                minX = x;
                minY = y;
                maxX = x;
                maxY = y;
                System.out.println("Checked in");
            }
            else {
                System.out.println("Not enough money in account to check in. Please deposit at least " + (100-balance) + " DKK");
            }
        }
        else {
            if (minX > x) {
                minX = x;
            }
            if (minY > y) {
                minY = y;
            } 
            if (maxX < x) {
                maxX = x;
            } 
            if (maxY < y) {
                maxY = y;
            }
            int price = priceCalculation();
            if (price > 50) {
                price = 50;
            }
            if (price < 12) {
                price = 12;
            }
            System.out.println("Continued journey (" + (timeStamp - lastCheckIn) + " minutes since last check in)");
            lastCheckIn = timeStamp;
        }
    }

    public void checkOut(int x, int y, int timeStamp) {
        
        if (checkedIn) {
            checkedIn = false;
            if (minX > x) {
                minX = x;
            }
            if (minY > y) {
                minY = y;
            } 
            if (maxX < x) {
                maxX = x;
            } 
            if (maxY < y) {
                maxY = y;
            }
            int price = priceCalculation();
            if (price > 50) {
                price = 50;
            }
            if (price < 12) {
                price = 12;
            }
            balance -= price;
            System.out.println("Checked out! " + (timeStamp - lastCheckIn) + " minutes since last check in. Price is " + price + " DKK, remaining balance is " + balance + " DKK");
        }
        else {
        System.out.println("Error: Cannot check out; Not currently checked in");
        }
    }

    public int priceCalculation() {
        int price = (12 + (maxX - minX + maxY - minY) * 3);
        return price;
    }
}