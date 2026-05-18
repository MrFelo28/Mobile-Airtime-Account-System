class MobileAccount{
    private String ownerName;
    private String phoneNumber;
    private double balance;
    private boolean active;

//priavte static fields
    private static int totalAccountsCreated=0;
    private static double totalMoneyLoaded=0.0;

//static final constants
    static final double RATE_PER_MINUTE=30.0;
    static final double SMS_COST=50.0;
    static final double MAX_TOPUP=100000.0;

//Constructor
    MobileAccount(String ownerName, String phoneNumber){
        this.ownerName=ownerName;
        this.phoneNumber=phoneNumber;
        this.balance=0.0;
        this.active=true;
        totalAccountsCreated++;
    }   

//methods

   public boolean topUp(double amount){
    if(amount>0 && amount <= MAX_TOPUP){
        balance+=amount;
        totalMoneyLoaded+=amount;
        return true;
    }
    else{
        System.out.println("OUT OF RANGE!");
        return false;
    }
   }

   public boolean makeCall(double minutes){
    if(!active){
        System.out.println("Customer["+phoneNumber+"]-"+ownerName+": CALL FAILED DUE TO INACtIVE ACCOUNT!");
        return false;
    }
    double cost=minutes* RATE_PER_MINUTE;

    if(balance<cost){
        System.out.println("Customer["+phoneNumber+"]-"+ownerName+": CALL FAILED DUE TO INSUFFICIENT BALANCE!");
        return false;
    }
    balance-=cost;
    return true;
   }

   public boolean sendSms(int count){
     if(!active){
        System.out.println("Customer["+phoneNumber+"]-"+ownerName+ ": SMS NOT SENT DUE TO INACTIVE ACCOUNT!");
        return false;
    }
    double cost=count*SMS_COST;

    if(balance<cost){
        System.out.println("Customer["+phoneNumber+"]-"+ownerName+ ": SMS NOT SENT DUE TO INSUFFICIENT BALANCE!");
        return false;
    }
    balance-=cost;
    return true;
   }

  public void deactivate(){
    active=false;
   }
     
  public void activate(){
    active=true;
   }

  public double getBalance(){
    return balance;
   }

  public static int getTotalAccountsCreated(){
    return totalAccountsCreated;
   }

  public static double getTotalMoneyLoaded(){
    return totalMoneyLoaded;
   }

   public void printStatement(){
    System.out.printf("[%s] %-20s Balance: %15.2f TZS  ACTIVE %s%n",
    phoneNumber,ownerName,balance,active);
   }
}

public class AirtimeSystem{
    public static void main(String[] args){
        MobileAccount acc1=new MobileAccount("Amina Hassan","0712-345-678");
        MobileAccount acc2=new MobileAccount("Baraka Juma","0755-987-654");
        MobileAccount acc3=new MobileAccount("Neema Salehe","0623-111-222");
     
    //topup
    acc1.topUp(10000);
    acc2.topUp(5000);
    acc3.topUp(20000);

    //tasks for acc1
    acc1.makeCall(50.0);
    acc1.sendSms(5);

    //task for acc2
    acc2.makeCall(200.0);
    acc2.sendSms(150);

    //tasks for acc3
    acc3.makeCall(10000.0);
    acc3.sendSms(10);

    System.out.println("\n==========================================================");
    System.out.println("               CS 234 - AIRTIME ACCOUNT REPORT            ");
    System.out.println("==========================================================");
    acc1.printStatement();
    acc2.printStatement();
    acc3.printStatement();
    System.out.println("==========================================================");
    System.out.println("Total Accounts Created : "+ MobileAccount.getTotalAccountsCreated());
    System.out.println("Total Money Loaded     : "+ MobileAccount.getTotalMoneyLoaded()+" TZS");
    System.out.println("==========================================================");
}
}
