# RouteVulScan
Burpsuite - Route Vulnerable scanning  递归式被动检测脆弱路径的burp插件

***

## 合作者

[@deep0](https://github.com/deep0)

## 介绍

RouteVulScan是使用java语言基于burpsuite api开发的可以递归检测脆弱路径的burp插件。

插件可以通过被动扫描的方式，递归对每一层路径进行路径探测，并通过设定好的正则表达式匹配相应包的关键字，展示在VulDisplay界面。可以自定义相关路径、匹配信息、与漏洞名称等。

<img src="./img/config.jpg">

访问 http://10.44.3.234/FD2aD90Mcg/login.html 探测的路径如下，可以看到RouteVulScan会对根路径，及第二层路径 /FD2aD90Mcg 探测，同理，如果有三层四层路径，都会进行探测。

探测过的url会打印在Output，如果是重复的url则不会请求，并打印在Errors。如果访问的url符合Config的规则，则会储存在VulDisplay面板进行展示。

<img src="./img/out.jpg">

<img src="./img/VulDisplay.jpg">



## 使用

* 装载插件：``` Extender - Extensions - Add - Select File - Next ```

* 初次装载插件会在burpsuite当前目录下生成Config_yaml.yaml配置文件，点击Update按钮更新最新规则。需要注意的是，如果你有自己添加的规则，最好先备份，因为在线更新会直接覆盖规则文件。

  <img src="./img/update.jpg">

* 使用Burpsuite IScannerCheck接口，在流量初次流经burp时进行扫描，重复流量不会进行扫描。

* 使用线程池增加扫描速度，默认线程10，可自行调节。

* Extend Switch按钮，插件主开关，默认开启

* Carry Head 按钮，开启后被动扫描会携带原始的请求头

* Filter_Host 输入框，可以只扫描指定host的url，*代表全部，如 *.baidu.com

* VulDisplay界面右键可删除选中的行，或全部删除

  <img src="./img/remove.jpg">

* 右键请求可选择将当前请求发送到插件进行主动扫描，插件会将站点地图中，与当前请求使用一样host的历史路径全部进行扫描

  <img src="./img/Active_scan.jpg">

  

  



## 更新计划

* 右键选择请求发送到插件扫描【✓】
* 域名过滤【✓】
* UI界面增加数据包大小【✓】 
* VulDisplay界面添加删除功能【✓】
* 插件功能开关【✓】
* 带原始请求头访问【✓】
* 可自定义post/get请求【✓】
* 配置文件在线更新【✓】
* 添加分类，提供可根据个人习惯对规则进行分类处理
* 添加选择，每个规则设置为可选的形式，可自由选择想要的规则
* vuldisplay面板中漏洞url添加单行或多行复制，字段添加排序功能



## 最后

***

### 如有正则、BUG、需求等欢迎提Issues

​	

