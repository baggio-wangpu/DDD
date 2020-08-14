### 介绍
这是一个基于Spring Boot，DDD洋葱模型的代码demo。


### 本地环境启动  

---
依赖项：由于依赖的kafka和redis的容器，请先启动business中的docker-compose后启动本项目

```.env
docker-compose up -d
./gradlew bootRun
```

### 本机API测试环境
内嵌mysql和redis启动依赖openssl 1.0，如果没有安装openssl,请运行命令：  
```  
brew install OpenSSL
```
如果openssl 已安装，但版本不是1.0, 请运行命令：
```
brew switch openssl 1.0.2r 
```

### uml tool
在IntelliJ IDEA 中安装 PlantUML插件，即可打开*.puml文件