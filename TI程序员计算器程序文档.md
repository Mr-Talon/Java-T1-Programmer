# TI程序员计算器程序文档

## 一、**软件系统分析和设计方案**

### 1、需求分析

#### 1）问题重述

**基本要求**：设计德州仪器程序员专用计算器（1977 年-1982 年发布）。按照TI程序员计算器（1982 年LCD版本）的原始面板键盘和显示布局设计出计算器的交互窗口，参照计算器的功能介绍，基本要求只需要实现10 进制和16 进制的基础算术运算。即不含括号的单步加减乘除运算。
**提高要求**：参照计算器的功能介绍，实现输入的 10 进制数和16 进制数之间的相互转换；实现与、或、异或、反码、补码和移位运算；实现单一进制模式下的带括号的组合多步运算；实现混合进制模式下带括号的组合多步运算；设计运算溢出等出错提示。

<img src="pic/QQ截图20220529155216.png" style="zoom:67%;" />

#### 2）需求分析综述

**使用java语言，运用java.swing界面设计技术，设计具有图形交互界面的，混合进制算数逻辑运算计算器的桌面应用程序。**

------

**界面需求分析**：按照TI程序员计算器说明书给出的参考图片，复刻计算器界面。

**交互需求分析**：通过用户**鼠标点击**图形交互界面按钮，输入运算的操作数和操作符。

**功能需求分析**：通过问题综述，可以看出基本要求中的功能要求是提高要求问题的子集，总体上可以用**混合进制算数逻辑运算**高度概括，具体需求如下：

1. 计算器具有8个数位的输入与输出
2. 16进制采用有符号数补码表示
3. 10进制以及16进制下算数运算（加减乘除）
4. 16进制下逻辑运算（与、或、异或、移位、求反码、求补码）
5. 10进制小数运算
6. 10进制16进制进制转换
7. 使用括号对运算顺序进行改变

**错误信号分析：**错误信号大体可以分为**溢出**和**语法错误**

| 序号 | 错误类型    | 错误信号变量名称   | 错误信号解释                   | 错误信号常量值 |
| ---- | ----------- | ------------------ | ------------------------------ | -------------- |
| 1    | OF/溢出     | INPUT_OVERFLOW     | 输入溢出                       | 5              |
| 2    | OF/溢出     | OUTPUT_OVERFLOW    | 输出溢出                       | 6              |
| 3    | GE/语法错误 | HEX_INPUT_WHEN_DEX | 在10进制情况下 输入a-f         | 3              |
| 4    | GE/语法错误 | SC1_WHEN_DEC       | 在10进制的时候使用反码按钮     | 8              |
| 5    | GE/语法错误 | SC2_WHEN_DEC       | 在10进制的时候使用补码按钮     | 9              |
| 6    | GE/语法错误 | AND_WHEN_DEC       | 在10进制的时候使用与运算按钮   | 12             |
| 7    | GE/语法错误 | OR_WHEN_DEC        | 在10进制的时候使用或运算按钮   | 13             |
| 8    | GE/语法错误 | XOR_WHEN_DEC       | 在10进制的时候使用异或运算按钮 | 14             |
| 9    | GE/语法错误 | SHE_WHEN_DEC       | 在10进制的时候使用移位按钮     | 15             |
| 10   | GE/语法错误 | INPUT_DOT_WHEN_HEX | 在16进制输入小数点             | 4              |
| 11   | GE/语法错误 | NEGATIVE_WHEN_HEX  | 在16进制状态下使用减号充当符号 | 11             |
| 12   | GE/语法错误 | MISS_PARENTHESES   | 缺少括号                       | 7              |
| 13   | GE/语法错误 | PARENTHESES_ERROR  | 括号使用的语法错误             | 10             |
| 14   | GE/语法错误 | EQUAL_ERROR        | 等号触发的多种错误             | 16             |
| 15   | GE/语法错误 | DIVIDE_ZERO        | 除以0                          | 17             |



### 2、功能及指标分析

对于上述需求分析，可以得到具体的功能及指标分析：

#### **1）界面及交互**

界面根据大作业要求必须使用**java.swing图形界面开发工具**进行编写，由于功能和实际TI程序员计算器相比略有删减，所以交互界面会有略微变化，但风格尽可能和实物保持一致。由于真实计算器是通过用户手动按下按钮进行输入，计算器通过液晶显示屏输出计算结果、状态信号以及错误信号，所以本软件只支持用户使用**鼠标点击**触发相关的**输入操作**。输出方面，按照真实计算器文档指标，可以输出8位数码。

#### **2）计算器核心功能**

1. 由于输入输出的限制，计算器在**10进制**输入输出状态下支持$-9999999 - 99999999$的范围，**16进制**按照文档要求，可以输出等效二进制下**32位有符号数补码形式**，即正数$0 - 7FFFFFFF(0 - 2147483647)$；

   负数$80000000 - FFFFFFFF(-2147483648  - -1)$。输入输出都有可能造成溢出，具体见**错误信号分析**部分。十进制可以使用负号（-）表示负数，十六进制使用符号位表示正负（以下不再区分正数负数）。

2. 10进制支持**整数、小数**的**加减乘除**运算，可以使用**括号**调整计算顺序。

3. 16进制只支持**整数**的**加减乘除**算术运算，以及逻辑运算**与、或、异或、移位、求反码、求补码**运算，可以使用**括号**对算数运算和逻辑运算（除求反码、求补码）的计算顺序进行调整。

4. 计算器支持10进制和16进制之间的**进制转换**，一个计算表达式可以是**混合进制输入**（混合进制包括了单一进制）

| 序号 | 符号 | 说明     |
| ---- | ---- | -------- |
| 1    | +    | 算数加   |
| 2    | -    | 算数减   |
| 3    | *    | 算数乘   |
| 4    | /    | 算数除   |
| 5    | &    | 逻辑与   |
| 6    | \|   | 逻辑或   |
| 7    | ^    | 逻辑异或 |
| 8    | <    | 逻辑移位 |
| 9    | =    | 等号     |
| 10   | 1'SC | 求反码   |
| 11   | 2'SC | 求补码   |

#### **3）错误信号**

由于输入输出限制，以及算数逻辑表达式的相关语法。

计算器可能会因为用户的非法输入产生**溢出类型错误**以及**语法错误**，如：输入溢出、10进制状态下使用十六进制补充操作数……

由于表达式的计算还可能产生如：输出溢出、除以0……等溢出类型错误和语法错误。

当错误信号**触发**，应该有相关信号展示，并且禁止用户的后续输入，只有当错误信号**消除**，才可以继续输入。

<img src="pic/QQ截图20220529165526.png" style="zoom: 50%;" />

### 3、系统设计方案

#### 1）软件架构

项目代码主要分为交互和事件处理部分以及三个核心算法。主类创建在交互和事件处理类T1_Programmer中，中间需要使用进制转换、计算、取反码补码的算法只需要调用相关算法类的**静态方法**，或者是在T1_Programmer类的事件处理方法中创建算法类的**匿名内部类**调用相关方法实现对话。能够实现这一操作得益于java的**包管理机制**，由于所有类都创建在项目包内，所以可以不受限制的相互调用。这样做能够将事件处理、算法编写分离，便于分工协作，每个人只需要知道自己会从上游获得什么，需要输出什么，不必要对小组其他成员代码的细节有详细了解，实现每个人工作的松耦合。

<img src="pic/QQ%E6%88%AA%E5%9B%BE20220601004002.png" style="zoom: 67%;" />

#### **2）界面设计方案**

java.swing界面设计方案可以通过直接编写代码完成，也可以使用集成开发环境中的java swing GUI设计工具包完成。对于课程学习，掌握swing类的继承关系，类的方法和属性，直接编写代码更加合适。对于工程项目，大量的交互组件和互相包含的容器组合，使用快速开发工具更加准确和方便。

对于本次项目，总共有35个按钮，4个信号输出位置，以及一个表达式输出窗口。组件繁多，容器互相嵌套，布局要求非常工整。所以选择了IDEA集成开发环境内置的**swing UI设计器**中的GUI窗体设计功能。

该功能类似Android开发中编写布局XML文件，只不过这里省去了对XML代码的直接编写，使用拖拽的方式进行布局。GUI窗体设计工具产生的.form文件是**XML资源文件**，里面使用了XML标签对象的格式对所有窗体、组件的布局等属性进行了标记。编写程序主类的时候会关联解析布局资源文件，按照布局的要求显示图形交互界面。

<img src="pic/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-30%20001235.png" style="zoom: 80%;" />

上图为GUI窗体工具包中的最终设计面板，下图为主要的容器嵌套树形关系

<img src="pic/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-30%20001409.png" style="zoom:67%;" />

在窗体中首先新建一个根容器root，分析可知，整个计算器可以分成三个大模块，从上到下依次为：**显示模块、信号显示模块、用户输入模块**。对于root根容器，使用GridLayoutManager对其进行布局。**GridLayoutManager**是IDEA集成开发环境推荐的布局管理方式，对于多行多列的表格形图形交互界面有很好的效果。

**显示模块**，整个模块使用一个JPanel封装，设置背景颜色background为白色。使用的组件是JLabel，用字符串的形式显示用户的输入和计算器的输出，可以在JLabel属性中设置horizontal size policy水平大小许可、font字体格式、foreground前景颜色（字体颜色）、以及JLabel类的类名。这里采用黑色作为字体颜色，水平大小可收缩、可增大。同时使用了一个水平分隔，放置在JLabel左侧，可以让不同位数的字符串始终出现在显示模块右侧。

![](pic/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-30%20003333.png)

**信号显示模块**，和显示模块类似，总共有4个JLabel，分别对应了10进制状态DEC、16进制状态HEX、溢出错误OF以及语法错误GE。由于需要在一行显示，这里整个信号显示模块的JPanel使用的是**FLowLayout**布局管理，设置属性horizontal align水平对齐方式为左侧对齐。对于四个信号，默认初始显示DEC，其他三个变量使用空格填充。如果使用setVisible属性设置，会导致全部左对齐，和真实硬件场景不符。

![](pic/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-30%20003455.png)

**用户输入模块**，对比原产品照片和当前软件需求可以知道，输入模块的按钮可以按照一个7*5的表格排列，但是中间的15位输入部分，背景颜色较为特殊，并且按钮上方有16进制对应的2进制编码，方便用户直接将16进制输出转换为2进制格式。

所以对于整个表格容器，选择GridLayoutManager作为布局管理，建立7*5的表格。合并中间5 * 3的表格，并且在其中嵌套一个JPanel，此嵌套JPanel同样是用GridLayoutManager作为布局管理，建立5*3的表格。中间的15个表格单元，再嵌套一个JPanel，同样使用GridLayoutManager作为布局管理，设置上下两行，上面一行放置一个JLabel作为二进制提示文本，下面一行放置一个JButton按钮，作为用户输入的焦点。5 * 3的表格外，其他表格单元按照排版要求放置相应的JButton，同时在需要的地方增加垂直分隔，以满足排版的美观。

对于35个按钮，都在其属性栏设置相应的对象名，设置了对象名的组件，集成开发环境会在主类代码中创建对象的私有成员，方便后续的事件处理。同时给按钮设置了默认显示文字，对应了真实计算器上按键的铭文。按照排版需求设置相应容器和按钮的背景颜色和前景文字颜色。为了交互的美观，对所有的JButton，取消勾选了borderPainted、focusCycleRoot、focusPainted、focusable等属性，使得点击按钮没有焦点框，阴影效果已经描边，让整体交互更加优雅。

<img src="pic/%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE%202022-05-30%20005600.png" style="zoom:80%;" />



**最终成品图形交互界面截图如下：**

![](pic/QQ%E6%88%AA%E5%9B%BE20220530143720.png)

#### 3）**交互设计方案**

用户输入统一使用对按钮的**高级事件处理**，即点击按钮触发**输入**，用户输入会在显示模块显示，每次显示一个操作数或者是左右括号。每当用户输入一个操作符或者是左右括号，表示上一个操作数输入完毕。这样设计符合数学上算术逻辑表达式的语法，符合TI程序员计算器的说明文档，也符合当前市面上主流的计算器交互逻辑。同时我们增加了两个功能，**回退**按钮“<--”和**清空**按钮“CLC”，更加方便用户对当前输入进行修改。

当所有表达式输入完毕，用户点击**等号**，计算器在显示模块返回**输出**结果。

对于**取反码**和**取补码**这两个特殊的逻辑运算，按下后会立即对表达式进行重写，直接在显示模块反馈给用户。

当然，用户会由于操作失误导致错误输入，错误输入主要分为两大类型。一种是**溢出错误**，即用户输入了超过输入允许范围的操作数，或者是用户正确输入的计算结果超出了计算器正确的表示范围。另一种错误是**语法错误**，由于本计算器涉及到不同进制下的混合计算，很多操作符和操作数都有相应的使用范围，加上括号的引入以及传统的表达式语法错误。本计算器可能会收到相当多的用户错误输入，对于非致命错误，计算器会屏蔽对输入的响应，但是不会产生错误信号；对于致命的错误，有可能会使得计算器核心算法不能正确运行，计算器会给出错误信号，只有当用户消除了所有错误信号，才可以继续输入。

**这样将纷繁复杂是防止用户错误输入的工作交由事件处理解决，既符合了错误的产生和消除逻辑，又可以让算法设计在相对理想的环境下完成。**

#### **4）核心功能设计方案**

##### **混合进制计算器的输入方案**

由于计算器对用户输入操作数的个数没有限制，软件无法确定用户输入的个数，所以使用数组等方案记录操作数和操作符不是明智的选择。本软件所有的用户输入，在软件底层都会转化成字符串形式，为此定义了两个临时的字符串变量：

```java
    private String expression="";   //总表达式
    private String currentString="";   //当前需要展示在JLabel ans上字符串
```

currentString记录**当前的操作数**，expression记录**总表达式**。

根据需求分析，我们知道该计算器在一个表达式中的不同操作数可能来自不同的数制。例如我们可以输入这样的表达式：$123_{10}+2BC_{16}+123_{16}$，根据计算机组成原理以及数字电路对于数制的介绍，我们可以知道，不同的数制只是对于数的展示形式的不同，计算的结果都是等效的。所以所有16进制模式下输入的16进制数，都会在软件底层通过调用**16进制转10进制**的方法将16进制对应的10进制操作数输入到总表达式中。这样计算器算法只需要处理10进制下的操作。（具体算法分析见**软件实现和代码编写**）

```java
currentString=new Translation(currentString).Decimal();
expression+=currentString;
```

我们知道，当表达式可以看做操作数和操作符的集合，除括号外，两个操作符之间是操作数的范围。可以根据这一语法特性设置用户输入方案。

1. 对于**1-9**，允许出现在10进制和16进制两个状态下。分析所有可能的前缀，**1）**前缀是左括号表示当前是左括号后第一个操作数，应该将左括号加入总表达式，清空当前表达式，并将当前字符输入。**2）**前缀是右括号，则产生了语法错误，但不是致命错误，计算器不会做出响应。**3）**前缀是操作符的情况请见操作符部分的分析。**4）**前缀是其他操作数的内容，则在当前表达式末尾追加当前字符。
2. 对于**A-F**，只允许出现在16进制状态下。其他情况和1-9完全一样。
3. 对于**0**，允许出现在10进制和16进制两个状态下。**1）**0作为当前操作数的开头字符，只有在输入小数的情况下可以出现，并且不可能出现多个0重复出现在开头的情况，这是语法错误，但是不是致命的，多次重复在操作数开头输入0计算器不会做出响应。**2）**其他情况和1-9完全一样。
4. 对于**小数点**，只允许出现在10进制模式下，分析所有可能的前缀，**1）**当前操作数为空的时候不可以用小数点作为操作数的第一个字符，这是严格意义上的数学表达式格式，也方便算法的设计。**2）**当前操作数中，已经出现了小数点，就不可以再次输入小数点，这是语法错误，但不致命，计算器不会做出响应。**3）**其他情况和1-9完全一样。
5. 对于**左括号**，允许出现在10进制和16进制模式下，分析所有可能前缀，**1)**前缀是左括号，是嵌套使用括号的情况，将前一个左括号加入总表达式，将当前表达式清空并加入新的左括号。**2）**前缀是右括号，是语法错误，会触发错误信号。**3）**前缀是操作数（包括小数点），也是语法错误，会触发错误信号。**4）**前缀是操作符见操作符部分的分析。
6. 对于**右括号**， 允许出现在10进制和16进制模式下，**1）**前缀是左括号，说明括号内为空，是语法错误，会触发错误信号。**2）**前缀是右括号，说明是嵌套使用括号的情况，将前一个右括号加入总表达式，将当前表达式清空并加入新的右括号。**3）**前缀是操作符，说明表达式缺少操作数，是语法错误，会触发错误信号。**4）**前缀是操作数，这里需要区分两种情况，一种是操作数是10进制输入模式，直接将操作数加入总表达式，清空当前表达式，将右括号加入当前表达式。另一种是16进制输入模式，由于算法设计，这里需要先将16进制操作数转换成10进制操作数，其他后续操作和10进制一样。
7. 对于**10进制按钮**，允许在10进制和16进制模式下使用，**1）**10进制模式没有效果。**2）**16进制模式下，如果当前表达式不是括号，就需要调用16进制转10进制函数实现进制转换，将转换结果更新当前表达式。由于16进制转换过来的数可能是负数，对于出现负数的情况，会在后台加入括号，防止计算算法出现错误。
8. 对于**16进制按钮**，允许在10进制和16进制模式下使用，所有的逻辑和10进制按钮对称。
9. 对于**回退按钮**，允许在10进制和16进制模式下使用，作用是删除当前表达式末尾的一个字符，同时还会对错误信号产生影响，具体见错误触发及消除设计方案。
10. 对于**清空按钮**，允许在10进制和16进制模式下使用，作用是删除当前表达式和总表达式，同时还会对错误信号产生影响，具体见错误触发及消除设计方案。
11. 对于**加、乘、除**，允许在10进制和16进制模式下使用，分析所有可能的前缀，**1）**当总表达式为空，操作符除了负号不应该出现在总表达式的最前端，是语法错误，但是不是致命错误，计算器不会给出响应。**2）**前缀是左括号，是语法错误，触发错误信号。**3）**前缀是右括号，将右括号加入总表达式，清空当前表达式，并且将当前符号加入总表达式。**4）**前缀是操作数，和右括号处理前缀是操作数的逻辑相同。**5）**前缀是操作符，语法错误，但是不是致命错误，计算器不会做出响应。
12. 对于**与、或、异或、移位**，允许在16进制模式下使用，逻辑和加、乘、除相同。
13. 对于**减号**，允许在10进制和16进制模式下使用。**1）**10进制模式下，如果出现在当前表达式开头或者是左括号之后，可以当做负号使用。将符号输入在当前表达式中。**2）**16进制模式下，由于16进制使用有符号数补码表示，最高位是符号位，所以不允许在当前表达式开头或者是左括号之后出现符号，这是语法错误，会触发错误信号。**3）**充当减号使用的时候逻辑和其他操作符一致。
14. 对于**求反码和补码**，允许在16进制模式下使用，可以将当前16进制码转换成对应的反码或者补码的形式，并且展现在显示模块。
15. 对于**等号**，是表达式输入完毕的标志，首先会对表达式中间的可能出现的错误进行判别，并且返回相应的错误信号。（具体见错误触发及消除设计方案）**1）**当前输入状态是10进制，首先会把当前表达式加入总表达式，调用计算方法，得到结果，判断是否溢出，如果溢出返回溢出信号。由于存在除法，会产生无限小数，这种溢出只是长度上的溢出，并不是大小上的溢出，所以还要对小数进行四舍五入。**2）**当前输入状态是16进制，首先是和其他操作符一样，对输入进行进制转换，得到最终的总表达式。对于除法，可能会出现小数结果，但是16进制模式下不可以出现小数点，此时计算器会自动转换到10进制模式，其他小数的处理逻辑和10进制一致。
16. 对于**关闭按钮**，和图形界面的关闭按钮功能一致。

##### **计算器计算算法**



##### **进制转换方案**

进制转换需要完成十进制与十六进制的互相转换，为此实现类Translation,并定义方法Decimal()实现十六进制转十进制，方法Hexadecimal()实现十进制转十六进制。由于在软件设计中，所有的用户输入都将被保存为字符串形式，因此声明String类型成员s存放上游传来字符串。此外，在进制转换过程中不考虑用户的错误输入及计算溢出，所有可能导致输出错误的在上游和返回结果后判断。此外，十六进制在该计算器中是以补码的形式存在，为了处理负数的互相转换，创建新类BintoHex，该类实现了十六进制字符串与二进制字节数组的互相转换。

**十六进制转换十进制**，由于补码的特性，十六进制字符串正数负数转换的方法不一致，需要对字符串进行判断。

1.**字符串长度小于8**，调用Long.parseLong方法直接获得十六进制代表的十进制正数，并返回十进制字符串

2**.字符串长度等于8且首字符大于’7‘**，调用调用BintoHEX方法获得二进制字符数组方法，再调用BigInteger直接获得二进制字符串代表的十进制负数，并返回十进制字符串。

3.**字符串长度等于8且首字符小于’7’**，调用Long.parseLong方法直接获得十六进制代表的十进制正数，并返回十进制字符串。

**十进制转换十六进制**，首先调用BigInteger方法获取十进制对应二进制数组，然后用二进制数组调用静态方法bin2HexStr获取对应十六进制字符串并返回。在测试结果的过程中，发现了一些错误，并给出相应的处理办法。

1.**无法返回十进制1-16对应的十六进制**，单独判断这种情况，直接调用Integer.toHexString方法返回对应字符串

2.**十六进制负数字符串存在缺省F**，判断返回字符串长度及二进制字节数组首字符，当十六进制为负数时，补齐F并返回修改后的字符串。

3.**返回字符串存在前缀'0'不缺省**，判断返回字符串并将再次循环判断去零字符串，直至字符串不含前缀'0'. 

##### **反码补码方案**



#### **5）错误触发及消除设计方案**

错误都是通过用户的输入或者是计算造成的，而且计算器的错误繁多，因此将错误的处理机制写在每一个按钮的事件处理里面，可以很好的进行分析，做到尽可能没有遗漏。

对于非致命的错误，计算器不会做出响应，这一部分已经在**核心功能设计方案-混合进制计算器的输入方案**中陈述。以下只讨论会触发错误信号的错误的触发和消除。

**触发**：

1. **0-9**，当输入的位数超过了8位，就会引起输入溢出错误INPUT_OVERFLOW。
2. **A-F**，输入状态是10进制，会引起语法错误HEX_INPUT_WHEN_DEX，同时和0-9一样也会引输入溢出错误INPUT_OVERFLOW。
3. **小数点**，16进制下不可以输入小数，会引起语法错误INPUT_DOT_WHEN_HEX，溢出同上。
4. **左括号**，左括号前面是右括号或者是操作数会引起语法错误PARENTHESES_ERROR。
5. **右括号**，右括号前面是左括号或者是其他操作符会引起语法错误PARENTHESES_ERROR。
6. **十进制按钮**，16进制转10进制可能会溢出，触发输出溢出错误OUTPUT_OVERFLOW。
7. **十六进制按钮**，十进制小数无法转换成16进制，会产生语法错误INPUT_DOT_WHEN_HEX。
8. **加号乘号除号**，加号乘号除号前面是左括号会产生语法错误PARENTHESES_ERROR。
9. **减号**，减号在16进制状态充当负号使用会触发语法错误NEGATIVE_WHEN_HEX。
10. **与或异或移位**，除了和加乘除一样不可以在左括号后使用，还不可以在10进制模式下使用，会产生AND_WHEN_DEC、OR_WHEN_DEC、XOR_WHEN_DEC、SHE_WHEN_DEC。
11. **取反码补码**，在10进制使用，会产生语法错误SC1_WHEN_DEC、SC1_WHEN_DEC、SC2_WHEN_DEC.
12. **等号**，总表达式左右括号数量不一致，产生语法错误MISS_PARENTHESES；表达式最后是左括号，产生语法错误PARENTHESES_ERROR；表达式最后是操作符，产生语法错误EQUAL_ERROR；收到算法抛出的除以0的异常，产生语法错误DIVIDE_ZERO；输出结果溢出，产生溢出错误OUTPUT_OVERFLOW。

**消除**

1. **十进制按钮**，16进制输入小数点产生的错误回到10进制应该消除。
2. **十六进制按钮**，10进制输入16进制操作数、10进制使用取反码、补码按钮产生的错误转换到16进制应该被消除。
3. **回退按钮**，通过回退按钮使得当前输入文本没有溢出就将溢出信号去除；16进制模式下输入了小数点或者是10进制小数转换成16进制产生的错误，如果表达式中小数点已经消除，该错误信号应该消除；10进制情况下输入了ABCDEF可以通过删除ABCDEF去除语法错误；此外通过回退按钮使得当前输入文本清空直接清除所有错误。
4. **清空按钮**，所有溢出错误、语法错误都会消除。

![](pic/Collage_20220601_001848.jpg)

上图从左到右分别展示了，溢出错误，语法错误，用回退按钮消除错误以及用清空按钮消除错误。



## 二、**软件实现和代码编写**

### 1、用户输入模块与事件处理

用户输入模块的逻辑简单，只是种类繁多，对于不同的按钮有不同的处理方法，下面列举具有代表性的按钮，在注释的帮助下不难理解代码的含义。

#### 1）操作数输入（以0和A为例）

```java
/*操作数0*/
a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {    //按钮0需要防止过多的0输入
                //表达式开头只有在小数点输入的时候可以是0，而且这个时候0一定是字符串的首个元素  如果第一个元素输入了0，第二个元素输入小数点
                //就不会进入return的分支，因为此时字符串长度已经等于2
                //只有在开头连续输入0 才是不被允许的 这里不返回错误信号  指示不允许用户这么输入
                if (error==0){                             //有错误无法输入
                    if (currentString.length()<8){         //没有溢出 正常进行
                        if(currentString.length()==1&&currentString.charAt(0) == '0'){
                            return;                        //当前字符串长度是1 并且输入的操作数是0  就造成了重复输入0
                        }
                        if(currentString.contains(")")){   //右括号后不可以是操作数
                            return;
                        }
                        if (currentString.contains("(")){  //由于括号也会显示  所有的操作数会作为括号的结束条件  将括号加入总表达式
                            expression+=currentString;
                            currentString="";
                        }
                        String text="0";
                        currentString+=text;
                        ans.setText(currentString);
                    }
                    else {
                        error=INPUT_OVERFLOW;              //内部错误信号
                        overflowError.setText("OF");       //计算器界面显示溢出错误
                    }
                }
            }
        });

/*操作数A 只展示部分代码*/
if (state==DEC){                    //对于a-f的字符 只能在16进制输入状态下输入
    error=HEX_INPUT_WHEN_DEX;
    grammarError.setText("GE");
}
else {                              //正确输入情况
    if (currentString.contains("(")){
        expression+=currentString;
        currentString="";
    }
    String text=aButton.getText();
    currentString+=text;
    ans.setText(currentString);
}
                    
```

#### 2）操作符输入（以减号为例）

```java
MinusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //减号前面只可能是左括号和右括号或者是数字  10进制情况下可以用减号表示负数
                if (error==0){
                    if (state==DEC){
                        if (expression.length()==0&&currentString==""){
                            //减号用作负号 要么是表达式的第一项 要么就是左括号之后
                            String text="-";
                            currentString=text;         //用作负号直接加入当前表达式
                            ans.setText(currentString);
                        }
                        //在左括号之后
                        else if (currentString.contains("(")){
                            expression+=currentString;
                            currentString="-";
                            ans.setText(currentString);
                        }
                        //用作减号的情况
                        else {
                            expression+=currentString;   //操作符需要将之前一个操作数放入总表达式
                            currentString="";
                            ans.setText(currentString);
                        }

                    }
                    else {
                        //16进制情况下 不允许输入减号作为负号
                        if ((expression.length()==0&&currentString=="")||currentString.contains("(")){
                            error=NEGATIVE_WHEN_HEX;
                            grammarError.setText("GE");
                            return;
                        }
                        if (!currentString.contains(")")){
                            currentString=new Translation(currentString).Decimal();  
                            //16进制将当前表达式加入总表达式需要先转换成10进制
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                        else{
                            //右括号直接加入总表达式
                            expression+=currentString;
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    if (!Objects.equals(expression, "")){   //由于减号可以作为负号出现在总表达式的第一个字符 所以增加了这个判断
                        String lastCharE=expression.substring(expression.length()-1);
                        if (notContainSymbol(lastCharE)&&!currentString.contains("-")){
                            expression+="-";        //最后jia
                        }
                    }
                }
            }
        });
```

#### 3）括号输入（以右括号为例）

```java
RightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //右括号前面只可能是 右括号或者是操作数
                if (error==0){
                    if (!notContainSymbol(currentString.substring(1))||currentString.contains("(")){ 
                        error=PARENTHESES_ERROR;                //右括号前面是左括号或者是其他操作符  语法错误
                        grammarError.setText("GE");
                    }
                    if (!currentString.contains(")")){
                        if (state==DEC){
                            expression+=currentString;          //右括号和左括号不同 左括号可以理解为一个字符 右括号需要理解为一个操作符
                            currentString="";                   //输入有操作符 代表前一个操作数的结束  需要把操作数置入总表达式
                        }
                        else if (state==HEX){
                            String temp=new Translation(currentString).Decimal();
                            expression+=temp;                   //16机制下需要转换成10进制才能加入表达式
                            currentString="";
                            ans.setText(currentString);
                        }
                    }
                    else {                                       //前面的操作数是右括号  嵌套括号
                        expression+=currentString;
                        currentString="";
                    }
                    String text=")";
                    currentString+=text;
                    ans.setText(currentString);
                    numOfRightParentheses++;
                }
            }
        });
```

#### 4）等号

```java
EqualButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //等号前面可以是 右括号 操作数   不能是左括号 操作符
                if(error==0){
                    if (numOfRightParentheses!=numOfLeftParentheses){     //处理少括号的语法错误
                        error=MISS_PARENTHESES;
                        grammarError.setText("GE");
                        return;
                    }
                    if (currentString.contains("(")){     			      //括号前面是左括号运算没有结束 错误
                        error=PARENTHESES_ERROR;
                        grammarError.setText("GE");
                        return;
                    }
                    if (!notContainSymbol(expression.substring(expression.length()-1))&&currentString==""){  
                        //等号前面不可以是操作符
                        error=EQUAL_ERROR;
                        grammarError.setText("GE");
                        return;
                    }

                    //16进制走这条分支
                    if (state==HEX){
                        if (!currentString.contains(")")){                //前面可能是右括号 或者是操作数   此分支处理操作数的情况
                            currentString=new Translation(currentString).Decimal();
                            expression+=currentString;
                        }
                        else{                                             //此分支 前面是括号 不需要转换成10进制 直接加入总表达式
                            expression+=currentString;
                        }

                        System.out.println("cal前："+expression);
                        try{
                            currentString=Calculator.compute(expression); //计算结果返回一个字符串  10进制
                        }
                        catch (ArithmeticException exception){            //算法会抛出除以0的异常对象
                            error=DIVIDE_ZERO;
                            grammarError.setText("GE");
                            return;
                        }
                        System.out.println("cal后："+currentString);

                        //16进制后处理
                        BigDecimal Up = BigDecimal.valueOf(2147483647);    //16进制上界
                        BigDecimal Down = BigDecimal.valueOf(-2147483648); //16进制下界
                        BigDecimal tempAns1 =BigDecimal.valueOf(Double.parseDouble(currentString));
                        if (tempAns1.compareTo(Up) < 0 && tempAns1.compareTo(Down) > 0){//判断是否溢出
                            if(currentString.contains(".")){
                                //计算结果有小数  只有16进制除法会遇到这种情况  自动转换成10进制模式
                                state=DEC;
                                DECState.setText("DEC");
                                HEXState.setText("         ");
                                if (currentString.length()>9){             //对于无限小数（除法运算会产生）
                                    //以下是四舍五入算法
                                    int point=currentString.indexOf(".");  //获取表达式小数点位置
                                    int need_to_left=8-point-1;            //计算小数点后需要保留的位数
                                    tempAns1=BigDecimal.valueOf(Double.parseDouble(currentString.substring(0,8)));
                                    if (Integer.parseInt(currentString.substring(7,8)) >=5){   
                                        //根据返回字符串的第九位判断是否需要四舍五入
                                        String plus="0.";
                                        for(int i=0;i<need_to_left-1;i++){
                                            plus+="0";
                                        }
                                        plus+="1";                         //制造一个五入的小数
                                        Double plusNum=Double.parseDouble(plus);
                                        tempAns1=tempAns1.add(BigDecimal.valueOf(plusNum));
                                        ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                        expression="";
                                    }
                                    else {                                 //不需要四舍五入直接截取字符串
                                        ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                        expression="";                  
                                    }
                                }
                                else {
                                    ans.setText(tempAns1.stripTrailingZeros().toPlainString());
                                    expression="";
                                }
                            }
                            else {
                                currentString=new Translation(currentString).Hexadecimal();
                                //输出没有小数点直接将结果展示出来
                                ans.setText(currentString);
                                expression="";
                            }
                        }
                        else {                                     
                            error=OUTPUT_OVERFLOW;  //输出结果溢出
                            overflowError.setText("OE");
                        }
                    }
                    /*10进制和16进制逻辑一样  考虑到篇幅不进行展示*/
                }
            }
        });
```

### 2、错误信号触发与消除机制

#### 1）错误信号的触发

错误信号的触发是有输入的操作数以及计算产生的，已近在用户输入模块与事件处理的代码分析中详细讲解

#### 2）错误信号的消除

**回退按钮和清楚全部按钮**

```java
		/*回退按钮*/
		BACKSPACEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentString.length()>0){
                    currentString=currentString.substring(0,currentString.length()-1);

                    //通过回退按钮使得当前输入文本没有溢出  就将溢出信号去除
                    if (error==INPUT_OVERFLOW&&currentString.length()<8){
                        error=0;
                        overflowError.setText("         ");
                    }

                    //这个错误会在两种情况发生 一个是16进制模式下输入了小数点 一个是10进制小数转换成16进制
                    //如果通过回退按钮删除了小数点 这个错误信号应该消失
                    if (error==INPUT_DOT_WHEN_HEX&&!currentString.contains(".")){
                        error=0;
                        grammarError.setText("         ");
                    }

                    //如果10进制情况下输入了ABCDEF可以通过删除ABCDEF去除语法错误
                    if (error==HEX_INPUT_WHEN_DEX&&notContainABCDEF(currentString)){
                        error=0;
                        grammarError.setText("         ");
                    }

                    //通过回退按钮使得当前输入文本清空 直接清除所有错误
                    if (currentString.length()==0&&grammarError.getText()!=""){
                        error=0;
                        grammarError.setText("         ");
                    }

                    if(error==0){
                        ans.setText(currentString);
                    }
                }
            }
        });

        /*清空按钮*/
        CLCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentString="";
                ans.setText(currentString);
                expression="";

                numOfLeftParentheses=0;
                numOfRightParentheses=0;

                //清零按钮按下之后 所有错误信号和所有输入全部清空
                error=0;
                overflowError.setText("         ");
                grammarError.setText("         ");
            }
        });
```

**进制转换按钮（以转换成16进制为例）**

```java
HEXButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (error==0||error==HEX_INPUT_WHEN_DEX||error==SC1_WHEN_DEC||error==SC2_WHEN_DEC){
                    //16进制按钮可以消除10进制的语法错误
                    
					/*省略了进制转换内部的代码*/
                    
                    if (error==HEX_INPUT_WHEN_DEX||error==SC1_WHEN_DEC||error==SC2_WHEN_DEC){   
                        //如果已经触发了输入语法错误 通过转换状态应该消除错误信号
                        error=0;
                        grammarError.setText("         ");
                    }
                }
            }
        });
```

### 3、算法模块





## 三、**算法分析**

### 1、计算器计算算法分析

### 2、进制转换算法分析

### 3、反码、补码算法分析



## 四、**软件调试和测试**

### 1、软件测试

### 2、问题与解决方案



## 五、**参考文献和材料**

[1]GALLARDO R,HOMMEL S。Java语言导学（原书第6版）[M]。北京：机械工业出版社，2017.7

[2]耿祥义，张跃平。Java面向对象程序设计：微课视频版（第3版）[M]。北京：清华大学出版社，2020.1

[3]Oracle. Java Documentation[DB/OL]. [2022-6-2]. https://docs.oracle.com/en/java/

[4]白白旧维. idea的Java窗体可视化工具Swing UI Designer的简单使用（一）[DB/OL]. （2021-06-12）[2022-6-2].  https://blog.csdn.net/weixin_43444930/article/details/117855310?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165410106616781685350733%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165410106616781685350733&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-117855310-null-null.142

[5]丿乐灬学. java BigDecimal加减乘除[DB/OL].  (2018-07-10 )[2022-6-2]. https://blog.csdn.net/qq_37880968/article/details/80986545?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165410125116782246455950%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fall.%2522%257D&request_id=165410125116782246455950&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~first_rank_ecpm_v1~rank_v31_ecpm-4-80986545-null-null.142

[6]吴师兄学算法. 还不会使用 GitHub ？ GitHub 教程来了！万字图文详解[DB/OL].  （2021-10-13）[2022-6-2]. https://zhuanlan.zhihu.com/p/369486197



## 六、**团队成员姓名和联系方法**

**张宸冉**	邮箱：1604690196@qq.com	Github:Mr-Talon	

负责内容：图形交互界面设计，输入逻辑设计与异常输入拦截，项目统筹，文档图形交互界面、输入逻辑、错误信号部分

**梁涛**		邮箱：332604872@qq.com	  Github:3dfish2dweb

负责内容：计算器计算算法设计，文档计算器算法设计、分析、测试部分

**杨德宝**	邮箱：2495499315@qq.com	Github:Lyr3

负责内容：计算器进制转换算法设计，文档进制转换算法设计、分析、测试部分

**佘振东**	邮箱：2577484662@.qq.com	Github:fsbbts

负责内容：计算器反码补码算法设计，文档反码补码算法设计、分析、测试部分