import java.util.*;
import java.sql.*;

public class Main {

    public static Connection getConnection()
    {
        String url = "jdbc:mysql://localhost:3306/company1";
        String username = "root";
        String passwrod = "root";
        try {
            return DriverManager.getConnection(url,username,passwrod);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void addEmployees(Connection con, Scanner sc)
    {
        System.out.println("Enter Employee ID:");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Employee Name:");
        String name = sc.nextLine();
        System.out.println("Enter Employee Age:");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Department:");
        String department= sc.nextLine();
        System.out.println("Enter Salary:");
        double salary = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter City:");
        String city = sc.nextLine();

        String add = "INSERT INTO employees(id,name,age,department,salary,city) values(?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(add);

            ps.setInt(1,id);
            ps.setString(2,name);
            ps.setInt(3,age);
            ps.setString(4,department);
            ps.setDouble(5,salary);
            ps.setString(6,city);

            int row = ps.executeUpdate();

            if(row>0)
            {
                System.out.println("Employee Added Successfully!");
            }
            else {
                System.out.println("Not Added!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void viewEmployees(Connection con)
    {
        try {

            String view = "Select * from employees";
            PreparedStatement ps = con.prepareStatement(view);
            ResultSet rs = ps.executeQuery();

            System.out.println("======================================================================");
            System.out.println("\t\t\tEMPLOYEE DETAILS");
            System.out.println("======================================================================");

            System.out.printf("%-5s %-20s %-5s %-15s %-12s %-15s%n",
                    "ID", "Name", "Age", "Department", "Salary", "City");

            System.out.println("----------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-5d %-20s %-5d %-15s %-12.2f %-15s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getString("city"));
            }

            System.out.println("======================================================================");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void searchEmployee(Connection con,Scanner sc)
    {
        System.out.println("Enter Id For Search : ");
        int id = sc.nextInt();

        String search = "select * from employees where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(search);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next())
            {
                System.out.println("======================================================================");

                System.out.printf("%-5s %-20s %-5s %-15s %-12s %-15s%n",
                        "ID", "Name", "Age", "Department", "Salary", "City");

                System.out.println("----------------------------------------------------------------------");

                System.out.printf("%-5d %-20s %-5d %-15s %-12.2f %-15s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getDouble("salary"),
                        rs.getString("city"));

                System.out.println("======================================================================");
            }
            else
            {
                System.out.println("Employee Not Found!");
            }


        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public static void updateEmployee(Connection con, Scanner sc)
    {
        System.out.println("Enter Id : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter New Name:");
        String name = sc.nextLine();
        System.out.println("Enter New Age:");
        int age = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter New Department:");
        String department = sc.nextLine();
        System.out.println("Enter New Salary:");
        double salary = sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter New City:");
        String city = sc.nextLine();

        String update = "UPDATE employees SET name = ?, age = ?, department = ?, salary = ?, city = ? WHERE id = ?";

        try{

            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1,name);
            ps.setInt(2,age);
            ps.setString(3,department);
            ps.setDouble(4,salary);
            ps.setString(5,city);
            ps.setInt(6,id);

            int row = ps.executeUpdate();

            if(row>0)
            {
                System.out.println("Employee Updated ");
            }
            else {
                System.out.println("Employee Not Updated !");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteEmployee(Connection con,Scanner sc)
    {
        System.out.println("Enter Id : ");
        int id = sc.nextInt();

        String delete= "Delete from employees where id = ?";

        try {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1,id);

            int row = ps.executeUpdate();

            if(row>0)
            {
                System.out.println("Employee Delete Successfully...");
            }
            else {
                System.out.println("Not Delete Something is wrong ");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
   }

   public static void searchByDepartment(Connection con,Scanner sc)
   {
       sc.nextLine();
       System.out.println("Enter Department : ");
       String department = sc.nextLine();

       String searchbyd = "Select * from employees where department = ?";

       try
       {
           PreparedStatement ps = con.prepareStatement(searchbyd);
           ps.setString(1, department);

           ResultSet rs = ps.executeQuery();

           boolean found = false;

           while (rs.next())
           {
               if (!found)
               {
                   System.out.println("======================================================================");

                   System.out.printf("%-5s %-20s %-5s %-15s %-12s %-15s%n",
                           "ID", "Name", "Age", "Department", "Salary", "City");

                   System.out.println("----------------------------------------------------------------------");

                   found = true;
               }

               System.out.printf("%-5d %-20s %-5d %-15s %-12.2f %-15s%n",
                       rs.getInt("id"),
                       rs.getString("name"),
                       rs.getInt("age"),
                       rs.getString("department"),
                       rs.getDouble("salary"),
                       rs.getString("city"));
           }

           if (found)
           {
               System.out.println("======================================================================");
           }
           else
           {
               System.out.println("No Employee Found in " + department + " Department!");
           }

       }
       catch (Exception e)
       {
           System.out.println(e.getMessage());
       }
   }

   public static void heighestSalary(Connection con)
   {
       String highestSalary =
               "SELECT * FROM employees " +
                       "WHERE salary = (SELECT MAX(salary) FROM employees)";

       try{

           PreparedStatement ps = con.prepareStatement(highestSalary);
           ResultSet rs = ps.executeQuery();

           if (rs.next())
           {
               System.out.println("======================================================================");

               System.out.printf("%-5s %-20s %-5s %-15s %-12s %-15s%n",
                       "ID", "Name", "Age", "Department", "Salary", "City");

               System.out.println("----------------------------------------------------------------------");

               do
               {
                   System.out.printf("%-5d %-20s %-5d %-15s %-12.2f %-15s%n",
                           rs.getInt("id"),
                           rs.getString("name"),
                           rs.getInt("age"),
                           rs.getString("department"),
                           rs.getDouble("salary"),
                           rs.getString("city"));

               } while (rs.next());

               System.out.println("======================================================================");
           }
           else
           {
               System.out.println("No Employees Found!");
           }

       }catch (Exception e)
       {
           System.out.println(e.getMessage());
       }

   }

   public static void averageSalary(Connection con)
   {
       String averagSal = "Select avg(salary) from employees";

       try {

           PreparedStatement ps = con.prepareStatement(averagSal);
           ResultSet rs = ps.executeQuery();

           if(rs.next())
           {
               System.out.println("Average Salary : "+rs.getDouble("avg(salary)"));
           }

       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }

   public static void countEmployee(Connection con)
   {
       String countEmp = "Select count(*) from employees";

       try {

           PreparedStatement ps = con.prepareStatement(countEmp);
           ResultSet rs = ps.executeQuery();

           if(rs.next())
           {
               System.out.println("Employee Count : "+rs.getInt("count(*)"));
           }

       } catch (Exception e) {
           System.out.println(e.getMessage());
       }
   }



    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();

        do{

            System.out.println("=========================================");
            System.out.println("EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("=========================================");

            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Search By Department");
            System.out.println("7. Find Highest Salary");
            System.out.println("8. Calculate Average Salary");
            System.out.println("9. Count Employees");
            System.out.println("10. Exit");

            System.out.println("=========================================");

            System.out.println("Enter Your Choice : ");
            int choice = sc.nextInt();

            System.out.println("=========================================");

            switch (choice)
            {
                case 1:
                    addEmployees(con,sc);
                    break;

                case 2:
                    viewEmployees(con);
                    break;

                case 3:
                    searchEmployee(con,sc);
                    break;

                case 4:
                    updateEmployee(con,sc);
                    break;

                case 5:
                    deleteEmployee(con,sc);
                    break;

                case 6:
                    searchByDepartment(con,sc);
                    break;

                case 7:
                    heighestSalary(con);
                    break;

                case 8:
                    averageSalary(con);
                    break;

                case 9:
                    countEmployee(con);
                    break;

                case 10:
                    System.out.println("Thank You!");
                    con.close();
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice : Please Enter Correct Choice !");
                    break;

            }
        }while(true);

    }
}

