

# Maven

## 引言

使用 IDEA 更新 maven 索引时 将中心仓库内文件下载本地 使用本地 web 服务器将下载下来properites和 repository 上传 使用 switchHost修改本地 hosts , 使得 idea 远程更新访问本地 web

发现 maven项目管理工具不够熟练 故搜集资料学习



主要参考：

[maven学习总结](https://www.cnblogs.com/xdp-gacl/tag/Maven%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93/)





## maven结构



　Maven目录分析

- bin：含有mvn运行的脚本
- boot：含有plexus-classworlds类加载器框架
- conf：含有settings.xml配置文件
- lib：含有Maven运行时所需要的java类库
- LICENSE.txt, NOTICE.txt, README.txt针对Maven版本，第三方软件等简要介绍

![image-20210730172730193](/Users/qlzhou/Library/Application Support/typora-user-images/image-20210730172730193.png)





## 项目结构

``` xml
　　Hello
　　　　　　| --src
　　　　　　| -----main
　　　　　　| ----------java
　　　　　　| ----------resources
　　　　　　| -----test
　　　　　　| ---------java
　　　　　　| ---------resources
　　　　　　| --pom.xml
```