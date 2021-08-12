package Lambda;

public class LambdaConcept {
    public void printsth(String waitToPrint) {
        System.out.println(waitToPrint);
    }
    interface printSthInterface {
        void print(String value);
    }

    public void printSthWithInterface(String waitToPrint, printSthInterface printSthInterface) {
        printSthInterface.print(waitToPrint);
    }

    public static void main(String[] args) {
        LambdaConcept lambdaDemo = new LambdaConcept();
        String waitToPrint = "This is the String waiting to print";
        lambdaDemo.printsth(waitToPrint);

        String waitToPrintWithInterface = "This is Functional interface ";

//        printSthInterface
    }

}
