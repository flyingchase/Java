# SSM-LearningNotes

Spring SpringMVC MyBatis







## 1 Spring

Spring



### 1.1 IOC



控制反转  `Inversion of Control` 类——>对象 对象的创建和初始化 销毁必须是要开发者自己完成 

Spring Bean容器可以自动完成



集合数组注入：

``` xml
<bean class="org.javaTry.ioc.model.User" id="user4">
    <property name="address" value="flyingchase.github.io"/>
    <property name="id" value="4"/>
    <property name="username" value="wlzhou"/>
    <property name="cat" ref="cat"/>
    <property name="cats">
        <array>
            <ref bean="cat"/>
            <bean class="org.javaTry.ioc.model.Cat" id="cat2">
                <property name="age" value="1"/>
                <property name="name" value="greatNameTwo"/>
            </bean>
        </array>
    </property>
</bean>
<bean class="org.javaTry.ioc.model.Cat" id="cat">
    <constructor-arg value="2"/>
    <property name="age" value="100"/>
    <property name="name" value="greatName"/>
</bean>
```

Map/Properties注入:

``` xml
<property name="details">
    <map>
        <entry key="gender" value="1"/>
        <entry key="age" value="46"/>
    </map>
</property>
<property name="info">
    <props>
        <prop key="phone">110</prop>
        <prop key="address">weidu</prop>
    </props>
</property>
```



Bean注册到Spring容器中有三种方式：	

- XML注入

- Java配置 SB中不使用xml配置

  - `@Configuration`  `@Bean`

  - ``` java
    @Configuration
    public class javaConfig {
        @Bean
        SayHello sayHello() {
            return new SayHello();
        }
    }
    
    public class SayHello {
        public String sayHello(String name) {
            return "hello " + name;
        }
    }
    
    public class JavaConfigTest {
        public static void main(String[] args) {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(javaConfig.class);
            SayHello sayHello = ctx.getBean("sayHello", SayHello.class);
            System.out.println( sayHello.sayHello("jianyi"));
        }
    }
    ```

  - 

- 自动化扫描



### 1.2 AOP





## 2 SpringMVC







## 3 MyBatis

