# Red
# 项目简介

  该项目由dom4j完成 是学习mvp + rxjava2.0 + retrofit2.0 + dagger2 + fresco + material design的产物<br>
  处于学习的目的制作了这款Material design风格的纯阅读App,基本涵盖了目前开发的所有主流框架<br>
# 特别感谢
 [知乎日报API分析](https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90)  
# 技术要点
* 整体框架使用MVP搭建 对应presenter ui moudel 三个包<br>
* 使用RxJava及Retrofit2做网络请求<br>
* 使用RxPresenter对订阅的生命周期做管理<br>
* 使用OkHttp3拦截器对网络数据进行了缓存,以及日志,超时重连等配置<br>
* 使用Material design控件<br>
* 使用Dagger2实现依赖注入 将M层注入P层,P层注入V层 无需new<br>
* 使用Fresco做图片的缓存加载处理<br>
* 使用RecyclerView实现上啦加载下拉刷新<br>
* 使用WebView做详情的加载
 
  
