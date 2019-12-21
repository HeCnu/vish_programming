public class task3 {
    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        int c = 10;

        System.out.println(solve(a, b, c));
    }

    public static String solve(int a, int b, int c){
        double x1 = 0, x2 = 0, D = 0;

        if(a != 0 && b != 0){
            D = b*b - 4*a*c;
            if(D >= 0){
                x1 = (-b + Math.sqrt(D))/2*a;
                x2 = (-b - Math.sqrt(D))/2*a;
            }
            else{
                return "No solves";
            }
        }
        if(a == 0 && b != 0){
            x1 = -c/b;
            return "x1 = " + x1;
        }
        if(a == 0 && b == 0){
            return "Not solves";
        }
        if(a != 0 && b == 0 && c != 0){
            if(a < 0){
                x1 = Math.sqrt(-c/a);
                x2 = -Math.sqrt(-c/a);
            }
            else {
                return "No solves";
            }
        }

        String strsolve = "x1 = " + x1 + "; x2 = " + x2;

        return strsolve;
    }
}