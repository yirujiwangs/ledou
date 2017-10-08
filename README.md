# ledouyaControl

# 环境配置

## java
- jdk 1.7 tomcat7.0
- IDE: Idea 14.1.4
- **file->new->project form existing sources->create project from existing sources**->next...
- file->settings->java compiler->eclipse
- file->project structure->moudules->勾选lib
- file->project structure->Artifacts->+号->webApplication exploded->from moudules
- run->edit configruations->+号->tomcat Server->Local
- run->edit configruations->server->configure选择tomcat路径
- run->edit configruations->deployment->Application context填写/ledou

## 前端
- cd webRoot
- npm install
- excluede node_moudules
- 发布时按顺序执行gulp中的任务，输出至dist

# web2
- 控制中心