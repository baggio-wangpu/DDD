### 本地环境启动  

---
依赖项：由于依赖的kafka和redis的容器，请先启动business中的docker-compose后启动本项目

```.env
docker-compose up -d
./gradlew bootRun
```
