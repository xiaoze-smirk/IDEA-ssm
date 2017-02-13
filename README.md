# 本项目是个人编写、基于SSM框架的课程项目

标签：SSM-DEMO

------
##个人简介
> * 小泽
> * 福建师范大学学生
> * 方向：软件编程

------
##SSM简介
现在市面上更加流行**SSM**框架，基于Spring自家的SpringMVC由于能够进行简单、高效的无缝耦合，使得它逐步代替市场上的Struts（还有另外一个原因是因为Struts爆出过安全这方面的问题）；而Mybatis不用说了，可以自控制Sql语句，因为项目数据大多数优化，或者错误都是发生在Sql的问题上，在这一方面，也是最关键的一方面,它比Hibernate不知道好几十倍,Hibernate虽然操作，但是太笨重了。附上一张SpringMVC操作流程

![SpringMVC-logo](http://wx4.sinaimg.cn/mw690/cf495cdcgy1fcoq54uzz4j20hs0aa74k.jpg)

------

## 项目介绍

本项目一开始进入登录界面，只有输入正确的密码，才能进入主界面；进入主界面后可以对课程，以及课程类型进行增删改查工作；所有的数据最终都是保存到Oracle里面。

### 1. 项目流程具体的Todo列表

- [ ] 登录账号、密码
- [ ] 课程类型的增删改查
- [ ] 课程的增删改查
- [x] 新增 druid 连接池
- [x] 新增 jQuery 实现
- [x] 新增 ajax 实现
- [x] 新增 mybatis拦截器
- [x] 新增 shiro 框架

### 2. Java某些代码展示

```java
@ModelAttribute
    public void getCourse(@RequestParam(value="courseNo",required=false) String courseNo,
                          Map<String, Object> map){
        if(courseNo != null&&courseService.loadCourseByNo(courseNo)!= null){
            map.put("course", courseService.loadCourseByNo(courseNo));
        }
    }
```

### 3. 项目流程图

st=>start: Start:>http://www.google.com[blank]
e=>end:>http://www.google.com
op1=>operation: My Operation
sub1=>subroutine: My Subroutine
cond=>condition: Yes
or No?:>http://www.google.com
io=>inputoutput: catch something...

st->op1->cond
cond(yes)->io->e
cond(no)->sub1(right)->op1

------

由于时间限制，以后会继续更改的！

小泽 [@xiaoze][3]     
2016 年 07月 07日    

[3]: http://weibo.com/3477691612/profile?topnav=1&wvr=6&is_all=1


