今天我们完成了一个巨大的跨越，你成功从“纯黑框终端”转移到了真正的**全栈开发环境 (VS Code)**，并打通了前后端与数据库的联调闭环。

为了方便你以后复习和快速查阅，我把你今天在 Kali 中用到的所有核心命令分门别类整理成了这份清单：

### 一、 网络代理与测试 (解决下载慢)

当你发现在虚拟机里下载外网资源（如插件、安装包）速度很慢时使用：

```bash
# 1. 临时设置终端走宿主机的代理 (注意替换你的宿主机 IP 和端口)
export http_proxy="http://192.168.0.112:7897"
export https_proxy="http://192.168.0.112:7897"

# 2. 测试代理是否连通 (返回 HTTP/2 200 说明成功)
curl -I https://www.google.com

```

### 二、 VS Code 安装与启动

通过微软官方源在 Kali 中安装和运行代码编辑器：

```bash
# 1. 添加微软 GPG 密钥和软件源
curl -sSL https://packages.microsoft.com/keys/microsoft.asc | sudo gpg --dearmor -o /usr/share/keyrings/microsoft-archive-keyring.gpg
echo "deb [arch=amd64 signed-by=/usr/share/keyrings/microsoft-archive-keyring.gpg] https://packages.microsoft.com/repos/code stable main" | sudo tee /etc/apt/sources.list.d/vscode.list

# 2. 更新并安装 VS Code
sudo apt update && sudo apt install code -y

# 3. 修复依赖 (如果 dpkg 本地安装 deb 包报错时使用)
sudo apt install -f -y

# 4. 在当前目录打开 VS Code (如果你是 root 用户，必须加后面那串参数)
code . --no-sandbox --user-data-dir="~/.vscode-root"

```

### 三、 界面字体美化

解决 VS Code 在 Kali 下终端字体间距过大、显示丑陋的问题：

```bash
# 1. 安装程序员专属等宽字体 JetBrains Mono
sudo apt install fonts-jetbrains-mono -y

# 2. 刷新系统字体缓存
fc-cache -f -v

```

### 四、 数据库服务管理 (MariaDB)

解决后端报错 `ConnectionException: 连接被拒绝` 的必备命令：

```bash
# 1. 启动 MariaDB 数据库服务 (每次开机或报错连接拒绝时运行)
sudo systemctl start mariadb

# 2. 重启数据库服务
sudo systemctl restart mariadb

# 3. 设置数据库开机自启 (一劳永逸，推荐)
sudo systemctl enable mariadb

# 4. 命令行直接查询数据库内容 (快速验证数据是否删除/添加成功)
mysql -u demo_user -p123456 -D spring_vue_demo -e "SELECT * FROM users;"

```

### 五、 项目运行命令 (在 VS Code 内置终端运行)

日常写代码最频繁使用的两个启动命令：

```bash
# 1. 启动后端 (进入 spring-demo-backend 文件夹后执行)
./mvnw spring-boot:run

# 2. 启动前端 (进入 frontend 文件夹后执行)
npm run dev
--------------------
# Username (用户名)：把默认的 root 删掉，改为我们之前创建的专属账号：demo_user

Password (密码)：填写密码：123456

Database (数据库名)：填写我们建好的库名：spring_vue_demo
```

---

```bash


```
```bash
cd ~/Downloads/spring-demo-backend
./mvnw spring-boot:run 


cd ~/Downloads/frontend           
npm run dev 
```
# 注意
```bash
cd ~/Downloads/spring-demo-backend
./mvnw spring-boot:run