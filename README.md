# Red
# 项目简介

 J.Red 一款纯阅读类的App 基于MVP + Retrofit + RxJava + Dagger2 <br>
 出于学习的目的制作了这款阅读类的App,借鉴了很多大神的作品,非常感谢大神们的开源精神<br>
 项目使用了当前Android最流行的MVP框架进行开发,技术点几乎涉及到了目前Android所有的主流开发技术<br>
  
  * 本项目还在测试阶段，发现bug或有好的建议欢迎issue、email(dom4j1464529456@163.com)<br>
  * IDE提示缺少Dagger开头的Class直接编译即可，会由Dagger2自动生成<br>
  * 本项目仅做学习交流使用，API数据内容所有权归原作公司所有，请勿用于其他用途<br>
# 特别感谢
   [知乎日报API分析](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)
   汇集知乎上的热门话题与新鲜事，板块众多<br>  
   [GeekNews](https://github.com/codeestX/GeekNews) 大神开源在Github上的作品,项目UI与框架借鉴此项目,让我受益匪浅,非常感谢大神的开源精神<br>
   [代码家/干货集中营](http://gank.io/api)  国内第三方技术分享平台,内有很多干货<br>
 
# 技术要点
* 整体框架使用MVP搭建 <br>
* 使用RxJava及Retrofit2做网络请求<br>
* 使用RxPresenter对订阅的生命周期做管理<br>
* 使用OkHttp3拦截器对网络数据进行了缓存,以及超时重连配置等<br>
* 使用Material design风格设计<br>
* 使用Dagger2做依赖注入 将M层注入P层,P层注入V层 无需new<br>
* 使用Fresco+Glide做图片的缓存加载处理<br>
* 使用X5WebView替换原生webView,做数据的展示<br>
* 使用RecyclerView做列表数据展示<br>
* 使用沉浸式布局做阅览,页面更美观
* 使用通用Adapter做RecyclerView适配器,告别创建adapter的痛苦<br>
* 详情请下载源码阅读<br>

# 项目展示

![image](https://github.com/dom4j1/Red/blob/master/showImage/gif1.gif)![image](https://github.com/dom4j1/Red/blob/master/showImage/gif2.gif) 
![image](https://github.com/dom4j1/Red/blob/master/showImage/gif3.gif)![image](https://github.com/dom4j1/Red/blob/master/showImage/1.jpg)
![image](https://github.com/dom4j1/Red/blob/master/showImage/2.jpg)![image](https://github.com/dom4j1/Red/blob/master/showImage/3.jpg)
![image](https://github.com/dom4j1/Red/blob/master/showImage/4.jpg)![image](https://github.com/dom4j1/Red/blob/master/showImage/5.jpg)

# 项目用到的第三方库 
* [rxjava](https://github.com/ReactiveX/RxJava)
* [rxandroid](https://github.com/ReactiveX/RxAndroid)
* [retrofit](https://github.com/square/retrofit)
* [okhttp](https://github.com/square/okhttp)
* [glide](https://github.com/bumptech/glide)
* [fresco](https://github.com/facebook/fresco)
* [dagger2](https://github.com/google/dagger)
* [butterknife](https://github.com/JakeWharton/butterknife)
* [fragmentation](https://github.com/YoKeyword/Fragmentation)
* [PhotoView](https://github.com/chrisbanes/PhotoView)<br>

# [Download](https://fir.im/Red)
 * 最后感谢GitHub上拥有开源精神的大神们,因为有你们才能让我们这些晚辈看的更远
 
 
  
