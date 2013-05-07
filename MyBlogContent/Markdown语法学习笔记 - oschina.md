#重拾Python 笔记三
######20130505
***
本文内容参考了[Markdown 语法说明 (简体中文版)][mk]，使用[MarkPad][]和[MarkdownPad 2][]编写完成。
#一、
Markdown兼容HTML语法，所以直接输入的HTML语句，会按HTML代码解析

#二、
Markdown的标题方法一：

    一级标题
    ======
    二级标题
    ---------

####    效果：
>一级标题
>======
>二级标题
>---------

#三、
Markdown的标题方法二：

    #一级标题
    ##二级标题
    ######直到六级标题

####    效果：
>#一级标题
>##二级标题
>######直到六级标题

#四、
区块引用，使用>符号，而且能嵌套，并且其中还能再使用其他的语法

    >#会缩进
    >>还能嵌套，比上一级缩进更多
    >>#再来一条，还能使用其他语法

####    效果：
>会缩进
>>还能嵌套，比上一级缩进更多
>>#再来一条，还能使用其他语法

#五、
无序列表，使用*、+、-即可

    +列表内容1

    +列表内容2

    -列表3

    -列表4

####    效果：
+列表内容1

+列表内容2

-列表3

-列表4

#六、
有序列表，数字+英文点

    1.有序列表1

    2.有序列表2

    3.有序列表3

####    效果：
1.有序列表1

2.有序列表2

3.有序列表3

#七、
代码区块每一行前面都要加四个空格或者一个tab位

    
        #this is code
        print "Hello, World!"

####    效果：
    #this is code
    print "Hello, World!"

#八、
分割线，这一行要有三个以上的*、-、_ 而且没有除了空格之外的其他符号

    *           *    *
    ---

####    效果：
*           *    *
---

#九、
行内链接，可使用相对路径和绝对路径，title属性，可以使用单、双引号和括号

    [六楼实验室](http://sixlab.duapp.com/ ‘title属性可省略’)
####    效果：
[六楼实验室](http://sixlab.duapp.com/ "title属性可省略")

#十、
参考链接，标记的冒号后边至少有一个空格或制表符，title属性可以另起一行，可以加缩进

    [六楼实验室][mark]
    [百度][baidu]
    [Google][]
    [mark]: http://sixlab.duapp.com/
         (title可省略)
    [baidu]: http://www.baidu.com/
    [Google]: http://www.google.com/

####    效果：
[六楼实验室][mark]
[百度][baidu]
[Google][]
[mark]: http://sixlab.duapp.com/
     (title可省略)
[baidu]: http://www.baidu.com/
[Google]: http://www.google.com/

#十一、
强调，使用*号或者_包围要强调的内容，就是让所包围文字粗体显示

    *斜体*
    __粗体__

####    效果：
*强调*
__再强调__

#十二、
行内代码段，使用反引号`包围,就是ESC键下边的那个键

    this is how the 'printf()' work。

####    效果：
this is how the `printf()` work。

#十三、
行内图片，支持绝对链接和相对链接，类似行内链接

	我的头像![头像](http://static.oschina.net/uploads/user/520/1040002_50.jpg "title可省略")

####    效果：
我的头像![头像](http://static.oschina.net/uploads/user/520/1040002_50.jpg "title可省略")

#十四、
参考图片，类似参考链接

    我的头像![我的头像][pic]
    [pic]: http://static.oschina.net/uploads/user/520/1040002_50.jpg "title可省略"

####    效果：
我的头像![我的头像][pic]
[pic]: http://static.oschina.net/uploads/user/520/1040002_50.jpg "title可省略"

#十五、
强大的反斜杠，和编程时候字符串里的转义一个意思啦，就是想输出**[]之类的符号，而不想让它被识别为markdown语法符号的时候，在这些符号前加个\

    \###这不是\*标题\*,但*这个*会斜体

    \[六楼实验室][http://sixlab.duapp.com/]

####    效果：
\###这不是\*标题\*,但*这个*会斜体

\[六楼实验室][http://sixlab.duapp.com/]

#OK
>基本上主要的内容就是这些了，比较好记，当然想用的高级也不是那么容易啦，我也是个初学者，如空行、空格等内容还不是很明白，文中内容可能也会有些地方不太准确，望指正。

>这篇文章就是先使用[MarkPad][]写了一半，然后又使用更流畅易用的[MarkdownPad 2][]编写完成的。编写过程参考了[Markdown 语法说明 (简体中文版)][mk]

[MarkPad]: http://code52.org/DownmarkerWPF/
[MarkdownPad 2]: [http://markdownpad.com]
[mk]: http://wowubuntu.com/markdown/#editor