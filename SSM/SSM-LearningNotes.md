# SSM-LearningNotes

Spring SpringMVC MyBatis







## 1 Spring

Spring



### 1.1 IOC



控制反转  `Inversion of Control` 类——>对象 对象的创建和初始化 销毁必须是要开发者自己完成 

Spring Bean容器可以自动完成



- Property 定义类的属性
- id 是 Spring 找到 Bean 的编号 可默认生成 
- class 为类全限定名
- Ref 属性为自定义类

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

- Java配置  SB中不使用xml配置

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

  - `@Service` ——>`@Component` `@Repository` `@Service` `@Controller`

  - ``` java
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.stereotype.Component;
    
    @Component(value="role")
    // @Component("role")
    // @Componenet // 默认为类的首字母小写类名
    public class Role {
        @Value("1")
        private Long id;
        @Value("xiaoming")
        private String roleName;
        @Value("haha")
        private String note;
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getRoleName() {
            return roleName;
        }
    
        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }
    
        public String getNote() {
            return note;
        }
    
        public void setNote(String note) {
            this.note = note;
        }
    }
    ```

    

  - Bean的名字默认为类名称小写 或者Service(“自定义”)  

  - 在 `Java Config`中使用`@ComponentScan(basePacksges = "包名称的相对路径扫描", includeFiles = ...)` 确定自动化扫描的范围 默认为同级包及子包

  - 在 Spring IoC 容器的实现类`AnnotationConfigApplicationContext`中生成容器

    - ``` java
      import org.springframework.context.ApplicationContext;
      import org.springframework.context.annotation.AnnotationConfigApplicationContext;
      
      public class mainApp {
          public static void main(String[] args) {
              ApplicationContext context = new AnnotationConfigApplicationContext(PojoScan.class);
              Role role = context.getBean(Role.class);
              System.err.println(role.getId());
          }
      }
      ```

    

    **自动化扫描总结：** 

    - 需要实体类的定义 @Component作用于类+@Bean作用于方法
      - 
    - 需要 JavaConfig @ComponentScan 来使得 Spring 找到扫描 Bean 的包 默认同级包
    - 需要实现类 AnnotationConfigApplicationContext 生成从其
    - 

- 

### 1.2 AOP





## 2 SpringMVC







## 3 MyBatis

