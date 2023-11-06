package Testing;

import Testing.Tests.Octree.ActiveBranchTesting;
import Testing.Tests.Octree.KeyTesting;
import Testing.Tests.Octree.LeafTesting;
import Testing.Tests.Octree.OctreTesting;
import Testing.Tests.WorldTesting;

import java.util.Scanner;
/*~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Testing
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  This is a custom testing interface used for not only unit testing
 *  but testing the functionality of components together
 */
public class Testing {
    private Scanner scanner = new Scanner(System.in);
    private String[] testAll = {"test"};
    private String[] packages = {"testAll"};
    public Testing(){
        System.out.println("A testing object has been created");

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *  Testing access methods
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Used for navigating testing packages
     */
    public void test(String test){
        //runes tests based off string input.
        switch (test){
            //return the help menu
            case "testAll": testAll();
                break;
            //return the help menu
            case "help": help();
                break;
            //return the help menu
            case "menu": menu();
                break;
        }
    }

    private void menu()
    {
        //provide instructions
        System.out.println(""" 
        Use the testing menu to run tests
        """);

        //display all testing packages
        for (int x = 0; x<packages.length; x++){
            System.out.println(x+1 + " : " + packages[x]);
        }

        //take user input
        System.out.print("Run package : ");
        String testingPackage = scanner.nextLine();
        System.out.println();

        switch (testingPackage){
            //return the help menu
            case "testAll": help();
                break;
        }
    }

    //Runs the help commands
    private void help(){
        System.out.println(""" 
        Use the test() function to run a test or a testing package. Or
        for use test(menu) for a text based menu to run tests from.
        """);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Class testing
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Used for testing classes
     */
    public void testAll(){
        testKey();
        testOctre();
        testActiveBranch();
        testWorld();
    }

    //test my octree class
    public void testOctre(){
        new OctreTesting().run();
    }
    public void testWorld() {
        new WorldTesting().run();
    }

    public void testActiveBranch() {
        new ActiveBranchTesting().run();
    }

    public void testLeaf(){
        new LeafTesting().run();
    }

    public void testKey(){
        new KeyTesting().run();
    }
}
