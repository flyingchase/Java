package org.javaTry.ioc;

import org.javaTry.ioc.model.Employee;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.StreamHandler;

public class lambdaDemo {

    List<Employee> emps = Arrays.asList(new Employee("张三", 13, 9999.99),
            new Employee("李四", 67, 444.44),
            new Employee("王五", 45, 55.55),
            new Employee("赵六", 45, 6666.66));



    public void test1() {
        Collections.sort(emps,(Employee e1,Employee e2)->{
            if (e1.getAge()==e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            }else {
                return e1.getAge().compareTo(e2.getAge());
            }
        });

        for (Employee e1 : emps) {
            System.out.println(e1);
        }
    }
    public static void main(String[] args) {
        // 使用 lambda表达式作为方法的参数
        // Integer res = new lambdaDemo().operation(100, (x) -> x * x);
        // System.out.println(res);
        //
        // new lambdaDemo().test1();
        new lambdaDemo().test02();
    }

    // 定义方法 参数为函数式接口
    public Integer operation(Integer num, FunctionalInterfaceDemo funcInterface) {
        return funcInterface.getValue(num);
    }


    // 定义函数式接口 只有一个抽象方法的接口
    /*接口没有() 无返回参数声明 */
    public interface FunctionalInterfaceDemo {
        public Integer getValue(Integer num);
    }



    @FunctionalInterface
    public interface functionDemo{
        public String getString(String str);
    }

    public String funcMethod(String str, functionDemo func) {
        return func.getString(str);
    }

    public void test02() {
        System.out.println(funcMethod("  \\t\\t\\t\\tH  el  lo  \\ Wor\\ d", (String str) -> str.trim()));
        System.out.printf("tolowerCase: ", funcMethod("GYFYFYSA", (String::toLowerCase)));
        System.out.println();
        System.out.printf("cut: ", funcMethod("woshizhu", (str) -> str.substring(0, 5)));
        System.out.println();


    }

}


