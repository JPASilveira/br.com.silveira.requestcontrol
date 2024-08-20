package view;

public class MenuPrinter {
    public static void MainMenu(){
        System.out.println("         REQUEST CONTROL        ");
        System.out.println("(A)pplicant | (R)equest | (E)xit");
    }
    public static void applicantMenu(){
        System.out.println("             APPLICANT              ");
        System.out.println("(A)dd | (E)dit | (D)elete | (R)eturn");
    }

    public static void applicantEditMenu(){
        System.out.println("       EDIT APPLICANT         ");
        System.out.println("(N)ame | (D)ocument | (R)eturn");
    }

    public static void requestMenu(){
        System.out.println("              REQUEST               ");
        System.out.println("(A)dd | (E)dit | (D)elete | (R)eturn");
    }

    public static void requestEditMenu(){
        System.out.println("                              EDIT REQUEST                              ");
        System.out.println("(A)pplicant | (O)pening Date | (C)losign Date | (D)escription | (R)eturn");
    }
    public static void RequestMenu(){
        System.out.println("      SELECT OR LIST        ");
        System.out.println("(S)elect | (L)ist | (R)eturn");
    }

    public static void listMenu(){
        System.out.println("               LIST               ");
        System.out.println("(A)ll | (N)ame | (D)ate | (R)eturn");
    }

}
